package com.example.hjk.kotlindemo.data.source.local.db

import android.content.Context
import android.content.SharedPreferences


/**
 * Created by HJK on 2018/9/19.
 * 配置存储管理--SharedPreferenceManager
 */
object SPM {
    /**
     * 存放常量
     */
    val CONSTANT = "constant"

    fun saveBoolean(context: Context, name: String, key: String, value: Boolean) {
        try {
            val sp = context.getSharedPreferences(name, 0)
            val editor = sp.edit()
            editor.putBoolean(key, value)
            editor.apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun saveInt(context: Context, name: String, key: String, value: Int) {
        try {
            val sp = context.getSharedPreferences(name, 0)
            val editor = sp.edit()
            editor.putInt(key, value)
            editor.apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun saveStr(context: Context, name: String, key: String, value: String) {
        try {
            val sp = context.getSharedPreferences(name, 0)
            val editor = sp.edit()
            editor.putString(key, value)
            editor.apply()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    fun getBoolean(context: Context, name: String, key: String, defaultValue: Boolean): Boolean {
        val sp = context.getSharedPreferences(name, 0)
        return sp != null && sp.getBoolean(key, defaultValue)
    }

    fun getInt(context: Context, name: String, key: String, defaultValue: Int): Int {
        val sp = context.getSharedPreferences(name, 0)
        return sp?.getInt(key, defaultValue) ?: -1
    }

    fun getStr(context: Context, name: String, key: String, defaultValue: String): String? {
        val sp = context.getSharedPreferences(name, 0)
        return if (sp != null) {
            sp.getString(key, defaultValue)
        } else ""
    }

    /**
     * 清除配置表
     *
     * @param name 配置文件名
     */
    fun removeSP(context: Context, name: String) {
        val sp = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }

}
