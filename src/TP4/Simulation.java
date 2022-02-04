package TP4;

public class Simulation {
    public static Jouet LesJo[]=new Jouet[10];

    public static void init_Jo() {
       for (int i = 0; i < LesJo.length; i++) {
            LesJo[i]=new Jouet(i);           
       }
    }
    public static void main(String[] args) {
        init_Jo();
        VerificateurJouet Ahmed=new VerificateurJouet("Ahmed");
        Ahmed.thread.start();
        VerificateurJouet Amine =new VerificateurJouet("Amine");
        Amine.thread.start();
        VerificateurJouet Bachir =new VerificateurJouet("Bachir",2,250);
        Bachir.thread.start();
    }
}
