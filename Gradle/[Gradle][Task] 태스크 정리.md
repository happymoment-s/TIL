# Gradle Task

## 태스크 정의
* 태스크 실행부 : << 사용후 클로저 안의 내용  
태스크 실행은 빌드 생명주기의 실행 단계에서 이루어짐
```Gradle
task hello1 << {

}
```
* 태스크 설정부 : << 를 사용하지 않은 클로저 안의 내용  
태스크 설정은 빌드 설정 단계에서 처리
```Gradle
task hello2 {

}
```
* gradle hello hello2 실행시 결과
```Gradle
hello2: // 빌드 설정 단계에서 먼저 처리됨
:hello // 태스크 시작
hello: // 태스크 실행
:hello2 UP-TO-DATE // 태스크 시작 하려니 설정단계에서 처리되어서 UP-TO-DATE 처리됨
```


## 태스크 의존관계, 실행 순서
* B 태스크 실행 후 A 태스크 실행
```Gradle
A.dependsOn B
```
```
A.mustRunAfter B
```