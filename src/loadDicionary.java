
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
            if (str == null) {
                break;
            } else {
                String[] aux = str.split("\\W+");
            }
        }
    }
}
