package Tp2;

import java.util.Arrays;

public class TriSimple {
    private int Tab[],nbElem=0,increment=5;
    public TriSimple(){
        Tab=new int[10];
        Arrays.fill(Tab, 0);
    }
    public TriSimple(int cap,int incr)
    {
        if(incr<=0)incr=1;
        if(cap<=0)cap=1;
        increment=incr;
        Tab=new int[cap];
        Arrays.fill(Tab, 0);
    }
    public void inserer(int elem)
    {
        Check_incre();
        Tab[nbElem]=elem;
        nbElem++;
        Arrays.sort(Tab,0,nbElem);
    }
    public void supprimer(int elem)
    {
        if(nbElem >0)
        {
            for (int i = 0; i < Tab.length; i++) {
                if(Tab[i]==elem){
                    Remove_ind(i);
                    break;
                }
            }
            
            nbElem--;
            check_Decr();
        }
    }
    private void Check_incre(){
        if(Tab.length<=nbElem+2)
        {
            Tab=Arrays.copyOf(Tab,Tab.length+increment);
            
        }
    }
    
    private void check_Decr() 
    {
        int Esp=Tab.length-nbElem;
        if(Esp>=increment*2){
            Tab=Arrays.copyOf(Tab,Tab.length-increment);
        }
    }
    private void Remove_ind(int ind)
    {
        for (int i = ind; i < nbElem-1; i++) {
            Tab[i]=Tab[i+1];
        }
    }
    public String toString()
    {
        StringBuffer sb=new StringBuffer();
        sb.append("\ncapacite:"+Tab.length+"\n");
        sb.append("Elements: ");
        for (int i = 0; i < nbElem; i++) 
        {
            if(i==nbElem-1)sb.append(Tab[i]+".");
            else sb.append(Tab[i]+" ,");
        }
        return sb.toString();
    }   
}
