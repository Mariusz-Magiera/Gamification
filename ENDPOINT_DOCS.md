# API Endpoints

## /users
### /all
*  GET
* returns every registered user
### /id/{id}
*  GET
* returns user with specific `{id}`
### /id/{id}/links
*  GET
* returns all profile links of the user with specific `{id}`

## /projects
### /all
*  GET
* returns every project
### /id/{id}
*  GET
* returns projectwith specific `{id}`
### /id/{id}/tasks
*  GET
* returns all tasks of the project with specific `{id}`

## /authorize
*  POST
* parameters:
	* name
	* password
* returns true if user with given name and password exists
* parameters are passed as a <String,String> Pair inside request body

