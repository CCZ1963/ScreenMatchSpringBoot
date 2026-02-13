# ScreenMatch - Búsqueda y Persistencia de Cine 🎬

ScreenMatch es una aplicación robusta desarrollada con **Spring Boot** que permite a los usuarios buscar información detallada sobre películas y series consumiendo la API de **OMDb** (Open Movie Database). A diferencia de una búsqueda simple, este sistema categoriza el contenido, gestiona múltiples calificaciones (Ratings) y mantiene un historial persistente en una base de datos relacional.

## 🚀 Tecnologías Utilizadas

- **Java 17**: Lenguaje principal del ecosistema.
    
- **Spring Boot 3.5.10**: Framework para la creación de la aplicación web y los servicios REST.
    
- **Spring Data JPA**: Para la gestión de la persistencia y mapeo objeto-relacional (ORM).
    
- **MySQL**: Motor de base de datos relacional para almacenar el historial, títulos y ratings.
    
- **Gson**: Biblioteca de Google para el parseo y deserialización personalizada de JSON.
    
- **Lombok**: Para reducir el código repetitivo (Boilerplate) mediante anotaciones como `@Data`.
    
- **Maven**: Gestor de dependencias y construcción del proyecto.
    

## 🛠️ Desafíos de Desarrollo y Soluciones

El desarrollo de este proyecto pasó por varias etapas críticas que demuestran el dominio de Java y Spring:

1. **Herencia Compleja con JPA**: Implementamos una estrategia de **`SINGLE_TABLE`** en la clase abstracta `Titulo`. Esto permitió unificar el almacenamiento de Películas y Series en una única tabla física (`titulos`), optimizando las consultas y la integridad de los datos mediante una columna discriminadora (`categoria_titulo`).
    
2. **Relaciones One-to-Many**: Se diseñó una relación relacional entre cada película/serie y sus diversas calificaciones (Ratings). Esto permite que por cada título guardado, se persistan múltiples fuentes de crítica (como IMDb o Rotten Tomatoes) en una tabla secundaria vinculada.
      
3. **Deserialización Polimórfica**: Creamos un `TituloDeserializador` personalizado para Gson. Gracias a esto, el sistema identifica automáticamente en tiempo de ejecución si la respuesta de la API es una `Pelicula` o una `Serie`, instanciando la clase correcta sin intervención del usuario .
    
4. **Optimización de API REST**: Se resolvieron problemas de recursión infinita en el JSON mediante el uso de `@JsonIgnore`, permitiendo una comunicación fluida entre el backend y el frontend.
    

## 💻 Instalación e Implementación

Para ejecutar este proyecto en tu entorno local (especialmente optimizado para **Ubuntu 24.04**), sigue estos pasos:

### 1. Requisitos Previos

- Instalar JDK 17: `sudo apt install openjdk-17-jdk`
    
- MySQL Server funcionando.
    
- Una API Key de OMDb (actualmente configurada en el servicio ).
    

### 2. Configuración de Base de Datos

Edita el archivo `src/main/resources/application.properties` con tus credenciales locales:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/screenmatch
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

 Crear Base de Datos y Usuario (lo hice utilizando la cuenta root o ubuntu en OCI):
    
    (Copia y pega línea por línea. Asegúrate de poner la MISMA contraseña que tienes configurada en tu proyecto Spring Boot).

```sql
CREATE DATABASE flighton;
CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'tu_password_aqui';
GRANT ALL PRIVILEGES ON flighton.* TO 'usuario'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

### 3. Ejecución

Puedes correr la aplicación desde tu IDE favorito (como IntelliJ IDEA) o mediante la terminal con el wrapper de Maven:

```bash
./mvnw spring-boot:run
```

## 🌐 Endpoints Principales

- **Buscar por Título**: `GET /api/buscar?titulo={nombre}` (Funciona para películas y series).
    
- **Ver Historial**: `GET /api/historial` (Muestra todas las búsquedas persistidas).
    
- **Búsqueda Múltiple**: `GET /api/buscar-multiple?termino={palabra}`.
    

## 📝 Próximos Pasos

- [ ] Implementar la interfaz gráfica web definitiva con búsqueda por Actor.
       
- [ ] Implementar un sistema de autenticación de usuarios.
    
