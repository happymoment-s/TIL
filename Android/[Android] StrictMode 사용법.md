# StrictMode 사용법

d

w


## 적용방법
```JAVAdasdsad


StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectNetwork()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
```

## 샘플
JAVAdasdsad
asd
```
D/StrictMode: StrictMode policy violation; ~duration=18 ms: android.os.StrictMode$StrictModeDiskReadViolation: policy=65543 violation=2
    at android.os.StrictMode$AndroidBlockGuardPolicy.onReadFromDisk(StrictMode.java:1263)
    at android.app.SharedPreferencesImpl.awaitLoadedLocked(SharedPreferencesImpl.java:203)
    at android.app.SharedPreferencesImpl.getString(SharedPreferencesImpl.java:224)
    at android.view.View.performClick(View.java:5210)
    at android.view.View$PerformClick.run(View.java:21328)
    at android.os.Handler.handleCallback(Handler.java:739)
    at android.os.Handler.dispatchMessage(Handler.java:95)
    at android.os.Looper.loop(Looper.java:148)
    at android.app.ActivityThread.main(ActivityThread.java:5551)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:730)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:620)
```



JAVAdasdsad

d
d

## 참고사이트
* [안드로이드 앱 성능 최적화 – 1. 즉각적인 반응을 위한 StrictMode 사용](http://www.kmshack.kr/2013/04/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%95%B1-%EC%84%B1%EB%8A%A5-%EC%B5%9C%EC%A0%81%ED%99%94-1-%EC%A6%89%EA%B0%81%EC%A0%81%EC%9D%B8-%EB%B0%98%EC%9D%91%EC%9D%84-%EC%9C%84%ED%95%9C-strictmode/)
