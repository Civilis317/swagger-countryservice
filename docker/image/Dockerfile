ARG SOURCE_IMAGE

FROM $SOURCE_IMAGE

COPY countryservice*.jar countryservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "/countryservice.jar"]