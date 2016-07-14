# Android Low Memory Killer

## killer에 의해 제거되는 프로세스
1. Service
2. Alarm

## 유지 방법
### service
- START_STICKY 사용
- HeartBeat 알람 등록 (서비스가 구동중인지 확인하여 죽었으면 다시 살리는 역할)
  - [TIL START_STICKY](https://github.com/happymoment-s/TIL/blob/master/Android/%5BAndroid%5D%5BService%5D%20START_STICKY.md)
- [startForeground()](https://developer.android.com/reference/android/app/Service.html#startForeground%28int,%20android.app.Notification%29) 사용으로 foreground 상태 유지

### Alarm
 - 찾는중..ㅠ

## 참고 사이트
- [안드로이드 커널 분석](http://www.ikpu.ac.kr/clcms/contents/0000000206//07/07_10.htm)