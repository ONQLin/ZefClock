# ZefClock
an alarm clock apk

06-11-20：
Include a login activity, a timeset activity, an edit activity and a time service.
Timeset activity calls a timeservice where a reciver watchdog the time change(every min).
The other modules' interfaces should be called in the receivers' Onreceive() method.
The time check has been set.
But the param of interfaces should be added to the entity "Centime" which can be conveyed to the tiemservice.
So plz realize your modules & their interfaces First(today).


