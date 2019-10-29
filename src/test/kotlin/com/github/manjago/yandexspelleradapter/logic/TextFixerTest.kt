package com.github.manjago.yandexspelleradapter.logic

import com.github.manjago.yandexspelleradapter.backend.SpellerResponseItem
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Before
import org.junit.Test

class TextFixerTest {

    private lateinit var textFixer: TextFixer

    @Before
    fun setUp() {
        textFixer = TextFixer()
    }

    @Test
    fun fixText() {
        val original = """
            досадная ашипка
            вкралась в текст
            за что едят хлеп копирайтеры
        """.trimIndent()

        val result = textFixer.fixText(original,
                listOf(SpellerResponseItem(code = 1, pos = 9, row = 0, col = 9, len = 6, word = "ашипка", s = listOf("ошибка", "ашыпка")),
                        SpellerResponseItem(code = 1, pos = 45, row = 2, col = 12, len = 4, word = "хлеп", s = listOf("хлеб"))
                ))

        assertThat(result, `is`(equalTo("""
            досадная ошибка
            вкралась в текст
            за что едят хлеб копирайтеры
        """.trimIndent())))
    }
}