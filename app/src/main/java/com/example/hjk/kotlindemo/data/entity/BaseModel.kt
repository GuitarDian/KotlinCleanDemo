package com.example.hjk.kotlindemo.data.entity


import java.io.Serializable

/**
 * Created by HJK on 2018/10/8.
 */

open class BaseModel : Serializable {

    private var id: Long = 0

    companion object {
        private const val serialVersionUID = 7587087154420506498L
    }

}
