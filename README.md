#Embedded DB with Spring JPA

This is a sample application using embedded DB(PostgreSQL) with Spring JPA repositories.
At present, none of the data is persisted to actual DB, but once the application is up, values persist for that session.

Sample is a Library system, where member can issue copies of books against their name. Books have multiple authors and authors can have multiple books.
Member can have multiple issued against them and if a copy is borrowed or not is maintained.
The project is currently based on simple CRUD calls for all the entities. Relationships between entiites can be driven based on business flows which will be done in future.
The aim of the project is to showcase embedded DB, Spring JPA, and this is based on Spring 4.1 and is using a completely XML-less approach
A scan is made for Web app initializer and the REST config utility is called which scans all packages and initializes beans in application context

Please feel free to suggest changes
