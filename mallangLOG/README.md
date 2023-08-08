# mallangLOG(Level1)

📍 [Velog 바로가기]()

### 1️⃣ 서비스 요구사항
|API 구분|기능|
|:---:|---|
|**전체게시글 목록조회**|- 제목, 작성자명, 작성내용, 작성날짜 조회 <br> - 작성날짜 기준 내림차순 정렬|
|**게시글 작성**|- 제목, 작성자명, 비밀번호, 작성내용 저장 <br> - 저장된게시글 Client 반환|
|**선택한게시글 조회**|- 선택한게시글 제목, 작성자명, 작성날짜, 작성내용 조회|
|**선택한게시글 수정**|- 수정요청 시, 수정할데이터 + 비밀번호 → 서버에서 비밀번호 유효성검사 <br> - 제목, 작성자명, 작성내용 수정 <br> - 수정된게시글 Client 반환|
|**선택한게시글 삭제**|- 삭제요청 시, 비밀번호 → 서버에서 비밀번호 유효성검사 <br> - 선택한게시글 삭제 <br> - 성공메시지 Client 반환|

### 2️⃣ Service Architecture
![serviceArchitecture_mallangLOG(Level1).png](..%2F..%2F..%2Fdevelope%2FArchitecture%2FserviceArchitecture_mallangLOG%28Level1%29.png)

### 3️⃣ Usecase
![usecase_mallangLOG(Level1).png](..%2F..%2F..%2Fdevelope%2FArchitecture%2Fusecase_mallangLOG%28Level1%29.png)

### 4️⃣ API 명세서
|기능|Method|URL|Request|Response|
|---|:---:|:---:|---|---|
|게시글작성|`POST`|/post|{<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"password" : "비밀번호",<br>&nbsp;&nbsp;"content" : "작성내용"<br>}|{<br>&nbsp;&nbsp;"id" : "고유번호",<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"content" : "작성내용",<br>&nbsp;&nbsp;"createdAt" : "생성시간",<br>&nbsp;&nbsp;"modifiedAt" : "수정시간"<br>}|
|전체게시글목록조회|`GET`|/posts|-|[<br>{<br>&nbsp;&nbsp;"id" : "고유번호",<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"content" : "작성내용",<br>&nbsp;&nbsp;"createdAt" : "생성시간",<br>&nbsp;&nbsp;"modifiedAt" : "수정시간"<br>},<br>{<br>&nbsp;&nbsp;"id" : "고유번호",<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"content" : "작성내용",<br>&nbsp;&nbsp;"createdAt" : "생성시간",<br>&nbsp;&nbsp;"modifiedAt" : "수정시간"<br>}<br>]|
|선택한게시글조회|`GET`|/post/{post-id}|-|{<br>&nbsp;&nbsp;"id" : "고유번호",<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"content" : "작성내용",<br>&nbsp;&nbsp;"createdAt" : "생성시간",<br>&nbsp;&nbsp;"modifiedAt" : "수정시간"<br>}|
|선택한게시글수정|`PUT`|/post/{post-id}|{<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"password" : "비밀번호",<br>&nbsp;&nbsp;"content" : "작성내용"<br>}|{<br>&nbsp;&nbsp;"id" : "고유번호",<br>&nbsp;&nbsp;"title" : "제목",<br>&nbsp;&nbsp;"username" : "작성자명",<br>&nbsp;&nbsp;"content" : "작성내용",<br>&nbsp;&nbsp;"createdAt" : "생성시간",<br>&nbsp;&nbsp;"modifiedAt" : "수정시간"<br>}|
|선택한게시글삭제|`DELETE`|/post/{post-id}|{<br>&nbsp;&nbsp;"password" : "비밀번호"<br>}|{<br>&nbsp;&nbsp;"status" : 200,<br>&nbsp;&nbsp;"message" : "Deleted Post Successfully"<br>}<br>{&nbsp;&nbsp;"status" : ["The Post does not exist.", "Password is incorrect"]<br>}|

### 5️⃣ Project Setting
- 언어 : JAVA
- 타입 : Gradle-Groovy
- 패키지 : Jar
- JDK : openjdk-19
- Java : 17
- SpringBoot : 3.1.2