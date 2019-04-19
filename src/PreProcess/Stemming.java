package PreProcess;

import java.util.LinkedList;
import org.apache.lucene.analysis.id.*;

public class Stemming {

    private LinkedList<String> outputList = new LinkedList<String>();
    IndonesianStemmer stemmer = new IndonesianStemmer();

    public LinkedList<String> getStemmerResult(LinkedList<String> input) {
        outputList.clear();
        for (String i : input) {
            char[] chars = i.toCharArray();
            int len = stemmer.stem(chars, chars.length, true);
            String stem = new String(chars, 0, len);
            outputList.add(stem);
        }
        return outputList;
    }
}
