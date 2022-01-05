package Tp2;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EssaiTri {
    public static void main(String[] args) {
        Scanner SN=new Scanner(System.in);
        String buffer; 
        TriSimple Tableau; 
        System.out.println("Donner la taille et le nombre incriment (10 , 5 par defaut):");
        buffer = SN.nextLine();
        if(buffer.equals(""))
        {
            Tableau=new TriSimple();
        }
        else
        {
            int incr;
            StringTokenizer Tok=new StringTokenizer(buffer, " ");
            int cap=Integer.parseInt(Tok.nextToken());
            if(Tok.hasMoreElements())
            {
                incr=Integer.parseInt(Tok.nextToken());
            }else{
                incr=0;
            }
            Tableau=new TriSimple(cap,incr);
        }
        System.out.println("la lettre c pour afficher le tableau \nla lettre a suivie de données à insérer\nla lettre s suivie de données à supprimer\nla lettre q pour quitter");
        do 
        {
            buffer = SN.next();
            if(buffer.equals("q"))break;
            if(buffer.equals("c")){
                System.out.println(Tableau);
            }else if(buffer.equals("a")||buffer.equals("s"))
            {
                int elem =SN.nextInt();
                if(buffer.equals("a"))Tableau.inserer(elem);
                else Tableau.supprimer(elem);
            }
        } while (true); 
        SN.close();
    }
}
