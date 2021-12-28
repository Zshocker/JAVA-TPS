package Tp2;

import java.util.StringTokenizer;

public class Tronq {
    public static String tronque(double x,int nbElem)
    {
        String str,prefix,suffix;
        StringTokenizer strs=new StringTokenizer(Double.toString(x),".");
        prefix=strs.nextToken();
        if(!strs.hasMoreTokens()||nbElem==0){
            return prefix;
        }
        str=prefix;
        suffix=strs.nextToken();
        if(suffix.length()>nbElem)suffix=suffix.substring(0,nbElem);
        str+="."+suffix;
        return str;
    }
    public static void main(String[] args) {
        if(args.length%2==0){
            int i=0;
            while(args.length>i)
            {
                try {
                    double X=Double.parseDouble(args[i]);
                    int nbEl=Integer.parseInt(args[i+1]);
                    System.out.print(tronque(X,nbEl)+" ");
                } 
                catch (NumberFormatException e) 
                {
                    System.out.println("erreur "+e.getMessage()+" n'est pas un nombre");
                }
                i=i+2;
            }
            return; 
        }else if(args.length==0){
            System.out.println("Donner des argument");
        }
        System.out.println("Erreur un argument monque");
    }
}
