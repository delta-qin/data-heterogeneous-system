package com.deltaqin.sys.remote.controller;

import com.alibaba.fastjson.JSONObject;
import com.deltaqin.sys.common.constant.AuthConstant;
import com.deltaqin.sys.common.constant.CodeConstant;
import com.deltaqin.sys.common.entities.CommonResult;
import com.deltaqin.sys.common.utils.NumberUtils;
import com.deltaqin.sys.mapper.SysRemoteInfluxPanalMapper;
import com.deltaqin.sys.model.SysRemoteDatabasePanal;
import com.deltaqin.sys.model.SysRemoteDatabasePredict;
import com.deltaqin.sys.model.SysRemoteInfluxPanal;
import com.deltaqin.sys.remote.config.MyHttp;
import com.deltaqin.sys.remote.service.PredictService;
import com.deltaqin.sys.remote.service.RemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Api("预警相关操作")
public class PredictController {
    @Autowired
    private RemoteService remoteService;

    @Autowired
    private PredictService predictService;

    @Autowired
    private HttpServletRequest httpServletRequest;



    private static RestTemplate restTemplate = new RestTemplate();

//    private static String URL = "http://10.138.130.1:32567/predict";//?data={data}&times={times}&threshold={threshold}&columns={columns}";
    private static String URL = "http://localhost:5000/predict";//?data={data}&times={times}&threshold={threshold}&columns={columns}";

    @ApiOperation("新增预警")
    @PostMapping("/remoteStatus/addPredict")
    public CommonResult addPredict(@RequestBody SysRemoteDatabasePredict sysRemoteDatabasePredict) {
        //判断是否已经存在预警
        Long panalId = sysRemoteDatabasePredict.getPanalId();
        if(predictService.IsPredictExist(panalId)){
            return CommonResult.failed();
        }

        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        sysRemoteDatabasePredict.setCid(cid);

        predictService.addPredict(sysRemoteDatabasePredict);

        return CommonResult.success(null);
    }

    @ApiOperation("删除预警")
    @DeleteMapping("/remoteStatus/deletePredict")
    public CommonResult deletePredict(@NotNull Long id) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        SysRemoteDatabasePredict sysRemoteDatabasePredict = new SysRemoteDatabasePredict();
        sysRemoteDatabasePredict.setCid(cid);
        sysRemoteDatabasePredict.setId(id);
        if(predictService.deleteByID(sysRemoteDatabasePredict)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("修改预警")
    @PutMapping("/remoteStatus/updatePredict")
    public CommonResult updatePredict(@RequestBody SysRemoteDatabasePredict sysRemoteDatabasePredict) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        sysRemoteDatabasePredict.setCid(cid);

        if(predictService.uodatePredict(sysRemoteDatabasePredict)){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取全部预警")
    @GetMapping("/remoteStatus/getAllPredict")
    public CommonResult getAllPredict() {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id
        List<Map<String,Object>> result = predictService.getAllPredict(cid);
        Map<String,Object> results = new HashMap<String,Object>();
        results.put("total",result.size());
        results.put("rows",result);
        return CommonResult.success(results);
    }

    @ApiOperation("获取某监控面板的全部预警")
    @GetMapping("/remoteStatus/getAllPredictByPanalID")
    public CommonResult getAllPredictByPanalID(@RequestParam @NotNull Long panalId) {
        Long cid = Long.parseLong(httpServletRequest.getHeader("cid"));//获取公司id

        List<SysRemoteDatabasePredict> allPredict = predictService.getAllPredictByPanalID(cid ,panalId);
        Map<String,Object> results = new HashMap<String,Object>();
        results.put("total",allPredict.size());
        results.put("rows",allPredict);
        return CommonResult.success(results);
    }




    @Scheduled(fixedDelay = 2 * 60 * 1000)//每1分钟执行一次
//    @Scheduled(fixedDelay =  10 * 60 * 1000)//每10分钟执行一次
    public void predict() throws InterruptedException {
        //获取全部预警
        List<SysRemoteDatabasePredict> predicts = predictService.getAllPredict();
        //遍历
        for(SysRemoteDatabasePredict predict:predicts){

//            System.out.println(predict);
            log.info("用户设置待预测阈值："+ predict.toString());

            SysRemoteInfluxPanal sysRemoteInfluxPanal = predictService.getPanalById(predict.getPanalId());//获取对应的panal
//            System.out.println(sysRemoteInfluxPanal);
            log.info("面板信息："+ sysRemoteInfluxPanal.toString());
            String sqlStatement = sysRemoteInfluxPanal.getSqlStatement();
            //获取当前时间
            long date = System.currentTimeMillis();
            //获取过去一天的数据
            sqlStatement += " WHERE time >= "+ (date-24*60*60*1000) +"ms and time <= " + date + "ms GROUP BY time(300s)";
            QueryResult.Result searchResult = remoteService.getSearchResult(sqlStatement, sysRemoteInfluxPanal.getDbId());
            //数据处理
            if(searchResult.getSeries().size() == 0){
                log.info("当前面板无数据");
                continue;
            }

            //准备参数
            JSONObject paramMap = new JSONObject();
            paramMap.put("times",12);//为该预测多少个数据
            paramMap.put("threshold",predict.getThreshold());//阈值告诉那边界限
//            paramMap.put("threshold",predict.getThreshold());//阈值告诉那边界限
            paramMap.put("data",searchResult.getSeries().get(0).getValues());
            paramMap.put("columns",searchResult.getSeries().get(0).getColumns());
            paramMap.put("method",predict.getMethod());
            paramMap.put("id",String.valueOf(predict.getId()));
            //发送请求
            log.info(sysRemoteInfluxPanal.getPanalName() + " 面板预测请求已发送");
            CommonResult<HashMap<String, Object>> commonResult = MyHttp.POST(URL,paramMap);
            log.info(sysRemoteInfluxPanal.getPanalName() +  "预测结果："+commonResult.toString());
            if(commonResult.getCode()==400) {

                SysRemoteInfluxPanal panalById = predictService.getPanalById(predict.getPanalId());
                Integer firstIndex = Integer.parseInt(String.valueOf(commonResult.getData().get("firstIndex")));
                Integer maxIndex = Integer.parseInt(String.valueOf(commonResult.getData().get("maxIndex")));
                String maxData = String.valueOf(commonResult.getData().get("maxData"));
                List<List<Object>> values = searchResult.getSeries().get(0).getValues();


                Boolean aBoolean = predictService.sendVerifyCode(predict.getPhone(), panalById.getPanalName(), String.valueOf((firstIndex + 1) * 5)
                        , String.valueOf((maxIndex + 1) * 5), maxData);
//                System.out.println("短信发送："+ aBoolean);
                log.info("短信发送："+ aBoolean);

            }
        }

    }
}
