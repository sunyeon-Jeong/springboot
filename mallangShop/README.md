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

### 3️⃣ 한계점
- 회원가입, 로그인 기능은 정상적으로 작동하나, 정보가 유지되지 않음
- 회원별로 다른 상품을 보여줄 수 없음
- `adminToken` → 계속된 전송으로 노출이 쉬워, 보안 취약

## 3 mallangShop 인가 Feature

### 1️⃣ 서비스기능 수정 및 추가
|서비스기능|
|---|
|5. 회원가입|
|6. 로그인|
|7. 로그인성공 → Token 발급|
|8. 로그아웃|
|9. 로그인 한 회원 → 관심상품 등록/조회/최저가등록 가능|
|10. ADMIN계정 → 모든상품 조회가능|

### 2️⃣ API 명세서 수정 - User
| 기능      |Method| URL |Request| Response                                                    |
|---------|---|---|---|-------------------------------------------------------------|
| 회원가입페이지 |`GET`| /api/user/signup |-| signup.html                                                 |
| 회원가입    |`POST`| /api/user/signup |POST Form 태그 <br> { <br> &nbsp;&nbsp;"username" : String, <br> &nbsp;&nbsp;"password" : String, <br> &nbsp;&nbsp;"email" : String, <br> &nbsp;&nbsp;"admin" : boolean, <br> &nbsp;&nbsp;"adminToken" : String <br> }| redirect:/api/user/login                                    |
| 로그인페이지  |`GET`| /api/user/login |-| login.html                                                  |
| 로그인     |`POST`| /api/user/login|{ <br> &nbsp;&nbsp;"username" : String, <br> &nbsp;&nbsp;"password" : String <br> } | Header <br> Authorization : Bearer \<JWT> <br> <br> success |

## 4 mallangShop 최종 Feature

### 1️⃣서비스기능
|서비스기능|
|---|
|1. 키워드로 상품검색  → 결과 목록으로 보여주기|
|2. 회원가입|
|3. 로그인|
|4. 로그인성공  → Token 발급|
|5. 로그아웃|
|6. 로그인 한 회원 → 관심상품 등록/조회/최저가등록 가능|
|7. ADMIN계정 → 모든상품 조회가능|
|8. 관심상품 목록 페이징 및 정렬|
|9. 폴더 생성 및 조회|
|10. 관심상품 → 폴더추가|
|11. 폴더 별 관심상품 조회|

### 2️⃣ API 명세서 - Product
|기능|Method|URL| Request                                                                                                                                                                                              | Response                                                                                                                                                                                                         |
|---|---|---|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|메인페이지|`GET`|/api/shop| -                                                                                                                                                                                                    | index.html                                                                                                                                                                                                       |
|Query로 상품검색, 상품검색결과 목록반환|`GET`|/api/search?query=검색어| -                                                                                                                                                                                                    | [ <br> { <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int <br> }, <br> ••• <br> ]                                         |
|관심상품 등록|`POST`|/api/products| Header <br> Authorization : Bearer <JWT\> <br> <br> { <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int <br> } | { <br> &nbsp;&nbsp;"id" : Long, <br> &nbsp;&nbsp;"title" : String, <br> &nbsp;&nbsp;"image" : String, <br> &nbsp;&nbsp;"link" : String, <br> &nbsp;&nbsp;"lprice" : int, <br> &nbsp;&nbsp;"myprice" : int <br> } |
|관심상품 조회|`GET`|/api/products?sortBy=String&isAsc=boolean&size=int&page=int| Header <br> Authorization : Bearer <JWT\>                                                                                                                                                            | Page<Product\>                                                                                                                                                                                                   |
|관심상품 최저가 등록|`PUT`|/api/products/{id}| Header <br> Authorization : Bearer <JWT\> <br> <br> { <br> &nbsp;&nbsp;"myprice" : int <br> }                                                                                                        | id                                                                                                                                                                                                               |

### 3️⃣ API 명세서 - Folder(폴더생성 및 조회)
|기능|Method| URL         |Request|Response|
|---|---|-------------|---|---|
|폴더 생성|`POST`| api/folders |{ <br> &nbsp;&nbsp;"folderNames" : [String, •••] <br> }|[String, •••]|
|폴더 조회|`GET`|api/user-folder|-|index.html <br> model 추가 → folders|
- 회원 별 폴더 추가가능
- 폴더 추가 시, 1 ~ N개 한번에 추가가능
- 회원 별 저장한 폴더 조회기능

### 4️⃣ API 명세서 - Folder(관심상품에 폴더추가기능)
| 기능          |Method|URL|Request| Response                                                                                                                                                                                                                                                                                                                                                                                                                                |
|-------------|---|---|---|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 폴더 전체 조회    |`GET`|api/folders|-| [ <br> { <br> &nbsp;&nbsp;"id" : int, <br> &nbsp;&nbsp;"name" : String, <br> &nbsp;&nbsp;"user" : { <br> &nbsp;&nbsp;&nbsp;&nbsp;"id" : int, <br> &nbsp;&nbsp;&nbsp;&nbsp;"username" : String, <br> &nbsp;&nbsp;&nbsp;&nbsp;"password" : String, <br> &nbsp;&nbsp;&nbsp;&nbsp;"email" : String, <br> &nbsp;&nbsp;&nbsp;&nbsp; "role" : String, <br> &nbsp;&nbsp;&nbsp;&nbsp; "folders" : [] <br> &nbsp;&nbsp; } <br> }, <br> ••• <br> ] |
| 관심상품에 폴더 추가 |`POST`|api/products/{productId}/folder|{productId} : 관심상품 Id <br> <br> [Form 형태] <br> folderId : 추가할 폴더 Id|폴더가 추가 된 관심상품 Id|
- 관심상품에 폴더 0 ~ N개 설정가능
- 관심상품 등록 시점 → 어느 폴더에도 저장 X
- 관심상품 별, 기 생성된 폴더 선택해 추가가능
- Folder와 Product → M : N 관계

### 5️⃣ API 명세서 - Folder(폴더 별 관심상품 조회)
|기능|Method|URL|Request|Response|
|---|---|---|---|---|
|폴더 별 관심상품 조회|`GET`|api/folders/{folderId}/products|{folderId} : 조회를 원하는 폴더 Id|Page<Product>|
- 폴더 별 관심상품 조회가능
  - '폴더별' : 폴더 별 저장된 관심상품 조회가능
  - '전체' : 폴더와 상관없이 전체 관심상품 조회가능