package Tp3_LocVoit;

import java.io.Serializable;
import java.util.Scanner;

public class Client implements Comparable<Client>, Serializable {
    private static final long serialVersionUID = 3L;
    private String nom = null, prenom = null, CIN = null, civilite = null;

    public Client(String nom, String prenom, String CIN, String civilite) {
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.civilite = civilite;
    }

    public static Client creer_client(Scanner sn) {
        Client cl = new Client();
        System.out.print(" \nle nom :");
        cl.nom = sn.nextLine();
        System.out.print(" \nle prenom:");
        cl.prenom = sn.nextLine();
        System.out.print(" \nCIN:");
        cl.CIN = sn.nextLine();
        System.out.print(" \ncivilite:");
        cl.civilite = sn.nextLine();
        return cl;
    }

    public Client() {
    }

    @Override
    public boolean equals(Object obj) {
        Client ob = (Client) obj;
        return ob.CIN.equals(CIN);
    }

    @Override
    public String toString() {
        return "{" +
                " nom='" + nom + "'" +
                ", prenom='" + prenom + "'" +
                ", CIN='" + CIN + "'" +
                ", civilite='" + civilite + "'" +
                "}";
    }

    public Client(String CIN) {
        this.CIN = CIN;
    }

    @Override
    public int compareTo(Client c) {
        if (equals(c))
            return 0;
        int a = nom.compareTo(c.nom);
        if (a != 0) {
            return a;
        }
        return prenom.compareTo(c.prenom);
    }

}
