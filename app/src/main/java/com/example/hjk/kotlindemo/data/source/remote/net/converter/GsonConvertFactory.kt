package com.example.hjk.kotlindemo.data.source.remote.net.converter


import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by XTER on 2017/7/3.
 */
class GsonConvertFactory private constructor(private val gson: Gson?) : Converter.Factory() {

    init {
        if (gson == null) throw NullPointerException("gson == null")
    }

    companion object {

        @JvmOverloads
        fun create(gson: Gson = Gson()): GsonConvertFactory {
            return GsonConvertFactory(gson)
        }
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *>? {

        val adapter :TypeAdapter<*> = gson?.getAdapter(TypeToken.get(type))!!
        return GsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(type: Type?, parameterAnnotations: Array<Annotation>?,
                                      methodAnnotations: Array<Annotation>?, retrofit: Retrofit?)
            : Converter<*, RequestBody>? {
        val adapter : TypeAdapter<*> = gson?.getAdapter(TypeToken.get(type))!!
        return GsonRequestBodyConverter(gson, adapter)
    }

}
