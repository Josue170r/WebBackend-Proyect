# WebBackend-Proyect
Repositorio para la materia de web client backend

# Variables de entorno
    1.- Ubicar el nombre del proyecto en la barra de compilación
    2.- Dirigirse a Edit Configurations
    3.- Ubicar el botón Modify options
    4.- Agregar la opción Enviroment variables 
    5.- Seleccionar el archivo .postgres de la carpeta /.envs/.local

# Docker compose 
    1- Setear la variable de entorno: 
        set COMPOSE_FILE=docker-compose.local.yml
    2.- Crear el contenedor
        docker compose build
    3. Levantar el contenedor con la imagen de postgres
        docker compose up
