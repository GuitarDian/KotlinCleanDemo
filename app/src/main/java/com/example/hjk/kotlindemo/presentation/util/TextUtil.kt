package com.example.hjk.kotlindemo.presentation.util

import android.text.TextUtils

import org.json.JSONException
import org.json.JSONObject

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.regex.Pattern
import kotlin.experimental.and

/**
 * 字符串操作工具包
 *
 * @author hujunyu
 * @version 1.0
 * @created 2013-8-22
 */
object TextUtil {
    private val HEX_STRING_FLAG = "0x"
    private val OCT_STRING_0 = "0"
    private val emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")

    private val dateFormater = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }
    }

    private val dateFormater2 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd")
        }
    }

    private val dateFormater3 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyyMMddHHmmss")
        }
    }

    val systemCurrentTime: String
        get() {
            val rightNow = Calendar.getInstance()
            return dateFormater3.get().format(rightNow.time)
        }

    /**
     * RGB转HSB
     * @param rgbR
     * @param rgbG 范围 0-255
     * @param rgbB 范围 0-255
     * @return
     */
//    fun rgb2hsb(rgbR: Int, rgbG: Int, rgbB: Int): FloatArray {
//
//        if (0 <= rgbR && rgbR <= 255 && 0 <= rgbG && rgbG <= 255 && 0 <= rgbB && rgbB <= 255) {
//            val rgb = intArrayOf(rgbR, rgbG, rgbB)
//            Arrays.sort(rgb)
//            val max = rgb[2]
//            val min = rgb[0]
//
//            val hsbB = max / 255.0f
//            val hsbS = if (max == 0) 0 else (max - min) / max.toFloat()
//
//            var hsbH = 0f
//            if (max == rgbR && rgbG >= rgbB) {
//                hsbH = (rgbG - rgbB) * 60f / (max - min) + 0
//            } else if (max == rgbR && rgbG < rgbB) {
//                hsbH = (rgbG - rgbB) * 60f / (max - min) + 360
//            } else if (max == rgbG) {
//                hsbH = (rgbB - rgbR) * 60f / (max - min) + 120
//            } else if (max == rgbB) {
//                hsbH = (rgbR - rgbG) * 60f / (max - min) + 240
//            }
//            return floatArrayOf(hsbH / 360 * 254, hsbS * 254 / 100, hsbB * 255 / 100)
//        }
//        return floatArrayOf(0f, 0f, 0f)
//    }


//    fun rgbToHsvJson(rgbR: Int, rgbG: Int, rgbB: Int): String {
//        val color = intToString(rgbR) +
//                intToString(rgbG) + intToString(rgbB)
//        val hsv = rgb2hsb(rgbR, rgbG, rgbB)
//        val jb = JSONObject()
//        try {
//            jb.put("hue", Math.round(hsv[0]))
//            jb.put("sat", Math.round(hsv[1] * 100))
//            jb.put("light", Math.round(hsv[2] * 100))
//            jb.put("color", color)
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//
//        return jb.toString()
//    }

    /**
     * 炫彩灯光参数设置
     * @return
     */
    fun rgbToHsvJsonForAutoMode(): String {
        val jb = JSONObject()
        try {
            jb.put("hue", 0)
            jb.put("sat", 254)
            jb.put("light", 1)
            jb.put("time", 5)
            jb.put("step", 45)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jb.toString()
    }

    private fun intToString(rgb: Int): String {
        var str = ""
        if (rgb < 10) {
            str = "00$rgb"
        } else if (rgb < 100) {
            str = "0$rgb"
        } else {
            str = Integer.toString(rgb)
        }
        return str
    }

    fun hsb2rgb(h: Float, s: Float, v: Float): IntArray {
        if (java.lang.Float.compare(h, 0.0f) >= 0 && java.lang.Float.compare(h, 360.0f) <= 0
                && java.lang.Float.compare(s, 0.0f) >= 0 && java.lang.Float.compare(s, 1.0f) <= 0
                && java.lang.Float.compare(v, 0.0f) >= 0 && java.lang.Float.compare(v, 1.0f) <= 0) {

            var r = 0f
            var g = 0f
            var b = 0f
            val i = (h / 60 % 6).toInt()
            val f = h / 60 - i
            val p = v * (1 - s)
            val q = v * (1 - f * s)
            val t = v * (1 - (1 - f) * s)
            when (i) {
                0 -> {
                    r = v
                    g = t
                    b = p
                }
                1 -> {
                    r = q
                    g = v
                    b = p
                }
                2 -> {
                    r = p
                    g = v
                    b = t
                }
                3 -> {
                    r = p
                    g = q
                    b = v
                }
                4 -> {
                    r = t
                    g = p
                    b = v
                }
                5 -> {
                    r = v
                    g = p
                    b = q
                }
                else -> {
                }
            }
            return intArrayOf((r * 255.0).toInt(), (g * 255.0).toInt(), (b * 255.0).toInt())
        }
        return intArrayOf(0, 0, 0)
    }

    fun integerToHexString(i: Int?): String {
        val temp = Integer.toHexString(i!!)
        return HEX_STRING_FLAG + if (temp.length < 2) OCT_STRING_0 + temp else temp
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    fun toDate(sdate: String): Date? {
        try {
            return dateFormater.get().parse(sdate)
        } catch (e: ParseException) {
            return null
        }

    }

    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    fun friendly_time(sdate: String): String {
        val time = toDate(sdate) ?: return "Unknown"
        var ftime = ""
        val cal = Calendar.getInstance()

        // 判断是否是同一天
        val curDate = dateFormater2.get().format(cal.time)
        val paramDate = dateFormater2.get().format(time)
        if (curDate == paramDate) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
            return ftime
        }

        val lt = time.time / 86400000
        val ct = cal.timeInMillis / 86400000
        val days = (ct - lt).toInt()
        if (days == 0) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
        } else if (days == 1) {
            ftime = "昨天"
        } else if (days == 2) {
            ftime = "前天"
        } else if (days > 2 && days <= 10) {
            ftime = days.toString() + "天前"
        } else if (days > 10) {
            ftime = dateFormater2.get().format(time)
        }
        return ftime
    }

    /**
     * 判断给定字符串时间是否为今日
     *
     * @param sdate
     * @return boolean
     */
    fun isToday(sdate: String): Boolean {
        var b = false
        val time = toDate(sdate)
        val today = Date()
        if (time != null) {
            val nowDate = dateFormater2.get().format(today)
            val timeDate = dateFormater2.get().format(time)
            if (nowDate == timeDate) {
                b = true
            }
        }
        return b
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    fun isEmpty(input: String?): Boolean {
        if (input == null || "" == input)
            return true

        for (i in 0 until input.length) {
            val c = input[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    fun isEmail(email: String?): Boolean {
        return if (email == null || email.trim { it <= ' ' }.length == 0) false else emailer.matcher(email).matches()
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    fun toInt(str: String, defValue: Int): Int {
        try {
            return Integer.parseInt(str)
        } catch (e: Exception) {
        }

        return defValue
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toInt(obj: Any?): Int {
        return if (obj == null) 0 else toInt(obj.toString(), 0)
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    fun toLong(obj: String): Long {
        try {
            return java.lang.Long.parseLong(obj)
        } catch (e: Exception) {
        }

        return 0
    }

    /**
     * 字符串转布尔值
     *
     * @param b
     * @return 转换异常返回 false
     */
    fun toBool(b: String): Boolean {
        try {
            return java.lang.Boolean.parseBoolean(b)
        } catch (e: Exception) {
        }

        return false
    }

    /**
     * 将指定byte数组以16进制的形式打印到控制台
     * @param hint
     * String
     * @param b
     * byte[]
     * @return void
     */
    fun printHexString(hint: String, b: ByteArray) {
        print(hint)
        for (i in 0..b.size) {
            var hex = Integer.toHexString((b[i] and 0xFF.toByte()).toInt())
            if (hex.length == 1) {
                hex = "0$hex"
            }
            print(hex.toUpperCase() + " ")
        }
        println("")
    }

    fun getFileName(str: String): String {
        val `in` = str.lastIndexOf(".")
        return if (`in` != -1) {
            str.substring(0, `in`)
        } else str.trim { it <= ' ' }
    }

    /**
     * 十六进制字符串转十进制字符串
     * @param hexStr
     * @return string
     */
    fun HexStringToDec(hexStr: String): String {
        return Integer.valueOf(hexStr, 16)!!.toString()
    }

    /**
     * 分割字符串为whereArgs
     *
     * @param whereStr 条件
     * @return where
     */
    fun parseWhereClause(whereStr: String): String {
        if (!TextUtils.isEmpty(whereStr)) {
            val wheres = whereStr.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sb = StringBuilder()
            for (i in wheres.indices) {
                if (i == wheres.size - 1) {
                    sb.append(" ").append(wheres[i]).append("=? ")
                } else {
                    sb.append(" ").append(wheres[i]).append("=? and ")
                }
            }
            return sb.toString()
        }
        return ""
    }

    /**
     * 分割字符串
     *
     * @param whereArgs 条件
     * @return where
     */
    fun parseWhereArgs(whereArgs: String): Array<String>? {
        return if (!TextUtils.isEmpty(whereArgs)) {
            whereArgs.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else null
    }

//    fun convertUnicode(ori: String): String {
//        var aChar: Char
//        val len = ori.length
//        val outBuffer = StringBuffer(len)
//        var x = 0
//        while (x < len) {
//            aChar = ori[x++]
//            if (aChar == '\\') {
//                aChar = ori[x++]
//                if (aChar == 'u') {
//                    // Read the xxxx
//                    var value = 0
//                    for (i in 0..3) {
//                        aChar = ori[x++]
//                        when (aChar) {
//                            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> value = (value shl 4) + aChar.toInt() - '0'.toInt()
//                            'a', 'b', 'c', 'd', 'e', 'f' -> value = (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
//                            'A', 'B', 'C', 'D', 'E', 'F' -> value = (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
//                            else -> throw IllegalArgumentException(
//                                    "Malformed   \\uxxxx   encoding.")
//                        }
//                    }
//                    outBuffer.append(value.toChar())
//                } else {
//                    if (aChar == 't')
//                        aChar = '\t'
//                    else if (aChar == 'r')
//                        aChar = '\r'
//                    else if (aChar == 'n')
//                        aChar = '\n'
//                    else if (aChar == 'f')
//                        aChar = '\f'
//                    outBuffer.append(aChar)
//                }
//            } else
//                outBuffer.append(aChar)
//
//        }
//        return outBuffer.toString()
//    }

    fun format(d: Double, regex: String): String {
        val df = java.text.DecimalFormat(regex)
        return df.format(d)
    }

    fun format(jsonStr: String): String {
        var level = 0
        val jsonForMatStr = StringBuilder()
        for (i in 0..jsonStr.length) {
            val c = jsonStr[i]
            if (level > 0 && '\n' == jsonForMatStr[jsonForMatStr.length - 1]) {
                jsonForMatStr.append(getLevelStr(level))
            }
            when (c) {
                '{', '[' -> {
                    jsonForMatStr.append(c).append("\n")
                    level++
                }
                ',' -> jsonForMatStr.append(c).append("\n")
                '}', ']' -> {
                    jsonForMatStr.append("\n")
                    level--
                    jsonForMatStr.append(getLevelStr(level))
                    jsonForMatStr.append(c)
                }
                else -> jsonForMatStr.append(c)
            }
        }
        return jsonForMatStr.toString()
    }

    private fun getLevelStr(level: Int): String {
        val levelStr = StringBuilder()
        for (levelI in 0 until level) {
            levelStr.append("\t")
        }
        return levelStr.toString()
    }

//    fun formatJson(msg: String): String {
//        return format(convertUnicode(msg))
//    }
}
