package Metode;

import java.util.LinkedList;
import File.Doc;
import File.DocSorting;
import File.DocTfIdf;
import PreProcess.Tokenizing;
import SubProcess.OpenFile;
import java.util.ArrayList;

public class TfIdfRanking2 extends TFIDFCalculator {

    private int limit = 20;
    private LinkedList<String> listName = new LinkedList<>();
    private String numeric[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private LinkedList<Check> listChecks = new LinkedList<>();
    private LinkedList<String> listNameCheck = new LinkedList<>();
    private ArrayList<String> listKelas = new ArrayList<>();
    private ArrayList<String> listFinalKelas = new ArrayList<>();

    public void getResult() {
        deleteDir();
        getWord();
        for (int i = 0; i < doc.length; i++) {
            String docName = doc[i].getName();
            docName = docName.replaceAll(".txt", "");
            docName = docName.replaceAll(" ", "");
            for (String j : numeric) {
                docName = docName.replaceAll(j, "");
            }

            DocSorting[] docSorting = new DocSorting[doc[i].getList().size()];
            for (int j = 0; j < docSorting.length; j++) {
                docSorting[j] = new DocSorting();
            }
            
            ArrayList<DocTfIdf> listTfIdf = new ArrayList<>();
            for (int j = 0; j < doc[i].getList().size(); j++) {
                DocTfIdf docTfIdf = new DocTfIdf();
                String term = doc[i].getList().get(j);
                double tf = tf2(doc[i].getList(), term);
                double idf = idf(listDocs, doc[i].getList().get(j));
                
                docTfIdf.setData(term, tf, idf);
                listTfIdf.add(docTfIdf);
                
                double tfidf = tfIdf(doc[i].getList(), listDocs, doc[i].getList().get(j));
                docSorting[j].setTerm(doc[i].getList().get(j));
                docSorting[j].setValue(tfidf);
            }
            
            //Proses TFIDF
            DocTfIdf[] arrayTfIdf = new DocTfIdf[listTfIdf.size()];
            for(int j=0; j<listTfIdf.size(); j++){
                arrayTfIdf[j] = new DocTfIdf();
                arrayTfIdf[j] = listTfIdf.get(j);
            }
            
            for (int x = 0; x < arrayTfIdf.length; x++) {
                for (int y = x + 1; y < arrayTfIdf.length; y++) {
                    if (arrayTfIdf[x].getTerm().compareTo(arrayTfIdf[y].getTerm()) > 0) {
                        DocTfIdf tmpDoc = new DocTfIdf();
                        tmpDoc = arrayTfIdf[x];
                        arrayTfIdf[x] = arrayTfIdf[y];
                        arrayTfIdf[y] = tmpDoc;
                    }
                }
            }
            
            LinkedList<DocTfIdf> listFinalTfIdf = new LinkedList<>();

            for (int j = 0; j < arrayTfIdf.length - 1; j++) {
                if (!arrayTfIdf[j].getTerm().equalsIgnoreCase(arrayTfIdf[j + 1].getTerm())) {
                    listFinalTfIdf.add(arrayTfIdf[j]);
                }
            }
            
            String outTfAndIdf = "";
            for(DocTfIdf dt:listFinalTfIdf){
                outTfAndIdf += "Term : "+dt.getTerm() +" || tf : "+dt.getTf() + " || idf : "+dt.getIdf() + "\n";
            }
            writeTxt.writeFile(outTfAndIdf, "log\\TF-IDF\\", doc[i].getName());
            //Akhir Proses TFIDF

            for (int x = 0; x < docSorting.length; x++) {
                for (int y = x + 1; y < docSorting.length; y++) {
                    if (docSorting[x].getTerm().compareTo(docSorting[y].getTerm()) > 0) {
                        DocSorting tmpDoc = new DocSorting();
                        tmpDoc = docSorting[x];
                        docSorting[x] = docSorting[y];
                        docSorting[y] = tmpDoc;
                    }
                }
            }

            for (int j = 0; j < docSorting.length - 1; j++) {
                int index = j;
                for (int k = j + 1; k < docSorting.length; k++) {
                    if (docSorting[k].getValue() < docSorting[index].getValue()) { //sorting
                        index = k;
                    }
                }

                DocSorting tmpDoc = new DocSorting();
                tmpDoc = docSorting[index];
                docSorting[index] = docSorting[j];
                docSorting[j] = tmpDoc;
            }

            LinkedList<DocSorting> list = new LinkedList<DocSorting>();

            for (DocSorting ds : docSorting) {
                list.add(ds);
            }

            Check check = new Check();
            check.setData(list, docName);
            listChecks.add(check);
        }

        ArrayList<String> namaNama = new ArrayList<>();
        for (int i = 0; i < listChecks.size(); i++) {
            if (i < listChecks.size() - 1) {
                if (!listChecks.get(i).getDocName().equalsIgnoreCase(listChecks.get(i + 1).getDocName())) {
                    namaNama.add(listChecks.get(i).getDocName());
                }
            } else {
                namaNama.add(listChecks.get(i).getDocName());
            }
        }

        LinkedList<DocSorting>[] listFinalTerm = new LinkedList[namaNama.size()];
        for (int i = 0; i < namaNama.size(); i++) {
            listFinalTerm[i] = new LinkedList();
            for (int j = 0; j < listChecks.size(); j++) {
                if (listChecks.get(j).getDocName().equalsIgnoreCase(namaNama.get(i))) {
                    listFinalTerm[i].addAll(listChecks.get(j).getTerms());
                }
            }
        }
        
        for (int i = 0; i < listFinalTerm.length; i++) {
            DocSorting[] docSorting = new DocSorting[listFinalTerm[i].size()];
            for (int j = 0; j < docSorting.length; j++) {
                docSorting[j] = new DocSorting();
                docSorting[j] = listFinalTerm[i].get(j);
            }

            for (int x = 0; x < docSorting.length; x++) {
                for (int y = x + 1; y < docSorting.length; y++) {
                    if (docSorting[x].getTerm().compareTo(docSorting[y].getTerm()) > 0) {
                        DocSorting tmpDoc = new DocSorting();
                        tmpDoc = docSorting[x];
                        docSorting[x] = docSorting[y];
                        docSorting[y] = tmpDoc;
                    }
                }
            }

            for (int j = 0; j < docSorting.length - 1; j++) {
                int index = j;
                for (int k = j + 1; k < docSorting.length; k++) {
                    if (docSorting[k].getValue() < docSorting[index].getValue()) { //sorting
                        index = k;
                    }
                }

                DocSorting tmpDoc = new DocSorting();
                tmpDoc = docSorting[index];
                docSorting[index] = docSorting[j];
                docSorting[j] = tmpDoc;
            }

            LinkedList<DocSorting> list = new LinkedList<DocSorting>();

            for (int j = 0; j < docSorting.length - 1; j++) {
                if (!docSorting[j].getTerm().equalsIgnoreCase(docSorting[j + 1].getTerm())) {
                    list.add(docSorting[j]);
                }
            }
            
            String outTfIdf = "";
            for (DocSorting ds : list) {
                outTfIdf += ds.getTerm() + " || " + ds.getValue() + "\n";
            }
            //System.out.println("\n\n==================================================\n\n");

            String outputText = "";
            if (list.size() > limit - 1) {
                for (int k = 0; k < limit; k++) {
                    listKelas.add(list.get(k).getTerm());
                    outputText += list.get(k).getTerm();
                }
            }
            
            writeTxt.writeFile(outputText, "output\\TfIdfOutput\\", doc[i].getName());
            writeTxt.writeFile(outTfIdf, "log\\TFIDF\\", doc[i].getName());
        }

        String nameText = "";
        for (String fn : namaNama) {
            nameText += fn + " ";
        }

        for (String i : listKelas) {
            int result = 0;
            for (String j : listFinalKelas) {
                if (i.equalsIgnoreCase(j)) {
                    result++;
                }
            }
            if (result < 1) {
                listFinalKelas.add(i);
            }
        }

        String outputText = "";
        for (String i : listFinalKelas) {
            outputText += i + " ";
        }

        writeTxt.writeFile(outputText, "output\\C45\\", "C45Output.txt");
        writeTxt.writeFile(nameText, "output\\C45\\", "DocumentName.txt");
        listName.clear();
    }
}

class Check {

    private LinkedList<DocSorting> terms;
    private String docName;

    public void setData(LinkedList<DocSorting> terms, String docName) {
        this.terms = terms;
        this.docName = docName;
    }

    public LinkedList<DocSorting> getTerms() {
        return this.terms;
    }

    public String getDocName() {
        return this.docName;
    }
}
