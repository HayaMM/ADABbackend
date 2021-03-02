<img src="https://i.ibb.co/fnb0TYy/F4273-E7-D-3-D8-E-4-B93-A785-D26-AA09-BC177.png" width="100" height="100">

## ADAB Application: 
ADAB App is a web application that allows the user to sign up and log in to the website to perform the CRUD functionalities which are adding, editing, see the details of the Quotes, and deleting, the user can like or dislike the quotes, the user can also see the most like quote in ADAB website, the user can reset the password when forget the password. The app uses JWT for authentication and authorization. and there are three tables in the database: **User**, **Quote**, **Liked**. for more insight about our design of the database see this diagram [ERD](ADAB_ERD.png).

#### The Front End side:
The Frontend for ADAB application [here](https://git.generalassemb.ly/ghaidhusall/ADABfrontend).

#### Deployed sites:
* Front end: 
* Back end: 

#### Technologies used: 
ADAB Application uses a number of technologies:
* Java, Spring Boot 
* Server, Apache Tomcat
* MySQL Database
* React
* Cascading Style Sheets and Bootstrap 
* JavaScript and HTML
* AWS, to deploy the web application
* GitHub, group project
* Git Bash

#### Future Work:
The user of **ADAB** will be able to follow friends and be a part of the community of adab lovers to share ,rate quotes and poems.
#### Planning Documentation:
The planning documentation for two weeks to build adab website [here](Planing.jpg)


#### Catalog of Routes (paths and methods) that the API expects:
| Verb        | URI Pattern           |
| ------------- |:-------------:| 
|GET     | /profile | 
| GET     | /indexquote      |   
| GET | /index    |  
| GET | /info      |  
| GET | /getimg      |  
| GET | /detail:id      |  
| POST | /registration      |  
| POST | /authenticate      |  
| POST | /changepassword      | 
| POST | /reset_password      |   
| PUT | /edit      |  
| DELETE | /delete      |  


| Verb | URI Pattern |

| ------------- | ------------- |

| POST | /user/registration |
| POST | /user/authenticate |
| POST | /user/changepassword |
| PUT | /user/edit |
| DELETE | /user/delete |
| POST | /user/reset_password |

| Verb | URI Pattern |

| ------------- | ------------- |

| POST | /quote/add |
| GET | /quote/index |
| GET | /quote/detail |
| PUT | /quote/edit |
| DELETE | /quote/delete |

| Verb | URI Pattern |

| ------------- | ------------- |

| POST | /liked/add |
| GET | /liked/index |
| GET | /liked/detail |
| PUT | /liked/edit |
| DELETE | /liked/delete |
