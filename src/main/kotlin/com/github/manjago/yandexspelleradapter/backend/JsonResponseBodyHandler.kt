package com.github.manjago.yandexspelleradapter.backend

import com.fasterxml.jackson.databind.ObjectReader
import java.net.http.HttpResponse

class JsonResponseBodyHandler<T>(val reader: ObjectReader) : HttpResponse.BodyHandler<T> {
    override fun apply(responseInfo: HttpResponse.ResponseInfo?): HttpResponse.BodySubscriber<T> {
        return HttpResponse.BodySubscribers
                .mapping(HttpResponse.BodySubscribers.ofByteArray())
                { reader.readValue<T>(it) }
    }

}