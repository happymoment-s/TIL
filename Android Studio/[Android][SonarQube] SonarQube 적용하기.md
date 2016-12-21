# SonarQube 적용하기


## Windows에서 서버 설정
* mysql 설치 [http://dev.mysql.com/downloads/mysql/](http://dev.mysql.com/downloads/mysql/)  
  user 설정 (IDENTIFIED가 비밀번호)
```
mysql -u root -p

CREATE DATABASE sonar CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'sonar' IDENTIFIED BY 'sonar';
GRANT ALL ON sonar.* TO 'sonar'@'%' IDENTIFIED BY 'sonar';
GRANT ALL ON sonar.* TO 'sonar'@'localhost' IDENTIFIED BY 'sonar';
FLUSH PRIVILEGES;
```

* SonarQube 설치 [http://www.sonarqube.org/downloads/](http://www.sonarqube.org/downloads/) 에서 최신버전 다운로드   
  (6.1 사용했다가 오류나서 5.6.4(LTS*) 버전으로 변경)
* config 설정
```
sonar.jdbc.username=sonar
sonar.jdbc.password=sonar
 
sonar.jdbc.url=jdbc:mysql://데이터베이스 아이피주소:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance
sonar.jdbc.driverClassName=com.mysql.jdbc.Driver
sonar.jdbc.validationQuery=select 1
```
* sonar 서버 시작  
  C:\SonarQube\sonarqube-5.4\bin\windows-x86-64 -> InstallNTService.bat -> StartNTService.bat -> StartSonar.bat 실행 -> 웹에서 [http://localhost:9000/](http://localhost:9000/) 실행시 웹페이지 확인가능
* 초기 실행시 admin/admin 으로 로그인 가능

## Linux에서 서버 설정
* java 8.0 = sonarqube 5.6 이상  / java 7.0 = sonarqube 5.5 이하로 설치해야함
```linux
wget https://sonarsource.bintray.com/Distribution/sonarqube/sonarqube-5.5.zip
unzip sonarqube-5.5.zip
mv sonarqube-5.5 /opt/
```
* config db 설정은 Windows와 동일 / web 설정은 아래와  같이
```
$cd /opt/sonarqube-5.1/conf
$vi sonar.properties

sonar.web.host=0.0.0.0
sonar.web.context=/sonar
sonar.web.port=9000
```
* 서버 시작
```
$cd /opt/sonarqube-5.1/bin
$cd linux-x86-64
$./sonar.sh start
```
* Linux Service로 등록 방법  
  sonar.sh를 etc/init.d/sonar에 이동 > 파일에서 다음과 같이 설정
```
sudo cp /opt/sonar/bin/linux-x86-64/sonar.sh /etc/init.d/sonar
sudo vi /etc/init.d/sonar

SONAR_HOME=/opt/sonarqube-5.6.4
PLATFORM=linux-x86-64

WRAPPER_CMD="${SONAR_HOME}/bin/${PLATFORM}/wrapper"
WRAPPER_CONF="${SONAR_HOME}/conf/wrapper.conf"
...
PIDDIR="/var/run"
```
* register service
```
sudo update-rc.d -f sonar remove
sudo chmod 755 /etc/init.d/sonar
sudo update-rc.d sonar defaults
```
* 로그 확인
```
vi /opt/sonarqube-5.5/logs/sonar.log
```
* 포트 연결 확인
```
netstat -anp | grep LISTEN
```

## sonar 서버 실행시 문제
* http://localhost:9000/ 실행시 'SonarQube is under maintenance' 표시   
  -> [http://127.0.0.1:9000/setup](http://127.0.0.1:9000/setup) 에서 mysql 업데이트 후 해결

## sonar 서버에 Plugin 설정
* 웹 페이지 -> 상단 Administration -> System -> update Center -> avaliable -> 원하는 플러그인 설치 -> 서버 재실행   
  필자는 git, github, java, android, korean pack 설치


## Android Studio 설정 
* build.gradle 설정
```
apply plugin: "sonar-runner"
sonarRunner {
    sonarProperties {
        property "sonar.host.url", "http://localhost:9000/"
        property "sonar.jdbc.url", "jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8"
        property "sonar.jdbc.username", "sonar"
        property "sonar.jdbc.password", "sonar"
        property "sonar.login", "admin"
        property "sonar.password", "admin"
        property "sonar.projectKey", "Sonar:Test"
        property "sonar.projectName", "프로젝트 명(아무거나)"
        property "sonar.projectVersion", "1.0"
        property "sonar.sourceEncoding", "UTF-8"
        property "sonar.language", "java"
        property "sonar.sources", "src/main/java"
        property "sonar.profile", "Sonar way"
    }
}
```
* Studio 터미널에서 'gradlew sonarRunner' 실행시 소나 웹서버로 프로젝트 업로드 -> 웹서버에서 분석 가능

## 참고사이트
* [SonarQube로 Android App을 분석하는 방법](https://www.davidlab.net/ko/tech/how-to-analyze-android-code-with-sonarqube/)
* [소나 설치 방법(windows)](http://galmaegi74.tistory.com/4)
* [ubuntu install](http://dev.mamikon.net/installing-sonarqube-on-ubuntu/)
* [우분투에 SonarQube 설치하기](http://wowzoo.blogspot.kr/2015/05/sonarqube.html)
