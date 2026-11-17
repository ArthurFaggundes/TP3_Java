package Ex08_PutParaAtualizar;

import Ex01_GetSimples.Post;
import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class putUpdate {

    public void putToUpdate() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities/10",
                "PUT");

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////
        // OUTRA FORMA DE FAZER O PUT/POST >>

        // Define o conteúdo (payload) e escreve no OutputStream
        OutputStream out = connection.getOutputStream();

        String jsonInputString = "{\"id\": 10, \"name\": \"atualizado\"}";
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        out.write(input);
        System.out.println("Cód. Resposta: " + connection.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        String singleObj = HttpUtil.LerDadosConexao(connection);

        System.out.println(singleObj);
        System.out.println("Cód. Resposta: " + connection.getResponseCode());
    }
}
