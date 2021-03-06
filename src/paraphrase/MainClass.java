/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainClass.java
 *
 * Created on 30-Aug-2012, 13:55:14
 */

package paraphrase;

/**
 *
 * @author Vali
 */

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import javax.print.DocFlavor.STRING;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import javax.swing.JOptionPane;
//OpenNLG

public class MainClass extends javax.swing.JFrame {

    /** Creates new form MainClass */
    public MainClass() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 400, 400));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setBackground(new java.awt.Color(204, 255, 255));
        jButton1.setText("Paraphrase");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setText("Please enter your text here:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addContainerGap(514, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    JFrame tFrame= new JFrame("showMessageDialog Test");
    String[] swords,ssymbols,ssynonyms,stags;
    Integer[] wordindex;
    double[] dprobs;
    String sword,verbform;
    int wsinterval,wfinterval,searchinterval;

    MainClass objmain;
    WordHandling objwh;
    EquivalentWords objeq;
    GrammarAnalysis objga;
    //Object declaration

    public MainClass(String stemp){
        this.sword=stemp;
    }




    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:

        objmain=new MainClass(jTextArea1.getText());
        try {
            paraphrase();
        }catch(Exception e){

        }


    }//GEN-LAST:event_jButton1MouseClicked

    private void paraphrase()throws Exception {
        objwh=new WordHandling();
        objga=new GrammarAnalysis();
        objeq=new EquivalentWords();
        try{
            objmain.wsinterval=4;
            objmain.wfinterval=4;
            objmain.searchinterval=4;
            objwh.Tokenize(objmain);
            objga.GeneralAnalysis(objmain);
            objeq.GeneralHandling(objmain);

        }catch(Exception e){
            JOptionPane.showMessageDialog(tFrame,"error occured 1"+e.toString());
     }

        OrthographicSupport();
        PrintText();
        

    }

    public void PrintText(){

        try {
            for(int index=0;index<objmain.swords.length;index++)
            {
                jTextArea2.append(objmain.ssymbols[index]+objmain.ssynonyms[index]);

            }
            jTextArea2.append(objmain.ssymbols[objmain.ssymbols.length-1]);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(tFrame,"Error in the printing words of sentence");
            //In case of exception, it is displayed in Message Dialog
        }

    }

    public void OrthographicSupport(){
        try {
             for(int index=0;index<objmain.swords.length;index++)
             {
                 if(Character.isUpperCase(objmain.swords[index].charAt(0))){
                    objmain.ssynonyms[index]=objmain.ssynonyms[index].replaceFirst(objmain.ssynonyms[index].substring(0, 1), objmain.ssynonyms[index].substring(0,1).toUpperCase());

                    if(!objmain.ssymbols[index].startsWith(".")&&!objmain.ssymbols[index].startsWith("?"))
                        objmain.ssynonyms[index]=objmain.swords[index];

                 }

                 if((objmain.swords[index].length()==2)||objmain.swords[index].equals("are"))
                        objmain.ssynonyms[index]=objmain.swords[index];

                 if(objmain.stags[index].equals("NNS"))
                    {
                     if(index>1)
                     if(objmain.stags[index-1].startsWith("JJ")&&objmain.stags[index-2].startsWith("JJ"))
                         objmain.ssymbols[index-1]=" and ";
                         
                     if(index%3==0&&index<objmain.swords.length-2){
                         if(objmain.ssymbols[index].startsWith(".")||objmain.ssymbols[index].startsWith("?")){
                            objmain.ssynonyms[index]=objmain.ssynonyms[index].replaceFirst(objmain.ssynonyms[index].substring(0, 1), objmain.ssynonyms[index].substring(0,1).toLowerCase());
                            objmain.ssynonyms[index]="These "+objmain.ssynonyms[index];
                        }

                        else {
                             if(index>2){
                             if(!objmain.stags[index-1].startsWith("JJ"))
                                objmain.ssynonyms[index-1]="those "+objmain.ssynonyms[index-1];

                             if(objmain.stags[index-1].startsWith("JJ"))
                                 if(!objmain.stags[index-2].startsWith("DT"))
                                    objmain.ssynonyms[index-1]="those "+objmain.ssynonyms[index-1];

                            }
                         }
                        }
                    }

                 if(objmain.stags[index].equals("NN"))
                 {
                     if(objmain.ssymbols[index].startsWith(".")||objmain.ssymbols[index].startsWith("?")||objmain.ssymbols[index].endsWith(".")){
                         objmain.ssynonyms[index]=objmain.ssynonyms[index].replaceFirst(objmain.ssynonyms[index].substring(0, 1), objmain.ssynonyms[index].substring(0,1).toLowerCase());

                         if(index<objmain.swords.length-2)
                         objmain.ssynonyms[index]="The "+objmain.ssynonyms[index];
                     }

                     else{
                         if(index>2)
                             if(objmain.stags[index-1].startsWith("JJ")&&objmain.stags[index-2].startsWith("JJ"))
                                objmain.ssymbols[index-1]=" and ";

                         if(index%4==0&&index>3){
                             if(objmain.stags[index-1].startsWith("JJ")){
                                objmain.ssynonyms[index-1]="the "+objmain.ssynonyms[index-1];
                             }
                             
                             else{
                                 if(!objmain.stags[index-1].startsWith("DT")&&!objmain.stags[index-1].startsWith("V")&&!objmain.stags[index-3].startsWith("DT")&&!objmain.ssynonyms[index].startsWith("didn't"))
                                 objmain.ssynonyms[index]="the "+objmain.ssynonyms[index];
                                 }
                         }
                     }
                 }
             }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(tFrame,"Error in the words of sentence");
            //In case of exception, it is displayed in Message Dialog
        }
    }
    /**
    * @param args the command line arguments
    */

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

}
