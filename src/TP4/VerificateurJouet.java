package TP4;
import java.lang.Thread;
import java.util.Random;
public class VerificateurJouet implements Runnable {
    public Thread thread=new Thread(this);
    private String nom;
    public VerificateurJouet(String nom) {
        this.nom = nom;
    }
    public void verifieJouet(int i)
    {
        System.out.println(nom+" est entrain de verifier le jouet "+i);
        try {
            Thread.sleep(new Random().nextInt(1000));
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
