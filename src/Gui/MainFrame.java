package Gui;

import PreProcess.CaseFolding;
import PreProcess.Stemming;
import PreProcess.Tokenizing;
import File.*;
import Metode.*;
import PreProcess.*;
import SubProcess.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class MainFrame extends javax.swing.JFrame {
    
    //Variable Training
    private static final int TRAINING = 0;
    private static final int TESTING = 1;

    private int mode;

    private final JFileChooser jfChooser = new JFileChooser("input");

    private String input = "";
    private String caseWord;
    private File[] file;
    private LinkedList<String> listToken;
    private LinkedList<String> listStopWord;
    private LinkedList<String> listStemming;

    private final CaseFolding caseFolding = new CaseFolding();
    private final Tokenizing tokenizing = new Tokenizing();
    private final StopWord stopWord = new StopWord();
    private final Stemming stemming = new Stemming();
    private final WriteTxt writeTxt = new WriteTxt();
    private final ArffMaker arffMaker = new ArffMaker();

    private final TfIdfRanking1 tfIdfRanking = new TfIdfRanking1();

    private final OpenFile openFile = new OpenFile();

    //Variable Testing
    private ConverterUtils.DataSource source1, source2;
    private Instances train, test;
    private StringBuffer[] predsBuffer;
    private PlainText[] plainText;
    private Evaluation evalKnn, evalSvm;

    private NaiveBayes naiveBayes;
    private J48 j48;
    private IBk ibk;
    private SMO smo;

    private String dirInput = "";
    private DefaultTableModel mdl;
    private TFIDFCalculator calculator;
    private DocSorting ds;
    int rowCount = 0;

    public MainFrame() {
        initComponents();
        arffMaker.mainFrame = this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtInput = new javax.swing.JTextField();
        btnInput = new javax.swing.JButton();
        btnTraining = new javax.swing.JButton();
        radioTraining = new javax.swing.JRadioButton();
        radioTesting = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaOutputKnn = new javax.swing.JTextArea();
        btnTesting = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaOutputSvm = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaDataCaseFolding = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        areaDataTokenizing = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        areaDataStopword = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        areaDataStemming = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        areaDataTfidf = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        areaDataTfAndIdf = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInput.setText("Input");
        btnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputActionPerformed(evt);
            }
        });

        btnTraining.setText("Proses");
        btnTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrainingActionPerformed(evt);
            }
        });

        radioTraining.setText("Training");
        radioTraining.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTrainingActionPerformed(evt);
            }
        });

        radioTesting.setText("Testing");
        radioTesting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTestingActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("PANEL TRAINING");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(radioTraining)
                                .addGap(17, 17, 17)
                                .addComponent(radioTesting)
                                .addGap(0, 415, Short.MAX_VALUE))
                            .addComponent(txtInput))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTraining, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioTraining)
                    .addComponent(radioTesting)
                    .addComponent(btnTraining))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Training", jPanel1);

        areaOutputKnn.setColumns(20);
        areaOutputKnn.setRows(5);
        jScrollPane1.setViewportView(areaOutputKnn);

        btnTesting.setText("Testing");
        btnTesting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestingActionPerformed(evt);
            }
        });

        areaOutputSvm.setColumns(20);
        areaOutputSvm.setRows(5);
        jScrollPane2.setViewportView(areaOutputSvm);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("KNN");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("SVM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(btnTesting, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(191, 191, 191))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTesting)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Testing", jPanel2);

        areaDataCaseFolding.setColumns(20);
        areaDataCaseFolding.setRows(5);
        jScrollPane3.setViewportView(areaDataCaseFolding);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CASE FOLDING");

        areaDataTokenizing.setColumns(20);
        areaDataTokenizing.setRows(5);
        jScrollPane4.setViewportView(areaDataTokenizing);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("TOKENIZING");

        areaDataStopword.setColumns(20);
        areaDataStopword.setRows(5);
        jScrollPane5.setViewportView(areaDataStopword);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("STOP WORD");

        areaDataStemming.setColumns(20);
        areaDataStemming.setRows(5);
        jScrollPane6.setViewportView(areaDataStemming);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("STEMMING");

        areaDataTfidf.setColumns(20);
        areaDataTfidf.setRows(5);
        jScrollPane7.setViewportView(areaDataTfidf);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("TFIDF");

        areaDataTfAndIdf.setColumns(20);
        areaDataTfAndIdf.setRows(5);
        jScrollPane8.setViewportView(areaDataTfAndIdf);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TF-IDF");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addGap(173, 173, 173)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(102, 102, 102))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel6)
                .addGap(217, 217, 217)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(118, 118, 118))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Data", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void deleteLog(){
        File logCaseFolding = new File("log\\CaseFolding");
        for (File i : logCaseFolding.listFiles()) {
            i.delete();
        }
        
        File logCaseStemming = new File("log\\Stemming");
        for (File i : logCaseStemming.listFiles()) {
            i.delete();
        }
        
        File logCaseStopWord = new File("log\\StopWord");
        for (File i : logCaseStopWord.listFiles()) {
            i.delete();
        }
        
        File logTfidf = new File("log\\TFIDF");
        for (File i : logTfidf.listFiles()) {
            i.delete();
        }
        
        File logTokenizing = new File("log\\tokenizing");
        for (File i : logTokenizing.listFiles()) {
            i.delete();
        }
        
        File logTfAndIdf = new File("log\\TF-IDF");
        for (File i : logTfAndIdf.listFiles()) {
            i.delete();
        }
    }

    private void proses() throws IOException {
        File dir = new File("output\\tmpOutput");
        for (File i : dir.listFiles()) {
            i.delete();
        }

        //System.out.println("jumlah artikel : " + file.length);
        for (int i = 0; i < file.length; i++) {
            input = "";
            
            //System.out.println("Nama File : " + file[i].getName() + " : ");
            input = openFile.getFile2(dirInput + file[i].getName());
            /*
            System.out.println("Nama Dokumen : "+file[i].getName());
            System.out.println("===================================");
            System.out.println(input);
            System.out.println("\n");
            */

            caseWord = caseFolding.getCaseFoldingResult(input, 1);
            //if (mode == TRAINING) {
                writeTxt.writeFile(caseWord, "log\\CaseFolding\\", file[i].getName().toLowerCase());
            //}

            listToken = tokenizing.getTokenizingResult(caseWord);
            //if (mode == TRAINING) {
                writeTxt.writeFile(listToken, "log\\Tokenizing\\", file[i].getName().toLowerCase());
            //}

            listStopWord = stopWord.getStopWordResult(listToken);
            //if (mode == TRAINING) {
                writeTxt.writeFile(listStopWord, "log\\StopWord\\", file[i].getName().toLowerCase());
            //}

            listStemming = stemming.getStemmerResult(listStopWord);
            //if (mode == TRAINING) {
                writeTxt.writeFile(listStemming, "log\\Stemming\\", file[i].getName().toLowerCase());
            //}

            //System.out.println("NAMA FILE : " + file[i].getName());
            //System.out.println("listToken : " + listToken.size());

            String outputText = "";
            for (String ls : listStemming) {
                outputText += ls + " \n";
            }
            writeTxt.writeFile(outputText, "output\\tmpOutput\\", file[i].getName().toLowerCase());
        }
        caseWord = "";
        
        if(mode == TRAINING){
            tfIdfRanking.getResult();
        }
        arffMaker.createArff(mode);
    }
    
    private void showExample(int opsi){
        areaDataCaseFolding.setText("");
        areaDataStemming.setText("");
        areaDataStopword.setText("");
        areaDataTfidf.setText("");
        areaDataTokenizing.setText("");
        areaDataTfAndIdf.setText("");
        
        File[] logCaseFolding = new File("log\\CaseFolding").listFiles();
        File[] logStemming = new File("log\\Stemming").listFiles();
        File[] logStopWord = new File("log\\StopWord").listFiles();
        File[] logTokenizing = new File("log\\Tokenizing").listFiles();
        File[] logTfIdf = new File("log\\TFIDF").listFiles();
        File[] logTfAndIdf = new File("log\\TF-IDF").listFiles();
        if(opsi == 0){
            areaDataCaseFolding.setText(openFile.getFile(logCaseFolding[0])+"\n\n========================\n\n"+openFile.getFile(logCaseFolding[1]));
            areaDataStemming.setText(openFile.getFile(logStemming[0])+"\n\n========================\n\n"+openFile.getFile(logStemming[1]));
            areaDataStopword.setText(openFile.getFile(logStopWord[0])+"\n\n========================\n\n"+openFile.getFile(logStopWord[1]));
            areaDataTokenizing.setText(openFile.getFile(logTokenizing[0])+"\n\n========================\n\n"+openFile.getFile(logStopWord[1]));
            areaDataTfidf.setText(openFile.getFile(logTfIdf[0])+"\n\n========================\n\n"+openFile.getFile(logTfIdf[1]));
            areaDataTfAndIdf.setText(openFile.getFile(logTfAndIdf[0])+"\n\n========================\n\n"+openFile.getFile(logTfAndIdf[1]));
        }else{
            areaDataCaseFolding.setText(openFile.getFile(logCaseFolding[0]));
            areaDataStemming.setText(openFile.getFile(logStemming[0]));
            areaDataStopword.setText(openFile.getFile(logStopWord[0]));
            areaDataTokenizing.setText(openFile.getFile(logTokenizing[0]));
        }
    }

    private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputActionPerformed
        jfChooser.setMultiSelectionEnabled(true);
        int choose = jfChooser.showOpenDialog(this);
        if (choose == JFileChooser.APPROVE_OPTION) {
            txtInput.setText(jfChooser.getCurrentDirectory().toString() + "\\");
            file = jfChooser.getSelectedFiles();
        }
        dirInput = txtInput.getText();
    }//GEN-LAST:event_btnInputActionPerformed

    private void radioTestingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTestingActionPerformed
        radioTraining.setSelected(false);
        mode = TESTING;
    }//GEN-LAST:event_radioTestingActionPerformed

    private void radioTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTrainingActionPerformed
        radioTesting.setSelected(false);
        mode = TRAINING;
    }//GEN-LAST:event_radioTrainingActionPerformed

    private void btnTrainingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrainingActionPerformed
        if ((!radioTesting.isSelected() && !radioTraining.isSelected()) || txtInput.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Anda belum memilih mode atau file !");
        } else {
            try {
                deleteLog();
                proses();
                if(mode == TRAINING){
                    showExample(0);
                }
                JOptionPane.showMessageDialog(null, "Proses Selesai");
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnTrainingActionPerformed

    private void btnTestingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestingActionPerformed
        try {
            showExample(1);
            
            source1 = new ConverterUtils.DataSource("output\\training.arff");
            train = source1.getDataSet();
            train.setClassIndex(train.numAttributes()-1);

            source2 = new ConverterUtils.DataSource("output\\testing.arff");
            test = source2.getDataSet();
            test.setClassIndex(test.numAttributes()-1);

            predsBuffer = new StringBuffer[2];
            plainText = new PlainText[2];
            for(int i=0; i<2; i++){
                predsBuffer[i] = new StringBuffer();
                plainText[i] = new PlainText();
                plainText[i].setHeader(train);
                plainText[i].setBuffer(predsBuffer[i]);
            }

            ibk = new IBk();
            ibk.buildClassifier(train);

            smo = new SMO();
            smo.buildClassifier(train);
            
            evalKnn = new Evaluation(train);
            evalKnn.evaluateModel(ibk, test, plainText[0]);
            areaOutputKnn.setText(predsBuffer[0].toString());
            
            evalSvm = new Evaluation(train);
            evalSvm.evaluateModel(smo, test, plainText[1]);
            areaOutputSvm.setText(predsBuffer[1].toString());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Proses Selesai");
    }//GEN-LAST:event_btnTestingActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaDataCaseFolding;
    private javax.swing.JTextArea areaDataStemming;
    private javax.swing.JTextArea areaDataStopword;
    private javax.swing.JTextArea areaDataTfAndIdf;
    private javax.swing.JTextArea areaDataTfidf;
    private javax.swing.JTextArea areaDataTokenizing;
    private javax.swing.JTextArea areaOutputKnn;
    private javax.swing.JTextArea areaOutputSvm;
    private javax.swing.JButton btnInput;
    private javax.swing.JButton btnTesting;
    private javax.swing.JButton btnTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton radioTesting;
    private javax.swing.JRadioButton radioTraining;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}