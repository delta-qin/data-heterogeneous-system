package com.deltaqin.sys.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author deltaqin
 * @date 2020/10/15 1:34 下午
 */
@ConfigurationProperties(prefix = "predict.sms")
@Component
@Data
public class PredictProperties {
    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;


}
