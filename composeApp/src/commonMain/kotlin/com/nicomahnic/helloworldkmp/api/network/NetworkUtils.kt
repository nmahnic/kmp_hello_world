package com.nicomahnic.helloworldkmp.api.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkUtils {

    private const val ACCESS_TOKEN = "79c99fda9894cf4017793cdb40721cb6"
    private const val BASE_URL = "https://www.superheroapi.com/api/$ACCESS_TOKEN"
    const val URL = "$BASE_URL/search/a"

    private val CustomLoggerPlugin = createClientPlugin("CustomLoggerPlugin") {
        onRequest { request, content ->
            println("NAMG: LoggerPlugin =============REQUEST==============" )
            println("NAMG: LoggerPlugin ${request.method.value} => ${request.url}" )
            println("NAMG: LoggerPlugin BODY => ${request.body}" )
            println("NAMG: LoggerPlugin =============END-REQUEST==============" )
        }

        onResponse {response ->
            println("NAMG: LoggerPlugin =============RESPONSE==============" )
            println("NAMG: LoggerPlugin ${response.request.method.value} / ${response.status} => ${response.request.url}" )
            println("NAMG: LoggerPlugin BODY => $response" )
            println("NAMG: LoggerPlugin =============END-RESPONSE==============" )
        }
    }

    val httpClient: HttpClient = HttpClient{
        install(CustomLoggerPlugin)
        install(ContentNegotiation) {
            json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }
}