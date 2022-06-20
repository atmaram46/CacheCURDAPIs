# CacheCURDAPIs
CURD API's Example

This example doesn't contain connection with any DB, and instead uses LRUMap for storing the Data. Size of Map is fixed and is defined in properties file. Property of LRUMap is, it keeps the latest used data in memorey, and if the amount of data which is added increases then the defined size, then it removes the oldest data from its memorey.

API's EndPoints:
1. Save User (POST)

    URL: http://localhost:9004/users/saveUser

    Headers: Content-Type:application/json
    
    Body: {
	          "email":"arun3@gmail.com",
	          "dob":"20220101",
	          "firstName":"Arun",
	          "lastName": "Kumar"
          }
	  
    Expected Response:
    
      200: Ok: User Added Successfully!!!
     
      400: Bad Request: Email Already Present!!!
      
      400: Bad Request: Error in Date of Birth!!!
      
      
2. Update User (PUT)

   URL: http://localhost:9004/users/updateUser
   
   Headers: Content-Type:application/json
   
   Body: {
	          "email":"arun3@gmail.com",
	          "dob":"20220101",
	          "firstName":"Arun",
	          "lastName": "Kumar"
          }
   
   Expected Response:
      
      200: Ok: User updated Successfully!!!
      
      400: Bad Request: Email Already Present!!!
      
      200: OK: Email not present!!! Adding new Entry!!!
      
 
3. Get by Email ID
    
    URL: http://localhost:9004/users/email
    
    Headers: email:arun3@gmail.com
    
    Expected Response:
      
      200: OK: {
                  "firstName": "Arun",
                  "lastName": "Kumar",
                  "email": "arun3@gmail.com",
                  "dob": "20200101"
                }
      
      400: Bad Request: No Data Found!!!
      
      
4. Delete User
    
    URL: http://localhost:9004/users/delete
    
    Headers: email:arun3@gmail.com
    
    Expected Resonse:
      
      200: OK: User Deleted!!!
      
      400: Bad Request: No Data Found!!!
                
