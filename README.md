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

http POST http://localhost:8080/preguntas-enroll/EXP006/enrollment_questionnaire \
  Content-Type:application/json \
  <<< '[{"id":{"noPregunta":1},"respuestaPregunta":"Respuesta 0"},{"id":{"noPregunta":2},"respuestaPregunta":"Respuesta 0"},{"id":{"noPregunta":3},"respuestaPregunta":"Respuesta 0"},{"id":{"noPregunta":4},"respuestaPregunta":"Respuesta 0"},{"id":{"noPregunta":5},"respuestaPregunta":"Respuesta 0"}]'
  
http GET http://localhost:8080/preguntas-enroll/EXP006/questionnaire

http POST http://localhost:8080/preguntas-enroll/EXP002/validate_questionnaire \
  Content-Type:application/json \
  <<< '[
  {
    "id": { "expediente": "EXP002", "noPregunta": 1 },
    "respuestaPregunta": "No",
    "estatus": "A",
    "fechaHora": "2025-06-30 11:00",
    "catalogoPreguntas": null
  },
  {
    "id": { "expediente": "EXP002", "noPregunta": 4 },
    "respuestaPregunta": "Polen",
    "estatus": "A",
    "fechaHora": "2025-06-30 11:05",
    "catalogoPreguntas": null
  }
]'
```

## command postgreSQL
```sql
\dt   // show tables on the databas conected
\l    // list databases
\q    // exit from psql
```