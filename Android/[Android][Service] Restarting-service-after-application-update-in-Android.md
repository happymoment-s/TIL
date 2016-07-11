# Restarting service after application update in Android

앱을 업데이트 하게될 경우 백그라운드 서비스가 죽게된다. 그때 백그라운드 서비스 다시 살리는 방법이다.

테스트 완료, 알람은 앱을 업데이트해도 사라지지 않는다.

- [참조사이트] (https://andreynovikov.info/programming-android-updated-service-restart-en.html)

## 구현방식
- 앱 업데이트시 호출되는 broadcast reciever를 이용하여 체크하는 방식
- DataScheme으로 'pakcage' 라고 설정시 app의 패키지명이 리시버로 전달된다.

## Manifest 또는 code 설정
- Manifest
```Java
<receiver android:name=".AppCycleReceiver">
    <intent-filter>
        <action android:name="android.intent.action.PACKAGE_REPLACED" />
        <data android:scheme="package" />
    </intent-filter>
</receiver>
```

- code
```Java
mAppCycleReceiver = new AppCycleReceiver();
filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
filter.addDataScheme("package");
registerReceiver(mAppCycleReceiver, filter);
```
## 리시버 구현
```Java
public class AppCycleReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_PACKAGE_REPLACED.equals(action)){
            String packageName = intent.getData().getSchemeSpecificPart();
            if (context.getPackageName().equals(packageName)) {
              // service start  
            }
        }
    }
}
```
