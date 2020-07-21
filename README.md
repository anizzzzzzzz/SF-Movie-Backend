# SF Movie
This is the backend-project built for showing where movies have been filmed in San Francisco. It is equipped with autocompletion 
feature to filter the search. The full movie data is available on [SF-Movie](https://data.sfgov.org/Culture-and-Recreation/Film-Locations-in-San-Francisco/yitu-d5am).

## Getting Started
These instructions will get you a copy of the project up and running on your local machine. 

### Built With
* **Java 1.8**
* **Spring Boot**
* **Gradle**
* **Postgres DB**

### Installation
```sh
$ git clone https://github.com/anizzzzzzzz/SF-Movie-Backend.git
$ cd SF-Movie-Backend/
```

### Installing Dependencies
##### On macOS and Linux:
``` 
./gradlew build --refresh-dependencies
``` 
##### On windows:
``` 
gradlew build --refresh-dependencies
```

### Prerequisities
#### Setting up Postgres:
Download and install postgres db on your system.  
You can follow either steps.  
* Create a db according to the configuration specified on src/main/resources/application-dev.properties file.
* Or, change the configuration specified on src/main/resources/application-dev.properties file as per your requirements.

#### Geocoding the location:
The fetched data only provides the location where films were shot. Due to this reason, I had to use the 
[Third party API](https://developer.here.com/) for fetching the coordinates of the location.

The following snippet demonstrates the API call :
``` 
https://geocode.search.hereapi.com/v1/geocode?q={location}&apiKey={api-key}
```

### Build App
##### On macOS and Linux:
``` 
./gradlew clean build
``` 
##### On windows:
``` 
gradlew clean build
```

### Running App
##### On macOS and Linux:
``` 
java -jar ./build/libs/sf-movies-0.0.1-SNAPSHOT.jar
``` 
With Spring Profile : 'prod'
``` 
java -jar -Dspring.profiles.active=prod ./build/libs/sf-movies-0.0.1-SNAPSHOT.jar
``` 
##### On windows:
``` 
java -jar build/libs/sf-movies-0.0.1-SNAPSHOT.jar
``` 
With Spring Profile : 'prod'
``` 
java -jar -Dspring.profiles.active=prod build/libs/sf-movies-0.0.1-SNAPSHOT.jar
``` 
The project will run on localhost:8080

#### Deployed on :
The application is hosted on [SF-Movie Rest API](https://sf-movie-backend.herokuapp.com).


### My Profile
[Anish Maharjan](https://www.linkedin.com/in/anish-maharjan-58a640149/)