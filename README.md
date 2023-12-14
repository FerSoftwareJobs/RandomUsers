# Estructura del proyecto

## app

Inicialización de la aplicación de Hilt 

## data

Contenedor que referencia a la capa de datos, contiene los mappers, repositorios, clases de datos de respuesta y otras clases relacionadas con los recursos de datos remotos

## di

Contiene los módulos del inyector de dependencias (Hilt)

## domain

Contenedor que referencia a la capa de dominio, contiene las clases de datos de la capa y los casos de uso

## ui

Contenedor que referencia a la capa de interfaz de usuario, contiene la actividad principal, fragmentos, adaptadores y viewmodels

## utils

Contiene las constantes, extensiones y una clase span custom

<br>
<br>
<br>

# Herramientas utilizadas
- Actividad principal y fragmentos
- Navigation compose y safe args
- ListAdapter
- Comunicación con servicio remoto a través de Retrofit2
- Inyección de dependencias con Dagger Hilt
- MVVM
- Corrutinas
- Glide
- Google Maps API

<br>
<br>
<br>

# Problemas encontrados en el desarrollo
- A partir de la API 29 no se puede configurar el color de los iconos de la barra de estado y las únicas opciones de configuración son el color gris para temas claros y el color blanco para temas oscuros
- Solo he podido mostrar una imagen detrás de la barra de estado utilizando herramientas deprecadas, las nuevas herramientas generaban nuevos problemas
- El color del icono de navegar hacia atrás en la barra de herramientas depende del color secundario de la aplicación, ya no se puede cambiar mediante los métodos anterior cono la propiedad naigationIconTint de la toolbar
- He tenido que modificar la clave de la API de google maps para que no sea válida al subirla en un repositorio público, cambiando el valor en el archivo AndroidManifest.xml por una clave válida se activa la funcionalidad.

<br>
<br>
<br>

# Funcionalidades y mejoras por implementar
- Filtrado
- Mostrar animación de carga durante la preparación y actualización del recycler view
- Parsear el genero para mostrarlo en castellano

<br>
<br>
<br>

P.D: como todo no iban a ser penas también he disfrutado desarrollando el proyecto y aquí dejo constancia
