package com.github.manjago.yandexspelleradapter

import com.github.manjago.yandexspelleradapter.frontend.AdapterRequest
import org.apache.coyote.Adapter
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

class SimpleTest : AbstractSpringIntergrationTest() {

    @Test
    fun case1() {

        val response = sendCheck(AdapterRequest("""
            досадная ашипка
            вкралась в текст
            за что едят хлеп копирайтеры
        """.trimIndent()))

        MatcherAssert.assertThat(response.body().text, `is`(equalTo("""
            досадная ошибка
            вкралась в текст
            за что едят хлеб копирайтеры
        """.trimIndent())))

    }
}