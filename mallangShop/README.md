# 🐰 mallangShop

[📍 Velog 바로가기](https://velog.io/@mallang/SpringBoot-mallangShop)

## 1 mallangShop 베타버전 Refactoring 

### 1️⃣ 서비스기능
|서비스기능|
|---|
|1. 키워드로 상품검색 → 결과 목록으로 보여주기|
|2. 관심상품 등록하기|
|3. 관심상품 조회하기|
|4. 관심상품 최저가등록하기|

### 2️⃣ API 명세서 - Product
|기능|Method|URL|Request| Response                                                                                                                                                                                                                                |
|---|---|---|---|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|메인페이지|`GET`|/api/shop|-| index.html                                                                                                                                                                                                                              |
|Query로 상품검색, 상품검색결과 목록반환|`GET`|/api/search?query=검색어|-| [ <br> { <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int <br> }, <br> ••• <br> ]                                                                |
|관심상품 등록|`POST`|/api/products|{ <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int <br> }| { <br> &nbsp;&nbsp;"id" : Long, <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int, <br> &nbsp;&nbsp;"myprice" : int <br> }                        |
|관심상품 조회|`GET`|/api/products|-| [ <br> { <br> &nbsp;&nbsp;"id" : Long, <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int, <br> &nbsp;&nbsp;"myprice" : int <br> }, <br> ••• <br>] |
|관심상품 최저가 등록|`PUT`|/api/products/{id}|{ <br> &nbsp;&nbsp;"myprice" : int <br> }|id|

### 3️⃣ AllInOneController 역할분리
#### 📍 Controller
![img.png](img.png)

#### 📍 Service
![img_1.png](img_1.png)

#### 📍 Repository
![img_2.png](img_2.png)

## 2 mallangShop 인증 Feature

### 1️⃣ 서비스기능 추가
|서비스기능|
|---|
|5. 회원가입|
|6. 로그인&로그아웃|

### 2️⃣ API 명세서 - User
|기능|Method|URL|Request|Response|
|---|---|---|---|---|
|회원가입페이지|`GET`|/api/user/signup|-|signup.html|
|회원가입|`POST`|/api/user/signup|POST Form 태그 <br> { <br> &nbsp;&nbsp;"username" : String, <br> &nbsp;&nbsp;"password" : String, <br> &nbsp;&nbsp;"email" : String, <br> &nbsp;&nbsp;"admin" : boolean, <br> &nbsp;&nbsp;"adminToken" : String <br> }|redirect:/api/user/login|
|로그인페이지|`GET`|/api/user/login|-|login.html|
|로그인|`POST`|/api/user/login|POST Form 태그 <br> { <br> &nbsp;&nbsp;"username" : String, <br> &nbsp;&nbsp;"password" : String <br> }|redirect:/api/shop|