To install download the project and open it into your environment
For the app to work you will need to have Java (version 17) installed

to run open the command prompt and enter the working directory of the project (Task for LAT 2025)
then run:

./mvnw.cmd package

the target directory will be created
then run:

java -jar ./target/TaskForLAT2025-0.0.1-SNAPSHOT.jar

Available endpoints with example curls in Windows PowerShell if you are accessing the api via localhost:
GET /api/fundraising/box - returns all boxes, with their ids and whether it is empty, assigned

curl.exe -X GET http://127.0.0.1:8080/api/fundraising/box

GET /api/fundraising/event - returns all events, with their names, amount and currency

curl.exe -X GET http://127.0.0.1:8080/api/fundraising/event

POST /api/fundraising/box - adds a new box

curl.exe -X POST http://127.0.0.1:8080/api/fundraising/box -H "Content-Type: application/json" -d '{}'

POST /api/fundraising/event - adds a new event, with specified currency (or default if not specified) and a name (or empty if not specified)

curl.exe -X POST http://127.0.0.1:8080/api/fundraising/event -H "Content-Type: application/json" -d '{""currency"": ""USD"", ""name"":""Fundacja 1""}'

PUT /api/fundraising/box/{boxId}/event/{eventId} - assigns box of id boxId to event of id eventId (if the box isn't already assigned)

curl.exe -X PUT http://127.0.0.1:8080/api/fundraising/box/1/event/1

PUT /box/{id} - adds to the box of id the value specified in the content of the request

curl.exe -X PUT http://127.0.0.1:8080/api/fundraising/box/1 -H "Content-Type: application/json" -d '{""currency"": ""PLN"", ""amount"": ""100.00""}'

There are two endpoints to handle value transfer and, by extension, currency conversion. To use the external one (which uses Exchange-API) you need to add your own key in application.properties
PUT /api/fundraising/box/{id}/transfer/external - transfers all funds from the box into the account, after converting them to the proper currency using the external converter

curl.exe -X PUT http://127.0.0.1:8080/api/fundraising/box/1/transfer/external

PUT /api/fundraising/box/{id}/transfer/internal - transfers all funds from the box into the account, after converting them to the proper currency using the internal converter with hardcoded exchange rates
curl.exe -X PUT http://127.0.0.1:8080/api/fundraising/box/1/transfer/internal
