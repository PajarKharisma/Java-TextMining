package SubProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class OpenFile {

    private String input;

    public String getFile(String path) {
        Scanner sc = null;
        input = "";
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        sc.useDelimiter("");
        while (sc.hasNext()) {
            input += sc.next();
            if (input.trim().isEmpty()) {
                continue;
            }
        }
        sc.close();

        return input;
    }

    public String getFile(File file) {
        Scanner sc = null;
        input = "";
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        sc.useDelimiter("");
        while (sc.hasNext()) {
            input += sc.next();
            if (input.trim().isEmpty()) {
                continue;
            }
        }
        sc.close();

        return input;
    }

    public String getFile2(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String output = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            output = sb.toString();
        } finally {
            br.close();
        }
        return output;
    }
}
