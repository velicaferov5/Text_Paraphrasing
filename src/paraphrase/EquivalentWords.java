/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paraphrase;

import edu.smu.tspell.wordnet.WordNetDatabase;
import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.AdverbSynset;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.VerbSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JOptionPane;
import sun.security.util.Length;



/**
 *
 * @author Vali
 */

public class EquivalentWords extends MainClass{
    WordNetDatabase database = WordNetDatabase.getFileInstance();
    NounSynset nounSynset;
    AdjectiveSynset adjectiveSynset;
    AdverbSynset adverbSynset;
    NounSynset[] hyponyms;
    Synset[] synsets;

    String[] wordforms;
    String[] ssynonymstemp;
    int wsintervaltemp,wfintervaltemp,objwsinterval,objwfinterval;


    public EquivalentWords(){

    }



public void GeneralHandling(MainClass objtemp){
        ssynonymstemp =new String[objtemp.swords.length];
        objwsinterval=objtemp.wsinterval;
        objwfinterval=objtemp.wfinterval;
        for(int indexparam=0;indexparam<objtemp.swords.length;indexparam++)
        {
            if(objtemp.swords[indexparam].startsWith("didn't")||objtemp.swords[indexparam].endsWith("didn't")){
                ssynonymstemp[indexparam]="did not";
                continue;
            }
            
            if(objtemp.swords[indexparam].startsWith("doesn't")){
                ssynonymstemp[indexparam]="does not";
                continue;
            }
            
            if(objtemp.swords[indexparam].startsWith("aren't")){
                ssynonymstemp[indexparam]="are not";
                continue;
            }

            if(objtemp.stags[indexparam].equals("DT")){
                    ssynonymstemp[indexparam]=objtemp.swords[indexparam];
                    continue;
            }

            if(objtemp.stags[indexparam].equals("MD")){
                    ssynonymstemp[indexparam]=objtemp.swords[indexparam];
                    continue;
            }


             if(objtemp.stags[indexparam].contains("PRP")){
                 ssynonymstemp[indexparam]=objtemp.swords[indexparam];
                 continue;
            }

            if(objtemp.stags[indexparam].equals("SYM")){
                    ssynonymstemp[indexparam]=objtemp.swords[indexparam];
                    continue;
            }


            if(objtemp.stags[indexparam].equals("JJ")){
                    ssynonymstemp[indexparam]=AdjectiveEquivalent(objtemp.swords[indexparam]);
                    continue;
            }

            if(objtemp.stags[indexparam].equals("JJR")){
                    ssynonymstemp[indexparam]=AdjectiveEquivalent(objtemp.swords[indexparam]);
                    continue;
            }

            if(objtemp.stags[indexparam].equals("JJS")){
                ssynonymstemp[indexparam]=AdjectiveEquivalent(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("NN")){
                    ssynonymstemp[indexparam]=NounSingular(objtemp.swords[indexparam]);
                    continue;
            }

            if(objtemp.stags[indexparam].equals("NNP")){
                ssynonymstemp[indexparam]=NounSingular(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("NNS")){
                   ssynonymstemp[indexparam]=NounPlural(objtemp.swords[indexparam]);
                   ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                   continue;
            }

            if(objtemp.stags[indexparam].equals("NNPS")){
                ssynonymstemp[indexparam]=NounPlural(objtemp.swords[indexparam]);
                ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("RB")){
                ssynonymstemp[indexparam]=AdverbEquivalent(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("RBR")){
                ssynonymstemp[indexparam]=AdverbEquivalent(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("RBS")){
                ssynonymstemp[indexparam]=AdverbEquivalent(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("VB")){
                ssynonymstemp[indexparam]=VerbForm(objtemp.swords[indexparam]);
                continue;
            }
            
            if(objtemp.stags[indexparam].equals("VBP")){
                ssynonymstemp[indexparam]=VerbForm(objtemp.swords[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("VBG")){
                ssynonymstemp[indexparam]=VerbForm(objtemp.swords[indexparam]);
                ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("VBD")){
                ssynonymstemp[indexparam]=VerbPast(objtemp.swords[indexparam]);
                ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("VBN")){
                ssynonymstemp[indexparam]=VerbPast(objtemp.swords[indexparam]);
                ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                continue;
            }

            if(objtemp.stags[indexparam].equals("VBZ")){
                ssynonymstemp[indexparam]=VerbThirdForm(objtemp.swords[indexparam]);
                ssynonymstemp[indexparam]=EquivalentCorrect(ssynonymstemp[indexparam],objtemp.stags[indexparam]);
                continue;
            }

            else{
            ssynonymstemp[indexparam]=GeneralSynonyms(objtemp.swords[indexparam]);
            }
        }

         objtemp.ssynonyms=ssynonymstemp;
    }

    public String GeneralSynonyms(String swordparam){
        try{
            Synset[] synsets = database.getSynsets(swordparam);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp=(int)synsets.length/2+1;

            for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        return wordforms[index2].toString();
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"general"+t.toString());

            t.printStackTrace();
        }
        return swordparam;
    }


    public String AdjectiveEquivalent(String swordparam){
        try{
            Synset[] synsets = database.getSynsets(swordparam,SynsetType.ADJECTIVE);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int) synsets.length /2+1;

            for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        return wordforms[index2];
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"adj"+t.toString());

            t.printStackTrace();
        }
        return swordparam;
    }

        public String NounSingular(String swordparam){
            try{
            Synset[] synsets = database.getSynsets(swordparam,SynsetType.NOUN);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        return wordforms[index2].toString();
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"noun "+t.toString());

            t.printStackTrace();
        }
            return swordparam;
    }


        public String NounPlural(String swordparam){
            String synonymtemp=new String("");
            synonymtemp=swordparam;
            try{
            Synset[] synsets = database.getSynsets(swordparam,SynsetType.NOUN);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            firstloop: for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        synonymtemp=wordforms[index2].toString();
                        break firstloop;
                    }
                    index2++;
                }
            }
            
        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"n plural "+t.toString());

            t.printStackTrace();
        }
            String synonymtemp2=synonymtemp;
            try{
            Lexicon lexicon = Lexicon.getDefaultLexicon();
            NLGFactory nlgFactory = new NLGFactory(lexicon);
            Realiser realiser = new Realiser(lexicon);
            NPPhraseSpec nnoun=nlgFactory.createNounPhrase(synonymtemp);
            if(nnoun.getFeature(Feature.NUMBER).toString().equals("SINGULAR"))
            {
            nnoun.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
            synonymtemp= realiser.realiseSentence(nnoun);
            }
            }catch(Exception e){
                return synonymtemp2;
            }
            if(synonymtemp.isEmpty())
                return synonymtemp2;

            if(synonymtemp.substring(synonymtemp.length()-4,synonymtemp.length()-1).equals("ses"))
                return synonymtemp.substring(0,synonymtemp.length()-3);

            return synonymtemp;
        }

        public String AdverbEquivalent(String swordparam){
             try{
            Synset[] synsets = database.getSynsets(swordparam,SynsetType.ADVERB);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        return wordforms[index2].toString();
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"adv"+t.toString());

            t.printStackTrace();
        }
              return swordparam;

    }



          public String VerbPast(String swordparam){
              String synonymtemp=swordparam;
              try{

                  Synset[] synsets = database.getSynsets(swordparam,SynsetType.VERB);

            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            firstloop: for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        synonymtemp= wordforms[index2].toString();

                        if(index1==0&&index2==0)
                            return swordparam;

                        break firstloop;
                    }
                    index2++;
                }
            }


        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"v"+t.toString());

            t.printStackTrace();
        }

              if(!synonymtemp.equals(swordparam)){
                  Lexicon lexicon = Lexicon.getDefaultLexicon();
                  NLGFactory nlgFactory = new NLGFactory(lexicon);
                  Realiser realiser = new Realiser(lexicon);
                  VPPhraseSpec vverb=nlgFactory.createVerbPhrase(synonymtemp);
                  vverb.setFeature(Feature.TENSE, Tense.PAST);
                  synonymtemp=realiser.realiseSentence(vverb);
              }
              return synonymtemp;
          }

          public String VerbThirdForm(String swordparam){
              String synonymtemp=swordparam;
              try{

                  Synset[] synsets = database.getSynsets(swordparam,SynsetType.VERB);
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            firstloop: for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        synonymtemp= wordforms[index2].toString();
                        break firstloop;
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"v"+t.toString());

            t.printStackTrace();
        }
              Lexicon lexicon = Lexicon.getDefaultLexicon();
              NLGFactory nlgFactory = new NLGFactory(lexicon);
              Realiser realiser = new Realiser(lexicon);
              VPPhraseSpec vverb=nlgFactory.createVerbPhrase(synonymtemp);
              vverb.setFeature(Feature.TENSE, Tense.PRESENT);
              synonymtemp=realiser.realiseSentence(vverb);
              return synonymtemp;
    }

          public String VerbForm(String swordparam){
              try{
                  Synset[] synsets = database.getSynsets(swordparam,SynsetType.VERB);
          
            wsintervaltemp=synsets.length;
            if(wsintervaltemp>objwsinterval)
            wsintervaltemp= (int)synsets.length/2+1;

            for (int index1 = 0; index1 < wsintervaltemp; index1++) {
                wordforms  = synsets[index1].getWordForms();
                wfintervaltemp=wordforms.length;
                if(wfintervaltemp>objwfinterval)
                    wfintervaltemp= (int)wordforms.length/2+1;
                int index2=0;
                while(index2<wfintervaltemp){
                    if(!swordparam.equals(wordforms[index2].toString())){
                        return wordforms[index2].toString();
                    }
                    index2++;
                }
            }

        }catch(Throwable t){
            JOptionPane.showMessageDialog(tFrame,"v"+t.toString());

            t.printStackTrace();
        }

        return swordparam;
    }




          public String EquivalentCorrect(String synonymtemp,String tagtemp){
              synonymtemp=synonymtemp.replaceFirst(synonymtemp.substring(0, 1), synonymtemp.substring(0,1).toLowerCase());
              if(synonymtemp.contains("."))
              synonymtemp=synonymtemp.substring(0,synonymtemp.indexOf("."));

              if(tagtemp.equals("VBG")){
                  if(synonymtemp.length()>2){
                  if(synonymtemp.substring(synonymtemp.length()-2, synonymtemp.length()-1).equals("e"))
                      synonymtemp=synonymtemp.substring(0,synonymtemp.length()-1)+"ing";
                  
                  else synonymtemp=synonymtemp+"ing";
                  }
              }
              return synonymtemp;
          }
}