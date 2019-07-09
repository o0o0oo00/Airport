package com.zcy.airportdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zcy.airport.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testAirport()
//        testAnyDoor()
    }

    /**
     * 无论是先注册，后发射，还是先发射，后注册，所有的观察者都会收到信息
     */
    private fun testAnyDoor() {
        AnyDoor.subscribeWorld(world1, Door<String> {
            println(" 世界 $world1  开门 1 收到信息$it")
        })

        AnyDoor.subscribeWorld(world1, Door<String> {
            println(" 世界 $world1  开门 2 收到信息$it")
        })

        // 改变世界信息 所有打开这个世界的任意门都会看见
        println("世界改变1")
        AnyDoor.changeWorld(world1, "世界改变1")

        AnyDoor.subscribeWorld(world1, Door<String> {
            println(" 世界 $world1  开门 3 收到信息$it")
        })

        AnyDoor.subscribeWorld(world1, Door<String> {
            println(" 世界 $world1  开门 4 收到信息$it")
        })

        println("世界改变2")
        AnyDoor.changeWorld(world1, "世界改变2")

        println("世界改变3")
        AnyDoor.changeWorld(world2, true)

        AnyDoor.subscribeWorld(world2, Door<Boolean> {
            println(" 世界 $world2  开门 1 收到信息$it")
        })
    }

    /**
     * 只有后注册的观察者才能收到消息，适用于不同的场景
     */
    private fun testAirport() {
        Airport.subscribeAim<String>(key1, Aim {
            println("飞机 $key1 降落 1  : 携带的信息 $it")
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

        println("飞机 $key2 起飞咯 第二次 ...")
        Airport.emit(key2, false)
    }
}
