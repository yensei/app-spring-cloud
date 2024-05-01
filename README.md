# app-spring-cloud
Practica para recordar como utilizar spring cloud
---
> **IMPORTANT**
* Se debe agregar al /etc/hosts para pasar los test
~~~
127.0.0.1       config-server
~~~
> **IMPORTANT**
* Para compilar los modulos products, shopping, customer requiere que este funcionando config-server para traer la configuracion de la base de datos, de lo contrario se debe deshabilitar el uso de config-server y colocar la configuración en application.yml del modulo correspondiente
---
# Base de datos
## Estandar de nombres
**__[t_mod_tablename]__**
* **t**  = Tipo de objeto
* **md** = modulo, debe tener 2 caracteres
* **nombre** = nombre relacionado a los datos que contiene, si tiene mas de un nombre se debe concatenar con '_', las primeras palabras con 3 caracteres y la última utilizar el texto completo **Ejemplo:** tabla invoice item --> tl_mod_inv_item, tabla invoice item historic --> tl_mod_inv_itm_historic

### Tipos de objeto ( a nivel de BD )
- **tl**  = tablas
- **tr**  = triggers
- **pr**  = procedimientos
- **vw**  = views
- **fn**  = funciones

### Modulos
- **sto** = store
- **caj** = cash = caja
- **mov** = movimientos 
- **acc** = accounts = cuentas contables
- **usr** = users =  usuarios
- **cus** = customers


### Tablas 
#### Modulo: Productos (Tienda) o negocio
- **tl_prd_product** : 
- **tl_prd_category** : 
___
# Docker
## Commands Utils
~~~bash
# Borrar los contenedores detenidos
docker rm $(docker ps -a -q)
# Ver logs 
docker logs <image hash id>
# Acceder a la consola 
docker exec -it <image id hash> sh 
# Levantar todos los contenedores con docker compose
docker compose --file 'docker-compose.yml' --project-name 'app-spring-cloud' start 
 ~~~
Para utilizar docker se crearon en cada modulo su correspondiente Dockerfile. 
## Crear contenerdor y deplegar
1. **Config Server**
~~~bash
# Ir a la carpeta del modulo
cd config-server
# Compilar con maven
mvn clean package
# Docker build & run
docker build --tag=app-spring-cloud/config-server:latest .
docker run -p 8888:8888 app-spring-cloud/config-server
~~~
2. **Eureka Server**
~~~bash
# Ir a la carpeta del modulo
cd config-server
# Compilar con maven
mvn clean package
# Docker build & run
docker build --tag=app-spring-cloud/eureka-server:latest .
# Revisar si la imagen se creo 
docker images -a
# Desplegar la imagen
docker run -p 8099:8099 app-spring-cloud/eureka-server
~~~
5. **Shopping**
~~~bash
# Ir a la carpeta del modulo
cd shopping
# Compilar con maven
mvn clean package
# Docker build & run
docker build --tag=app-spring-cloud/shopping:latest .
# Revisar si la imagen se creo 
docker images -a
# Desplegar la imagen
docker run -p 8093:8093 app-spring-cloud/shopping
~~~

7. **Utilizando docker compose**
~~~bash
# Ingresar a la carpeta scripts del root
cd scrips
# Levantar los servicios
docker-compose up
~~~

## Networking
Utiliza el tipo de red por defecto
* Ver si los contenedores se lanzan como bridge
`docker network inspect bridge`
