# TheSemicolonsProject
Team Semicolon's Project for CodeFury

### Introduction ###
An Order Processing System provides a comprehensive interface to connect Companies to prospective Customers. An already burgeoning E-Commerce economy calls for scalable Order Processing Systems which promise a comfortable User Experience, maintain the Integrity of Orders, and protect the data of Customers and Companies alike. Through our system, Customers can place an order through the Employees of a company and receive a Detailed Quote regarding their order. If the requirements and the costs of the Order satisfy the Customer's needs, they can approve the same, following which, an Invoice can be generated. Further functionalities are discussed in the Overview section. 

### Overview ###
This Readme talks about the project developed by team "Semicolons" for the project competition "CodeFury" by HSBC as a part of the training program. This project works as an order processing system which consists of two important entities: Employee and Customer. 

* The __Employee__ has two major functionalities: 
1. Creating a new quote consisting of a list of products and sending it to a particular customer on the basis of the customer's ID or name. 
2. Importing a number of new products to the database, provided as a JSON file.

* The __Customer__ has the following responsibilities:
1. Choosing a particular quote sent by the employee and approving it.
2. Getting a display of invoices of the customer's previously approved orders. However the invoices will only be shown if the order has been approved more than a day ago.

### High Level View ###
![High Level View](https://github.com/dhruvinamdar/TheSemicolonsProject/blob/main/docs/High%20Level%20View.jpeg)

### Stack ###
* __Frontend__: `HTML`, `CSS`, `Bootstrap`, `JavaScript`, `AJAX`, `JSP`
* __Backend__: `Servlets`,`Java`, `MySQL`
* __Jar Files__:
  * JDBC Connector: `mysql-connector-java-8.0.25.jar`
  * JSON Functionality:  
    * `jackson-databind-2.4.3.jar`
    * `jackson-core-2.4.3.jar`
    * `jackson-annotations-2.4.3.jar`
  * JSTL Functionality:  
    * `jstl-1.2.jar`
    * `standard-1.1.2.jar`

* __Server__: `Apache Tomcat v9.0`
* __IDE__: `Eclipse IDE for Enterprise Java and Web Developers - 2021-06`
* __Data-interchange format__: `JSON`

### Scope ###

* __Employee__
  * The employee is capable of creating a new quote by choosing the products that need to be put. He has the choice of choosing a particular customer that already exists and then generate a new quote.
  * The employee can also import products to the database by providing a list of products in a JSON file. Only a specific structure of a JSON object is accepted. The employee is notified of the number of products that have been successfully imported.
  * Overheads like the Shipping Cost and the GST is dynamically calculated once the Employee creates the order.
  * No new employee can be created via the application as of now.

* __Customer__
  * The customer can see a list of quotes that he has received from the employee. Then he can choose to see a particular order and approve it.
  * The customer can also see a list of invoices of his previous orders (approved more than a day ago).
  * He can also print a PDF of his invoice.
  * No new customer can be created via the application as of now.

### Database Schema ###
![Database Schema](https://github.com/dhruvinamdar/TheSemicolonsProject/blob/main/docs/DBSchema.jpeg)

### Requirements ###
* `jdk 16`
* `mysql 8.0`
* `apache-tomcat 9.0.52`

### How to Run the Application ###
* Clone the project via HTTP/SSL
* Create the Database for the application in MySQL Workbench via the script given in the docs folder. 
* Import the .jars mentioned in the stack and add it to the Build Path via the Deployment Assembly Option in Eclipse.
* Run the project on an Apache Tomcat Server.
* Use the products.json file in the docs folder for testing on the Import Products Function
* Check database for valid Employee and Customer credentials
* Populate Customer and Employee tables with scripts given in docs

### Class Diagram ###
![Class Diagram](https://github.com/dhruvinamdar/TheSemicolonsProject/blob/main/docs/Class%20Diagram.jpg)

### Future Scope ###

 * Give Employee rights to create a new Customer and enter details.
 * Give Customer an option to select their Order on their own.
 * Add third-party Payment Gateway APIs.
 * Add images for the product catalogue.


