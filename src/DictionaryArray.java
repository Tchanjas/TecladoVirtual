import java.io.*;

public class DictionaryArray {

    public static void load() throws IOException {

        String pathDictionary = "dictionary/test.txt";
        String pathStructure = "dictionary/dictionaryArrayStructure.dat";

        File file = new File(pathDictionary);
        if (!file.exists()) {
            return;
        }

        TrieArray dictionary = new TrieArray("");
        File estruturaTrie = new File(pathStructure);

        if (estruturaTrie.exists()) {
            dictionary = loadTrie(pathStructure);
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while (true) {
                String str = reader.readLine();
                if (str == null) {
                    break;
                } else {
                    // (?iu) é a case insensitive flag
                    str = str.replaceAll("(?iu)á|à|ã|â", "a");
                    str = str.replaceAll("(?iu)é|è|ẽ|ê", "e");
                    str = str.replaceAll("(?iu)í|ì|ĩ|î", "i");
                    str = str.replaceAll("(?iu)ó|ò|õ|ô", "o");
                    str = str.replaceAll("(?iu)ú|ù|ũ|û", "u");
                    str = str.replaceAll("(?iu)ç", "c");
                    String[] aux = str.split("[^A-Za-z]+");

                    for (int i = 0; i < aux.length; i++) {
                        TrieArray trieActual = dictionary;
                        aux[i] = aux[i].toLowerCase();
                        for (int j = 0; j < aux[i].length(); j++) {
                            if (j + 1 == aux[i].length()) {
                                trieActual.add(aux[i].charAt(j) + "", true);
                            } else {
                                trieActual.add(aux[i].charAt(j) + "", false);
                                trieActual = trieActual.filhos[aux[i].charAt(j) - 'a'];
                            }

                        }
                    }
                }
            }
            saveTrie(dictionary, pathStructure);
        }
    }

    public static void saveTrie(TrieArray structure, String path) {
        try {
            FileOutputStream fileoutput = new FileOutputStream(path);
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(structure);
            objectoutput.close();
        } catch (Exception e) {
            System.out.println("Erro na gravação da estrutura");
        }
    }

    public static TrieArray loadTrie(String path) {
        File estrutura = new File(path);
        TrieArray estruturaTrie = new TrieArray("");
        if (!estrutura.exists()) {
            return null;
        } else {
            try {
                FileInputStream fileInput = new FileInputStream(estrutura);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                estruturaTrie = (TrieArray) objectInput.readObject();
            } catch (Exception e) {
                System.out.println("Erro no carregamento da estrutura");
            }
            return estruturaTrie;
        }
    }

}
