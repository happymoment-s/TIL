# 패키지가 다른 테스트앱 설정하는 방법

## gradle 설정
* 'staging' 이라는 명칭으로 설정방법
```Groovy
staging.initWith(buildTypes.debug)
staging {
	applicationIdSuffix '.staging'	
	versionNameSuffix '-staging'
}
```
 * 위와 같이 설정시 [기존패키지명].staging 패키지명으로 설치가 되어서 기존앱과 충돌되지 않는다.
 * 앱 버전명도 [기존버전이름]-staging 형태로 설정된다.