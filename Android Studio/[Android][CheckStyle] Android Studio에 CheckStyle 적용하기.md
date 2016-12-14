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