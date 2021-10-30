package com.mixed.java;

import com.mixed.kotlin.KotlinConstant;
import com.mixed.kotlin.KotlinProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangyi
 * @date 2021/9/29
 */
@Component
public class JavaConsumer {

    @Autowired
    private KotlinProvider kotlinProvider;

    public void doConsumer(){
        kotlinProvider.send(KotlinConstant.weixinPay);
    }

}
