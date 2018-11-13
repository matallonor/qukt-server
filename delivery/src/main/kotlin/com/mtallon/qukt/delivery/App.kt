package com.mtallon.qukt.delivery

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = [
    "com.mtallon.qukt.delivery.config",
    "com.mtallon.qukt.dataproviders.db.jpa.config",
    "com.mtallon.qukt.delivery.rest.imp"
])
class App

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}
