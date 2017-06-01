# 이클립스 동작시 참고해야할 Android SDK version



### Android 빌드 오류
* Android SDK Tools 26.x.x 버전부터 명령어 변경으로 이클립스나 ant로 android 빌드시 에러 발생
* 반드시 Android SDK Tools 25.2.5 이하버전을 사용

### .aidl 파일을 .java파일로 compile 하지 못하는 오류
* Android SDK Build-tools 25.0.3 이상일 경우 .aidl 파일을 .java파일로 compile 하지 못하는 오류 발생
* 반드시 Android SDK Build-tools 25.0.2 이하로 사용
