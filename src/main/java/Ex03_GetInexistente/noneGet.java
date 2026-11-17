package Ex03_GetInexistente;

import Ex01_GetSimples.Post;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class noneGet {

    public void noneGetter() throws Exception {

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

        if (connection.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader( // Recebe a resposta
                    new InputStreamReader(connection.getInputStream())); // <--- quebra aqui

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

            Post post = new Post();
            post.setId((long) jsonObject.get("id"));
            post.setName((String) jsonObject.get("name"));
            post.setDescription((String) jsonObject.get("description"));

            System.out.println(post);
        }
        else {
            System.out.println("Entidade não encontrada...");
        }
    }
}
