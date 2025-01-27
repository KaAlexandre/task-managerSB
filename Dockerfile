# Use uma imagem base com Java 21
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/task-manager-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponha a porta que a aplicação usa
EXPOSE 9090

# Execute a aplicação ao iniciar o contêiner
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
