package File;

public class DocTfIdf {
    private String term;
    private double tf;
    private double idf;
    
    public void setData(String term, double tf, double idf){
        this.term = term;
        this.tf = tf;
        this.idf = idf;
    }
    
    public String getTerm(){
        return this.term;
    }
    
    public double getTf(){
        return this.tf;
    }
    
    public double getIdf(){
        return this.idf;
    }
}
