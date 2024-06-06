# Backend

To run the mysql db with docker:

```bash
docker run -d --platform linux/x86_64 --name integrador-db --restart always -e MYSQL_DATABASE='db' -e MYSQL_USER='user' -e MYSQL_PASSWORD='password' -e MYSQL_ROOT_PASSWORD='password' -p 3306:3306 -v integrador-db:/var/lib/mysql mysql:5.7 --max_conenctions=1000
```