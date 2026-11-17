import Ex01_GetSimples.simpleGet;
import Ex02_GetEspecifico.specificGet;
import Ex03_GetInexistente.noneGet;
import Ex04_ParametrosFicticios.fictionalGet;
import Ex05_PostNovaEntidade.newPost;
import Ex06_GetDePostCriado.getNewPost;
import Ex07_GetUpdate.getUpdate;
import Ex08_PutParaAtualizar.putUpdate;
import Ex09_DeleteValido.validDelete;
import Ex10_DeleteInvalido.invalidDelete;
import Ex11_OptionsVerificacao.verifyOptions;
import Ex12_TestandoSimpleAPI.simpleApi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha qual exercício rodar");
        System.out.println("Ex.1 Get Simples");
        System.out.println("Ex.2 Get Específico");
        System.out.println("Ex.3 Get Inexistente");
        System.out.println("Ex.4 Parametros Ficticios");
        System.out.println("Ex.5 Post Nova entidade");
        System.out.println("Ex.6 Get de Post Criado");
        System.out.println("Ex.7 Get de Post Atualizado");
        System.out.println("Ex.8 Put de Nova Entidade");
        System.out.println("Ex.9 Delete de Entidade");
        System.out.println("Ex.10 Delete de Entidade Inexistente");
        System.out.println("Ex.11 Leitura das Options");
        System.out.println("Ex.12 Testes com Simple API");
        System.out.print("[Ex. Núm.]: ");
        String escolha = scanner.nextLine();

        switch (escolha) {
            case "1":
                simpleGet ex01 = new simpleGet();
                ex01.simpleGetter();
                break;
            case "2":
                specificGet ex02 = new specificGet();
                ex02.specificGetter();
                break;
            case "3":
                noneGet ex03 = new noneGet();
                ex03.noneGetter();
                break;
            case "4":
                fictionalGet ex04 = new fictionalGet();
                ex04.fictionalGetter();
                break;
            case "5":
                newPost ex05 = new newPost();
                ex05.newPoster();
                break;
            case "6":
                getNewPost ex06 = new getNewPost();
                ex06.getNewPoster();
                break;
            case "7":
                getUpdate ex07 = new getUpdate();
                ex07.getUpdatePost();
                break;
            case "8":
                putUpdate ex08 = new putUpdate();
                ex08.putToUpdate();
                break;
            case "9":
                validDelete ex09 = new validDelete();
                ex09.validDeletion();
                break;
            case "10":
                invalidDelete ex10 = new invalidDelete();
                ex10.invalidDeletion();
                break;
            case "11":
                verifyOptions ex11 = new verifyOptions();
                ex11.verificationOptions();
                break;
            case "12":
                simpleApi ex12 = new simpleApi();
                ex12.testingSimpleApi();
                break;
        }
    }
}