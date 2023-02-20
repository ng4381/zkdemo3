docker rm -f $(docker ps -aq)
docker volume rm zkossdb_init
docker volume rm zkossdb_data
docker-compose -f docker-compose.yml up --build