# Conversor de Divisas

Una aplicación simple en Java que consulta una API de tasas de cambio para convertir entre diferentes divisas.

## Descripción del Proyecto

Este proyecto es una aplicación de consola en Java que permite a los usuarios obtener la tasa de cambio entre dos divisas y calcular la conversión de una cantidad específica, utilizando datos proporcionados por la API de [ExchangeRate-API](https://www.exchangerate-api.com/).

## Características

* Consulta tasas de cambio actualizadas.
* Permite seleccionar la divisa base y la divisa destino.
* Realiza la conversión de una cantidad especificada por el usuario.
* Validación básica de la entrada del usuario para los códigos de divisa.
* Manejo simple de errores en la comunicación con la API.

## Requisitos

* Java Development Kit (JDK) 11 o superior.
* Un gestor de dependencias como Maven o Gradle (recomendado para manejar la librería Gson).
* Una clave API de [ExchangeRate-API](https://www.exchangerate-api.com/). La capa gratuita es suficiente para esta aplicación.

## Configuración del Proyecto

1.  **Clonar el Repositorio:**
    ```bash
    git clone <URL_DEL_TU_REPOSITORIO>
    cd <nombre_del_tu_repositorio>
    ```

2.  **Obtener una Clave API:**
    * Ve a [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/).
    * Regístrate para obtener una clave API gratuita.
    * Una vez registrado, tu clave API estará disponible en tu panel de control.

3.  **Configurar la Clave API:**
    * Crea un nuevo archivo Java llamado `ClaveApi.java` en el directorio donde se encuentran tus otras clases (por ejemplo, en el directorio `src/main/java` si usas Maven/Gradle).
    * Dentro de `ClaveApi.java`, define una clase `public final` con una constante `public static final String` para tu clave API.
    * El contenido del archivo `ClaveApi.java` debería ser similar a esto:

    ```java
    public final class ClaveApi {
        public static final String API_KEY = "TU_CLAVE_API_AQUÍ"; // Reemplaza con tu clave API real
    }
    ```
    * **Importante:** Reemplaza `"TU_CLAVE_API_AQUÍ"` con la clave API que obtuviste de ExchangeRate-API.

4.  **Agregar la Dependencia Gson:**
    * Esta aplicación utiliza la librería Gson de Google para parsear la respuesta JSON de la API. Necesitas agregarla a tu proyecto.
    * **Si usas Maven:** Agrega la siguiente dependencia a tu archivo `pom.xml` dentro de la sección `<dependencies>`:
        ```xml
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version> </dependency>
        ```
    * **Si usas Gradle:** Agrega la siguiente línea a tu archivo `build.gradle` dentro de la sección `dependencies`:
        ```gradle
        implementation 'com.google.code.gson:gson:2.10.1' // Verifica la versión más reciente si es necesario
        ```
    * Si no usas Maven o Gradle, deberás descargar el archivo JAR de Gson y agregarlo manualmente al classpath de tu proyecto.

## Compilación y Ejecución

### Usando Maven

1.  **Compilar:**
    ```bash
    mvn clean compile
    ```
2.  **Ejecutar:**
    ```bash
    mvn exec:java -Dexec.mainClass="Main"
    ```

### Usando Gradle

1.  **Compilar:**
    ```bash
    gradle build
    ```
2.  **Ejecutar:**
    ```bash
    gradle run
    ```

### Sin Gestor de Dependencias (Compilación Manual)

1.  Asegúrate de tener el archivo `gson-2.10.1.jar` (o la versión que uses) en tu directorio.
2.  **Compilar:**
    ```bash
    javac -cp ".:gson-2.10.1.jar" Main.java ConsultaDivisa.java Divisa.java Constantes.java Entrada.java ClaveApi.java
    ```
    *(Nota: `.` es para incluir el directorio actual en el classpath. En Windows, usa `;` en lugar de `:`)*
3.  **Ejecutar:**
    ```bash
    java -cp ".:gson-2.10.1.jar" Main
    ```
    *(En Windows, usa `;` en lugar de `:`)*

## Cómo Usar la Aplicación

Una vez que la aplicación esté corriendo, sigue las instrucciones en la consola:

1.  El programa te dará la bienvenida.
2.  Se te pedirá que ingreses el código de la **divisa base** (ej: `USD`, `EUR`, `COP`). Introduce el código de 3 letras y presiona Enter.
3.  Se te pedirá que ingreses el código de la **divisa destino** (ej: `USD`, `EUR`, `COP`). Introduce el código de 3 letras y presiona Enter.
4.  Se te pedirá que ingreses la **cantidad** que deseas convertir. Introduce un número (puede ser decimal) y presiona Enter.
5.  La aplicación consultará la API y mostrará el resultado de la conversión.
6.  Finalmente, se te preguntará si deseas realizar otra consulta. Escribe `Si` para continuar o `No` para salir.

## Estructura del Código

* `Main.java`: Contiene el método principal (`main`) que maneja el flujo de la aplicación, la interacción con el usuario y llama a las otras clases.
* `ConsultaDivisa.java`: Se encarga de construir la URL de la API, realizar la solicitud HTTP y parsear la respuesta JSON utilizando Gson.
* `Divisa.java`: Un *record* que representa la estructura de los datos relevantes recibidos de la API para una conversión de par específica (resultado, códigos de divisa, tasa y resultado de conversión).
* `Constantes.java`: Una clase `final` que almacena las cadenas de texto (mensajes y prompts) utilizadas en la aplicación para mantener el código `Main` más limpio.
* `Entrada.java`: Contiene un método para validar que la entrada del usuario para los códigos de divisa sea un string de 3 letras.
* `ClaveApi.java`: (Debes crearla) Contiene tu clave API.

## Posibles Mejoras

* Listar las divisas disponibles para que el usuario las seleccione en lugar de tener que adivinar los códigos.
* Manejo de errores más robusto (códigos de divisa inválidos según la API, problemas de conexión, etc.).
* Interfaz gráfica de usuario (GUI).
* Permitir cambiar la cantidad a convertir después de obtener la tasa (reutilizar la tasa).
* Guardar un historial de conversiones.

## Licencia

Este proyecto se distribuye bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
