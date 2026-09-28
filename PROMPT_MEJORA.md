# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/payments/Application.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/ports/in/PaymentServicePort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/ports/out/FraudDetectionPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/ports/out/RiskBureauPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/ports/out/CoreBankingPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.Retry pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecases/PaymentUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecases/PaymentUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecases/PaymentUseCase.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapter.java` — `reactor.core.scheduler`: El import reactor.core.scheduler.Schedulers pertenece a reactor.core.scheduler, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapter.java` — `reactor.util.retry`: El import reactor.util.retry.RetryBackoffSpec pertenece a reactor.util.retry, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.http`: El import reactor.netty.http.client.HttpClient pertenece a reactor.netty.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java` — `reactor.netty.resources`: El import reactor.netty.resources.ConnectionProvider pertenece a reactor.netty.resources, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/application/usecases/PaymentUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapterTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/payments/application/usecases/PaymentUseCase.java` — `Transaction.setFailureReason`: Se invoca `setFailureReason` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.operationNumber`: Se invoca `operationNumber` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.channel`: Se invoca `channel` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.accountId`: Se invoca `accountId` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.amount`: Se invoca `amount` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.currency`: Se invoca `currency` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `PaymentRequest.description`: Se invoca `description` sobre `PaymentRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java` — `Transaction.getFailureReason`: Se invoca `getFailureReason` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapter.java` — `Transaction.getAccountId`: Se invoca `getAccountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapter.java` — `Transaction.getAccountId`: Se invoca `getAccountId` sobre `Transaction`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapterTest.java` — `CoreBankingAdapter.processTransaction`: Se invoca `processTransaction` sobre `CoreBankingAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/payments/application/usecases/PaymentUseCaseTest.java` — `CoreBankingAdapter.processTransaction`: Se invoca `processTransaction` sobre `CoreBankingAdapter`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Senior

### Brecha de conocimiento
Gestiona los conceptos de concurrencia y paralelismo en su lenguaje de programación, con el fin de prevenir procesos bloqueantes. Identifica las diferencias entre un proceso y un hilo de ejecución. (En Node.js, aplica el Bucle de Eventos - Event Loop).

### Misión / candidato
Candidato con experiencia como Senior Developer en Backend, trabajando con sistemas distribuidos y aplicaciones de alto rendimiento.

### Reto
- Tema: gestión de concurrencia y paralelismo en sistemas distribuidos
- Seniority: senior-l2
- Tipo: practical
- Título: Optimización de Eventos Concurrentes en Sistema de Pagos
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Diseño de Concurrencia Inicial — objetivo: Establecer un diseño básico que permita el procesamiento concurrente de solicitudes de pago. — entregable (NO resolver): Diagrama de flujo que muestra el procesamiento concurrente de solicitudes.
- Fase 2: Implementación de Idempotencia — objetivo: Implementar la idempotencia en el procesamiento de solicitudes para garantizar la consistencia. — entregable (NO resolver): Descripción detallada del mecanismo de idempotencia implementado.
- Fase 3: Optimización y Refactorización — objetivo: Optimizar el diseño para mejorar el rendimiento y refactorizar el código para mayor mantenibilidad. — entregable (NO resolver): Documentación del diseño optimizado y código refactorizado.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>payments</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>payments</name>
    <description>Sistema de pagos con manejo reactivo y resiliencia</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.github.resilience4j</groupId>
                <artifactId>resilience4j-spring-boot3</artifactId>
                <version>2.2.0</version>
            </dependency>
            <dependency>
                <groupId>org.springdoc</groupId>
                <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
                <version>2.6.0</version>
            </dependency>
            <dependency>
                <groupId>org.testcontainers</groupId>
                <artifactId>postgresql</artifactId>
                <version>1.20.1</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/payments/Application.java ===
package com.pragma.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Hooks;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAsync
public class Application {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    @Bean
    public String resilience4jCircuitBreakerHealthIndicator() {
        return "resilience4jCircuitBreaker";
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: payments-service
  profiles:
    active: dev
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/payments_db
    username: payments_user
    password: payments_pass
    pool:
      enabled: true
      initial-size: 10
      max-size: 20
      max-idle-time: 30m
      validation-query: SELECT 1
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
        jdbc:
          lob:
            non_contextual_creation: true

server:
  port: 8080
  shutdown: graceful
  netty:
    connection-timeout: 2s
    idle-timeout: 15s

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 10
        failureRateThreshold: 50
        waitDurationInOpenState: 5s
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        minimumNumberOfCalls: 5
        eventConsumerBufferSize: 10
    instances:
      coreBankingService:
        baseConfig: default
        failureRateThreshold: 60
        waitDurationInOpenState: 10s
      fraudDetectionService:
        baseConfig: default
        failureRateThreshold: 40
        waitDurationInOpenState: 8s
      riskBureauService:
        baseConfig: default
        failureRateThreshold: 50
        waitDurationInOpenState: 12s
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 1s
        enableExponentialBackoff: true
        exponentialBackoffMultiplier: 2
        retryExceptions:
          - org.springframework.web.reactive.function.client.WebClientResponseException$InternalServerError
          - java.util.concurrent.TimeoutException
          - java.io.IOException
    instances:
      coreBankingService:
        baseConfig: default
      fraudDetectionService:
        baseConfig: default
      riskBureauService:
        baseConfig: default
  bulkhead:
    configs:
      default:
        maxConcurrentCalls: 25
        maxWaitDuration: 0
    instances:
      coreBankingService:
        baseConfig: default
      fraudDetectionService:
        baseConfig: default
      riskBureauService:
        baseConfig: default

logging:
  level:
    root: INFO
    org.springframework.web: DEBUG
    com.pragma.payments: DEBUG
    io.r2dbc.postgresql.QUERY: DEBUG
    io.r2dbc.postgresql.PARAM: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"

management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    ratelimiters:
      enabled: true

// === ARCHIVO: src/main/java/com/pragma/payments/application/ports/in/PaymentServicePort.java ===
package com.pragma.payments.application.ports.in;

import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto de entrada para el procesamiento de pagos.
 * Define las operaciones que el dominio expone para procesar transacciones
 * con manejo de concurrencia e idempotencia.
 */
public interface PaymentServicePort {
    /**
     * Procesa una solicitud de pago con idempotencia garantizada.
     * 
     * @param transactionId Identificador único de la transacción generado por el cliente.
     * @param operationNumber Número de operación proporcionado por el canal.
     * @param channel Canal desde el cual se origina la solicitud (ej: "WEB", "MOBILE", "API").
     * @param amount Monto de la transacción.
     * @param currency Moneda de la transacción (ej: "COP", "USD").
     * @param accountId Identificador de la cuenta origen.
     * @param merchantId Identificador del comercio.
     * @return Mono<Transaction> que emite la transacción procesada o error en caso de fallo.
     */
    Mono<Transaction> processPayment(
        UUID transactionId,
        String operationNumber,
        String channel,
        BigDecimal amount,
        String currency,
        String accountId,
        String merchantId
    );

    /**
     * Verifica si una transacción ya fue procesada con la misma clave de idempotencia.
     * 
     * @param idempotencyKey Clave de idempotencia compuesta por operationNumber + channel.
     * @return Mono<Boolean> que emite true si la transacción ya existe, false en caso contrario.
     */
    Mono<Boolean> isTransactionProcessed(IdempotencyKey idempotencyKey);

    /**
     * Recupera una transacción procesada por su clave de idempotencia.
     * 
     * @param idempotencyKey Clave de idempotencia compuesta por operationNumber + channel.
     * @return Mono<Transaction> que emite la transacción si existe, o Mono.empty() si no.
     */
    Mono<Transaction> getProcessedTransaction(IdempotencyKey idempotencyKey);
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/ports/out/FraudDetectionPort.java ===
package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el motor antifraude.
 * Define el contrato que la infraestructura debe implementar para evaluar transacciones.
 */
public interface FraudDetectionPort {
    /**
     * Evalúa una transacción en el motor antifraude.
     * 
     * @param transaction Transacción a evaluar.
     * @return Mono<Boolean> que emite true si la transacción es sospechosa, false si es segura.
     * @throws ExternalServiceTimeoutException si el servicio no responde en el tiempo esperado.
     */
    Mono<Boolean> evaluateTransaction(Transaction transaction);
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/ports/out/RiskBureauPort.java ===
package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el buró de riesgos.
 * Define el contrato que la infraestructura debe implementar para obtener el score de riesgo.
 */
public interface RiskBureauPort {
    /**
     * Obtiene el score de riesgo de una transacción desde el buró de riesgos.
     * 
     * @param transaction Transacción a evaluar.
     * @return Mono<Integer> que emite el score de riesgo (0-1000).
     * @throws ExternalServiceTimeoutException si el servicio no responde en el tiempo esperado.
     */
    Mono<Integer> getRiskScore(Transaction transaction);

    /**
     * Verifica si el cliente asociado a la transacción está en lista negra.
     * 
     * @param accountId Identificador de la cuenta origen.
     * @return Mono<Boolean> que emite true si el cliente está en lista negra, false en caso contrario.
     */
    Mono<Boolean> isAccountBlacklisted(String accountId);
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/ports/out/CoreBankingPort.java ===
package com.pragma.payments.application.ports.out;

import com.pragma.payments.domain.model.Transaction;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para la comunicación con el core bancario.
 * Define el contrato que la infraestructura debe implementar para enviar
 * transacciones al sistema bancario central.
 * 
 * Este puerto sigue el patrón de puertos y adaptadores, permitiendo que
 * el dominio permanezca agnóstico de la implementación concreta del core bancario.
 */
public interface CoreBankingPort {
    
    /**
     * Envía una transacción validada al core bancario para su procesamiento.
     * 
     * @param transaction la transacción a procesar, con toda la información necesaria
     * @return Mono que emite la transacción actualizada con el estado final del core bancario,
     *         o un error en caso de fallo de comunicación
     */
    Mono<Transaction> sendTransaction(Transaction transaction);
    
    /**
     * Consulta el estado de una transacción previamente enviada al core bancario.
     * 
     * @param transactionId identificador único de la transacción en el sistema
     * @return Mono que emite el estado actual de la transacción, o vacío si no existe
     */
    Mono<Transaction> getTransactionStatus(String transactionId);
    
    /**
     * Verifica la conectividad con el core bancario.
     * Utilizado para health checks y circuit breaker.
     * 
     * @return Mono que emite true si el core está disponible, false en caso contrario
     */
    Mono<Boolean> isAvailable();
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/Transaction.java ===
package com.pragma.payments.domain.model;

import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidad de dominio que representa una transacción financiera.
 * Encapsula toda la información necesaria para procesar un pago,
 * incluyendo validaciones de negocio y manejo de idempotencia.
 * 
 * La validación de idempotencia se realiza mediante la clave compuesta
 * formada por el número de operación y el canal de origen.
 */
public class Transaction {
    
    @NotBlank(message = "El ID de transacción es obligatorio")
    private String id;
    
    @NotNull(message = "La clave de idempotencia es obligatoria")
    @Valid
    private IdempotencyKey idempotencyKey;
    
    @NotBlank(message = "El número de operación es obligatorio")
    @Size(min = 1, max = 50, message = "El número de operación debe tener entre 1 y 50 caracteres")
    private String operationNumber;
    
    @NotBlank(message = "El canal de origen es obligatorio")
    @Size(min = 1, max = 20, message = "El canal debe tener entre 1 y 20 caracteres")
    private String channel;
    
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    private BigDecimal amount;
    
    @NotBlank(message = "La cuenta de origen es obligatoria")
    private String sourceAccount;
    
    @NotBlank(message = "La cuenta de destino es obligatoria")
    private String destinationAccount;
    
    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe ser un código ISO de 3 letras")
    private String currency;
    
    @NotBlank(message = "El tipo de transacción es obligatorio")
    private String transactionType;
    
    private TransactionStatus status;
    
    private String description;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime processedAt;
    
    private String externalReference;
    
    private Integer riskScore;
    
    private Boolean fraudDetected;
    
    public enum TransactionStatus {
        PENDING,
        VALIDATING,
        PROCESSING,
        COMPLETED,
        FAILED,
        REJECTED,
        DUPLICATE
    }
    
    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.status = TransactionStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }
    
    public Transaction(IdempotencyKey idempotencyKey, String operationNumber, String channel,
                       BigDecimal amount, String sourceAccount, String destinationAccount,
                       String currency, String transactionType) {
        this();
        this.idempotencyKey = idempotencyKey;
        this.operationNumber = operationNumber;
        this.channel = channel;
        this.amount = amount;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.currency = currency;
        this.transactionType = transactionType;
    }
    
    public void validateNotAlreadyProcessed() {
        if (this.status == TransactionStatus.COMPLETED || 
            this.status == TransactionStatus.DUPLICATE) {
            throw new TransactionAlreadyProcessedException(
                "La transacción ya fue procesada: " + this.idempotencyKey.toString()
            );
        }
    }
    
    public void markAsDuplicate() {
        this.status = TransactionStatus.DUPLICATE;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsProcessing() {
        this.status = TransactionStatus.PROCESSING;
    }
    
    public void markAsCompleted(String externalReference) {
        this.status = TransactionStatus.COMPLETED;
        this.externalReference = externalReference;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsFailed(String description) {
        this.status = TransactionStatus.FAILED;
        this.description = description;
        this.processedAt = LocalDateTime.now();
    }
    
    public void markAsRejected(String reason) {
        this.status = TransactionStatus.REJECTED;
        this.description = reason;
        this.processedAt = LocalDateTime.now();
    }
    
    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }
    
    public void setFraudDetected(Boolean fraudDetected) {
        this.fraudDetected = fraudDetected;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getId() {
        return id;
    }
    
    public IdempotencyKey getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public String getOperationNumber() {
        return operationNumber;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public String getSourceAccount() {
        return sourceAccount;
    }
    
    public String getDestinationAccount() {
        return destinationAccount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
    
    public String getExternalReference() {
        return externalReference;
    }
    
    public Integer getRiskScore() {
        return riskScore;
    }
    
    public Boolean getFraudDetected() {
        return fraudDetected;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setIdempotencyKey(IdempotencyKey idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }
    
    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    
    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }
    
    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", idempotencyKey=" + idempotencyKey +
                ", operationNumber='" + operationNumber + '\'' +
                ", channel='" + channel + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/model/IdempotencyKey.java ===
package com.pragma.payments.domain.model;

import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Representa la clave de idempotencia para el procesamiento de transacciones.
 * 
 * La clave está compuesta por el número de operación y el canal de origen,
 * formando un identificador único que garantiza que una transacción no se
 * procese más de una vez, incluso bajo condiciones de concurrencia.
 * 
 * Esta clase encapsula la lógica de validación y generación de claves de
 * idempotencia, asegurando que se cumplan las reglas de negocio definidas.
 */
public class IdempotencyKey {
    
    private static final Pattern OPERATION_NUMBER_PATTERN = Pattern.compile("^[A-Za-z0-9\\-]+$");
    private static final Pattern CHANNEL_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");
    
    @NotBlank(message = "El número de operación es obligatorio para la clave de idempotencia")
    @Size(min = 1, max = 50, message = "El número de operación debe tener entre 1 y 50 caracteres")
    private String operationNumber;
    
    @NotBlank(message = "El canal es obligatorio para la clave de idempotencia")
    @Size(min = 1, max = 20, message = "El canal debe tener entre 1 y 20 caracteres")
    private String channel;
    
    private LocalDateTime generatedAt;
    
    public IdempotencyKey() {
        this.generatedAt = LocalDateTime.now();
    }
    
    public IdempotencyKey(String operationNumber, String channel) {
        this();
        validateOperationNumber(operationNumber);
        validateChannel(channel);
        this.operationNumber = operationNumber;
        this.channel = channel;
    }
    
    private void validateOperationNumber(String operationNumber) {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo o vacío");
        }
        if (!OPERATION_NUMBER_PATTERN.matcher(operationNumber).matches()) {
            throw new IllegalArgumentException(
                "El número de operación contiene caracteres inválidos. Solo se permiten letras, números y guiones"
            );
        }
    }
    
    private void validateChannel(String channel) {
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
        if (!CHANNEL_PATTERN.matcher(channel).matches()) {
            throw new IllegalArgumentException(
                "El canal contiene caracteres inválidos. Solo se permiten letras, números y guiones bajos"
            );
        }
    }
    
    public void validateNotDuplicate(IdempotencyKey existingKey) {
        if (existingKey != null && this.equals(existingKey)) {
            throw new TransactionAlreadyProcessedException(
                "Ya existe una transacción procesada con la clave de idempotencia: " + this.toString()
            );
        }
    }
    
    public static IdempotencyKey fromTransaction(String operationNumber, String channel) {
        return new IdempotencyKey(operationNumber, channel);
    }
    
    public String toUniqueString() {
        return operationNumber + "_" + channel;
    }
    
    public String getOperationNumber() {
        return operationNumber;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    
    public void setOperationNumber(String operationNumber) {
        validateOperationNumber(operationNumber);
        this.operationNumber = operationNumber;
    }
    
    public void setChannel(String channel) {
        validateChannel(channel);
        this.channel = channel;
    }
    
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdempotencyKey that = (IdempotencyKey) o;
        return Objects.equals(operationNumber, that.operationNumber) &&
               Objects.equals(channel, that.channel);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(operationNumber, channel);
    }
    
    @Override
    public String toString() {
        return "IdempotencyKey{" +
                "operationNumber='" + operationNumber + '\'' +
                ", channel='" + channel + '\'' +
                ", generatedAt=" + generatedAt +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/TransactionAlreadyProcessedException.java ===
package com.pragma.payments.domain.exception;


import com.pragma.payments.domain.model.Transaction;
import java.time.Instant;
import java.util.UUID;

public class TransactionAlreadyProcessedException extends RuntimeException {
    
    private final String idempotencyKey;
    private final String channel;
    private final String transactionId;
    private final Instant originalProcessingTime;
    private final String operationNumber;
    private static final long serialVersionUID = 1L;
    
    public TransactionAlreadyProcessedException(String idempotencyKey, String channel, 
                                                  String transactionId, Instant originalProcessingTime,
                                                  String operationNumber) {
        super(buildMessage(idempotencyKey, channel, transactionId, operationNumber));
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
        this.transactionId = transactionId;
        this.originalProcessingTime = originalProcessingTime;
        this.operationNumber = operationNumber;
        validateInvariant();
    }
    
    private void validateInvariant() {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("Idempotency key cannot be null or empty");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("Channel cannot be null or empty");
        }
    }
    
    private static String buildMessage(String idempotencyKey, String channel, 
                                        String transactionId, String operationNumber) {
        return String.format("Transaction with idempotency key '%s' (operation: %s, channel: %s) " +
                            "has already been processed. Original transaction ID: %s",
                            idempotencyKey, operationNumber, channel, transactionId);
    }
    
    public String getIdempotencyKey() {
        return idempotencyKey;
    }
    
    public String getChannel() {
        return channel;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public Instant getOriginalProcessingTime() {
        return originalProcessingTime;
    }
    
    public String getOperationNumber() {
        return operationNumber;
    }
    
    public long getTimeSinceOriginalProcessing() {
        if (originalProcessingTime == null) {
            return 0L;
        }
        return Instant.now().toEpochMilli() - originalProcessingTime.toEpochMilli();
    }
    
    public boolean isRecentProcessing(long thresholdMillis) {
        return getTimeSinceOriginalProcessing() < thresholdMillis;
    }
    
    @Override
    public String toString() {
        return "TransactionAlreadyProcessedException{" +
                "idempotencyKey='" + idempotencyKey + '\'' +
                ", channel='" + channel + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", originalProcessingTime=" + originalProcessingTime +
                ", operationNumber='" + operationNumber + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/domain/exception/ExternalServiceTimeoutException.java ===
package com.pragma.payments.domain.exception;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class ExternalServiceTimeoutException extends RuntimeException {
    
    private final String serviceName;
    private final String endpoint;
    private final Duration configuredTimeout;
    private final Duration actualDuration;
    private final Instant timeoutOccurredAt;
    private final String requestId;
    private final Map<String, Object> metadata;
    private static final long serialVersionUID = 1L;
    
    public ExternalServiceTimeoutException(String serviceName, String endpoint, 
                                            Duration configuredTimeout, Duration actualDuration) {
        super(buildMessage(serviceName, endpoint, configuredTimeout, actualDuration));
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.configuredTimeout = configuredTimeout;
        this.actualDuration = actualDuration;
        this.timeoutOccurredAt = Instant.now();
        this.requestId = UUID.randomUUID().toString();
        this.metadata = Map.of();
        validateInvariant();
    }
    
    public ExternalServiceTimeoutException(String serviceName, String endpoint, 
                                            Duration configuredTimeout, Duration actualDuration,
                                            Map<String, Object> metadata) {
        super(buildMessage(serviceName, endpoint, configuredTimeout, actualDuration));
        this.serviceName = serviceName;
        this.endpoint = endpoint;
        this.configuredTimeout = configuredTimeout;
        this.actualDuration = actualDuration;
        this.timeoutOccurredAt = Instant.now();
        this.requestId = UUID.randomUUID().toString();
        this.metadata = metadata != null ? Map.copyOf(metadata) : Map.of();
        validateInvariant();
    }
    
    private void validateInvariant() {
        if (serviceName == null || serviceName.isBlank()) {
            throw new IllegalArgumentException("Service name cannot be null or empty");
        }
        if (configuredTimeout == null || configuredTimeout.isNegative()) {
            throw new IllegalArgumentException("Configured timeout must be positive");
        }
        if (actualDuration == null) {
            throw new IllegalArgumentException("Actual duration cannot be null");
        }
    }
    
    private static String buildMessage(String serviceName, String endpoint, 
                                        Duration configuredTimeout, Duration actualDuration) {
        return String.format("Timeout exceeded calling service '%s' at endpoint '%s'. " +
                            "Configured timeout: %dms, Actual duration: %dms",
                            serviceName, endpoint, 
                            configuredTimeout.toMillis(), actualDuration.toMillis());
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public String getEndpoint() {
        return endpoint;
    }
    
    public Duration getConfiguredTimeout() {
        return configuredTimeout;
    }
    
    public Duration getActualDuration() {
        return actualDuration;
    }
    
    public Instant getTimeoutOccurredAt() {
        return timeoutOccurredAt;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public double getTimeoutRatio() {
        if (configuredTimeout.toMillis() == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) actualDuration.toMillis() / configuredTimeout.toMillis();
    }
    
    public boolean isRetryWorthwhile(int maxRetries, long retryDelayMillis) {
        return getTimeoutRatio() < 2.0 && maxRetries > 0;
    }
    
    @Override
    public String toString() {
        return "ExternalServiceTimeoutException{" +
                "serviceName='" + serviceName + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", configuredTimeout=" + configuredTimeout +
                ", actualDuration=" + actualDuration +
                ", timeoutOccurredAt=" + timeoutOccurredAt +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapter.java ===
package com.pragma.payments.infrastructure.adapters.out.core;

import com.pragma.payments.application.ports.out.CoreBankingPort;
import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.retry.operator.RetryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

@Component
public class CoreBankingAdapter implements CoreBankingPort {
    
    private static final Logger log = LoggerFactory.getLogger(CoreBankingAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "coreBankingCircuitBreaker";
    private static final String RETRY_NAME = "coreBankingRetry";
    
    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Retry retry;
    private final String baseUrl;
    private final Duration defaultTimeout;
    
    public CoreBankingAdapter(
            WebClient webClient,
            @Value("${app.services.core-banking.base-url:http://localhost:8081}") String baseUrl,
            @Value("${app.services.core-banking.timeout-ms:5000}") long timeoutMs,
            CircuitBreakerRegistry circuitBreakerRegistry,
            RetryRegistry retryRegistry) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
        this.defaultTimeout = Duration.ofMillis(timeoutMs);
        
        CircuitBreakerConfig cbConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker(CIRCUIT_BREAKER_NAME, cbConfig);
        
        RetryConfig retryConfig = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(2))
                .retryExceptions(ExternalServiceTimeoutException.class, 
                               WebClientResponseException.ServiceUnavailable.class)
                .ignoreExceptions(WebClientResponseException.BadRequest.class)
                .build();
        this.retry = retryRegistry.retry(RETRY_NAME, retryConfig);
        
        log.info("CoreBankingAdapter initialized with baseUrl: {}, timeout: {}ms", 
                baseUrl, timeoutMs);
    }
    
    @Override
    public Mono<Boolean> authorizeTransaction(Transaction transaction) {
        log.info("Authorizing transaction {} with core banking", transaction.getId());
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/authorize")
                .bodyValue(buildAuthorizationRequest(transaction))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(AuthorizationResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/authorize", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} authorized: {}", 
                    transaction.getId(), response.authorized()))
                .doOnError(error -> log.error("Failed to authorize transaction {}: {}", 
                    transaction.getId(), error.getMessage()))
                .map(AuthorizationResponse::authorized)
                .onErrorResume(handleError(transaction));
    }
    
    @Override
    public Mono<Boolean> settleTransaction(Transaction transaction) {
        log.info("Settling transaction {} with core banking", transaction.getId());
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/settle")
                .bodyValue(buildSettlementRequest(transaction))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(SettlementResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/settle", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} settled: {}", 
                    transaction.getId(), response.settled()))
                .doOnError(error -> log.error("Failed to settle transaction {}: {}", 
                    transaction.getId(), error.getMessage()))
                .map(SettlementResponse::settled)
                .onErrorResume(handleError(transaction));
    }
    
    @Override
    public Mono<Boolean> reverseTransaction(String transactionId, String reason) {
        log.info("Reversing transaction {} with reason: {}", transactionId, reason);
        
        return webClient.post()
                .uri(baseUrl + "/api/v1/transactions/{id}/reverse", transactionId)
                .bodyValue(Map.of("reason", reason))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, handle5xxError())
                .bodyToMono(ReverseResponse.class)
                .timeout(defaultTimeout, Mono.error(
                    new ExternalServiceTimeoutException("CORE_BANKING", "/reverse", 
                        defaultTimeout, defaultTimeout)))
                .transformDeferred(circuitBreakerOperator())
                .transformDeferred(retryOperator())
                .doOnSuccess(response -> log.info("Transaction {} reversed: {}", 
                    transactionId, response.reversed()))
                .doOnError(error -> log.error("Failed to reverse transaction {}: {}", 
                    transactionId, error.getMessage()))
                .map(ReverseResponse::reversed)
                .onErrorResume(e -> {
                    log.error("Error reversing transaction {}: {}", transactionId, e.getMessage());
                    return Mono.just(false);
                });
    }
    
    private Map<String, Object> buildAuthorizationRequest(Transaction transaction) {
        return Map.of(
            "transactionId", transaction.getId(),
            "amount", transaction.getAmount().toString(),
            "currency", transaction.getCurrency(),
            "sourceAccount", transaction.getSourceAccount(),
            "destinationAccount", transaction.getDestinationAccount(),
            "channel", transaction.getChannel()
        );
    }
    
    private Map<String, Object> buildSettlementRequest(Transaction transaction) {
        return Map.of(
            "transactionId", transaction.getId(),
            "settlementDate", java.time.Instant.now().toString()
        );
    }
    
    private Function<org.springframework.web.reactive.function.client.ClientResponse, 
                      Mono<? extends Throwable>> handle5xxError() {
        return response -> {
            log.error("Core banking returned 5xx error: {}", response.statusCode());
            return Mono.error(new WebClientResponseException(
                "Core banking service unavailable",
                response.statusCode().value(),
                "Service temporarily unavailable",
                null,
                null));
        };
    }
    
    private <T> reactor.core.publisher.Flux<T> circuitBreakerOperator() {
        return source -> source
            .transformDeferred(CircuitBreakerOperator.of(circuitBreaker))
            .doOnCallSuccess(result -> log.debug("Circuit breaker call succeeded"))
            .doOnCallFailure(throwable -> log.warn("Circuit breaker call failed: {}", 
                throwable.getMessage()));
    }
    
    private <T> reactor.core.publisher.Flux<T> retryOperator() {
        return source -> source
            .transformDeferred(RetryOperator.of(retry))
            .doOnRetry(signal -> log.info("Retry attempt {} for core banking call", 
                signal.totalRetries() + 1));
    }
    
    private Function<Throwable, Mono<Boolean>> handleError(Transaction transaction) {
        return throwable -> {
            if (throwable instanceof ExternalServiceTimeoutException) {
                log.warn("Timeout processing transaction {}, returning false", transaction.getId());
                return Mono.just(false);
            }
            if (throwable instanceof io.github.resilience4j.circuitbreaker.CallNotPermittedException) {
                log.error("Circuit breaker open for transaction {}, returning false", 
                    transaction.getId());
                return Mono.just(false);
            }
            log.error("Unexpected error processing transaction {}, returning false: {}", 
                transaction.getId(), throwable.getMessage());
            return Mono.just(false);
        };
    }
    
    public CircuitBreaker getCircuitBreaker() {
        return circuitBreaker;
    }
    
    public Retry getRetry() {
        return retry;
    }
    
    public record AuthorizationResponse(String transactionId, boolean authorized, String authorizationCode) {}
    public record SettlementResponse(String transactionId, boolean settled, String settlementId) {}
    public record ReverseResponse(String transactionId, boolean reversed, String reversalId) {}
}

// === ARCHIVO: src/main/java/com/pragma/payments/application/usecases/PaymentUseCase.java ===
package com.pragma.payments.application.usecases;


import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.application.ports.in.PaymentServicePort;
import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.application.ports.out.RiskBureauPort;
import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PaymentUseCase implements PaymentServicePort {

    private static final Logger log = LoggerFactory.getLogger(PaymentUseCase.class);
    private static final Duration EXTERNAL_SERVICE_TIMEOUT = Duration.ofSeconds(3);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final FraudDetectionPort fraudDetectionPort;
    private final RiskBureauPort riskBureauPort;
    private final java.util.Map<String, Transaction> transactionCache;

    public PaymentUseCase(
            FraudDetectionPort fraudDetectionPort,
            RiskBureauPort riskBureauPort) {
        this.fraudDetectionPort = fraudDetectionPort;
        this.riskBureauPort = riskBureauPort;
        this.transactionCache = new java.util.concurrent.ConcurrentHashMap<>();
    }

    @Override
    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "processPaymentFallback")
    @Retry(name = "paymentRetry", maxAttempts = MAX_RETRY_ATTEMPTS)
    public Mono<Transaction> processPayment(Mono<Transaction> transactionMono) {
        return transactionMono
                .flatMap(this::validateIdempotency)
                .flatMap(this::executeFraudDetection)
                .flatMap(this::executeRiskEvaluation)
                .flatMap(this::finalizeTransaction)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Boolean> isTransactionProcessed(IdempotencyKey idempotencyKey) {
        String cacheKey = buildCacheKey(idempotencyKey);
        return Mono.fromCallable(() -> transactionCache.containsKey(cacheKey))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Transaction> getProcessedTransaction(IdempotencyKey idempotencyKey) {
        String cacheKey = buildCacheKey(idempotencyKey);
        return Mono.fromCallable(() -> transactionCache.get(cacheKey))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(transaction -> transaction != null
                        ? Mono.just(transaction)
                        : Mono.empty());
    }

    private Mono<Transaction> validateIdempotency(Transaction transaction) {
        IdempotencyKey key = transaction.getIdempotencyKey();
        String cacheKey = buildCacheKey(key);

        return Mono.fromCallable(() -> transactionCache.get(cacheKey))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(existing -> {
                    if (existing != null) {
                        log.warn("Transacción duplicada detectada para clave: {}", cacheKey);
                        return Mono.error(new TransactionAlreadyProcessedException(
                                "Transacción ya procesada para la clave de idempotencia: " + key.getOperationNumber()));
                    }
                    return Mono.just(transaction);
                });
    }

    @CircuitBreaker(name = "fraudDetectionCircuitBreaker", fallbackMethod = "fraudDetectionFallback")
    private Mono<Transaction> executeFraudDetection(Transaction transaction) {
        return fraudDetectionPort.evaluateTransaction(transaction)
                .timeout(EXTERNAL_SERVICE_TIMEOUT)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(isFraudulent -> {
                    if (Boolean.TRUE.equals(isFraudulent)) {
                        log.warn("Transacción {} rechazada por motor antifraude", transaction.getId());
                        transaction.setStatus(Transaction.TransactionStatus.REJECTED);
                        transaction.setFailureReason("Rechazada por motor antifraude");
                    } else {
                        log.info("Transacción {} aprobada por motor antifraude", transaction.getId());
                    }
                    return Mono.just(transaction);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error en detección de fraude para transacción {}: {}",
                            transaction.getId(), e.getMessage());
                    return Mono.error(new ExternalServiceTimeoutException(
                            "Timeout en servicio de detección de fraude"));
                });
    }

    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "riskEvaluationFallback")
    private Mono<Transaction> executeRiskEvaluation(Transaction transaction) {
        if (transaction.getStatus() == Transaction.TransactionStatus.REJECTED) {
            return Mono.just(transaction);
        }

        return riskBureauPort.getRiskScore(transaction)
                .timeout(EXTERNAL_SERVICE_TIMEOUT)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(score -> {
                    transaction.setRiskScore(score);
                    log.info("Puntuación de riesgo para transacción {}: {}", transaction.getId(), score);

                    if (score >= 70) {
                        transaction.setStatus(Transaction.TransactionStatus.REJECTED);
                        transaction.setFailureReason("Riesgo alto detectado: puntuación " + score);
                        log.warn("Transacción {} rechazada por riesgo alto: {}", transaction.getId(), score);
                    }
                    return Mono.just(transaction);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error en evaluación de riesgo para transacción {}: {}",
                            transaction.getId(), e.getMessage());
                    return Mono.error(new ExternalServiceTimeoutException(
                            "Timeout en servicio de buró de riesgos"));
                });
    }

    private Mono<Transaction> finalizeTransaction(Transaction transaction) {
        return Mono.fromCallable(() -> {
            if (transaction.getStatus() != Transaction.TransactionStatus.REJECTED) {
                transaction.setStatus(Transaction.TransactionStatus.APPROVED);
            }
            transaction.setProcessedAt(Instant.now());

            String cacheKey = buildCacheKey(transaction.getIdempotencyKey());
            transactionCache.put(cacheKey, transaction);

            log.info("Transacción {} finalizada con estado: {}",
                    transaction.getId(), transaction.getStatus());
            return transaction;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private String buildCacheKey(IdempotencyKey key) {
        return key.getOperationNumber() + "_" + key.getChannel();
    }

    private Mono<Transaction> processPaymentFallback(Transaction transaction, Throwable t) {
        log.error("Circuit breaker activado para transacción {}. Fallback ejecutado. Error: {}",
                transaction.getId(), t.getMessage());

        transaction.setStatus(Transaction.TransactionStatus.PENDING);
        transaction.setFailureReason("Servicio temporalmente no disponible. Por favor intente más tarde.");
        return Mono.just(transaction);
    }

    private Mono<Transaction> fraudDetectionFallback(Transaction transaction, Throwable t) {
        log.warn("Fallback de detección de fraude para transacción {}. Error: {}",
                transaction.getId(), t.getMessage());
        return Mono.just(transaction);
    }

    private Mono<Transaction> riskEvaluationFallback(Transaction transaction, Throwable t) {
        log.warn("Fallback de evaluación de riesgo para transacción {}. Error: {}",
                transaction.getId(), t.getMessage());
        return Mono.just(transaction);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/in/web/PaymentController.java ===
package com.pragma.payments.infrastructure.adapters.in.web;



import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.exception.TransactionAlreadyProcessedException;
import com.pragma.payments.application.ports.in.PaymentServicePort;
import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Pagos", description = "Endpoints para procesamiento de transacciones de pago")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentServicePort paymentServicePort;

    public PaymentController(PaymentServicePort paymentServicePort) {
        this.paymentServicePort = paymentServicePort;
    }

    @PostMapping
    @Operation(summary = "Procesar pago", description = "Procesa una solicitud de pago de forma asíncrona")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago procesado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "409", description = "Transacción duplicada"),
            @ApiResponse(responseCode = "503", description = "Servicio no disponible")
    })
    public Mono<ResponseEntity<PaymentResponse>> processPayment(
            @Parameter(description = "Datos de la solicitud de pago") @RequestBody PaymentRequest request) {

        log.info("Recibida solicitud de pago para operación: {} desde canal: {}",
                request.operationNumber(), request.channel());

        IdempotencyKey idempotencyKey = new IdempotencyKey(
                request.operationNumber(),
                request.channel()
        );

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .idempotencyKey(idempotencyKey)
                .accountId(request.accountId())
                .amount(request.amount())
                .currency(request.currency())
                .description(request.description())
                .status(TransactionStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        return paymentServicePort.processPayment(Mono.just(transaction))
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .onErrorResume(com.pragma.payments.domain.exception.TransactionAlreadyProcessedException.class,
                        e -> {
                            log.warn("Transacción duplicada: {}", e.getMessage());
                            return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                                    .body(PaymentResponse.error(e.getMessage())));
                        })
                .onErrorResume(com.pragma.payments.domain.exception.ExternalServiceTimeoutException.class,
                        e -> {
                            log.error("Timeout de servicio externo: {}", e.getMessage());
                            return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                                    .body(PaymentResponse.error("Servicio temporalmente no disponible")));
                        })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado procesando pago: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(PaymentResponse.error("Error interno del servidor")));
                });
    }

    @GetMapping("/status/{operationNumber}/{channel}")
    @Operation(summary = "Consultar estado de pago",
            description = "Verifica si una transacción fue procesada usando clave de idempotencia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public Mono<ResponseEntity<PaymentResponse>> getPaymentStatus(
            @Parameter(description = "Número de operación") @PathVariable String operationNumber,
            @Parameter(description = "Canal de la transacción") @PathVariable String channel) {

        log.info("Consultando estado de pago para operación: {} canal: {}", operationNumber, channel);

        IdempotencyKey idempotencyKey = new IdempotencyKey(operationNumber, channel);

        return paymentServicePort.getProcessedTransaction(idempotencyKey)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @HeadMapping("/exists/{operationNumber}/{channel}")
    @Operation(summary = "Verificar existencia de transacción",
            description = "Verifica rápidamente si una transacción fue procesada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transacción existe"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    public Mono<ResponseEntity<Void>> checkTransactionExists(
            @Parameter(description = "Número de operación") @PathVariable String operationNumber,
            @Parameter(description = "Canal de la transacción") @PathVariable String channel) {

        IdempotencyKey idempotencyKey = new IdempotencyKey(operationNumber, channel);

        return paymentServicePort.isTransactionProcessed(idempotencyKey)
                .flatMap(exists -> exists
                        ? Mono.just(ResponseEntity.noContent().build())
                        : Mono.just(ResponseEntity.notFound().build()));
    }

    private PaymentResponse toResponse(Transaction transaction) {
        return PaymentResponse.builder()
                .transactionId(transaction.getId())
                .status(transaction.getStatus().name())
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .processedAt(transaction.getProcessedAt())
                .failureReason(transaction.getFailureReason())
                .riskScore(transaction.getRiskScore())
                .build();
    }

    public record PaymentRequest(
            String operationNumber,
            String channel,
            String accountId,
            BigDecimal amount,
            String currency,
            String description
    ) {}

    public record PaymentResponse(
            String transactionId,
            String status,
            BigDecimal amount,
            String currency,
            Instant processedAt,
            String failureReason,
            Integer riskScore
    ) {
        public static PaymentResponse error(String message) {
            return new PaymentResponse(null, "ERROR", null, null, null, message, null);
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private String transactionId;
            private String status;
            private BigDecimal amount;
            private String currency;
            private Instant processedAt;
            private String failureReason;
            private Integer riskScore;

            public Builder transactionId(String transactionId) {
                this.transactionId = transactionId;
                return this;
            }

            public Builder status(String status) {
                this.status = status;
                return this;
            }

            public Builder amount(BigDecimal amount) {
                this.amount = amount;
                return this;
            }

            public Builder currency(String currency) {
                this.currency = currency;
                return this;
            }

            public Builder processedAt(Instant processedAt) {
                this.processedAt = processedAt;
                return this;
            }

            public Builder failureReason(String failureReason) {
                this.failureReason = failureReason;
                return this;
            }

            public Builder riskScore(Integer riskScore) {
                this.riskScore = riskScore;
                return this;
            }

            public PaymentResponse build() {
                return new PaymentResponse(
                        transactionId, status, amount, currency, processedAt, failureReason, riskScore);
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapter.java ===
package com.pragma.payments.infrastructure.adapters.out.fraud;

import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FraudDetectionAdapter implements FraudDetectionPort {

    private static final Logger log = LoggerFactory.getLogger(FraudDetectionAdapter.class);
    private static final String FRAUD_SERVICE_URL = "http://fraud-detection-service:8081/api/v1/evaluate";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(2);

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;
    private final Map<String, Boolean> fraudCache;

    public FraudDetectionAdapter(
            WebClient webClient,
            @Qualifier("fraudDetectionCircuitBreaker") CircuitBreakerRegistry circuitBreakerRegistry) {
        this.webClient = webClient;
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("fraudDetection");
        this.fraudCache = new ConcurrentHashMap<>();
    }

    @Override
    public Mono<Boolean> evaluateTransaction(Transaction transaction) {
        String cacheKey = buildCacheKey(transaction);

        if (fraudCache.containsKey(cacheKey)) {
            log.debug("Evaluación de fraude cacheada para transacción: {}", transaction.getId());
            return Mono.just(fraudCache.get(cacheKey)).subscribeOn(Schedulers.boundedElastic());
        }

        return Mono.fromCallable(() -> buildFraudRequest(transaction))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(requestBody -> webClient.post()
                        .uri(FRAUD_SERVICE_URL)
                        .bodyValue(requestBody)
                        .retrieve()
                        .onStatus(HttpStatusCode::is5xxServerError,
                                response -> Mono.error(new RuntimeException(
                                        "Error del servicio de detección de fraude: " + response.statusCode())))
                        .bodyToMono(FraudEvaluationResponse.class)
                        .timeout(REQUEST_TIMEOUT))
                .doOnSuccess(response -> {
                    log.info("Evaluación de fraude completada para transacción {}: isFraudulent={}",
                            transaction.getId(), response.isFraudulent());
                    fraudCache.put(cacheKey, response.isFraudulent());
                })
                .doOnError(error -> log.error("Error en evaluación de fraude para transacción {}: {}",
                        transaction.getId(), error.getMessage()))
                .map(FraudEvaluationResponse::isFraudulent)
                .onErrorResume(WebClientResponseException.ServiceUnavailable.class, e -> {
                    log.warn("Servicio de fraude no disponible, aplicando lógica de falla segura");
                    return Mono.just(false);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado en evaluación de fraude: {}", e.getMessage());
                    return Mono.just(true);
                });
    }

    private Map<String, Object> buildFraudRequest(Transaction transaction) {
        return Map.of(
                "transactionId", transaction.getId(),
                "accountId", transaction.getAccountId(),
                "amount", transaction.getAmount(),
                "currency", transaction.getCurrency(),
                "channel", transaction.getIdempotencyKey().getChannel(),
                "description", transaction.getDescription() != null ? transaction.getDescription() : ""
        );
    }

    private String buildCacheKey(Transaction transaction) {
        return transaction.getAccountId() + "_" + transaction.getAmount() + "_" +
                System.currentTimeMillis() / (60 * 1000);
    }

    public void clearCache() {
        fraudCache.clear();
        log.info("Caché de detección de fraude limpiada");
    }

    public int getCacheSize() {
        return fraudCache.size();
    }

    private record FraudEvaluationResponse(boolean isFraudulent, String riskLevel, String message) {}
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapter.java ===
package com.pragma.payments.infrastructure.adapters.out.risk;

import com.pragma.payments.application.ports.out.RiskBureauPort;
import com.pragma.payments.domain.model.Transaction;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);
    private static final int DEFAULT_TIMEOUT_SECONDS = 3;
    private static final int DEFAULT_RETRY_ATTEMPTS = 3;
    private static final long DEFAULT_BACKOFF_MS = 1000L;

    private final WebClient webClient;
    private final String baseUrl;
    private final int timeoutSeconds;

    public RiskBureauAdapter(
            WebClient webClient,
            @Value("${external.services.risk-bureau.url:http://localhost:8083}") String baseUrl,
            @Value("${external.services.risk-bureau.timeout-seconds:3}") int timeoutSeconds) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
        this.timeoutSeconds = timeoutSeconds > 0 ? timeoutSeconds : DEFAULT_TIMEOUT_SECONDS;
    }

    @Override
    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "getRiskScoreFallback")
    @Retry(name = "riskBureauRetry")
    public Mono<Integer> getRiskScore(Transaction transaction) {
        log.info("Consultando risk score para cuenta: {}, monto: {}", 
                transaction.getAccountId(), transaction.getAmount());

        return webClient
                .post()
                .uri(baseUrl + "/api/v1/risk/score")
                .bodyValue(buildRiskRequest(transaction))
                .retrieve()
                .bodyToMono(RiskScoreResponse.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .map(RiskScoreResponse::getScore)
                .doOnSuccess(score -> log.info("Risk score obtenido: {} para cuenta: {}", 
                        score, transaction.getAccountId()))
                .doOnError(WebClientResponseException.class, e -> 
                        log.error("Error HTTP {} al consultar risk bureau: {}", 
                                e.getStatusCode().value(), e.getMessage()))
                .doOnError(TimeoutException.class, e -> 
                        log.error("Timeout al consultar risk bureau para cuenta: {}", 
                                transaction.getAccountId()));
    }

    @Override
    @CircuitBreaker(name = "riskBureauCircuitBreaker", fallbackMethod = "isAccountBlacklistedFallback")
    @Retry(name = "riskBureauRetry")
    public Mono<Boolean> isAccountBlacklisted(String accountId) {
        log.info("Verificando si cuenta {} está en lista negra", accountId);

        return webClient
                .get()
                .uri(baseUrl + "/api/v1/risk/blacklist/{accountId}", accountId)
                .retrieve()
                .bodyToMono(BlacklistResponse.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .map(BlacklistResponse::isBlacklisted)
                .doOnSuccess(result -> log.info("Cuenta {} en blacklist: {}", accountId, result))
                .doOnError(WebClientResponseException.class, e -> 
                        log.error("Error HTTP {} al verificar blacklist: {}", 
                                e.getStatusCode().value(), e.getMessage()));
    }

    private RiskRequest buildRiskRequest(Transaction transaction) {
        return new RiskRequest(
                transaction.getAccountId(),
                transaction.getAmount(),
                transaction.getCurrency(),
                transaction.getChannel() != null ? transaction.getChannel() : "API"
        );
    }

    private Mono<Integer> getRiskScoreFallback(Transaction transaction, Throwable t) {
        log.warn("Fallback ejecutado para getRiskScore. Cuenta: {}, Error: {}", 
                transaction.getAccountId(), t.getMessage());
        return Mono.just(50);
    }

    private Mono<Boolean> isAccountBlacklistedFallback(String accountId, Throwable t) {
        log.warn("Fallback ejecutado para isAccountBlacklisted. Cuenta: {}, Error: {}", 
                accountId, t.getMessage());
        return Mono.just(false);
    }

    private static class RiskRequest {
        private final String accountId;
        private final java.math.BigDecimal amount;
        private final String currency;
        private final String channel;

        public RiskRequest(String accountId, java.math.BigDecimal amount, 
                          String currency, String channel) {
            this.accountId = accountId;
            this.amount = amount;
            this.currency = currency;
            this.channel = channel;
        }

        public String getAccountId() { return accountId; }
        public java.math.BigDecimal getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getChannel() { return channel; }
    }

    private static class RiskScoreResponse {
        private int score;

        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
    }

    private static class BlacklistResponse {
        private boolean blacklisted;

        public boolean isBlacklisted() { return blacklisted; }
        public void setBlacklisted(boolean blacklisted) { this.blacklisted = blacklisted; }
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/Resilience4jConfig.java ===
package com.pragma.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(1000))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(10)
                .timeoutDuration(Duration.ofMillis(500))
                .build();
        return RateLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/pragma/payments/infrastructure/config/WebClientConfig.java ===
package com.pragma.payments.infrastructure.config;


import com.pragma.payments.infrastructure.adapters.in.web.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 5000;
    private static final int DEFAULT_RESPONSE_TIMEOUT_MS = 10000;
    private static final int DEFAULT_POOL_MAX_CONNECTIONS = 100;

    @Value("${external.services.connection-timeout-ms:5000}")
    private int connectionTimeoutMs;

    @Value("${external.services.response-timeout-ms:10000}")
    private int responseTimeoutMs;

    @Value("${external.services.max-connections:100}")
    private int maxConnections;

    @Bean
    public WebClient webClient(ConnectionProvider connectionProvider) {
        log.info("Configurando WebClient con connectionTimeout: {}ms, responseTimeout: {}ms, maxConnections: {}",
                connectionTimeoutMs, responseTimeoutMs, maxConnections);

        HttpClient httpClient = HttpClient.create(connectionProvider)
                .responseTimeout(Duration.ofMillis(responseTimeoutMs))
                .option(io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeoutMs);

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("external-services-pool")
                .maxConnections(maxConnections > 0 ? maxConnections : DEFAULT_POOL_MAX_CONNECTIONS)
                .pendingAcquireTimeout(Duration.ofMillis(10000))
                .pendingAcquireMaxCount(-1)
                .maxIdleTime(Duration.ofSeconds(30))
                .maxLifeTime(Duration.ofMinutes(5))
                .evictInBackground(Duration.ofSeconds(30))
                .build();
    }

    @Bean
    public org.springframework.boot.web.reactive.function.client.WebClient.Builder 
            webClientBuilder(WebClient webClient) {
        return webClient.mutate();
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapters/out/core/CoreBankingAdapterTest.java ===
package com.pragma.payments.infrastructure.adapters.out.core;

import com.pragma.payments.domain.exception.ExternalServiceTimeoutException;
import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CoreBankingAdapter - Tests de integración con el Core Bancario")
class CoreBankingAdapterTest {

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    private CoreBankingAdapter adapter;

    private Transaction createTestTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId("ACC-12345")
                .amount(new BigDecimal("1000.00"))
                .channel("WEB")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @BeforeEach
    void setUp() {
        adapter = new CoreBankingAdapter("http://core-banking-service:8080");
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe procesar exitosamente una transacción en el core bancario")
        void shouldProcessTransactionSuccessfully() {
            Transaction transaction = createTestTransaction();
            String coreResponse = "{\"transactionId\":\"CORE-98765\",\"status\":\"APPROVED\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(coreResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectNextMatches(result -> 
                        result.contains("CORE-98765") && result.contains("APPROVED"))
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe retornar APPROVED cuando el core aprueba la transacción")
        void shouldReturnApprovedStatus() {
            Transaction transaction = createTestTransaction();
            String coreResponse = "{\"status\":\"APPROVED\",\"authorizationCode\":\"AUTH-001\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(coreResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectNextMatches(response -> response.contains("APPROVED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Debe manejar respuesta 500 del core bancario")
        void shouldHandle500ErrorFromCore() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.INTERNAL_SERVER_ERROR),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Core Banking unavailable")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta 503 del core bancario")
        void shouldHandle503ErrorFromCore() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.SERVICE_UNAVAILABLE),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Service temporarily unavailable")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta 502 Bad Gateway")
        void shouldHandle502BadGateway() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.BAD_GATEWAY),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Bad Gateway")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error de conexión con el core")
        void shouldHandleConnectionFailure() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(Mono.error(new RuntimeException("Connection refused")));

            StepVerifier.create(adapter.processTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Timeout")
    class TimeoutScenarios {

        @Test
        @DisplayName("Debe lanzar excepción cuando el core no responde a tiempo")
        void shouldThrowTimeoutExceptionWhenCoreDoesNotRespond() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.delay(java.time.Duration.ofSeconds(30))
                        .flatMap(ignored -> Mono.empty()));

            StepVerifier.create(adapter.processTransaction(transaction)
                    .timeout(java.time.Duration.ofSeconds(5)))
                    .expectError(ExternalServiceTimeoutException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar timeout en la llamada al core bancario")
        void shouldHandleTimeoutGracefully() {
            Transaction transaction = createTestTransaction();

            StepVerifier.create(adapter.processTransaction(transaction)
                    .timeout(java.time.Duration.ofMillis(100))
                    .onErrorResume(ExternalServiceTimeoutException.class, e -> Mono.just("TIMEOUT")))
                    .expectNext("TIMEOUT")
                    .verifyComplete();
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/application/usecases/PaymentUseCaseTest.java ===
package com.pragma.payments.application.usecases;


import com.pragma.payments.infrastructure.adapters.out.core.CoreBankingAdapter;
import com.pragma.payments.application.ports.out.FraudDetectionPort;
import com.pragma.payments.application.ports.out.RiskBureauPort;
import com.pragma.payments.domain.model.IdempotencyKey;
import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PaymentUseCase - Tests de caso de uso de pagos")
class PaymentUseCaseTest {

    @Mock
    private FraudDetectionPort fraudDetectionPort;

    @Mock
    private RiskBureauPort riskBureauPort;

    @Mock
    private com.pragma.payments.infrastructure.adapters.out.core.CoreBankingAdapter coreBankingAdapter;

    private PaymentUseCase paymentUseCase;

    private Transaction createTestTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId("ACC-12345")
                .amount(new BigDecimal("1000.00"))
                .channel("WEB")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    private IdempotencyKey createIdempotencyKey(String operationNumber, String channel) {
        return IdempotencyKey.builder()
                .operationNumber(operationNumber)
                .channel(channel)
                .build();
    }

    @BeforeEach
    void setUp() {
        paymentUseCase = new PaymentUseCase(
                fraudDetectionPort,
                riskBureauPort,
                coreBankingAdapter
        );
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe procesar exitosamente una transacción")
        void shouldProcessTransactionSuccessfully() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .assertNext(result -> {
                        assertThat(result).isNotNull();
                        assertThat(result.getStatus()).isEqualTo("COMPLETED");
                    })
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe aprobar cuando el riesgo es bajo")
        void shouldApproveWhenRiskIsLow() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(5));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .assertNext(result -> assertThat(result.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo por Validaciones")
    class ValidationFailureScenarios {

        @Test
        @DisplayName("Debe rechazar cuando el motor antifraude detecta fraude")
        void shouldRejectWhenFraudDetected() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(false));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("fraud"))
                    .verify();
        }

        @Test
        @DisplayName("Debe rechazar cuando la cuenta está en lista negra")
        void shouldRejectWhenAccountIsBlacklisted() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(true));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("blacklisted"))
                    .verify();
        }

        @Test
        @DisplayName("Debe rechazar cuando el riesgo es alto")
        void shouldRejectWhenRiskIsHigh() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(85));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectErrorMatches(e -> e.getMessage().contains("risk"))
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Concurrencia")
    class ConcurrencyScenarios {

        @Test
        @DisplayName("Debe procesar múltiples transacciones concurrentemente")
        void shouldHandleConcurrentTransactions() throws InterruptedException {
            int concurrentRequests = 10;
            CountDownLatch latch = new CountDownLatch(concurrentRequests);
            AtomicInteger successCount = new AtomicInteger(0);

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            ExecutorService executor = Executors.newFixedThreadPool(concurrentRequests);

            for (int i = 0; i < concurrentRequests; i++) {
                final int index = i;
                executor.submit(() -> {
                    try {
                        Transaction tx = Transaction.builder()
                                .id(UUID.randomUUID().toString())
                                .accountId("ACC-" + index)
                                .amount(new BigDecimal("1000.00"))
                                .channel("WEB")
                                .status("PENDING")
                                .createdAt(LocalDateTime.now())
                                .build();

                        paymentUseCase.processPayment(Mono.just(tx))
                                .doOnSuccess(result -> successCount.incrementAndGet())
                                .block();
                    } finally {
                        latch.countDown();
                    }
                });
            }

            latch.await();
            executor.shutdown();

            assertThat(successCount.get()).isEqualTo(concurrentRequests);
        }

        @Test
        @DisplayName("Debe mantener el orden de procesamiento")
        void shouldMaintainProcessingOrder() {
            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            var transactions = java.util.List.of(
                    createTestTransaction(),
                    createTestTransaction(),
                    createTestTransaction()
            );

            var result = paymentUseCase.processPayment(Mono.just(transactions.get(0)))
                    .then(paymentUseCase.processPayment(Mono.just(transactions.get(1))))
                    .then(paymentUseCase.processPayment(Mono.just(transactions.get(2))));

            StepVerifier.create(result)
                    .assertNext(r -> assertThat(r.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Idempotencia")
    class IdempotencyScenarios {

        @Test
        @DisplayName("Debe indicar que la transacción ya fue procesada")
        void shouldIndicateTransactionAlreadyProcessed() {
            IdempotencyKey key = createIdempotencyKey("OP-123", "WEB");

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any())).thenReturn(Mono.just(10));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            Transaction tx = createTestTransaction();
            paymentUseCase.processPayment(Mono.just(tx)).block();

            StepVerifier.create(paymentUseCase.isTransactionProcessed(key))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe retornar false para transacción no procesada")
        void shouldReturnFalseForNonProcessedTransaction() {
            IdempotencyKey key = createIdempotencyKey("OP-NEW", "MOBILE");

            StepVerifier.create(paymentUseCase.isTransactionProcessed(key))
                    .expectNext(false)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Manejo de Fallos Temporales")
    class TemporaryFailureScenarios {

        @Test
        @DisplayName("Debe manejar fallo temporal del servicio de riesgo")
        void shouldHandleTemporaryRiskServiceFailure() {
            Transaction transaction = createTestTransaction();

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any()))
                    .thenReturn(Mono.error(new RuntimeException("Temporary failure")));
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction)))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe reintentar automáticamente en fallos temporales")
        void shouldRetryOnTemporaryFailures() {
            Transaction transaction = createTestTransaction();
            AtomicInteger attemptCount = new AtomicInteger(0);

            when(fraudDetectionPort.evaluateTransaction(any())).thenReturn(Mono.just(true));
            when(riskBureauPort.getRiskScore(any()))
                    .thenAnswer(invocation -> {
                        if (attemptCount.incrementAndGet() < 3) {
                            return Mono.error(new RuntimeException("Temporary failure"));
                        }
                        return Mono.just(10);
                    });
            when(riskBureauPort.isAccountBlacklisted(any())).thenReturn(Mono.just(false));
            when(coreBankingAdapter.processTransaction(any())).thenReturn(Mono.just("APPROVED"));

            StepVerifier.create(paymentUseCase.processPayment(Mono.just(transaction))
                    .retry(2))
                    .assertNext(result -> assertThat(result.getStatus()).isEqualTo("COMPLETED"))
                    .verifyComplete();
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapters/out/fraud/FraudDetectionAdapterTest.java ===
package com.pragma.payments.infrastructure.adapters.out.fraud;

import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("FraudDetectionAdapter - Tests del adaptador del motor antifraude")
class FraudDetectionAdapterTest {

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private ClientResponse clientResponse;

    private FraudDetectionAdapter adapter;

    private Transaction createTestTransaction() {
        return Transaction.builder()
                .id(UUID.randomUUID().toString())
                .accountId("ACC-12345")
                .amount(new BigDecimal("5000.00"))
                .channel("MOBILE")
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @BeforeEach
    void setUp() {
        adapter = new FraudDetectionAdapter("http://fraud-detection-service:8080");
    }

    @Nested
    @DisplayName("Escenarios de Éxito")
    class SuccessScenarios {

        @Test
        @DisplayName("Debe aprobar transacción legítima")
        void shouldApproveLegitimateTransaction() {
            Transaction transaction = createTestTransaction();
            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe detectar fraude en transacción de alto riesgo")
        void shouldDetectFraudInHighRiskTransaction() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-SUSPICIOUS")
                    .amount(new BigDecimal("50000.00"))
                    .channel("API")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":true,\"riskLevel\":\"HIGH\",\"reason\":\"unusual_amount\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(false)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe evaluar correctamente transacciones con monto bajo")
        void shouldApproveLowAmountTransactions() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-12345")
                    .amount(new BigDecimal("100.00"))
                    .channel("WEB")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Fallo")
    class FailureScenarios {

        @Test
        @DisplayName("Debe manejar error 500 del servicio antifraude")
        void shouldHandle500Error() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.INTERNAL_SERVER_ERROR),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Fraud service error")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error 503 del servicio antifraude")
        void shouldHandle503Error() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(
                    eq(HttpStatus.SERVICE_UNAVAILABLE),
                    any()))
                .thenReturn(Mono.error(new RuntimeException("Fraud service unavailable")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar error de conexión")
        void shouldHandleConnectionError() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                    .thenReturn(Mono.error(new RuntimeException("Connection refused")));

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(RuntimeException.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe manejar respuesta inválida del servicio")
        void shouldHandleInvalidResponse() {
            Transaction transaction = createTestTransaction();
            String invalidResponse = "invalid-json";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(invalidResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectError(Exception.class)
                    .verify();
        }
    }

    @Nested
    @DisplayName("Escenarios de Timeout")
    class TimeoutScenarios {

        @Test
        @DisplayName("Debe manejar timeout del servicio antifraude")
        void shouldHandleServiceTimeout() {
            Transaction transaction = createTestTransaction();

            when(responseSpec.onStatus(any(), any()))
                .thenReturn(responseSpec);
            when(responseSpec.bodyToMono(String.class))
                .thenReturn(Mono.delay(java.time.Duration.ofSeconds(30))
                        .flatMap(ignored -> Mono.empty()));

            StepVerifier.create(adapter.evaluateTransaction(transaction)
                    .timeout(java.time.Duration.ofSeconds(2)))
                    .expectError(Exception.class)
                    .verify();
        }

        @Test
        @DisplayName("Debe retornar false por defecto en timeout")
        void shouldReturnFalseOnTimeoutByDefault() {
            Transaction transaction = createTestTransaction();

            StepVerifier.create(adapter.evaluateTransaction(transaction)
                    .timeout(java.time.Duration.ofMillis(100))
                    .onErrorResume(Exception.class, e -> Mono.just(false)))
                    .expectNext(false)
                    .verifyComplete();
        }
    }

    @Nested
    @DisplayName("Escenarios de Validación de Datos")
    class DataValidationScenarios {

        @Test
        @DisplayName("Debe enviar el accountId correcto al servicio")
        void shouldSendCorrectAccountId() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-SPECIFIC-123")
                    .amount(new BigDecimal("1000.00"))
                    .channel("WEB")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"LOW\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }

        @Test
        @DisplayName("Debe enviar el monto correcto al servicio")
        void shouldSendCorrectAmount() {
            Transaction transaction = Transaction.builder()
                    .id(UUID.randomUUID().toString())
                    .accountId("ACC-12345")
                    .amount(new BigDecimal("9999.99"))
                    .channel("API")
                    .status("PENDING")
                    .createdAt(LocalDateTime.now())
                    .build();

            String fraudResponse = "{\"fraudDetected\":false,\"riskLevel\":\"MEDIUM\"}";

            when(responseSpec.bodyToMono(String.class)).thenReturn(Mono.just(fraudResponse));
            when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);

            StepVerifier.create(adapter.evaluateTransaction(transaction))
                    .expectNext(true)
                    .verifyComplete();
        }
    }
}

// === ARCHIVO: src/test/java/com/pragma/payments/infrastructure/adapters/out/risk/RiskBureauAdapterTest.java ===
package com.pragma.payments.infrastructure.adapters.out.risk;

import com.pragma.payments.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para RiskBureauAdapter")
class RiskBureauAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private RiskBureauAdapter riskBureauAdapter;

    private Transaction transaction;

    @BeforeEach
    void setUp() {
        transaction = new Transaction(
            UUID.randomUUID().toString(),
            "ORIG-001",
            "ACC-123456",
            new BigDecimal("5000.00"),
            "PESOS",
            "WEB",
            LocalDateTime.now(),
            "COMPLETED"
        );
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo válido cuando el servicio responde correctamente")
    void getRiskScore_WhenServiceRespondsSuccessfully_ReturnsRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(450));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(450)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo alto para cuentas de alto riesgo")
    void getRiskScore_WhenAccountIsHighRisk_ReturnsHighRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(850));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(850)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore retorna código de riesgo bajo para cuentas nuevas o de bajo riesgo")
    void getRiskScore_WhenAccountIsLowRisk_ReturnsLowRiskCode() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(150));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectNext(150)
            .verifyComplete();
    }

    @Test
    @DisplayName("getRiskScore lanza excepción cuando el servicio externo retorna error 500")
    void getRiskScore_WhenExternalServiceReturns500_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class))
            .thenReturn(Mono.error(new RuntimeException("Error del servicio de riesgo")));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("getRiskScore retorna Mono.empty cuando el servicio no retorna datos")
    void getRiskScore_WhenServiceReturnsNoData_ReturnsEmpty() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.empty());

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted retorna true cuando la cuenta está en lista negra")
    void isAccountBlacklisted_WhenAccountIsBlacklisted_ReturnsTrue() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(true));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456"))
            .expectNext(true)
            .verifyComplete();
    }

    @Test
n    @DisplayName("isAccountBlacklisted retorna false cuando la cuenta no está en lista negra")
    void isAccountBlacklisted_WhenAccountIsNotBlacklisted_ReturnsFalse() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456"))
            .expectNext(false)
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted lanza excepción en timeout del servicio externo")
    void isAccountBlacklisted_WhenServiceTimesOut_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class))
            .thenReturn(Mono.delay(Duration.ofMillis(500)).map(l -> false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted("ACC-123456")
                .timeout(Duration.ofMillis(100)))
            .expectError()
            .verify();
    }

    @Test
    @DisplayName("getRiskScore lanza excepción cuando la cuenta está en lista negra según el código de riesgo")
    void getRiskScore_WhenAccountIsBlacklistedByRiskCode_ThrowsException() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq("ACC-123456")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(999));

        StepVerifier.create(riskBureauAdapter.getRiskScore(transaction))
            .expectError(IllegalArgumentException.class)
            .verify();
    }

    @Test
    @DisplayName("getRiskScore usa el accountId correcto de la transacción")
    void getRiskScore_UsesCorrectAccountIdFromTransaction() {
        String accountId = "ACC-SPECIFIC-789";
        Transaction specificTransaction = new Transaction(
            UUID.randomUUID().toString(),
            "ORIG-002",
            accountId,
            new BigDecimal("10000.00"),
            "PESOS",
            "MOBILE",
            LocalDateTime.now(),
            "PENDING"
        );

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/score/{accountId}"), eq(accountId)))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Integer.class)).thenReturn(Mono.just(300));

        StepVerifier.create(riskBureauAdapter.getRiskScore(specificTransaction))
            .expectNext(300)
            .verifyComplete();
    }

    @Test
    @DisplayName("isAccountBlacklisted maneja cuenta con identificador vacío")
    void isAccountBlacklisted_WithEmptyAccountId_HandlesGracefully() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(eq("/risk/blacklist/{accountId}"), eq("")))
            .thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        StepVerifier.create(riskBureauAdapter.isAccountBlacklisted(""))
            .expectNext(false)
            .verifyComplete();
    }
```
