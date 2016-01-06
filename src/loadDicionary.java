
import java.io.*;

public class loadDicionary {

    public static void main(String[] args) throws IOException {
        File dicionario = new File("dictionary/test.txt");

        if (!dicionario.exists()) {
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dicionario)));

        while (true) {
            String str = reader.readLine();
            // (?iu) é a case insensitive flag
            if (str == null) {
                break;
            } else {
                System.out.println(str);
                str = str.replaceAll("(?iu)á|à|ã|â", "a");
                str = str.replaceAll("(?iu)é|è|ẽ|ê", "e");
                str = str.replaceAll("(?iu)í|ì|ĩ|î", "i");
                str = str.replaceAll("(?iu)ó|ò|õ|ô", "o");
                str = str.replaceAll("(?iu)ú|ù|ũ|û", "u");
                str = str.replaceAll("(?iu)ç", "c");
                System.out.println(str);
                String[] aux = str.split("\\W+");
            }
        }
    }
}
