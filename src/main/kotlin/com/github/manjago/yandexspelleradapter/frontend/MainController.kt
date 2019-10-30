package com.github.manjago.yandexspelleradapter.frontend

import com.github.manjago.yandexspelleradapter.backend.YandexSpellerService
import com.github.manjago.yandexspelleradapter.logic.TextFixer
import com.github.manjago.yandexspelleradapter.logic.TextProcessor
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/1")
class MainController(private val textProcessor: TextProcessor)  {

    @PostMapping(path = ["/check"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun check(
            @RequestBody @Validated request: AdapterRequest
    ): ResponseEntity<AdapterResponse> {
        return ResponseEntity.status(200).body(textProcessor.process(request))
    }

}