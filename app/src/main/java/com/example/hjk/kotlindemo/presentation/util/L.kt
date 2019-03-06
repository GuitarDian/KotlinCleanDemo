package com.example.hjk.kotlindemo.presentation.util

import android.text.TextUtils
import android.util.Log

import java.io.IOException

class L {

    companion object {
        var DEBUG = true

        fun v(msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg)) {
                Log.v(getMethodPath(4, 4), msg)
            }
        }

        fun i(msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg)) {
                Log.i(getMethodPath(4, 4), msg)
            }
        }

        fun d(msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg)) {
                Log.d(getMethodPath(4, 4), msg)
            }
        }

        fun w(msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg)) {
                Log.w(getMethodPath(4, 4), msg)
            }
        }

        fun e(msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg)) {
                Log.e(getMethodPath(4, 4), msg)
            }
        }

        fun vj(msg: String) {
            if (DEBUG) {
                for (i in 0 .. msg.length / 3000) {
                    v(5, TextUtil.format(msg.substring(i * 3000, (i + 1) * 3000)))
                }
            }
        }

        fun dj(msg: String) {
            if (DEBUG) {
                for (i in 0 .. msg.length / 3000) {
                    d(5, TextUtil.format(msg.substring(i * 3000, (i + 1) * 3000)))
                }
            }
        }

        fun ij(msg: String) {
            if (DEBUG) {
                for (i in 0 .. msg.length / 3000) {
                    i(5, TextUtil.format(msg.substring(i * 3000, (i + 1) * 3000)))
                }
            }
        }

        fun wj(msg: String) {
            if (DEBUG) {
                for (i in 0 .. msg.length / 3000) {
                    w(5, TextUtil.format(msg.substring(i * 3000, (i + 1) * 3000)))
                }
            }
        }

        fun ej(msg: String) {
            if (DEBUG) {
                for (i in 0 .. msg.length / 3000) {
                    e(5, TextUtil.format(msg.substring(i * 3000, (i + 1) * 3000)))
                }
            }
        }

        fun v(tag: String, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.v(tag, msg)
        }

        fun d(tag: String, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.d(tag, msg)
        }

        fun i(tag: String, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.i(tag, msg)
        }

        fun w(tag: String, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.w(tag, msg)
        }

        fun e(tag: String, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.e(tag, msg)
        }

        fun v(classPrior: Int, methodPriorint: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.v(getMethodPath(classPrior, methodPriorint), msg)
        }

        fun d(classPrior: Int, methodPrior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.d(getMethodPath(classPrior, methodPrior), msg)
        }

        fun i(classPrior: Int, methodPrior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.i(getMethodPath(classPrior, methodPrior), msg)
        }

        fun w(classPrior: Int, methodPrior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.w(getMethodPath(classPrior, methodPrior), msg)
        }

        fun e(classPrior: Int, methodPrior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.e(getMethodPath(classPrior, methodPrior), msg)
        }

        fun v(prior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.v(getMethodPath(prior, prior), msg)
        }

        fun d(prior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.d(getMethodPath(prior, prior), msg)
        }

        fun i(prior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.i(getMethodPath(prior, prior), msg)
        }

        fun w(prior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.w(getMethodPath(prior, prior), msg)
        }

        fun e(prior: Int, msg: String) {
            if (DEBUG && !TextUtils.isEmpty(msg))
                Log.e(getMethodPath(prior, prior), msg)
        }


        /**
         * 得到调用此方法的类名与方法名
         *
         * @param classPrior  类级
         * @param methodPrior 方法级
         * @return string
         */
        fun getMethodPath(classPrior: Int, methodPrior: Int): String {
            val stackTrace = Thread.currentThread().stackTrace

            val targetElement = stackTrace[classPrior]
            val fileName = targetElement.fileName
            //		String className = targetElement.getClassName();
            //		String[] classNameInfo = className.split("\\.");
            //		if (classNameInfo.length > 0) {
            //			className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
            //		}
            //
            //		if (className.contains("$")) {
            //			className = className.split("\\$")[0] + SUFFIX;
            //		}

            val methodName = targetElement.methodName
            var lineNumber = targetElement.lineNumber

            if (lineNumber < 0) {
                lineNumber = 0
            }
            val length = Thread.currentThread().stackTrace.size
            return if (classPrior > length || methodPrior > length) {
                ""
            } else
                "($fileName:$lineNumber)#$methodName-->"
        }

        /**
         * 得到调用此方法的类名与方法名（带包名）
         *
         * @param classPrior  类级
         * @param methodPrior 方法级
         * @return string
         */
        fun getPackageMethodPath(classPrior: Int, methodPrior: Int): String {
            val stackTrace = Thread.currentThread().stackTrace

            val targetElement = stackTrace[classPrior]
            val className = targetElement.className
            val methodName = targetElement.methodName
            var lineNumber = targetElement.lineNumber

            if (lineNumber < 0) {
                lineNumber = 0
            }
            val length = Thread.currentThread().stackTrace.size
            return if (classPrior > length || methodPrior > length) {
                ""
            } else
                "($className:$lineNumber)#$methodName-->"
        }

        /**
         * 测试方法，将线程中的序列全部输出
         */
        fun logThreadSequence() {
            val length = Thread.currentThread().stackTrace.size
            for (i in 0..length) {
                Log.i(Thread.currentThread().stackTrace[i].className,
                        Thread.currentThread().stackTrace[i].methodName)
            }
        }

        fun f(msg: String) {
            f(5, Level.I, msg)
        }

        fun f(prior: Int, msg: String) {
            f(prior, Level.I, msg)
        }

        fun f(prior: Int, level: Level, msg: String) {
            when (level) {
                Level.V -> v(prior + 1, msg)
                Level.D -> d(prior + 1, msg)
                Level.I -> i(prior + 1, msg)
                Level.W -> w(prior + 1, msg)
                Level.E -> e(prior + 1, msg)
            }
            val tag = getPackageMethodPath(prior, prior)

            try {
                val content = SysUtil.now + "|" + tag + msg
                FileUtil.writeLogFile(content)
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    enum class Level {
        V,
        D,
        I,
        W,
        E
    }

}
