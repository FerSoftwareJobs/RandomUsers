# Estructura del proyecto

## app

Inicialización de la aplicación de Hilt 

## data

Contenedor que referencia a la capa de datos, contiene las constantes, los mappers, repositorios, clases de datos de respuesta y otras clases relacionadas con los recursos de datos remotos

## di

Contiene los módulos del inyector de dependencias (Hilt)

## domain

Contenedor que referencia a la capa de dominio, contiene las clases de datos de la capa de dominio y los casos de uso

## ui

Contenedor que referencia a la capa de interfaz de usuario, contiene la actividad principal, fragmentos, adaptadores y viewmodels

## utils

Contiene una clase span custom

<br>
<br>
<br>

# Herramientas utilizadas
- Actividad principal anfitriona y fragmentos
- Navigation compose y safe args
- ListAdapter
- Comunicación con servicio remoto a través de Retrofit2
- Inyección de dependencias con Dagger Hilt
- MVVM
- Corrutinas
- Glide
- Google Maps API
- Mockk para testing
- Test unitarios

<br>
<br>
<br>

# Problemas encontrados en el desarrollo
- A partir de la API 29 no se puede configurar el color de los iconos de la barra de estado y las únicas opciones de configuración son el color gris para temas claros y el color blanco para temas oscuros.
- El color del icono de navegar hacia atrás en la barra de herramientas depende del color secundario de la aplicación, ya no se puede cambiar mediante los métodos anteriores como la propiedad naigationIconTint de la toolbar.
- He tenido que modificar la clave de la API de google maps para que no sea válida al subirla en un repositorio público, cambiando el valor en el archivo AndroidManifest.xml por una clave válida se activa la funcionalidad.

<br>
<br>
<br>

# Funcionalidades y mejoras implementadas
- Se ha desarrollado un mecanismo de scroll infinito que se encarga de cargar los usuarios de 20 en 20 (valor configurable a partir de una constante en el archivo AppConstants.kt), el listado se actualiza de forma imperceptible para el usuario, por otro lado, se ha bloqueado la actualización del listado mientras se muestran los resultados del filtrado.
- El comportamiento del filtrado en el menu no estaba definido previamente, la solución por la que se ha optado consiste en la utilización de un searchView para la introducción del texto para el filtrado junto con un desplegable con dos opciones (filtrar por nombre o email) que activan la búsqueda, la búsqueda se desactiva al borrar por completo el texto a buscar o al pulsar en la "x" del searchView.

<br>
<br>
<br>

P.D: he disfrutado mucho desarrollando el proyecto (en gran parte debido a la utilización de herramientas y soluciones a las que no estaba acostumbrado) y aquí dejo constancia de ello
