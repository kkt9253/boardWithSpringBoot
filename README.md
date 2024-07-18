# 사진 첨부 및 CRUD기능을 갖춘 게시물 프로젝트
## Stack
Spring Boot<br/>
Spring Data JPA<br/>
MariaDB<br/>
Lombok<br/>
Swagger


## 계층형 아키텍쳐
![6계층혁아키텍쳐](https://github.com/user-attachments/assets/98c3d62e-0700-42ad-bba4-a4603eb81deb)

## 1. Home 화면 (`/board`)
- **요청/응답 표**

| HTTP Method | Endpoint         | Description                 | Request Parameters | Request Body | Response   | 
|-------------|------------------|-----------------------------|--------------------|--------------|------------|
| GET         | /board           | Home 페이지 로드             | None               | None         | HTML 페이지 |
| GET         | /board/create    | 게시물 작성 페이지로 이동     | None               | None         | HTML 페이지 |
| GET         | /board/list      | 게시물 목록 페이지로 이동     | None               | None         | HTML 페이지 |
| GET         | /board/listDetail| 탁스타그램 페이지로 이동      | None               | None         | HTML 페이지 |

![1](https://github.com/user-attachments/assets/e135e347-7691-4d55-adc1-3075dcebbfe3)


## 2. 게시물 작성 페이지 (`/board/create`)
- **요청/응답 표**

| HTTP Method | Endpoint         | Description                 | Request Parameters | Request Body               | Response   | 
|-------------|------------------|-----------------------------|--------------------|----------------------------|------------|
| GET         | /board/create    | 게시물 작성 페이지 로드       | None               | None                       | HTML 페이지 |
| POST        | /board/create    | 게시물 작성                  | None               | title, context, photo (Multipart File) | 리다이렉트(홈) |

![2](https://github.com/user-attachments/assets/d3ea1d69-a097-463f-b730-dba9d42ab686)


## 3. 게시물 목록 페이지 (`/board/list`)
- **요청/응답 표**

| HTTP Method | Endpoint         | Description                 | Request Parameters | Request Body | Response   | 
|-------------|------------------|-----------------------------|--------------------|--------------|------------|
| GET         | /board/list      | 게시물 목록 페이지 로드       | None               | None         | HTML 페이지 |

![3](https://github.com/user-attachments/assets/22ca1bf9-0249-4662-8372-c7cdc179d8c5)


## 4. 게시물 상세 페이지 (`/board/detail`)
- **요청/응답 표**

| HTTP Method | Endpoint         | Description                  | Request Parameters  | Request Body | Response   | 
|-------------|------------------|------------------------------|---------------------|--------------|------------|
| GET         | /board/detail    | 게시물 상세 페이지 로드        | number (Long)       | None         | HTML 페이지 |
| POST        | /board/update    | 게시물 수정                   | None                | number, title, context, photo (Multipart File) | JSON |
| POST        | /board/delete    | 게시물 삭제                   | number (Long)       | None         | 리다이렉트 (목록 페이지로) |

![4](https://github.com/user-attachments/assets/d638d6ed-a2b0-418c-b849-f93c46e5a840)


## 5. 상세게시판 목록 페이지 (`/board/listDetail`)

![5](https://github.com/user-attachments/assets/88cca87b-9511-41da-b9a3-6cf0dae10292)
