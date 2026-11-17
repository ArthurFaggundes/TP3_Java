package Ex06_GetDePostCriado;

import Ex01_GetSimples.Post;
import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class getNewPost {

    public void getNewPoster() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities",
                "POST");

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        JSONObject jsonObjToPost = new JSONObject();

        jsonObjToPost.put("id", 11);
        jsonObjToPost.put("name", "aluno"); // {"name": "aluno"}

        out.writeBytes(jsonObjToPost.toJSONString());
        //out.flush(); out.close();

        System.out.println("Post Criado: "+ jsonObjToPost);

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

//        HttpURLConnection connectionGet = HttpUtil.obterConexao(
//                "https://apichallenges.com/sim/entities",
//                "GET");
//

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        String texto = HttpUtil.LerDadosConexao(connection);
        JSONParser parser = new JSONParser();

//        HttpURLConnection connectionTwo = HttpUtil.obterConexao(
//                "https://apichallenges.com/sim/entities",
//                "GET");
//
//        String fullRequest = HttpUtil.LerDadosConexao(connectionTwo);

        jsonObjToPost = (JSONObject) parser.parse(texto);

        Post tempPost = new Post();

        tempPost.setId((long) jsonObjToPost.get("id"));
        tempPost.setName((String) jsonObjToPost.get("name"));
        tempPost.setDescription((String) jsonObjToPost.get("description"));

        System.out.println(tempPost);
        System.out.println("Cód. Resposta: " + connection.getResponseCode());
    }
}
