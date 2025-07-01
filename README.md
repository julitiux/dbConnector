# paging_and_sorting

## start database
```shell
./starting.sh
```

## stop database
```shell
./stopping.sh
```

## enter database
```shell
docker exec -it dbConnector psql -U user -d db
```

## httpie command delete
```shell
http DELETE http://localhost:8080/preguntas-enroll/EXP002/delete
```

## command postgreSQL
```sql
\dt   // show tables on the databas conected
\l    // list databases
\q    // exit from psql
```