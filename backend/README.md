# Backend

## Database

To run the mysql db with docker:

```bash
docker run -d --platform linux/x86_64 --name integrador-db --restart always -e MYSQL_DATABASE='db' -e MYSQL_USER='user' -e MYSQL_PASSWORD='password' -e MYSQL_ROOT_PASSWORD='password' -p 3306:3306 -v integrador-db:/var/lib/mysql mysql:5.7
```

You may connect to the database by running

```bash
docker exec -it integrador-db mysql -p -u user db
```

## Running

´´´bash
docker build -t it-front .
docker run --name frontend -p 80:80 it-front
´´´

## Stopping

´´´bash
docker stop frontend
docker rm frontend
docker rmi it-front
´´´
