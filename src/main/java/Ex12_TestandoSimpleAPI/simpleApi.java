package Ex12_TestandoSimpleAPI;

import Ex05_PostNovaEntidade.HttpUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class simpleApi {

    public void testingSimpleApi() throws Exception {
        /////////////////////////////////////////////////////////////////////
        //                      GET todos os itens                         //
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connection = HttpUtil.obterConexao(
                "https://apichallenges.com/simpleapi/items",
                "GET");

        System.out.println("\n--------------------------------------------");
        System.out.println("Cód. Resposta: " + connection.getResponseCode());

        String texto = HttpUtil.LerDadosConexao(connection);

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(texto); // pega o obj. entities
        JSONArray jsonArray = (JSONArray) jsonObject.get("items"); // pega o conteúdo dentro desse obj.

        ArrayList<Product> products = new ArrayList<>();

        int count = 0;

        for (Object obj : jsonArray) {
            JSONObject jsonObj = (JSONObject) obj;

            Product post = new Product();
            post.setId((long) jsonObj.get("id"));
            post.setType((String) jsonObj.get("type"));
            post.setIsbn((String) jsonObj.get("isbn13"));
            post.setPrice((double) jsonObj.get("price"));
            post.setStock((long) jsonObj.get("numberinstock"));

            products.add(post);
            count++;

            if (count == 5) { // Para não precisar mostrar tudo, mas se quiser é só tirar isso
                break;
            }
        }
        for (Product prod : products) {
            System.out.println(prod);
        }
        System.out.println("(Limitado a " + count + " produtos)");

        /////////////////////////////////////////////////////////////////////
        //                        Gerar ISBN aleatório                     //
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionRandom = HttpUtil.obterConexao(
                "https://apichallenges.com/simpleapi/randomisbn",
                "GET");

        System.out.println("\n--------------------------------------------");
        System.out.println("Cód. Resposta: " + connectionRandom.getResponseCode());

        String randIsbn = HttpUtil.LerDadosConexao(connectionRandom).trim();

        System.out.println("Número aleatório: " + randIsbn);

        /////////////////////////////////////////////////////////////////////
        //                        Criar item com POST                      //
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionPost = HttpUtil.obterConexao(
                "https://apichallenges.com/simpleapi/items",
                "POST");

        System.out.println("\n--------------------------------------------");
        DataOutputStream out = new DataOutputStream(connectionPost.getOutputStream());
        JSONObject jsonObjToPost = new JSONObject();

        jsonObjToPost.put("type", "book");
        jsonObjToPost.put("isbn13", randIsbn);
        jsonObjToPost.put("price", 5.99);
        jsonObjToPost.put("numberinstock", 5);

        out.writeBytes(jsonObjToPost.toJSONString());
        out.flush(); out.close();

        System.out.println("Post Criado: "+ jsonObjToPost + " com id " + jsonObjToPost.get("id"));

        System.out.println("Cód. Resposta: " + connectionRandom.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        //                      Atualizar item com PUT                     //
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionPut = HttpUtil.obterConexao(
                "https://apichallenges.com/simpleapi/items/" + randIsbn, // <--- erro 422 pq o Post não foi feito de verdade, por ser uma api fake
                "PUT");

        OutputStream outPut = connectionPut.getOutputStream();

        String jsonInputString = "{\"id\": 10, \"price\": 10.99}";
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        outPut.write(input);

        System.out.println("\n--------------------------------------------");
        System.out.println("Cód. Resposta: " + connectionPut.getResponseCode());

        /////////////////////////////////////////////////////////////////////
        //                     Remover item com DELETE                     //
        /////////////////////////////////////////////////////////////////////

        HttpURLConnection connectionDe = HttpUtil.obterConexao(
                "https://apichallenges.com/simpleapi/items/" + randIsbn,
                "DELETE");

        System.out.println("\n--------------------------------------------");
        System.out.println("Cód. Resposta: " + connectionDe.getResponseCode());

    }
}
