package com.example.hjk.kotlindemo.presentation.init

import com.example.hjk.kotlindemo.presentation.base.BaseApp
import com.example.hjk.kotlindemo.presentation.util.L
import com.squareup.leakcanary.LeakCanary
import com.tencent.bugly.crashreport.CrashReport

// 	     * #                       _oo0oo_                     #
//		 * #                      o8888888o                    #
//		 * #                      88" . "88                    #
//		 * #                      (| -_- |)                    #
//		 * #                      0\  =  /0                    #
//		 * #                    ___/`---'\___                  #
//		 * #                  .' \\|     |# '.                 #
//		 * #                 / \\|||  :  |||# \                #
//		 * #                / _||||| -:- |||||- \              #
//		 * #               |   | \\\  -  #/ |   |              #
//		 * #               | \_|  ''\---/''  |_/ |             #
//		 * #               \  .-\__  '-'  ___/-. /             #
//		 * #             ___'. .'  /--.--\  `. .'___           #
//		 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
//		 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
//		 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
//		 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
//		 * #                       `=---='                     #
//		 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
//		 * #                                                   #
//		 * #               佛祖保佑         永无BUG            #


/**
 * Created by HJK on 2018/9/19.
 */

class DemoAPP : BaseApp() {

    companion object {
        private val BUGLY_APPID = "9a294885e3"
    }

    override fun init() {
        L.DEBUG = true
        //内存泄漏初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        //Bugly配置，第三个参数发布时设置为false。
        CrashReport.initCrashReport(this, BUGLY_APPID, true)

//        //初始化DBFlow
//        FlowManager.init(this)
//        //设置DBFlow日志最低等级
//        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V)

    }

}
