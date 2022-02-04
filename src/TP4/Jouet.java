package TP4;

public class Jouet {
    
    private int num;
    private boolean dejaVerifie=false;

    public Jouet(int num) {
        this.num = num;
    }
    public synchronized void tuEsVerifiePar(VerificateurJouet data) {
        if(dejaVerifie && data.getNiveau()==1)return; 
        if(data.getNiveau()>1){
            while (!dejaVerifie) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        data.verifieJouet(num);
        dejaVerifie=true;
        notify();
    }   
}
