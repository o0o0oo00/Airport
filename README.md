# AirportDemo
观察者模式-推模型/拉模型  实现消息/事件的传递  代替EventBus（ 简易情况下）

**推模型：事先注册观察者，然后由被观察者统一发送信息给观察者  
拉模型：观察者后来注册，依然可以主动去被观察者中取出最新数据**

推模型用法：
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

```
```
System.out: 飞机 key-one 起飞咯 ...
System.out: 飞机 key-one 降落 1  : 携带的信息 123
System.out: 飞机 key-one 降落 2  : 携带的信息 123
System.out: 飞机 key-two 起飞咯 ...
System.out: 飞机 key-two 降落 1  : 携带的信息 true
System.out: 飞机 key-two 起飞咯 第二次 ...
System.out: 飞机 key-two 降落 1  : 携带的信息 false
```

拉模型用法：

```
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
```
```
  System.out:
  世界改变1
  世界 world-1  开门 1 收到信息世界改变1
  世界 world-1  开门 2 收到信息世界改变1
  世界 world-1  开门 3 收到信息世界改变1
  世界 world-1  开门 4 收到信息世界改变1
  世界改变2
  世界 world-1  开门 1 收到信息世界改变2
  世界 world-1  开门 2 收到信息世界改变2
  世界 world-1  开门 3 收到信息世界改变2
  世界 world-1  开门 4 收到信息世界改变2
  世界改变3
  世界 world-2  开门 1 收到信息true

```
