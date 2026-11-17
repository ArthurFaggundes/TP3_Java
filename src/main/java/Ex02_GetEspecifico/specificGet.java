package Ex02_GetEspecifico;

import Ex01_GetSimples.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class specificGet {

    public void specificGetter() throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Insira o ID para buscar: ");
        String id = scanner.next();

        String path = "https://apichallenges.com/sim/entities";

        if (id != null ) {
            path = "https://apichallenges.com/sim/entities/" + id;
        }

        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(10_000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        BufferedReader in = new BufferedReader( // Recebe a resposta
                new InputStreamReader(connection.getInputStream()));

        String line;
        StringBuilder texto = new StringBuilder();

        while ((line = in.readLine()) != null) {
            texto.append(line).append("\n"); // Monta o texto

        }

        in.close();

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(texto.toString()); // Exception in thread "main" Unexpected token END OF FILE at position 0.

        Post post = new Post();
        post.setId((long) jsonObject.get("id"));
        post.setName((String) jsonObject.get("name"));
        post.setDescription((String) jsonObject.get("description"));

        System.out.println(post);

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////
    }
}
