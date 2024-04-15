# Ionix
# Proyecto de Gestión de Usuarios y Consulta a API Externa

Este proyecto consiste en una aplicación que ofrece servicios REST para la gestión de usuarios y consulta a una API externa cifrando parámetros con DES.

## Arquitectura

El proyecto sigue una arquitectura basada en el patrón de diseño MVC (Modelo-Vista-Controlador), donde encontramos las siguientes capas: 

1. Capa de Configuración (configuration)
2. Capa de Controladores (controller)
3. Capa de DTO (Data Transfer Objects)
4. Capa de Entidades (entities)
5. Capa de Repositorio (repository)
6. Capa de Seguridad (security)
7. Capa de Servicios (service)
8. Capa de Validación (validation)

## Tecnologías Utilizadas
- **Java** 17
- **Spring Boot** 3.2.4
- **MySQL** 8.0.36

## Dependencias del Proyecto

- **Spring Boot Starter Data JPA**: 3.2.4
- **Spring Boot Starter Validation**: 3.2.4
- **Spring Boot Starter Web**: 3.2.4
- **Spring Boot DevTools**: 3.2.4
- **MySQL Connector/J**: 
- **Spring Boot Starter Test**: 3.2.4
- **Spring Boot Starter Security**: 3.2.4
- **JJWT API**: 0.12.3
- **JJWT Implementation**: 0.12.3
- **JJWT Jackson**: 0.12.3

## Instalación

1. Clona este repositorio en tu máquina local.
2. Configura una base de datos MySQL local con el nombre `ionix` y actualiza la configuración en el archivo `application.properties`.
3. Ejecuta la aplicación Spring Boot.

## Uso

Una vez que la aplicación esté en funcionamiento, puedes utilizar los siguientes servicios REST para interactuar con ella.

## Servicios REST

### Creación de Usuarios

- **Método:** POST
- **Endpoint:** /api/users
- **Descripción:** Crea un nuevo usuario.
- **Parámetros de Solicitud:**
  - Name (nombre del usuario)
  - Username (nombre de usuario)
  - Email (correo electrónico del usuario)
  - Phone (número de teléfono del usuario)

### Listado de Usuarios Registrados

- **Método:** GET
- **Endpoint:** /api/users
- **Descripción:** Obtiene un listado de todos los usuarios registrados.

- **Método:** GET
- **Endpoint:** /api/users/{id}
- **Descripción:** Obtiene un usuario fitrado por el id.


### Obtención de Usuario por Email

- **Método:** GET
- **Endpoint:** /api/users/{email}
- **Descripción:** Obtiene un usuario registrado por su dirección de correo electrónico.

### Eliminación de Usuario

- **Método:** DELETE
- **Endpoint:** /api/users/{id}
- **Descripción:** Elimina un usuario por su ID.

## Servicio POST para Consulta a API Externa

- **Método:** POST
- **Endpoint:** /api/mockaroo
- **Descripción:** Realiza una consulta a una API externa cifrando el parámetro recibido con DES y enviándolo como parte de la URL. Se incluye un header X-API-Key con un valor específico.

## Implementación de Cifrado DES

La implementación del cifrado DES se realiza utilizando la clase `DESKeySpec` y `Cipher` de Java.

## Formato de Respuesta del Servicio

El servicio retorna un objeto JSON con la siguiente estructura:

{
  "responseCode": 0,
  "description": "OK",
  "elapsedTime": 245,
  "result": {
              "registerCount": NN
            }
}


El campo `elapsedTime` contiene el tiempo de ejecución del servicio "search" en milisegundos.

El campo `registerCount` contiene la cantidad de registros `items` devueltos por la API externa.

## Pruebas Unitarias

Se incluyen pruebas unitarias para verificar el correcto funcionamiento de los servicios.

## Documentación Adicional

Se proporciona documentación adicional en forma de comentarios en el código, archivos de configuración y en este archivo README.

## Licencia


