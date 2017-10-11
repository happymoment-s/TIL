# Failed to resolve: com.android.support:26.+

# 해결 방법
* 컴파일 SDK 버전 Oreo(8.0, 26) 부터는 프로젝트의 build.gradle에 구글 maven 주소를 추가해야 한다.
```gradle
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
    }
}

allprojects {
    repositories {
        jcenter()
        // 아래와 같이
        maven {
            url "https://maven.google.com"
        }
    }
}
```

# 참고 사이트
* [Failed to resolve: com.android.support](http://comoi.io/208)
