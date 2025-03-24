# Popcorn Palace Movie Ticket Booking System Instructions

## Set Up
In order to operate the system, the user must follow several steps:

### Create the DB
The user should create the DB and get it running:

- **Download Docker**: If it's not downloaded already
- **Set up the DB instance**: Using the command line, the user must go to the project directory, specifically to
the popcorn-palace folder which inside the popcorn-palace main folder. It is the directory
where the compose.yml file is.
The user should then run the following command to set up the DB container:
docker-compose up
- **(Optional) Connect to the DB**: In order to gain a direct access to the DB,
the user might connect to it. 
First, the user must find the container ID by running the following command and choosing
the suitable container:
docker ps
Second, The user should open a command line from the container using the command:
docker exec -it <container-id> bash
Then, the user can connect to an SQL interface where they can run SQL queries. It
is done by following the command:
psql -U popcorn-palace -D popcorn-palace

### Build
The user can build the project by:
- **Getting to the directory**: Using the command line, the user must go to the project directory, specifically to
the popcorn-palace folder which inside the popcorn-palace main folder. It is the directory
where the mvnw file (maven project set up) is.
- **Building the project**: The user should run the following command so the project
compiles and builds without errors:
mvn clean install
- **Note**: Each time the project builds, the database is restarted and the non-empty tables
are movies, which holds 2 registries (The Dark Knight, Iron Man) and theatres, which contains
4 registries (Dolby,Chewbacca,Marvel,Disney)

### Run
The user remain in the same directory, and run the project either from the IDE or
with the command:
mvn spring-boot:run

Now the user is able to send some HTTP requests by adhering to the defined APIs 
and using CURL from the command line.
A recommended way to do so is with Postman.
In it, the user should choose the method type (GET,POST,DELETE), then paste the following
URL followed by the desired gateway: http://localhost:8080/{gateway}.
If there is some needed info to send to the server, It can be chosen in the Body column,
where the user must choose the raw option and write a fitting json setting.

### Test
The user might build and make some creative tests of their own using Postman or CURL.
I wrote a small test in the PopcornPalaceApplicationTests.java.
I conducted most tests manually with Postman (HTTP requests) and out of shortage of time
I didn't get to write them all in the java file.