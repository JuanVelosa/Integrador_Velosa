# Velosa Front

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
