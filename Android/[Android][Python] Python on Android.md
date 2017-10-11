# Python on Android

## 라이브러리 종류
### pyBridge
* github 샘플 코드 : https://github.com/joaoventura/pybridge
* json 형식으로 python 스크립트 함수를 호출하는 형태.
* 기존 python 스크립트를 적용하려면 수정이 필요할 것으로 보임

### SL4A (script layer for android)
* 다양한 스크립트 언어를 안드로이드에서 동작하도록 지원하는 툴
* github : https://github.com/damonkohler/sl4a
* 또 다른 github : https://github.com/kuri65536/python-for-android
* SL4A에서 제공하는 apk를 통해 interpreter를 설치해서 사용하는 방식

### QPython
* apk로 지원
* edittext창에 python 코드 입력해서 실행할 수 있는 앱

### Jython
* 공식 사이트 : http://www.jython.org/archive/21/docs/whatis.html

### Buildozer
* github : https://github.com/kivy/buildozer

### Kivy의 P4A (python for android)
* 공식 사이트 : https://kivy.org/#home
* Python 공식 사이트에서 Kivy 설명이 되어있음 : https://pypi.python.org/pypi/python-for-android/0.4
* github : https://github.com/kivy/python-for-android
* 안드로이드는 파이썬 2, 3 모두 지원 (3은 테스트버전)
* 적용 설명서 : http://python-for-android.readthedocs.io/en/latest/
* windows에서는 동작하지 않음
* python으로 Android 앱을 만들어서 apk를 추출해주는 라이브러리  
(java코드와 혼용해서 사용하는것 아님)


----

### Android Studio 설정
* Android Studio에서 .py파일을 포함해서 빌드하기 위해서는 'Python Community Edition' 플러그인 설치필요


----
## SL4A 사용법
### SL4A apk 설치
* SL4A apk 설치 후 python interpreter 설치 필요

### 코드 사용법
* SL4A에서 제공하는 [IntentBuilders.java](
https://code.google.com/p/android-scripting/source/browse/android/Common/src/com/googlecode/android_scripting/IntentBuilders.java) 사용
* google 에서 제공하는 [Constants.java]( https://code.google.com/p/android-scripting/source/browse/android/Common/src/com/googlecode/android_scripting/Constants.java) 사용
```java
/** Called when the activity is first created. */
 @Override
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);

   Intent intent = buildStartInTerminalIntent(new File("/sdcard/sl4a/scripts/hello_world.py"));
   Log.d("SL4A Launcher", "The intent is " + intent.toString());
   startActivity(intent);

 }

 /**
 * Builds an intent that launches a script in a terminal.
 * @param script the script to launch
 * @return the intent that will launch the script
 */
 public static Intent buildStartInTerminalIntent(File script) {
   final ComponentName componentName = Constants.SL4A_SERVICE_LAUNCHER_COMPONENT_NAME;
   Intent intent = new Intent();
   intent.setComponent(componentName);
   intent.setAction(Constants.ACTION_LAUNCH_FOREGROUND_SCRIPT);
   intent.putExtra(Constants.EXTRA_SCRIPT_PATH, script.getAbsolutePath());
   return intent;
 }
}
```

* [참고사이트](https://norwied.wordpress.com/2012/04/11/run-sl4a-python-script-from-within-android-app/)

---
## pyBridge 사용법
### 선행조건
* crystax 설치 필요 : https://www.crystax.net/en/download
* `app/src/main/jni/Android.mk`의 `CRYSTAX_PATH`에 crystax 경로 설정
* `app/src/main/jni`에서 터미널 실행후 `CRYSTAX_PATH`의 `ndk-build.cmd`실행
  * 실행하면 libpybridge.so등 필요 파일 생성될 것으로 예상하나 `ndk-build.cmd`실행시 오류발생. 오류를 찾아봤지만 해결책이 나오지 않음..
* `src/main/libs`에 libcrystax, libpython3.5, libpybridge 필요

### 코드 구동 방식
```java
try {
    JSONObject json = new JSONObject();
    json.put("function", "greet"); // python의 'greet' 이라는 함수 사용
    json.put("name", "Python 3.5"); // greet의 name이라는 파라미터에 값 설정

    JSONObject result = PyBridge.call(json);
    String answer = result.getString("result"); // result로 결과 리턴

    TextView textView = (TextView) findViewById(R.id.textView);
    textView.setText(answer);

} catch (JSONException e) {
    e.printStackTrace();
}
```
* json 형태로 python의 함수와 파라미터를 설정 -> PyBrdige에 json을 call()에 넣어주면 json으로 결과값을 리턴해주는 형태
