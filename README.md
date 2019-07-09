# AirportDemo
观察者模式-推模型/拉模型  实现消息/事件的传递  代替EventBus（ 简易情况下）

**推模型：事先注册观察者，然后由被观察者统一发送信息给观察者  
拉模型：观察者后来注册，依然可以主动去被观察者中取出数据**

推模型实现：
```
Airport.subscribeAim<String>(key1, Aim {
    println("飞机 $key1 降落 1  : 携带的信息 $it")
})

Airport.subscribeAim<String>(key1, Aim {
    println("飞机 $key1 降落 2  : 携带的信息 $it")
})

println("飞机 $key1 起飞咯 ...")
Airport.emit(key1, "123")

Airport.subscribeAim<String>(key1, Aim {
    // ！！！！ **will not receiver**  ！！！！
    println("飞机 $key1 降落 3  : 携带的信息 $it")
})

Airport.subscribeAim(key2, Aim<Boolean> {
    println("飞机 $key2 降落 1  : 携带的信息 $it")
})

println("飞机 $key2 起飞咯 ...")
Airport.emit(key2, true)

```
拉模型实现：

wait...
