# Android Studio에 CheckStyle 적용하기

## Plugin 설치 
* Setting -> Plugin -> Browse repositories... 클릭 -> CheckStyle 검색 -> CheckStyle-IDEA 설치 -> Studio 재실행
* 재 실행 후 Settings(Android Studio > Preferences) -> Other Settings -> CheckStyle을 선택  

## 실행
* 오른쪽 하단에 CheckStyle 탭이 생김 -> 초록색 실행 버튼 누르면 파일단위로 검사, 실행버튼 아래 버튼은 모듈 단위로 검사

## 편집 속도가 느려지는 경우
* Code편집 시 마다 CheckStyle이 실행되면서 검사하기 때문.  
  File -> Settings(Android Studio -> Preferences) -> Editor -> Inspections에서 CheckStyle 항목을 Uncheck하고 OK

## 참고사이트
* [checkStyle 적용](https://www.davidlab.net/ko/tech/android-studio-tips-applying-google-style-guide/)

---

# Jenkins에서 CheckStyle 적용방법

## 설정
* Plug-in 설치  
  - Checkstyle Plug-in
  - Static Analysis Collector Plug-in
* Jenkins 프로젝트의 설정
  - Build설정의 'Invoke Gradle script'에서 'Tasks'항목에 'checkstyle' 추가 (gradle에 checkstyle 태스크 정의되어야함)
  - 빌드 후 조치에 'Publish Checkstyle analysis results' 추가 -> Checkstyle results 칸에 **/app/build/reports/checkstyle/*.xml 추가
* 소스코드 체크는 gradle 에서 수행하고, 그 결과만 jenkins의 결과분석 툴로 분석하는 형태

## Studio의 gradle 설정
```gradle
apply plugin: 'checkstyle' // 플러그인 설정
task checkstyle(type: Checkstyle) {
    configFile file("${project.rootDir}/indoornow_checks.xml") // indoornow 체크 설정
    source 'src/main/java'
    include '**/*.java'
    exclude '**/gen/**'
    exclude '**/R.java'
    exclude '**/BuildConfig.java'

    ignoreFailures = true // 빌드 실패 무시
    classpath = files()
}
``` 
  
## 참고사이트
* [Jenkins 정적분석(PMD, FindBug, CheckStyle) 구축과 실행 결과](http://pseg.or.kr/pseg/?mid=infouse&document_srl=4840&listStyle=viewer)
