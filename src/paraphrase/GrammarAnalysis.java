/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paraphrase;

/**
 *
 * @author Vali
 */

import opennlp.tools.coref.mention.Parse;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;



public class GrammarAnalysis extends MainClass{
    POSTaggerME tagger;
    String[] stagstemp,stemp;
    double[] dprobs,dprobstemp;
    int leftinterval,rightinterval,wordposition,textlength,searchintervalparam;


    public void GeneralAnalysis(MainClass objtemp){
        searchintervalparam=objtemp.searchinterval;
        objtemp.stags=new String[objtemp.swords.length];
       for(int gindex1=0;gindex1<objtemp.swords.length;gindex1++)
            {
                leftinterval=searchintervalparam;
                rightinterval=searchintervalparam;
                try{
                    IntervalAllocate(objtemp.swords,gindex1);
                    objtemp.stags[gindex1]=WordTag();
                   }catch(Exception e){
                    JOptionPane.showMessageDialog(tFrame,"3: "+e.toString());
                    e.printStackTrace();
                }
            }

    }

    public void TextTag(MainClass objtemp) throws Exception {
    InputStream modelInput = null;
    try {
        modelInput = new FileInputStream("Resources\\apache-opennlp-1.5.2-incubating\\bin\\en-pos-maxent.bin");
            POSModel pmodel = new POSModel(modelInput);
            tagger = new POSTaggerME(pmodel);
            objtemp.stags = tagger.tag(objtemp.swords);
            objtemp.dprobs= tagger.probs();

        }catch (IOException e) {
            // If model loading fails, error is handled through catch of exception
            JOptionPane.showMessageDialog(tFrame,"2: "+e.toString());
            e.printStackTrace();
        }
        finally {
            if (modelInput != null) {
                try {
                    modelInput.close();
                }
                catch (IOException e) {
                }
            }
        }
    }

    public void IntervalAllocate(String[] swordsparam, Integer indexparam){
        if(indexparam<searchintervalparam){
            leftinterval=indexparam;
            /*if the word that will be searched is one of the 1st n words in
             *searchinterval of text, then the left interval is equalized to the beginning of text
             */

            rightinterval=rightinterval+searchintervalparam-leftinterval;
            /*And the less number of words in left interval
             * with words from right
             */

            }
        if(rightinterval>swordsparam.length-1-indexparam){
            rightinterval=swordsparam.length-1-indexparam;
            /*if the interval between the being searched and end of text is
             * less than searchinterval words, then the leftinterval is reduced to
             * the end of the last word's index
             */
            for(int index1=0;index1<searchintervalparam-rightinterval;index1++)
            {
                if(leftinterval<indexparam-1){
                    leftinterval++;
                }
            }
        }

        int index2=0;
        //JOptionPane.showMessageDialog(Frame,"leftint:"+leftinterval+"   rightint:"+rightinterval+"\tindparam"+indexparam);

        stemp=new String[rightinterval+leftinterval+1];
        for(int index1=indexparam-leftinterval;index1<=indexparam+rightinterval;index1++)
        {
            stemp[index2]=swordsparam[index1];
            //JOptionPane.showMessageDialog(Frame,"stemp["+index2+": "+stemp[index2]);

            if(index1==indexparam)
                wordposition=index2;
            //JOptionPane.showMessageDialog(Frame,"ind2:"+index2+"\tind1:"+index1+"wordpos: "+wordposition);

            index2++;
        }




        /*customize the project with defining the context
         * to be searched. Define correctness
         * also speciality, e.g. IT texts and etc.
         */

    }

    public String WordTag() throws Exception {
    InputStream modelInput = null;
    
    try {
            modelInput = new FileInputStream("Resources\\apache-opennlp-1.5.2-incubating\\bin\\en-pos-maxent.bin");
            POSModel pmodel = new POSModel(modelInput);
            tagger = new POSTaggerME(pmodel);
            stagstemp = tagger.tag(stemp);
            dprobstemp= tagger.probs();
            
        }catch (IOException e) {
            // If model loading fails, error is handled through catch of exception
            JOptionPane.showMessageDialog(tFrame,"1: "+e.toString());
            e.printStackTrace();
        }
        finally {
            if (modelInput != null) {
                try {
                    modelInput.close();
                }
                catch (IOException e) {
                }
            }
        }
        return stagstemp[wordposition];
    }




    public String SpecialTag(String specialtemp) throws Exception {
    InputStream modelInput = null;

    String[] specialtemp1=new String[2];
    String[] specialtemp2=new String[2];
    specialtemp2[0]=specialtemp;
    specialtemp2[1]=specialtemp;


    try {
            modelInput = new FileInputStream("Resources\\apache-opennlp-1.5.2-incubating\\bin\\en-pos-maxent.bin");
            POSModel pmodel = new POSModel(modelInput);
            tagger = new POSTaggerME(pmodel);
            specialtemp1 = tagger.tag(specialtemp2);

        }catch (IOException e) {
            // If model loading fails, error is handled through catch of exception
            JOptionPane.showMessageDialog(tFrame,"1: "+e.toString());
            e.printStackTrace();
        }
        finally {
            if (modelInput != null) {
                try {
                    modelInput.close();
                }
                catch (IOException e) {
                }
            }
        }
        return specialtemp1[1];
    }
}
