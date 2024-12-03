#Use OpenJDK runtime
FROM openjdk:21

#set the working directory to /app
WORKDIR /app

#Copy the current directory contents into the container at /app
COPY target/simioshuman-1.2.jar /app

#Env
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db-simios:5432/simioshumandb
ENV SPRING_DATASOURCE_USERNAME=simioshumanuser
ENV SPRING_DATASOURCE_PASSWORD=simioshumanpass
ENV SPRING_PROFILES_ACTIVE=prod
#Make the port available to outside.
EXPOSE 8080

#Run simiosHuman.jar when the container launches
CMD [ "java", "-jar", "simioshuman-1.2.jar", "--enable-preview" ]

