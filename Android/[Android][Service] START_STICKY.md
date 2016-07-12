# Android Service 종류인 START_STICKY 이슈


## 설명
```
int START_STICKY
Constant to return from onStartCommand(Intent, int, int): if this service's process is killed while it is started (after returning from onStartCommand(Intent, int, int)), then leave it in the started state but don't retain this delivered intent. Later the system will try to re-create the service. Because it is in the started state, it will guarantee to call onStartCommand(Intent, int, int) after creating the new service instance; if there are not any pending start commands to be delivered to the service, it will be called with a null intent object, so you must take care to check for this.

This mode makes sense for things that will be explicitly started and stopped to run for arbitrary periods of time, such as a service performing background music playback.
```

* 서비스가 process kill 되었을 때 완전히 죽는(onDestory)것이 아니라 살아있는 형태의 설정

## 코드 구현
```Java
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
```
또는
```Java
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
```

* return START_STICKY; 와 return super.onStartCommand(intent, flags, startId); 는 동일한 값을 리턴한다.

## 추가 사항
* os 4.4 버전에서 START_STICKY를 사용함에도 불구하고 서비스가 살아나지 않는 버그가 존재하여, onTaskRemoved() 호출시 알람을 설정하여 서비스를 다시 살리는 코드가 추가 필요.
```Java
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (Build.VERSION.RELEASE.contains("4.4.1") ||
                Build.VERSION.RELEASE.contains("4.4.2") ||
                Build.VERSION.RELEASE.contains("4.4.3")) {
            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, getRestartIntent());
            LogManager.d(TAG, "Setting a wakeup alarm to go off due to Android 4.4.2 service restarting bug.");
        }
    }
```


## 참고 사이트

- [stackoverflow start-sticky-does-not-work-on-android-kitkat-edit-and-jelly-bean](http://stackoverflow.com/questions/20636330/start-sticky-does-not-work-on-android-kitkat-edit-and-jelly-bean)
- [AltBeacon github service 부분](https://github.com/AltBeacon/android-beacon-library/blob/master/src/main/java/org/altbeacon/beacon/service/BeaconService.java)
 - [Google developer Service](https://developer.android.com/reference/android/app/Service.html)