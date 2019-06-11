# ZefClock
an alarm clock apk

06-11-20：
1.Include a login activity, a timeset activity, an edit activity and a time service.
2.Timeset activity calls a timeservice where a reciver watchdog the time change(every min).
3.The other modules' interfaces should be called in the receivers' Onreceive() method.
4.The time check has been set.
5.But the param of interfaces should be added to the entity "Centime" which can be conveyed to the tiemservice.
6.So plz realize your modules & their interfaces First(today).


