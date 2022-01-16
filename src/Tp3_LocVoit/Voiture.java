package Tp3_LocVoit;

public class Voiture {
    private String Marque,Model;
    private int prixJ,anneeProduction;

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
    public int getAnneeProduction() {
        return this.anneeProduction;
    }
}
