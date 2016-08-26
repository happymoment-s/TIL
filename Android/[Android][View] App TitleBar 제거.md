# App TitleBar 제거

## manifest 설정
* manifest 에서 applicatoin 속성중 theme 속성을 아래와 같이 적용
```Android
<application
	android:theme="@style/Theme.AppCompat.NoActionBar"
/>
```
* 하지만 위와 같이 설정하면 기존 theme 스타일이 없어질 수 있다. 그렇기 때문에 styles.xml에 아래의 속성 추가
```Android
	<item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
```
* 최종 styles.xml의 모습
```Android
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>

        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>

</resources>
```

## code 설정
* onCreate() 에서 아래 코드 추가
```Android
requestWindowFeature(Window.FEATURE_NO_TITLE);
```


## 참고 사이트
* [안드로이드-TitleBar-없애기](http://freehoon.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Titlebar-%EC%97%86%EC%95%A0%EA%B8%B0)