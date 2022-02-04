package TP4;
import java.lang.Thread;
import java.util.Random;
public class VerificateurJouet implements Runnable {

    public Thread thread=new Thread(this);
    private String nom;
    private int Niveau=1;
    private int Vittese=1000;
    public VerificateurJouet(String nom) {
        this.nom = nom;
    }
    public VerificateurJouet(String nom, int Niveau, int Vittese) {
        this.nom = nom;
        this.Niveau = Niveau;
        this.Vittese = Vittese;
    }
    public int getNiveau() {
        return this.Niveau;
    }
    
    public void verifieJouet(int i)
    {
        System.out.println(nom+" est entrain de verifier le jouet "+i);
        try {
            Thread.sleep(new Random().nextInt(Vittese));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nom+" a fini de vérifier le jouet '"+i+"'");
    }
    @Override
    public void run() 
    {
        for (int i = 0; i <  Simulation.LesJo.length; i++) {
            Simulation.LesJo[i].tuEsVerifiePar(this);
        }
    }
    
}
