# app-spring-cloud
Practica para recordar como utilizar spring cloud

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
Para utilizar docker se crearon en cada modulo su correspondiente Dockerfile. 
## Crear contenerdor y deplegar
1. **Config Server**
~~~bash
# Ir a la carpeta del modulo
cd config-server
# Docker build & run
docker build --tag=config-server:latest .
docker run -p 8888:8888 config-server:latest
~~~