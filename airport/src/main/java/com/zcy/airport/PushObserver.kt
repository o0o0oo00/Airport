package com.zcy.airport

/**
 * @author:         zhaochunyu
 * @description:    观察者模式 - 推模型
 *                  必须先注册，后发送，才会收到数据，如果发射完再接收，不会收到数据
 *                  飞机需要知道目的地 才能降落
 * @date:           2019-07-08
 */

/*************    key   *************/
const val key1 = "key-one"
const val key2 = "key-two"
/************   key end   ***********/

/**
 * 被观察者的基类
 * 被观察者持有所有观察者的引用
 * 每次都是被观察者 ~主动~ 发送数据
 * 观察者即使注册了之后也不会收到数据，除非被观察者中发射
 */
object Airport {

    // 所有的目的地，飞机场都知道
    private val map: MutableMap<String, MutableList<Aim<Any>>> = mutableMapOf()

    // 添加一个新的目的地
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> subscribeAim(no: String, aim: Aim<T>) {
        if (!map.containsKey(no)) {
            map[no] = mutableListOf()
        }
        map[no]!!.add(aim as Aim<Any>)
    }

    // 飞机起飞，飞往所有目的地
    fun emit(no: String, plane: Any) {
        map[no]?.forEach {
            it.arrive(plane)
        }
    }

}

/** 观察者基类
 *  目的地
 */
class Aim<in T>(private var block: (T) -> Unit) {
    // 飞机降落
    fun arrive(plane: T) {
        try {
            block.invoke(plane)
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
    }
}