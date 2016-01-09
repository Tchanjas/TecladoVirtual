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
                    Trie nodoActual = dictionary;
                    aux[i] = aux[i].toLowerCase();
                    for (int j = 0; j < aux[i].length(); j++) {
                        if (j + 1 == aux[i].length()) {
                            nodoActual.add(aux[i].charAt(j) + "", true);
                        } else {
                            nodoActual.add(aux[i].charAt(j) + "", false);
                            nodoActual = nodoActual.filhos.get(aux[i].charAt(j) + "");
                        }

                    }
                }
            }
        }

    }

}
