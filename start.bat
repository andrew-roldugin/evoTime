@REM docker run --name postgres_db -e POSTGRES_PASSWORD=user -e POSTGRES_USER=user -e POSTGRES_DB=evo-time -d -p 9999:5432 postgres:15.0-alpine3.16
@REM docker network create --attachable evoTimeNetwork
docker-compose build
docker-compose up

pause