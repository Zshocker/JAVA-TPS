package Tp3_LocVoit;

import java.util.Scanner;

public class Voiture {
    private String Marque,Model;
    private int prixJ,anneeProduction;
    public static Voiture create_Voiture(Scanner sn)
    {
        Voiture nb=new Voiture();
        System.out.print("\nMarque= ");
        nb.Marque=sn.nextLine();
        System.out.print("Model= ");
        nb.Model=sn.nextLine();
        System.out.print("prix de jour= ");
        nb.prixJ=0;
        do{
            try {
                nb.prixJ =sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(nb.prixJ<=0);
        System.out.print("annee de Production= ");
        nb.anneeProduction=0;
        do{
            try {
                nb.anneeProduction =sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }  
        }while(nb.anneeProduction<=0);
        return nb;
    } 
    public Voiture() {
    }
    public Voiture(String Marque, String Model, int prixJ, int anneeProduction) {
        this.Marque = Marque;
        this.Model = Model;
        this.prixJ = prixJ;
        this.anneeProduction = anneeProduction;
    }
    @Override
    public String toString() {
        return "{" +
            " Marque='" + Marque + "'" +
            ", Model='" + Model + "'" +
            ", prix de location à la journée ='" + prixJ + "'" +
            ", année de production ='" + anneeProduction + "'" +
            "}";
    }
     

      @Override
    public boolean equals(Object obj) {
        Voiture vs=(Voiture)obj;
        return Marque.equals(vs.Marque) && Model.equals(vs.Model) && prixJ==vs.prixJ && anneeProduction==vs.anneeProduction;
    }
    public String getMarque() {
        return this.Marque;
    }
    public String getModel() {
        return this.Model;
    }
    public int getPrixJ() {
        return this.prixJ;
    }
    public int getAnneeProduction() 
    {
        return this.anneeProduction;
    }
}
