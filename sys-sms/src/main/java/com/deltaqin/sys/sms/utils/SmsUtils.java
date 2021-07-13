package com.deltaqin.sys.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import com.deltaqin.sys.sms.config.PredictProperties;
import com.deltaqin.sys.sms.config.SmsProperties;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    @Autowired
    private SmsProperties prop;



    public CommonResponse sendSms(String phone, String code, String signName, String template) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", prop.getAccessKeyId(), prop.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        // 相当于是配置类，配置请求的时候各种模板以及参数，
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", template);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");


        //hint 此处可能会抛出异常，注意catch
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("用户注册短信已发送，电话号码为：" + phone + "，验证码为：" + code);
            return response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;
    }


    public CommonResponse sendPredict(Map<String,String> msg, String signName, String template) throws ClientException {


        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", prop.getAccessKeyId(), prop.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        // 相当于是配置类，配置请求的时候各种模板以及参数，
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        String phone = msg.get("phone");
        String max = msg.get("max");
        String maxValue = msg.get("maxValue");
        String name = msg.get("name");
        String first = msg.get("first");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", template);

        JsonObject parme=new JsonObject();
        parme.addProperty("name", name);
        parme.addProperty("max", max);
        parme.addProperty("maxValue", maxValue);
        parme.addProperty("first", first);
        parme.addProperty("phone", phone);
        request.putQueryParameter("TemplateParam",parme.toString());

        //hint 此处可能会抛出异常，注意catch
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("资源预警短信已发送，电话号码为：" + phone + "，面板名字为：" + name + "，关键信息：" + first + "分钟后首次超出阈值，"
                    + max + "分钟之后达到最大值：" + maxValue);
            return response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return null;
    }
}
