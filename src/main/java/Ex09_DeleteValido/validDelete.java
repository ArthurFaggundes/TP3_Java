package Ex09_DeleteValido;

import Ex01_GetSimples.Post;
import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class validDelete {

    public void validDeletion() throws Exception
    {
        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities/9",
                "DELETE");

        System.out.println("Cód. Resposta: " + connection.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionGet = HttpUtil.obterConexao(
                "https://apichallenges.com/sim/entities/9",
                "GET");
        try
        {
            String singleObj = HttpUtil.LerDadosConexao(connectionGet); // FileNotFoundException

            JSONParser parser = new JSONParser();
            JSONObject jsonObj = (JSONObject) parser.parse(singleObj);

            Post post = new Post();
            post.setId((long) jsonObj.get("id"));
            post.setName((String) jsonObj.get("name"));
            post.setDescription((String) jsonObj.get("description"));

            System.out.println("Post 'Deletado': "+ post);
            System.out.println("Cód. Resposta: " + connection.getResponseCode());

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Erro': "+ e); // <----- Cod 404
        }
        finally
        {
            if (connectionGet != null) {
                connectionGet.disconnect();
            }
        }
    }
}
