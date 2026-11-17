package Ex05_PostNovaEntidade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static HttpURLConnection obterConexao(String rota, String metodo) throws Exception
    {
        if (rota == null || rota.isBlank() || metodo == null || metodo.isBlank()) {
            return null;
        }
        URL url = new URL(rota);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setConnectTimeout(10_000);
        connection.setRequestMethod(metodo);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        return connection;
    }

    public static String LerDadosConexao(HttpURLConnection connection) throws Exception
    {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        String linha;
        StringBuilder textoCompleto = new StringBuilder();

        while ((linha = in.readLine()) != null) {
            textoCompleto.append(linha).append("\n"); // Monta o texto
        }
        in.close();

        return textoCompleto.toString();
    }
}
