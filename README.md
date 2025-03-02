


Create User Request

POST 

http://localhost:8080/api/user/register

payload example:

{
"firstName":"Alan",
"lastName": "Lima",
"document":"9999999999",
"email":"test@test.com",
"password": "123456",
"balance": "1000",
"userType":"COMMON"
}

"document" and "email" are unique

{
"transferValue": 599.99,
"sender": 1,
"receiver": 2
}

"sender" "receiver" need ID information

H2 Console
http://localhost:8080/h2-console/
username=sa
password=password


