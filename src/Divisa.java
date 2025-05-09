import java.util.Collections;
import java.util.Map;
import java.util.Set;

public record Divisa(String result,
                     String base_code,
                     Map<String, Double> conversion_rates) {


    public double obtenerConversion(String aDivisa){

        return conversion_rates.get(aDivisa);
    }

    public Set<String> obtenerCodigosDivisa(String aDivisa) {
        return Collections.unmodifiableSet(conversion_rates.keySet());
    }

    // Método para verificar si la llamada a la API fue exitosa
    public boolean isSuccess() {
        return "success".equalsIgnoreCase(result);
    }

}
