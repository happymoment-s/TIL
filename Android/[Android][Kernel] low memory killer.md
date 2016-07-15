# Android Low Memory Killer

## killer에 의해 제거되는 프로세스
1. Service
2. Alarm (알람이 죽는다는 리포트를 보긴했으나 실제로 os가 알람을 죽이는지 명확하지는 않음..다른 정리앱이 죽인걸수도..)

## 유지 방법
### service
- START_STICKY 사용
   - [TIL START_STICKY](https://github.com/happymoment-s/TIL/blob/master/Android/%5BAndroid%5D%5BService%5D%20START_STICKY.md)
- HeartBeat 알람 등록 (서비스가 구동중인지 확인하여 죽었으면 다시 살리는 역할)
- [startForeground()](https://developer.android.com/reference/android/app/Service.html#startForeground%28int,%20android.app.Notification%29) 사용으로 foreground 상태 유지

### Alarm
 - 찾는중..ㅠ

## 참고 사이트
- [안드로이드 커널 분석](http://www.ikpu.ac.kr/clcms/contents/0000000206//07/07_10.htm)
- [Effective Handling of Low Memory
Scenarios in Android](http://www.it.iitb.ac.in/frg/wiki/images/f/f4/113050076_Rajesh_Prodduturi_Stage-01_report_8_113050076_stage01.pdf)
- [stack](http://stackoverflow.com/questions/15862651/android-kills-broadcast-receivers-on-system-low-memory)
