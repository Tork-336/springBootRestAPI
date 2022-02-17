

# PRUEBA SPRINGBOOT Y ANGULAR

Se necesita crear una solución de arquitectura orientada a servicios REST, basado en
lenguaje java con framework springboot y maven como tecnología de manejo de
dependencias, la base de datos de su elección, (Mysql, Postgress, Oracle, etc). La idea es
crear los siguientes endpoints y las siguientes vistas (de preferencia usar framework angular 2
en adelante):
<br/>
<br/>
1. Listar todos los productos. 
2. Listar de manera paginada los productos, de 10 en 10. 
3. Guardar un producto. 
4. Editar un producto. 
5. Eliminar un producto. 
6. Consultar un producto por nombre
7. Listar todos los productos con precio menor a cierto valor. 
8. Listar todos los productos que inicien con un nombre especifico.
<br/>
<br/>
<br/>


### Back-end API Rest

Se definio los siguientes endpoints:

La respusa para los endpoints en una accion exitosa tendran el siguiente
body:

#### Response de Servicio

````
{
    "data": [],
    "total": 0,
    "success": true
}
````

Si la accion a realizar no fue exitosa la property "success", tendra como
valo "false".
<br/>
<br/>
#### Obtener productos

TIPO: GET<br/>
URL: "http://host-name/product?page=3&size=3&all=false"
Parametros: page, size y all

El valor para el paramentro "all" = true, permite obtener todos los registros de la entidad
Product. Lo parametros de paginacion se envian con "all" = false. Obtedra 
registros de la entidad Product paginado.

#### Crear productos

TIPO: POST<br/>
URL: "http://localhost:8080/product"

El siguiente endpoind permite crear, enviado un body en formato JSON del registro.
Es posible enviar un array de JSON para crear registros por lotes.

body:

```
{
    "products":[
        {
            "description": "Producto013", 
            "expiryDate":"", 
            "name": "Producto013", 
            "price": 376.000,
            "categorySet":[
                {
                    "id": 1
                }
            ]
        }
    ]
}
```
<br/>

#### Actualizar productos

TIPO: UPDATE<br/>
URL: "http://localhost:8080/product"

El siguiente endpoind permite actualizar, enviado un body en formato JSON del registro.
Es posible enviar un array de JSON para crear registros por lotes.

````
[
    {
        "id": 5,
        "description": "Producto005 - Ha cambiado...!! ", 
        "creationDate": "2022-02-03T22:04:57" ,
        "expiryDate":"", 
        "name": "Producto005", 
        "price": 107
    }
]
````
<br/>
<br/>

#### Eliminar productos

TIPO: DELETE<br/>
URL: "http://localhost:8080/product"

El siguiente endpoind permite eliminar, enviado un body en formato JSON del registro.
Es posible enviar un array de JSON para crear registros por lotes.

````
[
    {"id": 15}
]
````
<br/>

#### Filtrar productos

TIPO: POST<br/>
URL: "http://localhost:8080/product/filter"

El siguiente endpoind permite filtra, enviado un body en formato JSON donde el valor
de "name" sera por cual filtro obtener registros.

Filtros definidos:

- Por nombre del producto: "name": "name"
- Por precio menor al valor enviado: "name": "price"
- Por primer nombre del producto: "name": "nameFirts"


````
{
    "name": "name",
    "value": "Producto013"
}
````
