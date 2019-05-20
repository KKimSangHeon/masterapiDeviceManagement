FROM java:8
ADD target/api-template-0.0.4.war/ /home/warCollection/masterAPI.war
ADD properties/local/application.properties /home/propertiesCollection/application.properties
EXPOSE 8280:8280

ENTRYPOINT ["java","-jar","-Dproperties.path=/home/propertiesCollection","/home/warCollection/masterAPI.war"]
