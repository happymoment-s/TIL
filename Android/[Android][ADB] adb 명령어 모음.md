# adb 명령어 모음

## 서비스 실행 여부
```
adb shell dumpsys activity services | grep [검색명]
```

## 서비스 시작, 중지
```
adb shell am startservice [INTENT]
```
```
adb shell am stopservice [INTENT]
```
* [INTENT] 예시 : app.package.name/.example.package.path.MyServiceClass

## apk 설치
```
adb install [apk파일 경로]
```

## app disable 설정(삼성 스마트매니저 절전상태)
```
adb shell am [package명]
```
