import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scannerPrincipal = new Scanner(System.in);
        ConsultaDivisa consulta = new ConsultaDivisa();
        Conversion conversor = new Conversion(); // Instancia de Conversion

        System.out.println("******************************************");
        System.out.println("Este programa te permite hacer conversión entre divisas");
        System.out.println("******************************************");

        while (true) {
            System.out.println("\nPor favor, ingresa el código de la divisa base (ej: USD, EUR, COP):");
            String divisaBaseCodigo = scannerPrincipal.nextLine().toUpperCase();

            Entrada entrada = new Entrada();

            if(!entrada.validarEntrada(divisaBaseCodigo)){
                System.out.println("Código de divisa incorrecto, debe ingresar un código de 3 letras válido");
                continue; // Volver al inicio del bucle para pedir otra divisa base
            }


            Divisa divisaBaseInfo = consulta.buscaDivisa(divisaBaseCodigo);

            // Verificar si la consulta fue exitosa y se obtuvieron las tasas
            if (divisaBaseInfo == null || !divisaBaseInfo.isSuccess()) {
                System.out.println("No se pudieron obtener las tasas de cambio para la divisa base '" + divisaBaseCodigo + "'. Intenta con otro código.");
                System.out.println("¿Desea intentar con otra divisa base? (Si/No)");
                String respuestaContinuar = scannerPrincipal.nextLine();
                if (!respuestaContinuar.equalsIgnoreCase("si")) {
                    break; // Salir del bucle principal si el usuario no quiere intentar de nuevo
                }
                continue; // Volver al inicio del bucle para pedir otra divisa base
            }

            System.out.println("\nTasas de conversión disponibles desde " + divisaBaseInfo.base_code() + ":");
            Set<String> codigosDivisasDisponibles = divisaBaseInfo.obtenerCodigosDivisa(divisaBaseInfo.base_code());
            if (codigosDivisasDisponibles.isEmpty()) {
                System.out.println("No hay tasas de conversión disponibles para esta divisa base.");
            } else {
                // Imprimir las divisas en columnas para mejor legibilidad (opcional)
                int count = 0;
                for (String codigo : codigosDivisasDisponibles) {
                    System.out.printf("%-6s ", codigo); // Formato para alinear
                    count++;
                    if (count % 10 == 0) { // Imprimir 10 códigos por línea
                        System.out.println();
                    }
                }
                System.out.println(); // Nueva línea al final
            }


            System.out.println("\n-----------------------------------------");
            System.out.println("Ingresa el código de la divisa a la que quieres convertir:");
            String divisaDestinoCodigo = scannerPrincipal.nextLine().toUpperCase();

            if (!codigosDivisasDisponibles.contains(divisaDestinoCodigo)) {
                System.out.println("Error: El código de divisa de destino '" + divisaDestinoCodigo + "' no es válido o no está disponible.");
                System.out.println("¿Desea realizar otra consulta? (Si/No)");
                String respuestaContinuar = scannerPrincipal.nextLine();
                if (!respuestaContinuar.equalsIgnoreCase("si")) {
                    break;
                }
                continue; // Volver al inicio del bucle para una nueva consulta
            }

            double cantidad = 0;
            boolean entradaValida = false;
            while(!entradaValida) {
                System.out.println("Indica la cantidad a convertir:");
                try {
                    cantidad = Double.parseDouble(scannerPrincipal.nextLine()); // Usar nextLine() y parsear para evitar problemas con nextDouble()
                    if (cantidad <= 0) {
                        System.out.println("La cantidad no puede ser negativa o 0. Intenta de nuevo.");
                    } else {
                        entradaValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, ingresa un número válido para la cantidad.");
                }
            }


            try {
                double tasaConversion = divisaBaseInfo.obtenerConversion(divisaDestinoCodigo);
                double resultado = conversor.conversion(tasaConversion, cantidad);

                System.out.println("\n-----------------------------------------");
                System.out.println(cantidad + " " + divisaBaseInfo.base_code() + " Equivalen a: " + String.format("%.2f", resultado) + " " + divisaDestinoCodigo);
                System.out.println("-----------------------------------------");

            } catch (IllegalArgumentException e) {
                System.err.println("Error durante la conversión: " + e.getMessage());
                // Esto podría ocurrir si obtenerConversion falla a pesar de la verificación previa,
                // lo cual indicaría un problema lógico o en los datos de la API.
            }


            System.out.println("\n******************************************");
            System.out.println("¿Desea realizar otra consulta? (Si/No)");
            System.out.println("******************************************");
            String menu = scannerPrincipal.nextLine().toUpperCase();

            if (!menu.equalsIgnoreCase("si")) {
                break;// Salir del bucle principal
            }
        }

        System.out.println("\nFinalizó la ejecución del programa!");
        scannerPrincipal.close(); // Cerrar el scanner al finalizar
    }

}
