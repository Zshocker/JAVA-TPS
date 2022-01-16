package Tp3_LocVoit;

import java.util.ArrayList;
import java.util.List;

public class InterCritere implements Critere {
    List<Critere> lesCriteres = new ArrayList<Critere>();
    public InterCritere() {
    }
    
    void addCritere(Critere c){
        if(lesCriteres.indexOf(c)==-1){
            lesCriteres.add(c);
        }
    } 
    void addCritere(Critere... ct){
        for (Critere c : ct) 
        {
            if(lesCriteres.indexOf(c)==-1)
            {
                lesCriteres.add(c);
            }
        }
    } 
    @Override
    public boolean estSatisfaitPar(Voiture v) {
          for (Critere critere : lesCriteres) {
               if(!critere.estSatisfaitPar(v))
               {
                   return false;
               }
          }
        return true;
    }
    
}
