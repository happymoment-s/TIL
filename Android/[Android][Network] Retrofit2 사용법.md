# Retrofit2 사용

## 초기 설정
*

## Logging 설정
*

## 공통 header 설정
*

## Post 전송시 param과 body(json) 동시에 설정하는 방법
* 동시에 사용하는 방법은 못찾음..   
* @Field 파라미터를 사용하지 않고 @Url 파라미터를 사용하여 url을 dynamic하게 전달받도록 설정하고 url에 ?param_key=param_value 형태의 string 추가하여 전달하게 변경  
* @Body 파라미터만 사용


## @Body에 json 설정 방법
* @Body String 으로 service 설정후 JsonObject.toString() 사용시 '\'역슬래시 삽입으로 통신 실패발생
* gson converter 컴파일 후 compile  'com.squareup.retrofit2:converter-gson:2.1.0'   
 .addConverterFactory(GsonConverterFactory.create()) 추가, 
  json 형태의 POJO 객체 구현해서 전달하면 성공
* 이것때문에 Jackson에서 Gson으로 변경..?!



## 에러 

### 1. @Field parameters can only be used with form encoding. (parameter #1)
* POST에서 @Field 파라미터 사용시 @Form 인코딩 어노테이션 설정 안했을 경우 발생
* 아래와 같이 api interface에서 @FormUrlEncoded annotation을 추가한다.
```Java
@FormUrlEncoded
@POST("/users")
```

### 2. @Body parameters cannot be used with form or multi-part encoding. (parameter #2)
* Post 방식일때 @Body 파라미터와 @FormUrlEncoded 어노테이션을 동시에 사용할 때 발생

### 3. Only one encoding annotation is allowed.
* @Multipart 어노테이션과 @FormUrlEncoded 어노테이션을 같이 사용할 때 발생

### 4. @Part parameters can only be used with multipart encoding. (parameter #2)
* @Part 파라미터는 @Multipart 어노테이션 인코딩만 사용가능

### 5. @Field parameters can only be used with form encoding. (parameter #1)
* @Field 파라미터는 @Form 어노테이션 인코딩만 사용 가능

### 6. Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path $
* 기본적으로 gson에서 Lenient 설정이 안되어 있어서 발생..(Lenient가 뭐지..?) 
* 아래와 같이 GsonConverterFactory add시 추가설정
```JAVA
Gson gson = new GsonBuilder()
        .setLenient()
        .create();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://whatever.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build(); 
```
* [malformedjsonexception-with-retrofit-api](http://stackoverflow.com/questions/27485346/malformedjsonexception-with-retrofit-api)

### 7. java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path
* gson으로 컨버팅시 .addHeader("Accept-Encoding", "gzip, deflate") 헤더가 추가되어있으면 파싱하지 못하는 오류