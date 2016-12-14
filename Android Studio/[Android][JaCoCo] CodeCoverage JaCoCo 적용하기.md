# CodeCoverage JaCoCo 적용하기

## 설정
* build.gradle 설정
```gradle
apply plugin: 'com.android.application'

android {
    ...
}

dependencies {
    ...
}

// jacoco 플러그인 설정
apply plugin: 'jacoco'
jacoco {
    reportsDir = file("${buildDir}/reports")
}

// 커버리지 측정 태스크 정의 (이름은 변경 가능)
task coverageReport(type: JacocoReport, dependsOn: 'testDebugUnitTest') { // 테스트 수행 후 커버리지 측정 될 수 있도록 depend 적용
    group = "Reporting"
    description = "Generate Jacoco coverage reports"

	// 커버리지 측정할 소스 디렉토리 지정
    def coverageSourceDirs = ['src/main/java'] 
	// *.class 파일이 있는 디렉토리 지정
    classDirectories = fileTree( 
            dir: "${buildDir}/intermediates/classes/debug",
            excludes: ['**/R.class',
                       '**/R$*.class',
                       '**/BuildConfig.*',
                       '**/Manifest*.*',
                       'com/android/**/*.class']
    )
    sourceDirectories = files(coverageSourceDirs)
	// 커버리지 측정 겨로가를 저장할 파일 이름 지정
    executionData = files("${buildDir}/jacoco/testDebugUnitTest.exec")
	// 결과 리포트 형식 지정
    reports {
        xml.enabled = true
        html.enabled = true
    }
}
```

## 실행 방법
* sync 후 terminal에서 task실행 (task명은 변경 가능)
```
gradlew coverageReport
```
* 결과는 app/build/reports/태스크명/html/index.html 실행시 확인
* 단위테스트가 있는 함수는 초록색 영역으로, 안된 함수는 빨간색 영역으로 표시됨

## 참고사이트
 * [jacoco 적용하기](http://kunny.github.io/lecture/quality/2016/02/13/jacoco_unit_test_android/)
