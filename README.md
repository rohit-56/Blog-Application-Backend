
# Blog Application

Blog Application is a user-friendly project where a user can register itself, post a blog, add comments.

This repository contains the backend of a Blog Application project, which is responsible for handle all the blogs, user informations and is implemented using the Spring Boot Java Framework which serves as the web framework for the backend.

## Backend Technology Stack

    1. Java
    2. Spring Boot
    3. MySQL
    4. Gradle
    5. H2 Database
## API Reference

### User API

```http
To Create User Method:POST- /user/create
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `Username` | `string` | **Required**. Your Username |
| `Email` | `string` | **Required**. Your Email |
| `Password` | `string` | **Required**. Your Password to login |
| `Image` | `string` | User can upload their images too |
| `Bio` | `string` | User can write about himself/herself |

```http
To Update User Method:PUT- /user/update
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Id of item to fetch |
| `Username` | `string` | Updated Username |
| `Email` | `string` | Updated Email |
| `Password` | `string` | Updated Password |
| `Image` | `string` | Updated Image |
| `Bio` | `string` | Updated bio of user |


```http
Login Request Method:POST- /api/auth/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `Username` | `string` |  **Required** User Username |
| `Password` | `string` |  **Required** User Password |


```http
Add Blog Request Method:POST- /blog/create/user/{user_id}/category/{category_id}
```

| Parameter    | Type     | Description                       |
| :--------    | :------- | :-------------------------------- |
| `user_id`     | `Long` |  **Required** User Id(Primary Key)  |
| `category_id` | `Long` |  **Required** Category Id(Primary Key) |
| `Title` | `string` |  **Required** Title of Blog |
| `Subtitle` | `string` |  **Required** Subtitle of blog |
| `TagRequest` | `Array` |  **Required** List of Tags |
| `Body` | `string` |  **Required** Content of blog |
| `ImageCover` | `string` |  Image cover for blog to display |





## Documentation

Document REST APIs with Swagger in Spring Boot Project 
#### Swagger Path:

http://localhost:8081/swagger-ui/index.html
