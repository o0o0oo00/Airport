# AirportDemo
观察者模式-推模型/拉模型  实现消息/事件的传递  代替EventBus（ 简易情况下）

```
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

```