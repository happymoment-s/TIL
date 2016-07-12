# Android Service ������ START_STICKY �̽�


## ����
```
int START_STICKY
Constant to return from onStartCommand(Intent, int, int): if this service's process is killed while it is started (after returning from onStartCommand(Intent, int, int)), then leave it in the started state but don't retain this delivered intent. Later the system will try to re-create the service. Because it is in the started state, it will guarantee to call onStartCommand(Intent, int, int) after creating the new service instance; if there are not any pending start commands to be delivered to the service, it will be called with a null intent object, so you must take care to check for this.

This mode makes sense for things that will be explicitly started and stopped to run for arbitrary periods of time, such as a service performing background music playback.
```

* ���񽺰� process kill �Ǿ��� �� ������ �״�(onDestory)���� �ƴ϶� ����ִ� ������ ����

## �ڵ� ����
```Java
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
```
�Ǵ�
```Java
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
```

* return START_STICKY; �� return super.onStartCommand(intent, flags, startId); �� ������ ���� �����Ѵ�.

## �߰� ����
* os 4.4 �������� START_STICKY�� ����Կ��� �ұ��ϰ� ���񽺰� ��Ƴ��� �ʴ� ���װ� �����Ͽ�, onTaskRemoved() ȣ��� �˶��� �����Ͽ� ���񽺸� �ٽ� �츮�� �ڵ尡 �߰� �ʿ�.
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


## ���� ����Ʈ

- [stackoverflow start-sticky-does-not-work-on-android-kitkat-edit-and-jelly-bean](http://stackoverflow.com/questions/20636330/start-sticky-does-not-work-on-android-kitkat-edit-and-jelly-bean)
- [AltBeacon github service �κ�](https://github.com/AltBeacon/android-beacon-library/blob/master/src/main/java/org/altbeacon/beacon/service/BeaconService.java)
 - [Google developer Service](https://developer.android.com/reference/android/app/Service.html)