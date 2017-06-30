package SufixTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        PrefixTree prefixTree = new PrefixTree();

        Scanner fileReader = new Scanner(System.in);  // Reading from System.in
//        System.out.println("Enter the path of the file: ");
//        String file = fileReader.nextLine();
        String file = "C:/Users/I859949/Downloads/nomes.csv";
        HashMap wordsHashMap = readWords(file);
        for(Object key : wordsHashMap.keySet()) {
            prefixTree.addWord(key.toString(), wordsHashMap.get(key).toString());
        }
        System.out.println(prefixTree.getWords("gu"));
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
}
