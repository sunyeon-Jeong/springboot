## mallangShop

[📍 Velog 바로가기]()

### 1️⃣ 서비스기능
|서비스기능|
|---|
|1. 키워드로 상품검색 → 결과 목록으로 보여주기|
|2. 관심상품 등록하기|
|3. 관심상품 조회하기|
|4. 관심상품 최저가등록하기|
|5. 회원가입|
|6. 로그인|

### 2️⃣ API 명세서
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