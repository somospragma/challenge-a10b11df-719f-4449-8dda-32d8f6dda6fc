# Optimización de Eventos Concurrentes en Sistema de Pagos

En un sistema de pagos distribuido, múltiples solicitudes de transacción llegan simultáneamente desde diferentes canales (web, móvil, API). El sistema debe procesar estas solicitudes de manera eficiente, evitando bloqueos y maximizando el rendimiento. Los actores involucrados son el 'originador de créditos', el'motor antifraude', el 'buró de riesgos' y el 'core bancario'. El sistema debe garantizar que las transacciones se procesen de manera idempotente (clave: número de operación + canal) y que los fallos temporales (timeout del buró >2s, respuesta 5xx del core) no afecten la disponibilidad del servicio.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | gestión de concurrencia y paralelismo en sistemas distribuidos |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Diseño de Concurrencia Inicial

**Objetivo:** Establecer un diseño básico que permita el procesamiento concurrente de solicitudes de pago.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los puntos de entrada de solicitudes y los servicios involucrados en el proceso de pago.
- Definir cómo se manejarán las solicitudes concurrentes para evitar bloqueos.
- Establecer criterios de aceptación para la fase: procesamiento eficiente de solicitudes sin bloqueos.

**Entregable:** Diagrama de flujo que muestra el procesamiento concurrente de solicitudes.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la diferencia entre procesos y hilos de ejecución.
- Piensa en cómo puedes aprovechar la concurrencia sin introducir bloqueos.

</details>

### Fase 2: Implementación de Idempotencia

**Objetivo:** Implementar la idempotencia en el procesamiento de solicitudes para garantizar la consistencia.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Define cómo se garantizará la idempotencia en el procesamiento de solicitudes (clave: número de operación + canal).
- Establece criterios de aceptación para la fase: procesamiento idempotente de solicitudes.
- Considera cómo manejarás los fallos temporales (timeout del buró >2s, respuesta 5xx del core).

**Entregable:** Descripción detallada del mecanismo de idempotencia implementado.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo puedes usar la clave de idempotencia para evitar duplicados.
- Considera cómo manejarás los fallos temporales para mantener la disponibilidad del servicio.

</details>

### Fase 3: Optimización y Refactorización

**Objetivo:** Optimizar el diseño para mejorar el rendimiento y refactorizar el código para mayor mantenibilidad.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Identifica áreas de optimización en el diseño actual.
- Refactoriza el código para mejorar la mantenibilidad y escalabilidad.
- Establece criterios de aceptación para la fase: diseño optimizado y código refactorizado.

**Entregable:** Documentación del diseño optimizado y código refactorizado.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo puedes mejorar el rendimiento sin sacrificar la consistencia.
- Piensa en cómo puedes refactorizar el código para que sea más mantenible y escalable.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la concurrencia y cómo difiere del paralelismo en el contexto de este reto?
- **paraQueSirve**: ¿Para qué sirve implementar la idempotencia en el procesamiento de solicitudes?
- **comoSeUsa**: ¿Cómo se usa la clave de idempotencia para garantizar la consistencia en el procesamiento de solicitudes?
- **erroresComunes**: ¿Cuáles son los errores comunes al manejar fallos temporales en un sistema distribuido?
- **queDecisionesImplica**: ¿Qué decisiones implica la optimización y refactorización del diseño?

## Criterios de Evaluacion

- Diseño inicial que permite el procesamiento concurrente de solicitudes.
- Implementación de idempotencia en el procesamiento de solicitudes.
- Manejo de fallos temporales para mantener la disponibilidad del servicio.
- Optimización y refactorización del diseño para mejorar el rendimiento y mantenibilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
