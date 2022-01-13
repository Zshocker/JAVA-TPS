package TP3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Hotel {
    public static Scanner Sn=new Scanner(System.in);
    List<Chambre> TabC=new ArrayList<Chambre>(); 
    private String fichier=null;
    public static Chambre Create_chambre()
    {   
        int num=-1,cate=-1,capacity=-1;
        double Prix=0;
        System.out.print("\nDonner le numero de chambre: ");
        do{
            try {
                num =Sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(num==-1);

        System.out.print("\nDonner la capacite de chambre: ");
        do{
            try {
                capacity =Sn.nextInt();
                if(capacity<1||capacity>4){
                    System.out.println("la capacité doit etre entre 1 et 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(capacity<1||capacity>4);
        System.out.print("\nDonner le Prix de chambre: ");      
        do{
            try {
                Prix =Sn.nextDouble();
                if(Prix<=0){
                    System.out.println("le prix qui doit être supérieur à 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(Prix<=0);

        System.out.print("\nDonner la category de chambre: ");
        do{
            try {
                cate =Sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(cate==-1);
        return new Chambre(num,cate,capacity,Prix);
    }
    public Hotel(int numbrC){
        Remplire_Tableau(numbrC);
    }
    public void Remplire_Tableau(int numbreC){
        System.out.println("\nRemplire le Tableau: ");
        for (int i = 0; i < numbreC; i++) {
			System.out.println("\n--------------------------------------------------------\n");
            TabC.add(Create_chambre());
        }
    }
    public Hotel(String nomFichier)
    {
        Read_file(nomFichier);
    }
    public void Afficher_Cat(int cat) 
    {
        for (Chambre C : TabC) { 
            if(C.TestCat(cat)){
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println(C);
            }
        }
    }
    public void Trier()
    {
        Collections.sort(TabC);
    }
    public void WriteINDoc()
    {
        File F=new File(fichier);
        DataOutputStream out=null;
        try {
            out=new DataOutputStream(new FileOutputStream(F));
            out.writeInt(TabC.size());
            for (Chambre chambre : TabC) {
                chambre.Write_in_buffer(out);
            }
        } catch (FileNotFoundException e) 
        {
        } catch (IOException e) {
        }finally
        {
            try {
            if(out!=null)out.close();
            } catch (IOException e) 
            {
              System.out.println("probleme d'ocloture le fichier");
            }
        }
    }
    public void Read_file(String nomFich)
    {
        if(fichier!=null)WriteINDoc();
        fichier=nomFich;
        File F=new File(fichier);
        DataInputStream out=null;
        try {

            out=new DataInputStream(new FileInputStream(F));
            int NombreE=out.readInt();
            TabC.clear();
            for (int j = 0; j < NombreE; j++) { 
                int num=out.readInt();
                int cate=out.readInt();
                int capacity=out.readInt();
                double Prix=out.readDouble();
                char etat=out.readChar();
                TabC.add(new Chambre(num,cate,capacity,Prix,etat));     
            }
        }
        catch (FileNotFoundException e) 
        {
        } catch (IOException e) {
        }finally
        {
            try {
            if(out!=null)out.close();
            } catch (IOException e) 
            {
              System.out.println("probleme d'ocloture le fichier");
            }
        }
    }
    public void Delete_Room(int E)
    {
        for (int i = 0; i < TabC.size(); i++) {
            if(TabC.get(i).Test_num(E)){
                TabC.remove(i);
                break;
            }
        }
        WriteINDoc();
    }
    public void Ajouter_Room()
    {
        System.out.println("\n--------------------------------------------------------\n");
        TabC.add(Create_chambre());
        WriteINDoc();
    }
    public static void main(String[] args) {
        Hotel E=new Hotel("chambres.dat");
        //E.Ajouter_Room();
        E.Trier();
        E.Delete_Room(1);
        E.Read_file("chambres.dat");
        E.Afficher_Cat(1);
    } 
}
