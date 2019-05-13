
# API Endpoints

## /users
### /all
*  GET
* returns every registered user
### /id/{id}
*  GET
* returns user with specific `{id}`
### /add
*  POST
* request body:
	* JSON user
* adds new user
### /id/{id}/links
*  GET
* returns all profile links of the user with specific `{id}`
### /id/{id}/projects
*  GET
* returns all projects that the user with specific `{id}` participates in

## /projects
### /all
*  GET
* returns every project
### /id/{id}
*  GET
* returns project with specific `{id}`

### /id/{id}/tasks
*  GET
* returns all tasks of the project with specific `{id}`
### /id/{id}/users
*  GET
* returns all participants of the project with specific `{id}`
## /authorize
*  POST
* request body:
	* JSON user object with fields: 
		* name
		* password
* returns true if user with given name and password exists

