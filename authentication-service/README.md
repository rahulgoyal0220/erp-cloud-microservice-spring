# hrmanagement

1. Register User API:
POST  http://localhost:8082/api/auth/signup
Body :
{
	"name": "Rahul",
	"username" : "rahulg",
	"email": "rahulg@gmail.com",
	"password": "somepassword"
}

2. Login User API:
POST http://localhost:8082/api/auth/signin
Body:
{
	"username" : "rahulg",
	"password" : "somepassword"
}

3. Test User detail api. 
GET http://localhost:8082/api/user
In the Header request pass
Key  as  Authorization 
Value as Bearer TOKE_RETURNED_FROM_LOGIN
