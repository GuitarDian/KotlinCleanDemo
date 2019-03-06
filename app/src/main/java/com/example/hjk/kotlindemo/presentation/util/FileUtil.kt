package com.example.hjk.kotlindemo.presentation.util

import android.os.Environment
import android.text.TextUtils

import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

/**
 * Created by XTER on 2017/11/2.
 * 文件工具--带rx
 */
object FileUtil {
    /**
     * 删除文件或目录
     *
     * @param path 文件或目录。
     * @return true 表示删除成功，否则为失败
     */

    @Synchronized
    fun delete(path: File?): Boolean {
        if (null == path) {
            return true
        }
        if (path.isDirectory) {
            val files = path.listFiles()
            if (null != files) {
                for (file in files) {
                    if (!delete(file)) {
                        return false
                    }
                }
            }
        }
        return !path.exists() || path.delete()
    }

    @Synchronized
    @Throws(IOException::class)
    fun createFile(path: String) {
        if (TextUtils.isEmpty(path)) {
            return
        }
        val file = File(path)
        if (!file.exists())
            if (path.endsWith("/")) {
                file.mkdirs()
            } else {
                if (file.parentFile.exists()) {
                    file.createNewFile()
                } else {
                    file.parentFile.mkdirs()
                    file.createNewFile()
                }
            }
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     */
    @Throws(IOException::class)
    fun writeFileContent(filepath: String, newstr: String) {
        var pw: PrintWriter? = null
        try {
            val fw = FileWriter(filepath, true)
            pw = PrintWriter(fw)
            pw.println(newstr)
            pw.flush()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            createFile(filepath)
        } finally {
            if (pw != null) {
                pw.close()
            }
        }
    }

    @Throws(IOException::class)
    fun writeLogFile(newstr: String) {
        val logFileName = Environment.getExternalStorageDirectory().toString() + "/jvxs/logs/log_" + SysUtil.date + ".txt"
        //		File logFile = new File(logFileName);
        //		if(logFile.exists()&&logFile.length()>200*1024*1024){
        //			logFileName =
        //		}
        var pw: PrintWriter? = null
        try {
            val fw = FileWriter(logFileName, true)
            pw = PrintWriter(fw)
            pw.println(newstr)
            pw.flush()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            createFile(logFileName)
        } finally {
            if (pw != null) {
                pw.close()
            }
        }
    }

    fun writeToFile(content: String, filePath: String) {
        var fileWriter: FileWriter? = null
        try {
            fileWriter = FileWriter(filePath, true)
            fileWriter.write(content)
            fileWriter.flush()
        } catch (t: Throwable) {
            t.printStackTrace()
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}
