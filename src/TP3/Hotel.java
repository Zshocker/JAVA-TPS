package TP3;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Hotel {
    private Chambre[] TabC; 
    public static Chambre Create_chambre()
    {   
        int num=-1,cate=-1,capacity=-1;
        double Prix=0;
        Scanner Scan=new Scanner(System.in);
        System.out.print("\nDonner le numero de chambre: ");
        do{
            try {
                num =Scan.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(num==-1);

        System.out.print("\nDonner la capacite de chambre: ");
        do{
            try {
                capacity =Scan.nextInt();
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
                Prix =Scan.nextDouble();
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
                cate =Scan.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(cate==-1);
        Scan.close();
        return new Chambre(num,cate,capacity,Prix);
    }
    public Hotel(int numbrC){
        TabC=new Chambre[numbrC];
        System.out.println("\nRemplire le Tableau: ");
        for (int i = 0; i < TabC.length; i++) {
			System.out.println("\n--------------------------------------------------------\n");
            TabC[i]=Create_chambre();
        }
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
        Arrays.sort(TabC);
    }
    public void WriteINDoc()
    {
        File F=new File("chambres.dat");
        DataOutputStream out=null;
        try {
            out=new DataOutputStream(new FileOutputStream(F));
            for (Chambre chambre : TabC) {
                chambre.Write_in_buffer(out);
            }
        } catch (FileNotFoundException e) 
        {
        } catch (IOException e) {
        }finally{
            try {
            if(out!=null)out.close();
            } catch (IOException e) {}
        }
    }
}
