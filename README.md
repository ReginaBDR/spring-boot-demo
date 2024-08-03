# Demo Application

## Development

To start the application run the following command in the terminal

```
./gradlew bootRun
```

### Using Docker to simplify development

To start a postgresql database in a docker container, run:

```
docker compose -f src/main/docker/postgresql.yml up -d
```

To stop it and remove the container, run:

```
docker compose -f src/main/docker/postgresql.yml down
```

## Testing

### Spring Boot tests

To launch the application's tests, run:

```
./gradlew test
```

## API Documentation

To access the Api documentation in dev environment:

```
http://localhost:8080/swagger-ui/index.html
```
