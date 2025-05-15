import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaDivisa {


    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/" + ClaveApi.API_KEY + "/pair/";

    public Divisa buscaDivisa(String codigoDivisaBase, String codigoDivisaDestino, double valorAConvertir){

        URI direccion = URI.create(API_BASE_URL + codigoDivisaBase.toUpperCase() + "/"+ codigoDivisaDestino.toUpperCase() + "/" + valorAConvertir);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Divisa.class);
    }

}
