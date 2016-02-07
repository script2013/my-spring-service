curl -H "Content-Type: application/json" -XPUT 'localhost:8080/counters/inc' -d '
{
  "userId": "stefan",
  "pageId": "hello" 
}'
