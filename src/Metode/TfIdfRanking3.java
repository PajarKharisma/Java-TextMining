package Metode;

import java.util.LinkedList;

import File.DocSorting;
import File.DocTfIdf;
import java.util.ArrayList;

public class TfIdfRanking3 extends TFIDFCalculator {

    private LinkedList<DocSorting> listAll = new LinkedList<>();
    private String numeric[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private LinkedList<String> listName = new LinkedList<>();
    private ArrayList<String> listKelas = new ArrayList<>();
    private ArrayList<String> listFinalKelas = new ArrayList<>();

    public void getResult() {
        deleteDir();
        getWord();
        for (int i = 0; i < doc.length; i++) {
            //System.out.println("Nama Dokument : " + doc[i].getName());
            listName.add(doc[i].getName());
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
                //System.out.println("Word : " + doc[i].getList().get(j) + "    ||     value : " + tfidf);
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
                    if (docSorting[k].getValue() > docSorting[index].getValue()) { //sorting
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

            /*
            for(DocSorting ds:list)
                System.out.println("Term : "+ds.getTerm() + "|| Value : "+ds.getValue());
            */
            
            String outputText = "";
            //System.out.println("Size : "+list.size());
            String outTfIdf = "";
            for(DocSorting ds : list){
                outTfIdf += ds.getTerm() + " || " + ds.getValue() + "\n";
            }
            //System.out.println("\n\n==================================================\n\n");

            for(DocSorting k:list){
                listKelas.add(k.getTerm());
                outputText += k.getTerm() + " ";
            }
            
            writeTxt.writeFile(outTfAndIdf, "log\\TF-IDF\\", doc[i].getName());
            writeTxt.writeFile(outputText, "output\\TfIdfOutput\\", doc[i].getName());
            writeTxt.writeFile(outTfIdf, "log\\TFIDF\\", doc[i].getName());
        }
        
        LinkedList<String> filteredName = new LinkedList<>();
        LinkedList<String> finalName = new LinkedList<>();

        for (String name : listName) {
            name = name.replaceAll(".txt", "");
            name = name.replaceAll(" ", "");
            for (String j : numeric) {
                name = name.replaceAll(j, "");
            }
            filteredName.add(name);
        }

        for (int i = 0; i < filteredName.size(); i++) {
            if (i < filteredName.size() - 1) {
                if (!filteredName.get(i).equalsIgnoreCase(filteredName.get(i + 1))) {
                    finalName.add(filteredName.get(i));
                }
            } else {
                finalName.add(filteredName.get(i));
            }
        }

        String nameText = "";
        for (String fn : finalName) {
            nameText += fn + " ";
        }
        
        for(String i:listKelas){
            int result = 0;
            for(String j:listFinalKelas){
                if(i.equalsIgnoreCase(j))
                    result++;
            }
            if(result < 1)
                listFinalKelas.add(i);
        }
        
        String outputText = "";
        for(String i:listFinalKelas)
            outputText += i + " ";
        
        writeTxt.writeFile(outputText, "output\\C45\\", "C45Output.txt");
        writeTxt.writeFile(nameText, "output\\C45\\", "DocumentName.txt");
        listName.clear();
    }
}
