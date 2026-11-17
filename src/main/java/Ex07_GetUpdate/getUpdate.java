package Ex07_GetUpdate;

import Ex01_GetSimples.Post;
import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class getUpdate {

    public void getUpdatePost() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities/10",
                "GET");

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        JSONObject jsonObjToPost = new JSONObject();

        jsonObjToPost.put("id", 10);
        jsonObjToPost.put("name", "atualizado");

        out.write(jsonObjToPost.toString().getBytes());
        out.flush(); out.close();

        String singleObj = HttpUtil.LerDadosConexao(connection);

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        JSONParser parser = new JSONParser();
        jsonObjToPost = (JSONObject) parser.parse(singleObj);

        // Cria um post para mostrar
        Post post = new Post();
        post.setId((long) jsonObjToPost.get("id"));
        post.setName("atualizado");
        post.setDescription((String) jsonObjToPost.get("description"));

        System.out.println("Post Atualizado: "+ post);
        System.out.println("Cód. Resposta: " + connection.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionTwo = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities",
                "GET");

        String fullRequest = HttpUtil.LerDadosConexao(connectionTwo);

        JSONObject jsonObject = (JSONObject) parser.parse(fullRequest);
        JSONArray jsonArray = (JSONArray) jsonObject.get("entities");

        ArrayList<Post> posts = new ArrayList<>();

        for (Object obj : jsonArray) {
            JSONObject tempObj = (JSONObject) obj;
            Post tempPost = new Post();

            tempPost.setId((long) tempObj.get("id"));
            tempPost.setName((String) tempObj.get("name"));
            tempPost.setDescription((String) tempObj.get("description"));

            posts.add(tempPost);
        }
        for (Post poster : posts) {
            System.out.println(poster);
        }

        System.out.println("Cód. Resposta: " + connection.getResponseCode());
        connection.disconnect();
    }
}
