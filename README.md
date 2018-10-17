# mutant

To run this project execute

`./gradlew bootRun`

This will start app at http://localhost:8080.

To check if someone is a mutant, paste the following in a terminal (change payload as needed):

```
curl -X POST \
  http://localhost:8080/mutant \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}'
```

To check app stats for mutants, use the following command:

```
curl -X GET \
  http://localhost:8080/stats \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache'
```
