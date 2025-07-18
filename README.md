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
http POST http://localhost:8080/customer_management/EXP002/validate_questionnaire \
  Content-Type:application/json \
  <<< '{
	"questions":[
		{
			"question": {
				"questionId":"1",
				"answer":"No"
			}
		},
		{
			"question": {
				"questionId":"4",
				"answer":"Polen"
			}
		}
	]
}'

http POST http://localhost:8080/customer_management/EXP010/enrollment_questionnaire \
  Content-Type:application/json \
  <<< '{
	"questions":[
		{
			"question": {
				"questionId":"2",
				"answer":"Yes"
			}
		},
		{
			"question": {
				"questionId":"4",
				"answer":"Bee"
			}
		}
	]
}'

http GET http://localhost:8080/customer_management/A/questions

http GET http://localhost:8080/customer_management/EXP001/retrieve_subordinates

http GET http://localhost:8080/customer_management/EXP006/questionnaire

http DELETE http://localhost:8080/customer_management/EXP002/delete
```

## command postgreSQL
```sql
\dt   // show tables on the databas conected
\l    // list databases
\q    // exit from psql
```