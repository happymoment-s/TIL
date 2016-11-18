# Android Battery Histroian 2.0 사용법


## 설정 순서
 * GO 설치 - 환경변수 설정
 * python 2.7x 버전 설치
 * 자바, git 설치
 * Battery Historian 설치
	```
	go get -d -u github.com/google/battery-historian/
	```
 * Battery Historian 시작

	```
	$ cd $GOPATH/src/github.com/google/battery-historian
	
	$ go run setup.go
	
	$ go run cmd/battery-historian/battery-historian.go [--port <default:9999>]
	```
 * http://localhost:9999 에 접속


## adb 명령어
 * adb shell dumpsys batterystats --reset
 * adb shell dumpsys batterystats --enable full-wake-history
 * adb bugreport > [원하는 파일명].txt


## 참고 사이트
 * [공식 github 사이트](https://github.com/google/battery-historian)
 * [참고사이트1](http://goodtogreate.tistory.com/entry/Battery-Historian-20-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95)
 * [참고사이트2 - 윈도우즈](http://linsoo.co.kr/entry/%EC%9C%88%EB%8F%84%EC%9A%B0%EC%97%90%EC%84%9C-Battery-Historian-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0)


