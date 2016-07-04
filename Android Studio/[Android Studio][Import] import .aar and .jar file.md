# aar 또는 jar 파일 import 하는 방법

## 1. aar 파일 import

### 1.1. Module Settings 방법
- 참고사이트 : [Adding AAR Files to Your Android Studio Project] (http://docs.millennialmedia.com/android-ad-sdk/adding-aar-files.html) 

1. application 프로젝트 마우스 우측 클릭 -> Open Module Settings 클릭
2. 좌측 상단에 초록색 '+' 버튼(New Module) 클릭
3. 'Import .JAR or .AAR Package' 선택후 우측 하단에 'Next' 버튼 클릭 -> ... 버튼으로 aar 파일 선택 -> 'Finish' 버튼 클릭
4. 우측 상단에 'Dependencies' 클릭 -> 우측 상단에 초록색 '+' 버튼 클릭 -> '3. Module Dependency' 클릭 -> aar 클릭

### 1.2. gradle 설정 방법
- 참고사이트 : [Reference a local .aar in your Android project] (http://kevinpelgrims.com/blog/2014/05/18/reference-a-local-aar-in-your-android-project/)
- 해당 방식은 스크립트로 동적 library 파일 import 방식으로 사용가능할 것으로 보인다.

1. aar 파일을 application 프로젝트 안에 원하는 위치에 복사, 일반적으로 [app]/libs 폴더나 [app]/aars 폴더를 생성해서 넣는다.
2. app의 build.gradle에서 다음과 같이 설정
```Gradle
repositories {
    flatDir {
        dirs 'libs' // aar파일이 들어있는 폴더 위치
    }
}

dependencies {
    compile(name:'libraryfilename', ext:'aar')
    // 또는
    // compile 'library package name:library name:library version@aar' // ex) 'com.kevinpelgrims.library:library:1.0.0@aar' 
}
```


## 2. jar 파일 import

### 2.1. Module Settings 방법
1.1. 참조

### 2.2. 오른쪽 버튼으로 설정 방법
1. jar 파일을 특정 위치에 복사, 일반적으로 [app]/libs 폴더
2. jar 파일 오른쪽 버튼 클릭 -> 'Add As Library...' 버튼 클릭

### 2.3. gradle 설정 방법
```Gradle
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile files('libs/libraryfilename.jar')
}
```