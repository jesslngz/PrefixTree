package SufixTree;

import SufixTree.Exception.InvalidCharException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        PrefixTree prefixTree = new PrefixTree();

        Scanner scanner = new Scanner(System.in);  // Reading from System.in
        String file = "nomes.csv";
        HashMap wordsHashMap = readWords(file);
        for (Object key : wordsHashMap.keySet()) {
            prefixTree.addWord(key.toString(), wordsHashMap.get(key).toString());
        }

        //Start of user interaction
        System.out.print("Digite o prefixo para podermos procurar palavras que o contenham: ");
        String userOutput = scanner.next();
        verifyPrefix(userOutput);
        List wordsList = prefixTree.getWords(userOutput);

        while (wordsList.isEmpty()) {
            System.out.print("Não encontramos palavras com este prefixo. Digite outro prefixo: ");
            userOutput = scanner.next();
            verifyPrefix(userOutput);
            wordsList = prefixTree.getWords(userOutput);
        }

        if (!wordsList.isEmpty()) {
            System.out.print("Agora digite uma das palavras ao lado e diremos o significado: ");
            System.out.println(wordsList);

            String word = scanner.next();
            String meaning = (String) wordsHashMap.get(word); //Search word meaning

            while (meaning == null) { //If the user enters the wrong word
                System.out.print("A palavra digitada não está na lista. Digite uma das palavras ao lado: ");
                System.out.println(wordsList);

                word = scanner.next();
                meaning = (String) wordsHashMap.get(word); //Search word meaning
            }
            System.out.println(String.format("O significado de %s é: %s.", word, meaning));
        }
    }

    private static HashMap readWords(String file) throws IOException {
        BufferedReader names = new BufferedReader(new FileReader(file));
        System.out.println("Carregando o arquivo: " + file);
        HashMap wordsAndDefinitions = new HashMap();
        while (names.ready()) {
            String string = names.readLine().toLowerCase();
            int aux = string.indexOf(";");
            String chave = string.substring(0, aux);
            String valor = string.substring(aux + 1, string.length());
            wordsAndDefinitions.put(chave, valor);
        }
        names.close();
        return wordsAndDefinitions;
    }

    public static void verifyPrefix(String prefix) {
        for (int i = 0; i < prefix.length(); i++) {
            int isValid = prefix.charAt(i) - 'a';
            if ((isValid < 0 || isValid > 25) ){
                throw new InvalidCharException();
            }
        }
    }
}
