package Tp3_LocVoit;

public class CriterePrix implements Critere{
    int Prix;
    public CriterePrix(int Prix) {
        this.Prix = Prix;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getPrixJ()<Prix;
    }
    @Override
    public boolean equals(Object obj) 
    {
        try {
            CriterePrix ps=(CriterePrix)obj;
            return ps.Prix==Prix;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
