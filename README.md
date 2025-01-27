# Task Manager

Um gerenciador de tarefas simples desenvolvido com Spring Boot.

## Pré-requisitos

- JDK 17+
- Maven 3.6+
- PostgreSQL (opcional, para o perfil `postgres`)

# Requisições da api

![image](https://github.com/user-attachments/assets/38a98e1d-a659-40b5-b7aa-9d617c25c530)


![image](https://github.com/user-attachments/assets/b498f1d6-ac63-42db-b160-eca2c8f0f750)


![image](https://github.com/user-attachments/assets/88492c36-0786-4b54-909f-5c8e7c4d7dad)


![image](https://github.com/user-attachments/assets/b4876229-f1ce-4617-86e1-99185996c516)


![image](https://github.com/user-attachments/assets/48d905a5-7bcb-4eb2-a3f3-2e66920de4c0)


![image](https://github.com/user-attachments/assets/70939bb1-4d67-429e-a752-a91109e0990a)


![image](https://github.com/user-attachments/assets/4acddf7c-dfc4-4eb6-876d-b967d81aa86f)


Obs: caso queira usar o h2 database para usar a api, apenas altere o perfil para o nome h2 no applicationn.yml que fica no pacote resources, caso for usar o postgreSQL, ceritifique-se de que ele está devidamente configurado!

# Rodando o projeto nos perfis do postgre e do h2

PostgreSQL: mvn spring-boot:run -Dspring-boot.run.profiles=default 
H2: mvn spring-boot:run -Dspring-boot.run.profiles=h2

Acesso ao H2 database:

Abra o navegador e acesse http://localhost:9090/h2-console.

Use as seguintes credenciais:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: password
