# Android Studio layout preview에서 한글 깨짐현상 해결 방법

### 폰트 변경
*  스튜디오설치경로\plugins\android\lib\layoutlib\data\fonts 의 fonsts.xml 파일 편집
* family lang=”ko” 의 한글 폰트 변경, 기본으로 나눔고딕이 포함되어 있으니 해당 폰트를 사용
```
<family lang="ko">
    <font weight="400" style="normal" index="0">NanumGothic.ttf</font>
</family>
```

* 나는 안됨..

### 참고 사이트
* [[Android] Android Studio 레이아웃 프리뷰 한글 깨짐 현상](http://www.skultz.com/2016/09/20/android-android-studio-%EB%A0%88%EC%9D%B4%EC%95%84%EC%9B%83-%ED%94%84%EB%A6%AC%EB%B7%B0-%ED%95%9C%EA%B8%80-%EA%B9%A8%EC%A7%90-%ED%98%84%EC%83%81/)
