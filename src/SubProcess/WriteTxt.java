package SubProcess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WriteTxt {

    private BufferedWriter writeOut;

    public void writeFile(String file, String pathOutput, String name) {
        try {
            writeOut = new BufferedWriter(new FileWriter(pathOutput + name));
            writeOut.write(file);
            writeOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void writeFile(LinkedList<String> listFiles, String pathOutput, String name) {
        File dir = new File(pathOutput);
        
        try {
            String file = "";
            for (String i : listFiles) {
                file += i + "\n";
            }
            writeOut = new BufferedWriter(new FileWriter(pathOutput + name));
            writeOut.write(file);
            writeOut.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
