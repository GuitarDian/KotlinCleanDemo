package com.example.hjk.kotlindemo.presentation.util

import android.annotation.TargetApi
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.hjk.kotlindemo.presentation.util.SysUtil.belongCalendar

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Created by XTER on 2016/9/20.
 * 系统相关
 */
object SysUtil {

    /**
     * 得到当前日期
     *
     * @return time
     */
    val date: String
        get() {
            val d = Date()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            return sdf.format(d)
        }

    /**
     * 得到当前时间
     *
     * @return time
     */
    val now: String
        get() {
            val d = Date()
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
            return sdf.format(d)
        }

    /**
     * 得到当前时间
     *
     * @return time
     */
    val now2: String
        get() {
            val d = Date()
            val sdf = SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
            return sdf.format(d)
        }

    /**
     * 得到当前时间
     *
     * @return time
     */
    val now3: String
        get() {
            val d = Date()
            val sdf = SimpleDateFormat("HH:mm", Locale.CHINA)
            return sdf.format(d)
        }


    /**
     * 获取系统几月几日星期几
     * @return
     */
    // 获取当前月份
    // 获取当前月份的日期号码
    val systemData: String
        get() {
            val c = Calendar.getInstance()
            c.timeZone = TimeZone.getTimeZone("GMT+8:00")
            val mMonth = (c.get(Calendar.MONTH) + 1).toString()
            val mDay = c.get(Calendar.DAY_OF_MONTH).toString()
            var mWay = c.get(Calendar.DAY_OF_WEEK).toString()
            if ("1" == mWay) {
                mWay = "天"
            } else if ("2" == mWay) {
                mWay = "一"
            } else if ("3" == mWay) {
                mWay = "二"
            } else if ("4" == mWay) {
                mWay = "三"
            } else if ("5" == mWay) {
                mWay = "四"
            } else if ("6" == mWay) {
                mWay = "五"
            } else if ("7" == mWay) {
                mWay = "六"
            }
            return mMonth + "月" + mDay + "日   " + "星期" + mWay
        }

    /**
     * 得到转换时间
     *
     * @param time 数
     * @return time
     */
    fun getTime(time: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
        return sdf.format(time)
    }

    /**
     * 得到转换时间
     *
     * @param time 数
     * @return time
     */
    fun getTime2(time: Long): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        return sdf.format(time)
    }

    /**
     * 判断是否在规定时间内
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @return
     */
    fun timeIsBelong(beginTime: String, endTime: String): Boolean? {
        val df = SimpleDateFormat("HH:mm")//设置日期格式
        var now: Date? = null
        var bTime: Date? = null
        var eTime: Date? = null
        try {
            now = df.parse(df.format(Date()))
            bTime = df.parse(beginTime)
            eTime = df.parse(endTime)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return belongCalendar(now, bTime, eTime)
    }

    private fun belongCalendar(nowTime: Date?, beginTime: Date?, endTime: Date?): Boolean {
        val date = Calendar.getInstance()
        date.time = nowTime

        val begin = Calendar.getInstance()
        begin.time = beginTime

        val end = Calendar.getInstance()
        end.time = endTime

        return if (date.after(begin) && date.before(end)) {
            true
        } else {
            false
        }
    }

    /**
     * 将String转换为utf-8
     */
    fun getUTF8XMLString(xml: String): String {
        // A StringBuffer Object
        val sb = StringBuffer()
        sb.append(xml)
        var xmString = ""
        var xmlUTF8 = ""
        try {
            xmString = String(sb.toString().toByteArray(charset("UTF-8")))
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

        return xmlUTF8
    }

    /**
     * 判断服务是否开启
     * @param context
     * @param ServiceName 服务的全名称全路径的.比如："com.example.demo.service.myservice".
     * @return
     */
    fun isServiceRunning(context: Context, ServiceName: String?): Boolean {
        if ("" == ServiceName || ServiceName == null)
            return false
        val myManager = context
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningService = myManager
                .getRunningServices(30) as ArrayList<ActivityManager.RunningServiceInfo>
        for (i in runningService.indices) {
            if (runningService[i].service.className.toString() == ServiceName) {
                return true
            }
        }
        return false
    }

    /* 获得状态栏高度 */
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    /* 获得状态栏高度 */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun getStatusBarHeight2(context: Context): Int {
        var result = 0
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(`object`).toString())
            result = context.resources.getDimensionPixelSize(height)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }

        return result
    }

    /* 获取操作拦高度 */
    fun getActionBarHeight(context: Context): Int {
        var actionBarHeight = 0
        val tv = TypedValue()
        if (context.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return actionBarHeight
    }

    /* 得到系统栏高度 */
    fun getSystemBarHeight(context: Context): Int {
        return getActionBarHeight(context) + getStatusBarHeight(context)
    }

    /* 得到系统栏（包括状态栏和操作栏）参数 */
    fun getSystemBarParam(context: Context): LinearLayout.LayoutParams {
        val occupyHeight = getActionBarHeight(context) + getStatusBarHeight(context)
        return LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, occupyHeight)
    }

    /**
     * 判断系统中是否存在可以启动的相机应用
     *
     * @return 存在返回true，不存在返回false
     */
    fun hasCamera(context: Context): Boolean {
        val packageManager = context.packageManager
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list.size > 0
    }

    fun getScreenWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.heightPixels
    }

    fun getLargerScreenSide(context: Context): Int {
        val dm = context.resources.displayMetrics
        return Math.max(dm.widthPixels, dm.heightPixels)
    }

    fun getShorterScreenSide(context: Context): Int {
        val dm = context.resources.displayMetrics
        return Math.min(dm.widthPixels, dm.heightPixels)
    }

    fun getShorterScreenSideToHight(context: Context): Int {
        return Math.round(getShorterScreenSide(context) * getScreenRatio(context))
    }

    fun getScreenRatio(context: Context): Float {
        return getShorterScreenSide(context).toFloat() / getLargerScreenSide(context)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue dp单位
     * @return px单位
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue pxValue px单位
     * @return dp单位
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue sp单位
     * @return px单位
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue px单位
     * @return sp单位
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 退出
     */
    fun exit() {
        //获取PID
        android.os.Process.killProcess(android.os.Process.myPid())
        //常规java、c#的标准退出法，返回值为0代表正常退出
        System.exit(0)
    }
}
