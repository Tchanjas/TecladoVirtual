
import java.io.*;

public class createDictionary {

    public static void main(String[] args) throws IOException {
        File dicionario = new File("dictionary/test.txt");

        if (!dicionario.exists()) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dicionario)));

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
                    System.out.print(aux[i]  + " ");
                }
                System.out.println("");
            }
        }
    }
}
