# Challenge ONE | Back End | Foro Alura 


![Foro_Alura_logo](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/495dc591-ec52-41d3-a199-2c033cd9d7e6)

</p>
<p align = "center">
<img src="https://img.shields.io/badge/_Lenguaje-190202?style=flat"/><img src="https://img.shields.io/badge/_Java-F20000?style=flat"/>
</p>

- ### :computer: Tecnologías utilizadas:

  - [Eclipse](https://www.eclipse.org/)
  - [MySql](https://www.mysql.com/)
  - [Java](https://www.java.com/en/)

  - [Spring Security](https://start.spring.io/)
  - [Token JWT](https://jwt.io/)

### Diseño de base de datos:

![DeseñoBaseDeDatos](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/3cdb9458-1b8a-44a6-990a-551a43c7c0ce)


### Descripción  :page_facing_up:

El foro alura es un lugar donde todos los alumnos de la plataforma alura
pueden colocar sus preguntas sobre determinados cursos, este mágico lugar está lleno de mucho aprendizaje y de colaboración entre alumnos, profesores y moderadores.

Ya sabemos para que sirve el foro y sabemos cómo se ve, pero ¿sabemos cómo funciona por detrás? Es decir ¿dónde se almacenan las informaciones? ¿cómo se tratan esos datos para que se relacione un tópico con una respuesta, o como se relacionan los usuarios con las respuestas de un tópico?

Ese es nuestro desafío, vamos a replicar a nivel de back end este proceso, y para eso crearemos una API REST usando Spring.

Nuestra API va a centrarse específicamente en los tópicos, y debe permitir a los usuarios:

    

## Crear un nuevo tópico :o: 
  Registro de un nuevo tópico
  La API debe tener un endpoint para el registro de nuevos tópicos y debe aceptar solicitudes POST para el URI /topicos.

  Los datos del tópico( titulo, mensaje, autor y curso) deben enviarse en el cuerpo de la solicitud, en formato JSON.

  Reglas del negocio
  Todos los campos son obligatorios.
  La API no debe permitirlos registros duplicados (que contengan el mismo el mismo título y mensaje)

![New Topic](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/4cd6f1aa-8268-4698-b7e9-ddd4e717144e)

  

## Mostrar todos los tópicos creados :o: 
  La API debe tener un endpoint para la lista de todos los tópicos y debe aceptar solicitudes GET para el URI /topicos

  Los datos del tópico (titulo, mensaje, fecha de creación, estatus autor y curso) deben devolverse en el cuerpo de la respuesta, en formato JSON.

  ![Get Topics](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/ffd327f3-6132-4900-90ce-35fd5d27983b)


## Mostrar un tópico específico :o:

  La API debe tener un endpoint para la lista de todos los tópicos y debe aceptar solicitudes GET para el URI /topicos/{id}

  Los datos del tópico (titulo, mensaje, estatus, autor y curso) deben devolverse en el cuerpo de la respuesta, en formato JSON.

  ![iGet a Topic](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/b2597341-c6e5-45f7-943e-e0f7d72eef35)

  
## Actualizar un tópico :o:

  La API debe tener un endpoint para el registro de nuevos tópicos y debe aceptar solicitudes PUT para el URI /topicos/{id}

  Observación: Sí no existe el tema a actualizar, regresará un mensaje de error.
      
  ![PUT Topic](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/4652dc67-8109-4e0d-8790-73f03f8b13b9)

    
## Eliminar un tópico :o:

  La API debe tener un endpoint para la eliminación de tópicos y debe aceptar requisiciones DELETE para el URI /topicos/{id}

  Observación: Al eliminar un tema regresará un 204 Not Content afirmando la desactivación del tema

  ![Delete Topic](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/b7877cd5-68c1-482d-90ad-ba242dd0e850)


## Recuerda que esto es un desafio opcional y que puedes hacer otras implementaciones incluso si no están siendo sugeridas aqui :star:
  Implementa otras rutas en tu aplicación

  Para nuestro foro estar completo deberíamos tener otras rutas o endopints que nos permitirán crear, listar, actualizar y eliminar otras informaciones además de los tópicos como:

  /usuario <br>
  /respuestas

  También están implementadas las entradas para /Usuarios (Users), /Answers (Respuestas), /Courses (Cursos):

  /Usuarios
  ![Users Controllers](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/f6841946-8f48-484f-b83e-c0ee681e61c9)

  <br>
  /Respuestas <br>
  
  ![Answers Controllers](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/e3db18f3-b486-434c-93f5-ab725e0619b9)

  <br>
  /Cursos <br>
  
  ![Course Controllers](https://github.com/Valamca/Foro_Alura_API_REST/assets/129345721/a4836e54-dd60-4c7a-a977-c818dd5f4f91)

  <br>

**Desarrollador** :wink: 

 <img src="https://avatars.githubusercontent.com/u/129345721?v=4" width=115>
 
 **Francisco Valam Cortes**  <br>[<sub>GitHub</sub>](https://github.com/ValamCA) <img src="https://i.postimg.cc/hPxhb2YB/icons8-github-50.png" width =16>
 <br>[<sub>Linkedin </sub> ](https://www.linkedin.com/in/franciscovalamca/)<img src="https://i.postimg.cc/C5LJHycc/icons8-linkedin-48.png" width =16 ><br>
 [<sub>Twitter</sub>](https://twitter.com/FNiggalam)<img src="https://i.postimg.cc/xTrL2ND9/icons8-twitter-48.png" width =16 ><br>
  
