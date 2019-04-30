# Gamification - Backend Server
Web app server for a university project.



# Running in a docker
To run the server on a docker you first need to create a docker image from source code. In order to do that you need to navigate to the root directory of this project and enter the following command:

## For Linux:
```
./mvnw package docker:build
```

## For Windows:
```
mvnw.cmd package docker:build
```

### Starting the server
After that you can start the server with
```
docker run -p 8080:8080 -t <docker_image>
```

You can check the name of the image by typing
```
docker images
```

### Stopping the server
To stop the server find ID of the running container by typing
```
docker container ls
```
and stop it with
```
docker kill <container_id>
```
