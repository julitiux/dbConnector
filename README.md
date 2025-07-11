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

http GET http://localhost:8080/preguntas-enroll/A/questions

http POST http://localhost:8080/preguntas-enroll/EXP010/enrollment_questionnaire \
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


  
http GET http://localhost:8080/preguntas-enroll/EXP006/questionnaire

http POST http://localhost:8080/preguntas-enroll/EXP002/validate_questionnaire \
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

http GET http://localhost:8080/preguntas-enroll/EXP001/retrieve_subordinates
```

## command postgreSQL
```sql
\dt   // show tables on the databas conected
\l    // list databases
\q    // exit from psql
```