# Web Service Framework

La siguiente aplicacion recrear un servidor concurrente web sencillo en java. El servidor será capaz de entregar tanto paginas html como imágenes tipo PNG. Además del servidor proveerá un framework IoC construido mediante POJOs para la construcción de aplicaciones web.

## Como empezar

### Prerequisites

Para poder correr el servidor localmente es necesario tener instalado los sigientes programas:

* maven
* git
* heroku
SI no se posee alguno de estos programas estos son manuales de instalacion de cada uno de ellos

**Maven**

`Windows`
https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows

`Linux`
https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html

`Mac Oxs`
https://www.mkyong.com/maven/install-maven-on-mac-osx/

**Git**

`All plataforms` https://git-scm.com/book/es/v1/Empezando-Instalando-Git

**Heroku**

`All plataforms ` https://devcenter.heroku.com/articles/heroku-cli



### Installing

Para instalar la aplicacion es necesario clonar el proyecto con el siguiente comando

````
git clone https://github.com/GrCross/webServiceFramework.git
````

una vez descargado el proyecto se procede se procede a compilarlo con el siguiente comando desde el directori raiz:

````
mvn compile
````

Una vez compilado el proyecto es necesario agregar la direccion del repositorio remoto de Heroku con el siguiente comando:

````
git remote add heroku https://git.heroku.com/web-service-framework.git
````

Cuando ya se halla agregado la direccion procedemos a ejecutarlo:


````
heroku local web 
````
y se procede a abrir la aplicacion desde el explorador

````
localhost:5000/apps/video
````


## Running the tests

La aplicacion contiene varios archivos para probar su funcionalidad, desde archivos HTML hasta archivos mp4

Usar los siguientes links:

Pagina de inicio
````
localhost:5000/
````
Una vez en la pagina de inicio se podra acceder a los recursos desde ella

Imagen jpeg
````
localhost:5000/apps/elesis
````

imagen jpg
````
localhost:5000/apps/landscape
````

archivo mp3
````
localhost:5000/apps/music
````

pagina HTML

````
localhost:5000/apps/itsDown
````


archivo mp4
````
localhost:5000/apps/video
````

## Displiege

La aplicacion se desplego en Heroku con el siguiente link

````
https://web-service-framework.herokuapp.com/apps/video
````

## Construido con
* [Maven](https://maven.apache.org/) - Manejador de dependencias


## Autores

* **Daniel Rosales** - [GrCross](https://github.com/GrCross)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details


