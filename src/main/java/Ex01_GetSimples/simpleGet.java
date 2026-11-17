package Ex01_GetSimples;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class simpleGet {

    public void simpleGetter() throws Exception {
        String path = "https://apichallenges.com/sim/entities";

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
        JSONObject jsonObject = (JSONObject) parser.parse(texto.toString()); // pega o obj. entities
        JSONArray jsonArray = (JSONArray) jsonObject.get("entities"); // pega o conteúdo dentro desse obj.

        ArrayList<Post> posts = new ArrayList<>();

        for (Object obj : jsonArray) {
            JSONObject jsonObj = (JSONObject) obj;

            Post post = new Post();
            post.setId((long) jsonObj.get("id"));
            post.setName((String) jsonObj.get("name"));
            post.setDescription((String) jsonObj.get("description"));

            posts.add(post);
        }
        for (Post post : posts) {
            System.out.println(post);
        }
        System.out.println("Cód. Resposta: " + connection.getResponseCode());
    }
}
