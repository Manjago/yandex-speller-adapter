package com.github.manjago.yandexspelleradapter.frontend

data class AdapterResponse(
        val text: String? = null,
        val errorWords: List<WordInfo> = listOf(),
        val hasErrors: Boolean
) {
    data class WordInfo(
            val word: String,
            val replaceTo: List<String>?
    )
}
