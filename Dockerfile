# Étape 1 : build Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Copier uniquement le pom.xml et télécharger les dépendances en cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et builder le JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : image d'exécution légère
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port de ton API
EXPOSE 8080

# Lancer le JAR
ENTRYPOINT ["java","-jar","app.jar"]
