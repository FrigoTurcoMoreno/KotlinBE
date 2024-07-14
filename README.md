# Kotlin-BE
This is a basic structure for a Spring Boot application built with Kotlin.

## Technologies
- Kotlin + Spring Boot
- Oracle OpenJDK 21.0.2
- Spring Security
- Gradle
- MySQL
- Docker

## How to Launch

### Create `properties.env` from `empty-properties.env`

#### Locally
1. Comment out the `be` service in `Dockerfile.be`.
2. Start the services with Docker Compose (ensure port 3306 is free):
   ```sh
   docker compose -f docker-compose.yml up
3. Launch the backend through your IDE (IntelliJ, Eclipse, etc.) using the properties.env file.
4. Use Postman to test the endpoints.
#### Docker
1. Start the services with Docker Compose (ensure ports 3306 and 8080 are free):
    ```sh
   docker compose -f docker-compose.yml up
2. Use Postman to test the endpoints.

## Author
Moreno Frigo Turco
- [![GitHub Icon](https://img.icons8.com/material-outlined/24/ffffff/github.png)](https://github.com/FrigoTurcoMoreno) [GitHub](https://github.com/FrigoTurcoMoreno)
- [![LinkedIn Icon](https://img.icons8.com/material-rounded/24/ffffff/linkedin.png)](https://www.linkedin.com/in/moreno-frigo-turco-4a423a294) [LinkedIn](https://www.linkedin.com/in/moreno-frigo-turco-4a423a294)
- [![Instagram Icon](https://img.icons8.com/material-rounded/24/ffffff/instagram-new.png)](https://www.instagram.com/morenofrigo.dev) [Instagram](https://www.instagram.com/morenofrigo.dev)
- [![X Icon](https://img.icons8.com/material-rounded/24/ffffff/twitter.png)](https://x.com/morenofrigo_dev) [X](https://x.com/morenofrigo_dev)

Feel free to reach out for any questions or collaboration opportunities!