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
http POST http://localhost:8080/customer_management/EXP002/questions/validate \
  Content-Type:application/json \
  <<< '{
	"questions":[
		{
			"questionId":"1",
			"answer":"No"
		},
		{
			"questionId":"4",
			"answer":"Polen"
		}
	]
}'

http POST http://localhost:8080/customer_management/EXP010/enroll_questions \
  Content-Type:application/json \
  <<< '{
	"questions":[
		{
			"questionId":"2",
			"answer":"Yes"
		},
		{
			"questionId":"4",
			"answer":"Bee"
		}
	]
}'

http GET http://localhost:8080/customer_management/questions?statusId=A

http GET http://localhost:8080/customer_management/EXP001/subordinates

http GET http://localhost:8080/customer_management/EXP006/questions

http DELETE http://localhost:8080/customer_management/EXP002/delete
```

## command postgreSQL
```sql
\dt   // show tables on the databas conected
\l    // list databases
\q    // exit from psql
```