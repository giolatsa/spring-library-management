### DISCLAIMER
Please consider that I hadn't yet learnt the technologies that were needed to complete the task,
those topics are scheduled later my EPAM Upskill course so with my current knowledge it was impossible to complete any part of the task.
Past few days I spent learning just enough Spring Boot , Database Access , REST API development 
to be able to at least implement foundational functionality of the program. Unfortunately I wasnt able 
to learn about Spring Security , User Interface , Thymeleaf in time . But those topics were very interesting
and I will definitely be researching them as well 


### SET UP

1. To set up the database run :
``docker-compose up``
It is set in a way to automatically create postgres with library database

2. run ``mvn package`` 

3. open cmd in target folder where mvn package created the jar file
4. run ``java -jar library-app.jar``

I have also included Library.postman_collection.json file to import
inside postman client to easily test the application