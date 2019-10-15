package com.github.manjago.yandexspelleradapter.frontend

import com.github.manjago.yandexspelleradapter.backend.YandexSpellerService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/1")
class MainController(private val yandexSpellerService: YandexSpellerService)  {

    @PostMapping(path = ["/check"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun getPsList(
            @RequestBody @Validated request: AdapterRequest
    ): ResponseEntity<AdapterResponse> {

        val spellerResponse = yandexSpellerService.check(request.text)

        return ResponseEntity.status(200).body(if (spellerResponse != null && spellerResponse.isNotEmpty()) {
            AdapterResponse(text = spellerResponse.toString(), hasErrors = true)
        } else {
            AdapterResponse(hasErrors = false)
        })
    }

}