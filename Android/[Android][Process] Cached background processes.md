# Cached background processes

## 설정 확인법
* 실행중인 서비스 우측 상단에서 '캐시된 프로세스' 또는 '임시 저장된 프로세스' 클릭

## 서비스 동작여부 확인
* 캐시된 백그라운드 프로세스 서비스는 adb로 실행중인 서비스 체크시 나타나지 않음
  * adb shell dumpsys activity services | grep

## cached로 빠지는 이유
* 확인중

## 참고 사이트
 * [on-android-whats-the-difference-between-running-processes-and-cached-backgroun](http://stackoverflow.com/questions/14259504/on-android-whats-the-difference-between-running-processes-and-cached-backgroun)