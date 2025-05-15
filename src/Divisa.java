import java.util.Collections;
import java.util.Map;
import java.util.Set;

public record Divisa(String result,
                     String base_code,
                     String target_code,
                     double conversion_rate,
                     double conversion_result) {




    // Metodo para verificar si la llamada a la API fue exitosa
    public boolean isSuccess() {
        return "success".equalsIgnoreCase(result);
    }

}
