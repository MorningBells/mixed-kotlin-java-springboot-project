package com.mixed.kotlin

import cn.hutool.json.JSONUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages=["com.mixed"])
class KotlinJavaApplication

fun main(args: Array<String>) {
//    print(JavaLanguage().showLanguage())
    var applicationContext = runApplication<KotlinJavaApplication>(*args)
    print("bean列表查询..."+ JSONUtil.toJsonStr(applicationContext.getBeanDefinitionNames()))
}
