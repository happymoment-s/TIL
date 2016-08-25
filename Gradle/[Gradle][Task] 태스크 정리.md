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
--------
* mustRunAfter 의 경우 A 태스크와 B 태스크가 동시에 실행될 경우 A와 B의 순서를 정의해주는 관계
```
A.mustRunAfter B
```
* 위와 같이 설정 후 gradlew A 라고 실행했을 경우 B 다음 A가 실행되는것이 아니라 그냥 A만 실행된다.
gradlew A B 라고 실행했을 경우 두개의 태스크가 동시에 실행되니, B다음 A가 실행되게 해주는 역할밖에 하지 않는다.
--------
```
A.dependsOn B
A.dependsOn C
B.mustRunAfter C
```
* 위와 같이 설정한 후 gradlew A 라고 실행했을 경우 C -> B -> A 순서대로 태스크가 실행된다. 이럴때 mustRunAfter 사용.



## 참고 사이트
* mustRunAfter : [http://trickyandroid.com/gradle-tip-3-tasks-ordering/](http://trickyandroid.com/gradle-tip-3-tasks-ordering/)
