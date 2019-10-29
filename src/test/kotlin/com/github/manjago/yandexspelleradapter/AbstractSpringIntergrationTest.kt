package com.github.manjago.yandexspelleradapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.manjago.yandexspelleradapter.backend.JsonResponseBodyHandler
import com.github.manjago.yandexspelleradapter.frontend.AdapterRequest
import com.github.manjago.yandexspelleradapter.frontend.AdapterResponse
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.time.Duration

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AbstractSpringIntergrationTest {

    @LocalServerPort
    protected var serverPort: Int = 0

    @Autowired
    lateinit var objectMapper: ObjectMapper

    protected val httpClient: HttpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(30))
            .version(HttpClient.Version.HTTP_1_1)
            .build()

    private fun makeCheckRequest(jsonBody: String): HttpRequest {
        return HttpRequest.newBuilder(
                URI.create("http://localhost:${serverPort}/yandex-speller-adapter/1/check"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build()
    }

    protected fun sendCheck(request: AdapterRequest) = httpClient.send(
            makeCheckRequest(objectMapper.writeValueAsString(request)),
            JsonResponseBodyHandler<AdapterResponse>(objectMapper.readerFor(AdapterResponse::class.java)))!!

}