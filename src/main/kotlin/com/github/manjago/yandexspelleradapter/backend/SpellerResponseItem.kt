package com.github.manjago.yandexspelleradapter.backend

data class SpellerResponseItem(
        val code: Int,
        val pos: Int,
        val row: Int,
        val col: Int,
        val len: Int,
        val word: String,
        val s: List<String>?
)
