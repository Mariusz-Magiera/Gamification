
# API Endpoints

## /users
### /all
*  GET
* returns every registered user

### /add
*  POST
* request body:
	* JSON user
* adds new user
* returns true if the request ended succesfully 

### /id/{id}
*  GET
* returns user with specific `{id}`

### /id/{id}/links
*  GET
* returns all profile links of the user with specific `{id}`

### /id/{id}/projects
*  GET
* returns all projects that the user with specific `{id}` participates in

### /id/{id}/addAchievement
*  POST
*  URI params:
   * points: integer, number of points assigned to new achievement
   * desc: string, description of new achievement
*  request body:
   * JSON of user attempting to add achievement, must have `MOD` privileges, doesn't have to contain anything other than username and unencrypted password
* returns true if the request ended succesfully 

### /id/{id}/addLink
*  POST
*  URI params:
   * type: string, type of link, eg. image, facebook, twitter, etc.
   * link: string, URL to the resource
*  request body:
   * JSON of user attempting to add achievement, must be the user pointed to by `{id}`, doesn't have to contain anything other than username and unencrypted password
* returns true if the request ended succesfully 










## /projects
### /all
*  GET
* returns every project

### /add
*  POST
* request body:
	* JSON project, only required field is `name`
* adds new project
* returns true if the request ended succesfully 

### /id/{id}
*  GET
* returns project with specific `{id}`

### /id/{id}/tasks
*  GET
* returns all tasks of the project with specific `{id}`

### /id/{id}/users
*  GET
* returns all participants of the project with specific `{id}`

### /id/{id}/addAchievement
*  POST
*  URI params:
   * points: integer, number of points assigned to new achievement
   * desc: string, description of new achievement
*  request body:
   * JSON of user attempting to add achievement, must have `MOD` privileges, doesn't have to contain anything other than username and unencrypted password
* returns true if the request ended succesfully 

### /id/{id}/addTask
*  POST
*  URI params:
   * points: integer, number of points assigned to new task
   * desc: string, description of new task
   * due_date: date, deadline of the new task
*  request body:
   * JSON of user attempting to add task, must have `MOD` privileges, doesn't have to contain anything other than username and unencrypted password
* returns true if the request ended succesfully 

### /id/{id}/addUser
*  POST
*  request body:
   * JSON of user attempting get assigned to the project, doesn't have to contain anything other than username and unencrypted password
* assigns the user to the project with specific `{id}`
* returns true if the request ended succesfully 








## /authorize
*  POST
* request body:
	* JSON user object with fields: 
		* name
		* password
* returns true if user with given name and password exists

