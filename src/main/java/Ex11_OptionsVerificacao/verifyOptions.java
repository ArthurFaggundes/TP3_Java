package Ex11_OptionsVerificacao;

import Ex01_GetSimples.Post;
import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class verifyOptions {

    public void verificationOptions() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities",
                "OPTIONS");

        System.out.println("Cód. Resposta: " + connection.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////
        String metodosPermitidos = connection.getHeaderField("Allow");

        if (metodosPermitidos != null) {
            System.out.println("Métodos permitidos (Allow): " + metodosPermitidos);
        } else {
            System.out.println("O servidor não retornou o cabeçalho 'allow'.");
        }

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        // Lê os cabecalhos de retorno como os métodos permitidos

//        Map<String, List<String>> campos = connection.getHeaderFields();
//
//        for (Map.Entry<String, List<String>> entry : campos.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        connection.disconnect();
    }
}
