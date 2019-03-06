package com.example.hjk.kotlindemo.data.source.remote.net.converter


import com.example.hjk.kotlindemo.presentation.util.L
import com.google.gson.Gson
import com.google.gson.TypeAdapter

import java.io.IOException

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by XTER on 2017/7/3.
 */
class GsonResponseBodyConverter<T>( private val gson: Gson,
                                    private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val json = value.string()
        L.d(json)
        try {
            return adapter.fromJson(json)
        } finally {
            value.close()
        }
    }
}
