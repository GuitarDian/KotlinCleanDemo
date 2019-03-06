package com.example.hjk.kotlindemo.presentation.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Created by XTER on 2016/9/20.
 * Toast统一管理类
 */
object ToastUtil {
    private var toast: Toast? = null
    val handler = Handler(Looper.getMainLooper())

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 消息内容
     */
    fun showShort(context: Context, message: CharSequence) {
        handler.post {
            if (null == toast) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(message)
            }
            toast!!.show()
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param context 上下文
     * @param message 消息内容ID
     */
    fun showShort(context: Context, message: Int) {
        handler.post {
            if (null == toast) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                toast!!.setText(message)
            }
            toast!!.show()
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 消息内容
     */
    fun showLong(context: Context, message: CharSequence) {
        handler.post {
            if (null == toast) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                toast!!.setText(message)
            }
            toast!!.show()
        }

    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文
     * @param message 消息内容ID
     */
    fun showLong(context: Context, message: Int) {
        handler.post {
            if (null == toast) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                toast!!.setText(message)
            }
            toast!!.show()
        }
    }
}
