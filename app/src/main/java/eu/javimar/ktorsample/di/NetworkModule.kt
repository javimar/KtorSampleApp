package eu.javimar.ktorsample.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.javimar.ktorsample.data.network.PostsService
import eu.javimar.ktorsample.data.network.PostsServiceImpl
import eu.javimar.ktorsample.data.urls.BASE_URL
import eu.javimar.ktorsample.data.urls.TIME_OUT_MILLIS
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providePostService(httpClient: HttpClient): PostsService = PostsServiceImpl(httpClient)

    @Provides
    fun provideKtorClient(@ApplicationContext context: Context): HttpClient {
        return HttpClient(provideEngine(context)) {
            expectSuccess = true
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
                filter { request ->
                    request.url.host.contains(BASE_URL)
                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    private fun provideEngine(context: Context): HttpClientEngine {
        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
        // creating the Ktor HttpClienEngine
        return OkHttp.create {
             config {
                 connectTimeout(TIME_OUT_MILLIS, TimeUnit.MILLISECONDS)
             }
            addInterceptor(chuckerInterceptor)
        }
    }
}