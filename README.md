## Executes the accesses to and modifications of the university database as specified in Exercises 3.11 a-d and 3.12 a-d in the Database System Concepts book

Authors: Evan Arroyo and Joshua Sims  
Date: 2017-04-11

### To run the program
* Navigate to postgresql cmd line
* Run university_ddl.sql
* Run university_copy.sql
* Return to BASH
* Compile all .java files
* Run Exercise311
* Navigate to postgresql cmd line
* Run university_drop_ddl.sql
* Run university_copy.sql 
* Run Exercise312

### Location of the four kinds of JDBC statements
* executeUpdate occurs in Exercise312
* executeQuery occurs in Exercise311 and Exercise312
* prepareStatement occurs in Exercise312
* getMetaData occurs in both Exercise311 and Exercise312

### Bugs
* An error is thrown at the end of the Exercise311 test regarding a column out of bounds -- we do not understand why it is thrown
