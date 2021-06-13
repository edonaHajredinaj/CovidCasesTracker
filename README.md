## Covid Cases Tracker

#### Technologies used
- Java
- Mysql
- Spring Boot
- JPARepository

#### Database Tables

| cases      |  |
| ----------- | ----------- |
| PatientID (increment)       | int(11) unsigned |
| FullName    | varchar(25)        |
| BirthDate      | date  |
| Age       | int(11)  |
| Email    | varchar(25)       |
| CasePeriod    | varchar(25)       |
| City    | varchar(25)       |
| UnderlyingDisease     | tinyint(1) |



## REST API Routes/Endpoints

### CovidCases API

###### path = "api/v1/covidcases"
Open in HTTP client

- `GET covidcases` returns all cases.
- `GET products/{id}` returns a case by id and the persons information.
- `POST covidcases` saves a case, the body of the request:
	
```
{
  "birthday": "1981-09-23",
  "casePeriod": "2021-06-10 16:47:00",
  "city": "Porto",
  "email": "marzia@email.com",
  "fullName": "Marzia Dikushi",
  "underlyingDisease": true
}
```
		
- `PUT covidcases/{id}` updates a case with the new/correct information, the body of the request can contain any of the fields:

```
{
  "birthday": "1992-05-18",
  "casePeriod": "2021-06-10 16:47:00",
  "city": "Lagos",
  "email": "maria1992@gmail.com",
  "fullName": "Maria Pront",
  "underlyingDisease": false
}
```




## The Code Challenge

Create a Java Backend Web-Service for the purpose of tracking COVID-19 cases.

More specifically,  create a Spring Boot application in Java that incorporates a database. The database can be any of your choice, file based or DBMS.

Basic operations that your app should support:
- Insertion of a new case.
- Updating an existing case.
- Finding a case by an ID of your choice (e.g. patient’s ID).
- Viewing statistics that result from your data.

For each COVID-19 case you should try to store the following data (but you may add more of your choice):
- Patient ID.
- Full Name.
- Date of birthfor the patient.
- Timestamp for the COVID case.
- City (Location of the patient).
- Underlying diseasefor the patient(true/false if exists or not).

Additional info:
• For long lasting operations, background threading shouldbe utilized.
• Inside your web service you are expected to use at least two Design Patterns of your choice (please use comments inside your code to clearly show its use).
• For the creation of statistics (some basic ones) you are expected to use the Java Stream API.
• Optionally, you may try to upload a file regarding each case (e.g. a scanned image with the patient’s medical exams).
