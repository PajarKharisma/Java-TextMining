package Metode;

import java.io.File;
import java.util.LinkedList;
import File.*;
import PreProcess.Tokenizing;
import SubProcess.OpenFile;
import SubProcess.WriteTxt;

public class TFIDFCalculator {

    protected File[] file;
    protected OpenFile openFile;
    protected Tokenizing tokenizing;
    protected Doc[] doc;
    protected LinkedList<LinkedList<String>> listDocs;
    protected WriteTxt writeTxt = new WriteTxt();
    private DocSorting ds;

    public double tf(LinkedList<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                result++;
            }
        }
        //System.out.println("Term : "+term+" || TF : "+result);
        return result / doc.size();
    }
    
    public double tf2(LinkedList<String> doc, String term){
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word)) {
                result++;
            }
        }
        //System.out.println("Term : "+term+" || TF : "+result);
        return result;
    }

    public double idf(LinkedList<LinkedList<String>> docs, String term) {
        double n = 0;
        for (LinkedList<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        
        //System.out.println("Term : "+term+" || idf : "+Math.log(docs.size() / n));
        return Math.log(docs.size() / n);
    }

    public double tfIdf(LinkedList<String> doc, LinkedList<LinkedList<String>> docs, String term) {
        return tf2(doc, term) * idf(docs, term);
    }

    protected void getDoc() {
        file = new File("output\\tmpOutput").listFiles();
    }

    protected void getWord() {
        getDoc();
        openFile = new OpenFile();
        tokenizing = new Tokenizing();
        doc = new Doc[file.length];
        listDocs = new LinkedList<>();

        for (int i = 0; i < doc.length; i++) {
            String input = openFile.getFile(file[i]);
            doc[i] = new Doc();
            doc[i].setName(file[i].getName());
            doc[i].setList(tokenizing.getTokenizingResult(input));
        }

        for (Doc i : doc) {
            listDocs.add(i.getList());
        }
    }

    protected void deleteDir() {
        File dir = new File("output\\TfIdfOutput");
        for (File i : dir.listFiles()) {
            i.delete();
        }
    }

    /*public static void main(String[] args) {
     TFIDFCalculator calculator = new TFIDFCalculator();
     calculator.sorting();
    	
     List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum", "Has");
     List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
     List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
     List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

     TFIDFCalculator calculator = new TFIDFCalculator();
     double tfidf = calculator.tfIdf(doc1, documents, "Has");
     System.out.println("TF-IDF (ipsum) = " + tfidf);
     }*/
}
