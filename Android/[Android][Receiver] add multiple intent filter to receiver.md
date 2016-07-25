# add multiple intent filter to receiver

## 에러 상황
* 다음과 같이 intent filter를 중복해서 적용했더니 receiver에서 action이 전달되지 않는 현상 발생
   * BOOT_COMPLETED가 발생 안됨 ㅠㅠ
```Android
        <receiver
            android:name=".TestReceiver"
            android:enabled="false"
            android:exported="false" >
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
                <action android:name="android.intent.action.PACKAGE_REPLACED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>
```

## 수정
* 다음과 같이 <intent-filter>를 나눠서 적용.. 잘 동작함
```Android
        <receiver
            android:name=".TestReceiver"
            android:enabled="false"
            android:exported="false" >
            <intent-filter>
                <action android:name="android.intent.action.BOOT_COMPLETED" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.PACKAGE_REPLACED"/>
                <data android:scheme="package"/>
            </intent-filter>
        </receiver>
```

## 참고 사이트
* [http://stackoverflow.com/questions/6515017/android-receiver-for-multiple-actions](http://stackoverflow.com/questions/6515017/android-receiver-for-multiple-actions)