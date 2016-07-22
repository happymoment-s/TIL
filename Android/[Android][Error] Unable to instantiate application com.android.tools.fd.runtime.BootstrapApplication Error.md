# Unable to instantiate application com.android.tools.fd.runtime.BootstrapApplication Error

## 에러 로그
```
07-22 17:06:22.625 11966-11966/[packageName] E/AndroidRuntime: FATAL EXCEPTION: mainProcess: [packageName], PID: 11966
 java.lang.RuntimeException: Unable to instantiate application com.android.tools.fd.runtime.BootstrapApplication: java.lang.ClassNotFoundException: Didn't find class 'com.android.tools.fd.runtime.BootstrapApplication' on path: DexPathList[[zip file '/data/app/[packageName]-2.apk'],nativeLibraryDirectories=[/data/app-lib/[packageName]-2, /vendor/lib, /system/lib]]
 at android.app.LoadedApk.makeApplication(LoadedApk.java:507)
 at android.app.ActivityThread.handleBindApplication(ActivityThread.java:4745)
 at android.app.ActivityThread.access$1600(ActivityThread.java:170)
 at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1364)
 at android.os.Handler.dispatchMessage(Handler.java:102)
 at android.os.Looper.loop(Looper.java:146)
 at android.app.ActivityThread.main(ActivityThread.java:5635)
 at java.lang.reflect.Method.invokeNative(Native Method)
 at java.lang.reflect.Method.invoke(Method.java:515)
 at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1291)
 at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1107)
 at dalvik.system.NativeStart.main(Native Method)
Caused by: java.lang.ClassNotFoundException: Didn't find class 'com.android.tools.fd.runtime.BootstrapApplication' on path: DexPathList[[zip file '/data/app/[packageName]-2.apk'],nativeLibraryDirectories=[/data/app-lib/[packageName]-2, /vendor/lib, /system/lib]]
 at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:67)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:497)
 at java.lang.ClassLoader.loadClass(ClassLoader.java:457)
 at android.app.Instrumentation.newApplication(Instrumentation.java:981)
 at android.app.LoadedApk.makeApplication(LoadedApk.java:502)
 at android.app.ActivityThread.handleBindApplication(ActivityThread.java:4745) 
 at android.app.ActivityThread.access$1600(ActivityThread.java:170) 
 at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1364) 
 at android.os.Handler.dispatchMessage(Handler.java:102) 
 at android.os.Looper.loop(Looper.java:146) 
 at android.app.ActivityThread.main(ActivityThread.java:5635) 
 at java.lang.reflect.Method.invokeNative(Native Method) 
 at java.lang.reflect.Method.invoke(Method.java:515) 
 at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1291) 
 at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1107) 
 at dalvik.system.NativeStart.main(Native Method) 
```

## 원인, 해결방법
**1. Misconfiguration of AndroidManifest.xml**

```Android
<application 
       android:name="Novak ESC Track guide" 
       android:icon="@drawable/icon" 
       android:label="@string/app_name" 
       android:description="@string/help_text" >
```
* name 필드는 더 이상 사용하지 않습니다. label 필드를 사용하세요.

```Android
<application 
    android:icon="@drawable/icon" 
    android:label="@string/app_name" 
    android:description="@string/help_text" >
```

**2. "lib" 폴더 대신 "libs" 폴더 사용**

**3. ADT/SDK/IDE 를 최신버전으로 업데이트**

**4. Android Studio에서 "Settings" 수정**
  * Disable the Instant Run option in Android Studio. Instructions are in the Android Studio Instant Run documentation.
Instant Run tries to do hot swapping of your code; this causes the application class to be moved.
To disable Instant Run Go to File --> Settings--> Build,Execution,Deployment -->Instant Run ---> uncheck "Enable instant run"

**5. Gradle 버전, 설정 변경**
 
Changing:
```
classpath 'com.android.tools.build:gradle:2.0.0-alpha1'
```
By:
```
classpath 'com.android.tools.build:gradle:1.2.3'
```

Changing:
```
buildToolsVersion '23.0.2'
```
By:
```
buildToolsVersion "21.1.2"
```
3/3: (in <project folder>/.idea/gradle.xml)
And:
```
<option name="gradleHome" value="$APPLICATION_HOME_DIR$/gradle/gradle-2.8" />
```
By:
```
<option name="gradleHome" value="$APPLICATION_HOME_DIR$/gradle/gradle-2.4" />
```
## 참고사이트
 * [Unable to instantiate application com.android.tools.fd.runtime.BootstrapApplication ?Android](http://stackoverflow.com/questions/33967703/unable-to-instantiate-application-com-android-tools-fd-runtime-bootstrapapplicat)
 * [java.lang.ClassNotFoundException on working app](http://stackoverflow.com/questions/3781151/java-lang-classnotfoundexception-on-working-app)