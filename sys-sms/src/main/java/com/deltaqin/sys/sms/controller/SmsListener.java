package com.deltaqin.sys.sms.controller;

import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.deltaqin.sys.sms.config.PredictProperties;
import com.deltaqin.sys.sms.config.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import com.deltaqin.sys.sms.utils.SmsUtils;

import java.util.Map;

/**
 * 监听到消息之后发送短信
 */
@Component
@Slf4j
//@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {
    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties smsProperties;
    @Autowired
    private PredictProperties predictProperties;


    /**
     * - `value`：这个消费者关联的队列。值是`@Queue`，代表一个队列
     * - `exchange`：队列所绑定的交换机，值是`@Exchange`类型
     * - `key`：队列和交换机绑定的`RoutingKey`
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sys.sms.queue",durable = "true"),
            exchange = @Exchange(value = "sys.sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"sms.verify.code"}
    ))
    public void listenSms(Map<String,String> msg){
        log.info("消费消息,内容为：" + msg.toString());
        if (msg == null || msg.size() <= 0){
            //不做处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(code)){
            //发送消息
            try {
                this.smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
            }catch (ClientException e){
                return;
            }
        }else {
            //不做处理
            return;
        }
    }


    /**
     * - `value`：这个消费者关联的队列。值是`@Queue`，代表一个队列
     * - `exchange`：队列所绑定的交换机，值是`@Exchange`类型
     * - `key`：队列和交换机绑定的`RoutingKey`
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "predict.sms.queue",durable = "true"),
            exchange = @Exchange(value = "predict.sms.exchange",ignoreDeclarationExceptions = "true"),
            key = {"predict.verify.code"}
    ))
    public void listenPredict(Map<String,String> msg){
        log.info("消费消息,内容为：" + msg.toString());
        if (msg == null || msg.size() <= 0){
            //不做处理
            return;
        }
        String phone = msg.get("phone");
        String name = msg.get("name");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(name)){
            //发送消息
            try {
                this.smsUtils.sendPredict(msg, predictProperties.getSignName(), predictProperties.getVerifyCodeTemplate());
            }catch (ClientException e){
                return;
            }
        }else {
            //不做处理
            return;
        }
    }
}
