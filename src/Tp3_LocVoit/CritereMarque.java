package Tp3_LocVoit;

public class CritereMarque implements Critere{
    String marque;
    public CritereMarque(String marque) {
        this.marque = marque;
    }
    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getMarque().equals(marque);
    }
    @Override
    public boolean equals(Object obj) {
        try {
            CritereMarque vs=(CritereMarque)obj;
            return vs.marque.equals(marque);
        } catch (ClassCastException e)
        {
            return false;
        }
    }
}
