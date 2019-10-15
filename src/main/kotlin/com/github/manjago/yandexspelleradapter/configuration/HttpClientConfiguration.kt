package com.github.manjago.yandexspelleradapter.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.http.HttpClient
import java.time.Duration

@Configuration
class HttpClientConfiguration {
    @Bean
    fun httpClient(): HttpClient {
        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .version(HttpClient.Version.HTTP_1_1)
                .build()
    }
}