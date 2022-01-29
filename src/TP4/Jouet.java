package TP4;

public class Jouet {
    
    private int num;
    private boolean dejaVerifie=false;
    public Jouet(int num) {
        this.num = num;
    }
    public synchronized void tuEsVerifiePar(VerificateurJouet data) {
        if(dejaVerifie)return; 
        data.verifieJouet(num);
        dejaVerifie=true;
    }   
}
