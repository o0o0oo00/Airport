package com.zcy.airport

/**
 * @author:         zhaochunyu
 * @description:    拉模型-只要订阅，就可以拿到数据
 *                  后注册观察者，主动去拿被观察者里面的数据
 *                  可以打开任意门 访问到任何地方，
 * @date:           2019-07-09
 */

const val world1 = "world-1"
const val world2 = "world-2"

object AnyDoor {
    // 一把钥匙 对应一个世界
    private val map: MutableMap<String, World> = mutableMapOf()

    // 可以有很多个门户来观察这个世界
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> subscribeWorld(key: String, door: Door<T>) {
        if (!map.containsKey(key)) {
            map[key] = World()
        }
        map[key]?.let { world ->
            world.subscribe(door.apply {
                world.data?.let { update(world) }
            } as Door<Any>)
        }
    }

    fun changeWorld(key: String, data: Any) {
        if (!map.containsKey(key)) {
            map[key] = World()
        }
        map[key]?.change(data)
    }

}

class World {
    private val list: MutableList<Door<Any>> = mutableListOf()

    fun subscribe(door: Door<Any>) {
        list.add(door)
    }

    var data: Any? = null

    fun change(data: Any) {
        this.data = data
        list.forEach {
            it.update(this)
        }
    }
}

class Door<T : Any>(private val block: (T) -> Unit) {

    @Suppress("UNCHECKED_CAST")
    fun update(world: World) {
        block.invoke(world.data as T)
    }

}