package com.mixed.kotlin

import com.mixed.java.JavaConstant
import org.springframework.stereotype.Component

/**
 * @author wangyi
 * @date 2021/9/29
 */
@Component
class KotlinProvider {

    fun send(message: String){
        println(JavaConstant.ALIPAY);
    }
}