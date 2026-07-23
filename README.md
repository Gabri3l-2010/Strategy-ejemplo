#  Sistema de Navegación Dinámica para Flotas de Drones
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Design Patterns](https://img.shields.io/badge/Design_Pattern-Strategy-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completado-success?style=for-the-badge)
Este proyecto es una simulación de un sistema de enrutamiento para una flota de drones de reparto. Demuestra de manera práctica y realista la implementación del **Patrón de Diseño Strategy** en Java.
El sistema permite que un dron cambie su algoritmo de cálculo de ruta en tiempo de ejecución (en pleno vuelo) para adaptarse a cambios en el entorno (batería baja, clima adverso, emergencias), sin necesidad de reiniciar el sistema ni utilizar complejas y frágiles estructuras de control `if/else`.
## Tabla de Contenidos
- [¿Por qué usar el patrón Strategy?](#-por-qué-usar-el-patrón-strategy)
- [Arquitectura (Diagrama UML)](#-arquitectura)
- [Estrategias Implementadas](#-estrategias-implementadas)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Ejecución](#-ejecución)
- [Retos Adicionales (Extensibilidad)](#-retos-adicionales)
##  ¿Por qué usar el patrón Strategy?
En un sistema tradicional, el dron calcularía su ruta usando múltiples condicionales, lo que eventualmente crearía un **"God Object"** (Objeto Dios) imposible de mantener y probar. 
Al aplicar el patrón Strategy logramos:
1. **Cumplir el Principio Abierto/Cerrado (OCP):** Podemos añadir nuevos algoritmos de vuelo (ej. rutas nocturnas, rutas furtivas) sin tocar el código central del dron.
2. **Aislamiento de Lógica:** Las dependencias específicas (APIs de clima para rutas seguras, cálculos de altitud para rutas ecológicas) quedan encapsuladas en sus propias clases.
3. **Flexibilidad en Tiempo de Ejecución:** El cliente puede intercambiar el comportamiento del dron de forma dinámica.
## 🏗 Arquitectura
El proyecto está compuesto por el Contexto (`DroneNavigator`), la interfaz de la Estrategia (`RouteCalculationStrategy`) y las estrategias concretas.
```mermaid
classDiagram
    class DroneNavigator {
        -RouteCalculationStrategy strategy
        +DroneNavigator(RouteCalculationStrategy)
        +setStrategy(RouteCalculationStrategy)
        +navigate(origin, destination)
    }
    class RouteCalculationStrategy {
        <<interface>>
        +calculateRoute(origin, destination) String
    }
    class EcoRouteStrategy {
        +calculateRoute(origin, destination) String
    }
    class ExpressRouteStrategy {
        +calculateRoute(origin, destination) String
    }
    class SafeRouteStrategy {
        +calculateRoute(origin, destination) String
    }
    DroneNavigator o-- RouteCalculationStrategy : Tiene una
    RouteCalculationStrategy <|.. EcoRouteStrategy : Implementa
    RouteCalculationStrategy <|.. ExpressRouteStrategy : Implementa
    RouteCalculationStrategy <|.. SafeRouteStrategy : Implementa
