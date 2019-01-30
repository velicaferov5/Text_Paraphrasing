/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paraphrase;

/**
 *
 * @author Vali
 */

import javax.swing.*;


public class WordHandling extends MainClass{
    public WordHandling(){

    }


    public void Tokenize(MainClass objtemp) throws Exception  {
        String separatorsymbols = "[ .,?!()]+";
        objtemp.swords=new String[objtemp.sword.split(separatorsymbols).length];
        objtemp.swords=objtemp.sword.split(separatorsymbols);
        objtemp.wordindex=new Integer[objtemp.swords.length*2];
        objtemp.ssymbols = new String[objtemp.swords.length+1];
        objtemp.wordindex[0]=objtemp.sword.indexOf(objtemp.swords[0]);
        objtemp.ssymbols[0]=objtemp.sword.substring(0, objtemp.wordindex[0]);
        try {
            for(int index=1;index<objtemp.wordindex.length;index++)
            {
                if(index%2==1)
                {
                    if(index==1)
                    {
                        objtemp.wordindex[index]=objtemp.sword.indexOf(objtemp.swords[(index-1)/2],objtemp.wordindex[index-1]) + objtemp.swords[(index-1)/2].length();
                    }
                    else
                    {
                        objtemp.wordindex[index]=objtemp.sword.indexOf(objtemp.swords[(index-1)/2],objtemp.wordindex[index-1]) + objtemp.swords[(index-1)/2].length();
                        objtemp.ssymbols[(index-1)/2]=objtemp.sword.substring(objtemp.wordindex[index-2], objtemp.wordindex[index-1]);
                        if(index==objtemp.wordindex.length-1)
                        {
                            objtemp.ssymbols[(index+1)/2]=objtemp.sword.substring(objtemp.wordindex[index],objtemp.sword.length());
                        }
                    }
                }
                else
                {
                    objtemp.wordindex[index]=objtemp.sword.indexOf(objtemp.swords[index/2],objtemp.wordindex[index-1]);
                }
            }
        } catch (ArithmeticException e) {
           JOptionPane.showMessageDialog(tFrame,"Error in the number of words in sentence");
            e.printStackTrace();
        }
    }
}
