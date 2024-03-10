This is a simple app that uses Spring MVC and MySQL DB
It has a list of tasks which you could observe, edit, delete or add new using front-end part

By default it should be run from docker container. First of all run mvn clean install. Then use docker-compose.yaml
After compose fill the database using dump.sql

You could also run it from Tomcat server directly without making a docker container
Make sure that you have MySQL DB up already and filled with dump.sql
After this make some changes in <head> section of src/main/webapp/html/tasks.html
and some another changes in com/javarush/config/AppConfig.java in getDataSource() function
Changes are already commented out
