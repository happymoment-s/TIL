# aar �Ǵ� jar ���� import �ϴ� ���

## 1. aar ���� import

### 1.1. Module Settings ���
- �������Ʈ : [Adding AAR Files to Your Android Studio Project] (http://docs.millennialmedia.com/android-ad-sdk/adding-aar-files.html) 

1. application ������Ʈ ���콺 ���� Ŭ�� -> Open Module Settings Ŭ��
2. ���� ��ܿ� �ʷϻ� '+' ��ư(New Module) Ŭ��
3. 'Import .JAR or .AAR Package' ������ ���� �ϴܿ� 'Next' ��ư Ŭ�� -> ... ��ư���� aar ���� ���� -> 'Finish' ��ư Ŭ��
4. ���� ��ܿ� 'Dependencies' Ŭ�� -> ���� ��ܿ� �ʷϻ� '+' ��ư Ŭ�� -> '3. Module Dependency' Ŭ�� -> aar Ŭ��

### 1.2. gradle ���� ���
- �������Ʈ : [Reference a local .aar in your Android project] (http://kevinpelgrims.com/blog/2014/05/18/reference-a-local-aar-in-your-android-project/)
- �ش� ����� ��ũ��Ʈ�� ���� library ���� import ������� ��밡���� ������ ���δ�.

1. aar ������ application ������Ʈ �ȿ� ���ϴ� ��ġ�� ����, �Ϲ������� [app]/libs ������ [app]/aars ������ �����ؼ� �ִ´�.
2. app�� build.gradle���� ������ ���� ����
```Gradle
repositories {
    flatDir {
        dirs 'libs' // aar������ ����ִ� ���� ��ġ
    }
}

dependencies {
    compile(name:'libraryfilename', ext:'aar')
    // �Ǵ�
    // compile 'library package name:library name:library version@aar' // ex) 'com.kevinpelgrims.library:library:1.0.0@aar' 
}
```


## 2. jar ���� import

### 2.1. Module Settings ���
1.1. ����

### 2.2. ������ ��ư���� ���� ���
1. jar ������ Ư�� ��ġ�� ����, �Ϲ������� [app]/libs ����
2. jar ���� ������ ��ư Ŭ�� -> 'Add As Library...' ��ư Ŭ��

### 2.3. gradle ���� ���
```Gradle
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile files('libs/libraryfilename.jar')
}
```