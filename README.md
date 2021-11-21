# Usuarios crean Post CRUD

Proyecto con Usuarios que pueden publicar post y aplicar mensajes CRUD(con Soft Delete) a los mismos, tambien se les puede
hacer consultas para obtener todos los post con un titulo o categoria especifica. 
Ademas un usuario obtiene su token cuando inicia sesion, para poder hacer peticiones HTTP a diferentes endpoint. 

El proyecto fue testeado con JUnit y la documentacion fue realizada en [Postman](https://documenter.getpostman.com/view/16938604/UVJWqKw2).

### Dependencies
- Spring Web
- Spring Security
- Java Persistence API
- PSQL
- Json Web Token
- Model Maper
- Lombok