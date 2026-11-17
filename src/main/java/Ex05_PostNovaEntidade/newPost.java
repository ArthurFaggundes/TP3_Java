package Ex05_PostNovaEntidade;

import Ex01_GetSimples.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class newPost {

    public void newPoster() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities",
                "POST");

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        JSONObject jsonObjToPost = new JSONObject();

        jsonObjToPost.put("name", "aluno"); // Essa infor. não está indo para um post

        out.write(jsonObjToPost.toString().getBytes());
        //out.writeBytes(jsonObjToPost.toJSONString());
        out.flush(); out.close();

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        String texto = HttpUtil.LerDadosConexao(connection);

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        JSONParser parser = new JSONParser();
        //ArrayList<Post> posts = new ArrayList<>();

        jsonObjToPost = (JSONObject) parser.parse(texto); // pega o obj. entities
        //JSONArray jsonArray = (JSONArray) jsonObjToPost.get("entities"); // pega o conteúdo dentro desse obj.

        Post post = new Post(); //Cria um post para mostrar
        post.setId((long) jsonObjToPost.get("id"));
        post.setName((String) jsonObjToPost.get("name"));
        post.setDescription((String) jsonObjToPost.get("description"));

        System.out.println(post);
        System.out.println("Cód. Resposta: " + connection.getResponseCode());
    }
}
