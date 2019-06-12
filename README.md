# ZefClock
an alarm clock apk

06-11-20：
1.Include a login activity, a timeset activity, an edit activity and a time service.
2.Timeset activity calls a timeservice where a reciver watchdog the time change(every min).
3.The other modules' interfaces should be called in the receivers' Onreceive() method.
4.The time check has been set.
5.But the param of interfaces should be added to the entity "Centime" which can be conveyed to the tiemservice.
6.So plz realize your modules & their interfaces First(today).
7.加了gradle看可以不可以，不能编译的话这库就白给了
8.现在能到时间后跳转了

06-12:
1.目前一直闹钟后台能调用音乐和语音操作了，分享文档也完成了，但不太了解消息弹出和摇晃终止的模块进展如何。
2.我整了一个我这个用例似乎有点实际作用的接口...就是重新开启后台服务（监听时间）的接口，或许在后期关闭闹钟之后（原本的后台服务中断）的重新操作（如复用或者修改等）有所帮助。
