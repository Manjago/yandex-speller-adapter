package com.github.manjago.yandexspelleradapter.logic

import com.github.manjago.yandexspelleradapter.backend.SpellerResponseItem
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class TextFixer {
    fun fixText(original: String, advices: List<SpellerResponseItem>): String {

        if (advices.isEmpty()) {
            return original
        }

        val sortedAdvices = advices.sortedWith(Comparator { o1, o2 ->
            o2.pos.compareTo(o1.pos)
        })

        logger.info { "text $original, advices $sortedAdvices" }

        var modified = original
        for(item in sortedAdvices) {

            if (item.s != null && item.s.isNotEmpty()) {
                modified = modified.replaceRange(startIndex = item.pos, endIndex = item.pos + item.len,
                        replacement = item.s[0])
            }

        }

        return modified
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}