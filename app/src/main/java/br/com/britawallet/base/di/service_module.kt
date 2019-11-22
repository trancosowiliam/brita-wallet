@file:Suppress("SpellCheckingInspection")
package br.com.britawallet.base.di

import br.com.britawallet.BuildConfig
import br.com.britawallet.base.extensions.log
import br.com.britawallet.base.extensions.toCurl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"
private const val CURL_INTERCEPTOR = "CURL_INTERCEPTOR"

const val BANCO_CENTRAL = "BANCO_CENTRAL"
const val MERCADO_BITCOIN = "MERCADO_BITCOIN"

val serviceModule = module {
    single(BANCO_CENTRAL.toQualifier()) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BANCO_CENTRAL_URL)
            .client(/* OkHttpClient */ get())
            .addConverterFactory(get())
            .build()
    } bind Retrofit::class

    single(MERCADO_BITCOIN.toQualifier()) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.MERCADO_BITCOIN_URL)
            .client(/* OkHttpClient */ get())
            .addConverterFactory(get())
            .build()
    } bind Retrofit::class

    @Suppress("ConstantConditionIf")
    single {
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get(HEADER_INTERCEPTOR.toQualifier()))

        if (BuildConfig.DEBUG)
            builder.addInterceptor(get(CURL_INTERCEPTOR.toQualifier()))

        builder.build()
    } bind OkHttpClient::class

    single(HEADER_INTERCEPTOR.toQualifier()) {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("SO", "android")
                    .addHeader("Version", BuildConfig.VERSION_CODE.toString())
                    .build()
            )
        }
    }

    @Suppress("ConstantConditionIf")
    single(CURL_INTERCEPTOR.toQualifier()) {
        Interceptor { chain ->
            chain.request().let { request ->
                log(request.toCurl())
                chain.proceed(request)
            }
        }
    }

    single {
        GsonConverterFactory.create()
    } bind Converter.Factory::class
}

fun String.toQualifier() = named(this)