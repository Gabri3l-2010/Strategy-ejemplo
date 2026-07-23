
package org.gabrielchiu.System;

// 1. La Interfaz Strategy
interface RouteCalculationStrategy {
    String calculateRoute(String origin, String destination);
}
// 2. ConcreteStrategy A: Ruta Ecológica
class EcoRouteStrategy implements RouteCalculationStrategy {
    @Override
    public String calculateRoute(String origin, String destination) {
        return "[Ruta ECO] Calculando trayecto de " + origin + " a " + destination + 
               "\n   -> Optimizando altitud para viento a favor." +
               "\n   -> Consumo estimado de batería: 12%";
    }
}
// 3. ConcreteStrategy B: Ruta Exprés (Emergencias)
class ExpressRouteStrategy implements RouteCalculationStrategy {
    @Override
    public String calculateRoute(String origin, String destination) {
        return "[Ruta EXPRÉS] Calculando trayecto de " + origin + " a " + destination + 
               "\n   -> Trazando línea recta a velocidad máxima." +
               "\n   -> Consumo estimado de batería: 35%";
    }
}
// 4. ConcreteStrategy C: Ruta Segura (Mal clima o carga frágil)
class SafeRouteStrategy implements RouteCalculationStrategy {
    @Override
    public String calculateRoute(String origin, String destination) {
        return "[Ruta SEGURA] Calculando trayecto de " + origin + " a " + destination + 
               "\n   -> Evitando zonas residenciales y alerta de tormenta en sector oeste." +
               "\n   -> Consumo estimado de batería: 22%";
    }
}
// 5. El Contexto (El navegador del Dron)
class DroneNavigator {
    private RouteCalculationStrategy strategy;
    // Se inyecta una estrategia inicial por defecto
    public DroneNavigator(RouteCalculationStrategy strategy) {
        this.strategy = strategy;
    }
    // Permite cambiar la estrategia en TIEMPO DE EJECUCIÓN
    public void setStrategy(RouteCalculationStrategy strategy) {
        System.out.println(">>> ALERTA: Cambiando modo de vuelo...");
        this.strategy = strategy;
    }
    // El contexto delega el trabajo al objeto estrategia
    public void navigate(String origin, String destination) {
        System.out.println("Iniciando secuencia de navegación...");
        String routeDetails = strategy.calculateRoute(origin, destination);
        System.out.println(routeDetails);
        System.out.println("¡Despegue autorizado!\n");
    }
}
// 6. El Cliente (Main)
public class DeliveryDroneApp {
    public static void main(String[] args) {
        String origin = "Centro de Distribución Norte";
        String destination = "Hospital Central";
        // Escenario 1: Envío normal, priorizamos la batería
        System.out.println("--- ESCENARIO 1: Entrega estándar ---");
        DroneNavigator dron1 = new DroneNavigator(new EcoRouteStrategy());
        dron1.navigate(origin, destination);
        // Escenario 2: Emergencia médica, el dron cambia a exprés a mitad del día
        System.out.println("--- ESCENARIO 2: Traslado de medicinas urgente ---");
        dron1.setStrategy(new ExpressRouteStrategy());
        dron1.navigate(origin, destination);
        // Escenario 3: Los sensores del dron detectan vientos huracanados
        System.out.println("--- ESCENARIO 3: Sensores detectan ráfagas de viento peligrosas ---");
        dron1.setStrategy(new SafeRouteStrategy());
        dron1.navigate(origin, destination);
    }
}