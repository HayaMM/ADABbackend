## ADAB Application:
ADAB App is a web application that allows the user to sign up and log in to the website to perform the CRUD functionalities which are adding, editing, see the details of the Quotes, and deleting and the user can like or dislike the quotes, the user can also see the most like quote in ADAB website, the user can reset the password when forget the password. The app uses JWT for authentication and authorization. and there are three tables in the database: User, Quote, Liked. for more insight about our design of the database see this diagram [ERD](ADAB_ERD.png).

#### The Front End side:
The Frontend for ADAB application [here](https://git.generalassemb.ly/ghaidhusall/ADABfrontend).

####Deployed sites:
* Front end: 
* Back end: 

#### Technologies used: 
ADAB Application uses a number of technologies:
* Java.
* Spring boot.
* Tomcat server.
* CSS , Bootstrap.
* React.
* MySQL.

#### Future Work:

#### Planning Documentation:

#### Catalog of Routes (paths and methods) that the API expects:

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
