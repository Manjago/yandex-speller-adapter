package com.github.manjago.yandexspelleradapter.logic

import com.github.manjago.yandexspelleradapter.backend.SpellerResponseItem
import com.github.manjago.yandexspelleradapter.frontend.AdapterResponse
import org.springframework.stereotype.Component


@Component
class WordLister {
    fun list(errors: List<SpellerResponseItem>): List<AdapterResponse.WordInfo> {
        if (errors.isNullOrEmpty()) {
            return listOf()
        }

        return errors.asSequence().map {
            AdapterResponse.WordInfo(word = it.word,
                    replaceTo = it.s ?: listOf()
            )
        }.toList()
    }
}