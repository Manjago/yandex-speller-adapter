package com.github.manjago.yandexspelleradapter.logic

import com.github.manjago.yandexspelleradapter.backend.SpellerResponseItem
import com.github.manjago.yandexspelleradapter.backend.YandexSpellerService
import com.github.manjago.yandexspelleradapter.frontend.AdapterRequest
import com.github.manjago.yandexspelleradapter.frontend.AdapterResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TextProcessor(private val yandexSpellerService: YandexSpellerService,
                    private val textFixer: TextFixer,
                    private val wordLister: WordLister) {
    fun process(request: AdapterRequest) : AdapterResponse {
        val spellerResponse = yandexSpellerService.check(request.text)

        return if (spellerResponse != null && spellerResponse.isNotEmpty()) {
            AdapterResponse(text = textFixer.fixText(request.text, spellerResponse), hasErrors = true,
                    errorWords = wordLister.list(spellerResponse))
        } else {
            AdapterResponse(hasErrors = false)
        }
    }
}
