# Monitoring measurements

Application receive and store measurements (gas, cold and hot water usage) by existing user.
Opportunity  to create user from current application is absent.
System has two RESTful services:
For getting the history of previously submitted measurements for a given user
```sh
GET
/monitoring/getValues/{login}
```

Example response body:
```sh
{
    "code": 200,
    "errorMessage": null,
    "histories": [
        {
            "eventDate": 1530962614411,
            "gasValue": 3,
            "coldValue": 3,
            "hotWaterValue": 3
        },
        {
            "eventDate": 1530962688482,
            "gasValue": 4,
            "coldValue": 4,
            "hotWaterValue": 4
        },
        {
            "eventDate": 1530962698922,
            "gasValue": 5,
            "coldValue": 5,
            "hotWaterValue": 5
        }
    ]
}
```

And for submitting the current measurements for a given user
```sh
POST
/monitoring/submit
```

Example request body for submit service:
```sh
{
	"login" : "login",
	"pin" : "pin:,
	"gasValue" : 0,
	"coldValue" : 0,
	"hotWaterValue" : 0
}
```

Example response body:
```sh
{
	"code" : 400,
	"errorMessage":"User by requested login is invalid"
}
```

### How to run
 - Before using need to set path to db and correct user (in beans.xml bean with id="dataSource")
 - For running use tomcat7 plugin (tomcat7:run)