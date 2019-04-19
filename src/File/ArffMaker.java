package File;

import PreProcess.Tokenizing;
import Gui.MainFrame;
import SubProcess.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ArffMaker {
    private static final int TRAINING = 0;
    private static final int TESTING = 1;
    
    private String docTitle = "";
    private OpenFile openFile = new OpenFile();
    private Tokenizing tokenizing = new Tokenizing();
    private LinkedList<String> listAtt;
    private LinkedList<String> listKelas;
    private File[] file;
    private String numeric[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private WriteTxt writeTxt = new WriteTxt();
    public MainFrame mainFrame;
    
    public void createArff(int mode) throws IOException{
        mainFrame = new MainFrame();
        if(mode == TRAINING)
            docTitle = "training";
        else
            docTitle = "testing";
        
        String kelas = openFile.getFile("output\\C45\\DocumentName.txt");
        String word = openFile.getFile("output\\C45\\C45Output.txt");
        listAtt = tokenizing.getTokenizingResult(word);
        listKelas = tokenizing.getTokenizingResult(kelas);
        
        String outputText = "";
        outputText += "@relation "+docTitle+"\n\n";
        for(String la : listAtt){
            outputText += "@attribute "+la+" numeric\n";
        }
        outputText += "@attribute @kelas@ {";
        for(int i=0; i<listKelas.size(); i++){
            outputText += listKelas.get(i);
            if(i < listKelas.size()-1)
                outputText += ",";
        }
        outputText += "}\n\n@data\n";
        
        file = new File("output\\tmpOutput").listFiles();
        
        float ndata = file.length;
        float step = 1;
        float percentage = 0;
        System.out.println("====== CREATE ARFF PROCESS =======");
        for (File file1 : file) {
            percentage = (step/ndata)*100;
            step++;
            System.out.println("Proses : "+percentage+" %");
            
            String name = file1.getName();
            name = name.replaceAll(".txt", "");
            for(String num:numeric)
                name = name.replaceAll(num, "");
            String text = openFile.getFile(file1);
            LinkedList<String> list = tokenizing.getTokenizingResult(text);
            int n = 0;
            for(String att : listAtt){
                for(String term : list){
                    if(term.equalsIgnoreCase(att)){
                        n++;
                    }
                }
                outputText += n +", ";
                n = 0;
            }
            if(mode == TRAINING)
                outputText += name +"\n";
            else
                outputText += "?\n";
        }       
        writeTxt.writeFile(outputText, "output\\", docTitle+".arff");
    }
}