package com.zcy.airportdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zcy.airport.Aim
import com.zcy.airport.Airport
import com.zcy.airport.key1
import com.zcy.airport.key2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Airport.subscribeAim<String>(key1, Aim {
            println("飞机 $key1 降落 1  : $it")
        })

        Airport.subscribeAim<String>(key1, Aim {
            println("飞机 $key1 降落 2  : 携带的信息 $it")
        })

        println("飞机 $key1 起飞咯 ...")
        Airport.emit(key1, "123")

        Airport.subscribeAim<String>(key1, Aim {
            // ！！！！ will not receiver  ！！！！
            println("飞机 $key1 降落 3  : $it")
        })

        Airport.subscribeAim(key2, Aim<Boolean> {
            println("飞机 $key2 降落 1  : 携带的信息 $it")
        })

        println("飞机 $key2 起飞咯 ...")
        Airport.emit(key2, true)

    }
}
