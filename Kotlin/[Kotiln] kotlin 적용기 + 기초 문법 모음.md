# Kotlin 적용기

## 설정

### Android Studio 설정
* plugin 설치
  * ```File > Settings > Plugins > Browse JetBrains Plugins > Kotlin``` 설치
* 프로젝트에 적용
  * ```Android Studio > Tools > Kotlin > Configure Kotlin in Project```
  * 모듈 전체 혹은 개별 모듈에 적용 가능
* 적용 후
```gradle
// Project/build.gradle
buildscript {
    ext.kotlin_version = '1.1.0'
    dependencies {
        ...
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}```
```gradle
// app/build.gradle
dependencies {
    ...
    compile "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
}
```
### Project 설정
* MainActivity 에서 Help > Find Action > Convert Java File To Kotlin File (ctrl + alt + shift + k)
MainActivity가 .kt 확장자로 변경되면서 Kotlin 문법으로 변경된다.

### 문법
* 상속, 구현 - 모두 ':' 구분자 뒤에 클래스명() 으로 설정
```java
class KotlinActivity : AppCompatActivity() {
}
```
```java
class MapActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
```

* 변수  
1) 타입 뒤에 '?'를 사용하면 null을 선언할 수 있다.  
2) 변수뒤에'!!'를 사용하면 null인 경우 exception이 발생한다.  
3) '?:'을 이용하여 null일 경우 대체값을 지정할 수 있다.
```Java
/*1*/ var mVariable: Int? null
/*2*/ mVariable!!.toString()
/*3*/ mVariable.toStroing() ?: "null"
```

* static 변수 사용시 - 'companion object {}' 안에서 변수 정의
```java
// 일반 TAG 정의시
companion object {
    private val TAG = MainActivity::class.java.simpleName
}
```

* String에 변수 사용시 - ""(쌍따움표) 안에서 '$' 표시로 변수 접근 가능
```java
var code = 0
var message = "message"
var logString = "변수 접근 $code $message"
```

* 형변환 - 맨 뒤에 'as' 를 붙이고 그 이후 변경 Type을 명시
```Java
mCastType = mTest!!.get(Type.CastType) as CastType
```

* 함수 - 'fun' 으로 정의
```Java
override fun onCreate() {
}
```

* 단일 Listener 이용시  
1) 람다식을 사용하여 파라미터를 접근 할 수 있다.  
2) 파라미터의 리스너가 하나밖에 없다면 리스너를 생성하는것을 생략 가능하다.
```java
// 1
mTest!!.addErrorListener {
    code, message -> Log.d(TAG, "$code $message")
}
// 2
mTest!!.connect {
    Log.d(TAG, "onConnected()")
}
```

* List의 특정 index 접근 - '[index]' 형식으로 접근
```java
mBrandList!![0].id
```

* 객체의 getter, setter 사용시 - 그냥 '.' 사용하여 접근 가능
```java
mBrand.id
```

* 위젯 접근 - 위젯의 id로 바로 사용 가능  
gradle에서 extensions를 추가해야하며,  
id로 사용시 kotlinx.android.synthetic.{sourceSetName}.{layoutName}.* 형태로 import가 발생된다.
```java
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'
```
```java
// import kotlinx.android.synthetic.main.activity_main.*
fun initView() {
    activity_main_button_imageView.setOnClickListener {
    }
}
```

* 기타
  * 클래스의 이름 가져올때
  ```java
  MainActivity::class.java.simpleName
  ```
