# Javadoc generate task

## Android Studio 설정
* Tools -> generate JavaDoc 에서 다음과 같이 설정
```
Locale : ko_KR
Other command line arguments : -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
```

## gradle 구현 
* 버전 : gradle 2.4 / plugin 1.3.0
```Gradle
libraryVariants.all { variant ->
        def name = variant.name.capitalize()
        task("generate${name}Javadoc", type: Javadoc) {
            title = "title name"

            source = variant.javaCompile.source
            ext.androidJar = "${android.sdkDirectory}/platforms/${android.compileSdkVersion}/android.jar"

            classpath += files(variant.javaCompile.classpath.files)
            classpath += files(ext.androidJar)
            classpath += files(android.getBootClasspath())

            destinationDir = file("${outputDocFilePath}/dir_name/")

            options {
                links "http://docs.oracle.com/javase/7/docs/api/"
                linksOffline "http://d.android.com/reference/","${android.sdkDirectory}/docs/reference"
                docEncoding = 'UTF-8'
                encoding = 'UTF-8'
                charSet = 'UTF-8'
            }

            // include 'class 위치/class 파일명.java' // 포함
            // exclude 'class 위치/class 파일명.java' // 제외

            failOnError false
        }
    }
```