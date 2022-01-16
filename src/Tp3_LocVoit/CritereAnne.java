package Tp3_LocVoit;

public class CritereAnne implements Critere {
    int anne;
    public CritereAnne(int ann) {
        anne=ann;
    }
    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getAnneeProduction()==anne;
    }
    @Override
    public boolean equals(Object obj) {
       try
       {
            CritereAnne vs=(CritereAnne)obj;
            return vs.anne==this.anne;
       }
       catch(ClassCastException e){
            return false;
       }
    }
}
