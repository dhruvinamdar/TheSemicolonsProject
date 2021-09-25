# TheSemicolonsProject
Team Semicolon's Project for CodeFury

### Overview ###
This Readme talks about the project developed by team "Semicolons" for the project compeitition "CodeFury" by HSBC as a part of the training program. This project works as an order processing system which consists of two important entities: Employee and Customer. 

* The __Employee__ has two major functionalities: 
1. Creating  a new quote consisting of a list of products and sending it to a particular customer on the basis of the customer's ID or name. 
2. Importing a number of new products to the database, provided as a JSON file.

* The __Customer__ has the following responsibilities:
1. Choosing a particular quote sent by the employee and approving it.
2. Getting a display of invoices of the customer's previously approved orders. However the invoices will only be shown if the order has been approved more than a day ago.

### Stack ###
* __Frontend__: `HTML`, `CSS`, `Bootstrap`, `JavaScript`, `AJAX`, `JSP`
* __Backend__: `Servlets`,`Java`, `MySQL`
* __Jar Files__:
  * JDBC Connector: `mysql-connector-java-8.0.25.jar`
  * JSON Functionality:  
    * `jackson-databind-2.4.3.jar`
    *  `jackson-core-2.4.3.jar`
    *  `jackson-annotations-2.4.3.jar`
* __Server__: `Apache Tomcat v9.0`
* __IDE__: `Eclipse IDE for Enterprise Java and Web Developers - 2021-06`
* __Data-interchange format__: `JSON`

### Scope ###

* __Employee__
  * The employee is capable of creating a new quote by choosing the products that need to be put. He has the choice of choosing a particular customer that already exists and then generate a new quote.
  * The employee can also import products to the database by providing a list of products in a JSON file. Only a specific structure of a JSON object is accepted. The employee is notified of the number of products that have been successfully imported.
  * No new employee can be created in the application as of now.

* __Customer__
  * The customer can see a list of quotes that he has received from the employee. Then he can choose to see a particular order and approve it.
  * The customer can also see a list of invoices of his previous orders (approved more than a day ago).
  * He can also print a pdf of his invoice.
  * No new customer can be created in the application as of now.

### Requirements ###

### Database Schema ###
![Database Schema](https://github.com/dhruvinamdar/TheSemicolonsProject/blob/main/dbSchema.png)
