import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerPrincipal = new Scanner(System.in);
        ConsultaDivisa consulta = new ConsultaDivisa();

        System.out.println(Constantes.INICIO);


        while (true) {
            System.out.println(Constantes.MENSAJE_DIVISABASE);
            String codigoDivisaBase = scannerPrincipal.nextLine().toUpperCase();


            Entrada entrada = new Entrada();
            if(!entrada.validarEntrada(codigoDivisaBase)){
                System.out.println(Constantes.ERROR_DIVISA);
                continue; // Volver al inicio del bucle para pedir otra divisa base
            }
            String codigoDivisaDestino;
            while (true){
                System.out.println(Constantes.MENSAJE_DIVISADESTINO);
                codigoDivisaDestino = scannerPrincipal.nextLine().toUpperCase();


                if(!entrada.validarEntrada(codigoDivisaDestino)){
                    System.out.println(Constantes.ERROR_DIVISA);
                    continue; // Volver al inicio del bucle para pedir otra divisa destino
                }
                break;
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

            Divisa divisa = consulta.buscaDivisa(codigoDivisaBase, codigoDivisaDestino, cantidad);

            // Verificar si la consulta fue exitosa y se obtuvieron las tasas
            if (divisa == null || !divisa.isSuccess()) {
                System.out.println("No se pudieron obtener las tasas de cambio para la divisa base '" + codigoDivisaBase + "'. Intenta con otro código.");
                System.out.println("¿Desea intentar con otra divisa base? (Si/No)");
                String respuestaContinuar = scannerPrincipal.nextLine();
                if (!respuestaContinuar.equalsIgnoreCase("si")) {
                    break; // Salir del bucle principal si el usuario no quiere intentar de nuevo
                }
                continue; // Volver al inicio del bucle para pedir otra divisa base
            }

            try {

                System.out.println("\n-----------------------------------------");
                System.out.println(cantidad + " " + divisa.base_code() + " Equivalen a: " + String.format("%.2f", divisa.conversion_result()) + " " + divisa.target_code());
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
