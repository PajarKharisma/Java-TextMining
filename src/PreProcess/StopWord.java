package PreProcess;

import File.Config;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class StopWord {

    private String[] words;
    private LinkedList<String> listOutput = new LinkedList<String>();
    private LinkedList<String> listStopWord = new LinkedList<String>();
    Map<String, Integer> wordList;

    public void getStopWordList() {
        String stopWord = "";
        Scanner sc = null;
        try {
            sc = new Scanner(new File("StopWord"+Config.delimiter+"StopWordList.txt"));
            //sc = new Scanner(new File("input\\artikel 1.txt"));
        } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "File Stop Word Tidak Ditemukan!!!");
        }
        sc.useDelimiter(" ");
        while (sc.hasNext()) {
            stopWord += sc.next();
            if (stopWord.trim().isEmpty()) {
                continue;
            }
        }
        sc.close();

        tokenList(stopWord);
    }

    private void tokenList(String str) {
        this.listStopWord.clear();
        StringTokenizer stringToken = new StringTokenizer(str);
        while (stringToken.hasMoreElements()) {
            this.listStopWord.add(stringToken.nextToken());
        }

        wordList = new HashMap<>();
        for (String i : listStopWord) {
            wordList.put(i, 0);
        }
    }

    @SuppressWarnings("rawtypes")
    public LinkedList getStopWordResult(LinkedList<String> input) {
        getStopWordList();
        this.listOutput.clear();
        for (String i : input) {
            if (!wordList.containsKey(i)) {
                this.listOutput.add(i);
            }
        }
        return this.listOutput;
    }
}