# Tarjeta de Fidelidad Gamificada

Este proyecto implementa un sistema de fidelización de clientes por consola en **Java 21**, usando **TDD** con **JUnit 5** y medición de cobertura con **JaCoCo (EcclEmma)**.

---

## 📐 Diseño General (UML Simplificado)

```
+----------------+        +------------------+
|    Cliente     |        |      Compra      |
+----------------+        +------------------+
| id             |        | id               |
| nombre         |        | idCliente        |
| correo         |        | monto            |
| puntos         |        | fecha            |
| nivel          |        +------------------+
| streakDias     |
+----------------+
       ^                                ^
       |                                |
+----------------+        +------------------+
| ClienteRepo    |        |  CompraRepo      |
+----------------+        +------------------+
| agregar()      |        | registrar()      |
| obtener()      |        | listarPorCliente()|
| actualizar()   |        | contarComprasHoy()|
| eliminar()     |        +------------------+
+----------------+
```

Nivel es un `enum` con lógica de umbrales y multiplicadores.

---

## 🛠 Instrucciones de Compilación y Ejecución

### Requisitos

* Java 21
* Maven 3.9+

### Antes que nada, cambiar de directorio

```bash
cd TarjetaFidelidad
```

### Compilar

```bash
mvn clean compile
```

### Ejecutar por consola

```bash
mvn exec:java
```

---

## 🧪 Ejecutar Pruebas

```bash
mvn test
```

### Ejemplo de salida:

```
[INFO] Running com.fidelidad.FidelidadAppIntegrationTest
[INFO] Tests run: 1, Failures: 0
[INFO] Running com.fidelidad.ClienteTest
[INFO] Tests run: 4, Failures: 0
[INFO] Running com.fidelidad.CompraRepoTest
[INFO] Tests run: 3, Failures: 0

[INFO] Results:
[INFO] Tests run: 8, Failures: 0

[INFO] BUILD SUCCESS
```

---

## 📈 Medición de Cobertura

### Herramienta utilizada:

**JaCoCo (EcclEmma)**  vía Maven plugin.

### Comando:

```bash
mvn verify
```

### Ver reporte:

```bash
open target/site/jacoco/index.html
```

### ¿Qué tipo de cobertura se midió y por qué?

Se midió:

* **Cobertura de líneas**
* **Cobertura de métodos**
* **Cobertura de clases**

Esto permite validar que las pruebas ejercitan efectivamente la lógica de negocio y que todos los caminos principales del sistema están cubiertos. La cobertura se aplicó sobre:

* Validación de datos (`ClienteTest`)
* Reglas de acumulación y bonificación (`CompraRepoTest`)
* Simulación del flujo completo (`FidelidadAppIntegrationTest`)

---

## 📦 Estructura del Proyecto

```
src/
├── main/java/com/fidelidad/
│   ├── Cliente.java
│   ├── Compra.java
│   ├── Nivel.java
│   ├── ClienteRepo.java
│   ├── CompraRepo.java
│   └── FidelidadApp.java
└── test/java/com/fidelidad/
    ├── ClienteTest.java
    ├── CompraRepoTest.java
    └── FidelidadAppIntegrationTest.java
```

---

## 📝 Licencia

Este proyecto está bajo la licencia [MIT License](LICENSE).

---
