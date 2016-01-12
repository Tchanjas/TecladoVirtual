
import java.io.*;

public class Dictionary {

    public static void load(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        Trie dictionary = new Trie();

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
                    Trie trieActual = dictionary;
                    aux[i] = aux[i].toLowerCase();
                    for (int j = 0; j < aux[i].length(); j++) {
                        if (j + 1 == aux[i].length()) {
                            trieActual.add(aux[i].charAt(j) + "", true);
                        } else {
                            trieActual.add(aux[i].charAt(j) + "", false);
                            trieActual = trieActual.filhos.get(aux[i].charAt(j) + "");
                        }

                    }
                }
            }
        }
        saveTrie(dictionary);
    }

    public static void saveTrie(Trie structure) {
        try {
            FileOutputStream fileoutput = new FileOutputStream("dictionary/dictionaryStructure.dat");
            ObjectOutputStream objectoutput = new ObjectOutputStream(fileoutput);
            objectoutput.writeObject(structure);
            objectoutput.close();
        } catch (Exception e) {
            System.out.println("Erro na gravação da estrutura");
        }
    }

    public static Trie loadTrie(String path) {
        File estrutura = new File(path);
        Trie estruturaTrie = new Trie();
        if (!estrutura.exists()) {
            return null;
        } else {
            try {
                FileInputStream fileInput = new FileInputStream(estrutura);
                ObjectInputStream objectInput = new ObjectInputStream(fileInput);
                estruturaTrie = (Trie) objectInput.readObject();
            } catch (Exception e) {
                System.out.println("Erro no carregamento da estrutura");
            }
            return estruturaTrie;
        }
    }

}
