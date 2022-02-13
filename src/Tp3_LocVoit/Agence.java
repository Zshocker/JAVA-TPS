package Tp3_LocVoit;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Agence implements Serializable {

    List<Voiture> voitures = new ArrayList<Voiture>();
    List<Client> Clints = new ArrayList<Client>();
    Map<Client, Voiture> Location = new TreeMap<Client, Voiture>();
    private static final long serialVersionUID = 1L;

    public static Agence readAgence(String fichier) {
        ObjectInputStream ne = null;
        FileInputStream fileInputStream = null;
        Agence B = null;
        try {
            fileInputStream = new FileInputStream(fichier);
            ne = new ObjectInputStream(fileInputStream);
            B = (Agence) ne.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                    ne.close();
                }
            } catch (IOException e) {
            }
        }
        if (B == null) {
            System.out.println("aucune instance est trouver dans le fichier");
            B = new Agence();
        }
        return B;
    }

    public Agence() {
    }

    public boolean estLoueur(Client client) {
        return Location.containsKey(client);
    }

    public boolean estLoue(Voiture v) {
        Collection<Voiture> ga = Location.values();
        return ga.contains(v);
    }

    public void WriteAgence(String ficher) {
        ObjectOutputStream OS = null;
        try {
            OS = new ObjectOutputStream(new FileOutputStream(ficher));
            OS.writeObject(this);
            OS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                OS.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean exist(Voiture v) {
        if (voitures.indexOf(v) != -1) {
            return true;
        }
        return false;
    }

    public void ajouter_client(Client c) {
        if (Clints.contains(c))
            return;
        Clints.add(c);
    }

    public Iterator<Client> tous_client() {
        return Clints.iterator();
    }

    public Client find_byCIN(String se) throws Clientnotfound {
        Client c = new Client(se);
        for (Client client : Clints) {
            if (client.equals(c))
                return client;
        }
        throw new Clientnotfound();
    }

    public void loueVoiture(Client client, Voiture v) throws VoitureDejaAlouer, ClientDejaLoueur, VoitureNexistePas {
        if (!exist(v))
            throw new VoitureNexistePas();
        if (estLoueur(client))
            throw new ClientDejaLoueur();
        if (estLoue(v))
            throw new VoitureDejaAlouer();
        Location.put(client, v);
    }

    public void loueVoiture_ParInd(Client client, int indiceVoi) {
        try {
            loueVoiture(client, voitures.get(indiceVoi));
        } catch (VoitureDejaAlouer | ClientDejaLoueur | VoitureNexistePas e) {
            System.out.println(e);
        }
    }

    public void rendVoiture(Client client) throws ClientNestPasLoeur {
        if (estLoueur(client)) {
            Location.remove(client);
        } else
            throw new ClientNestPasLoeur();
    }
    public void rendVoiture(Voiture v) throws VoitureNexistePas, ClientNestPasLoeur {
        if (estLoue(v)) 
        {
            Set<Entry<Client,Voiture>> as= Location.entrySet();
            for (Entry<Client,Voiture> ent : as) {
                if(ent.getValue()==v){
                    rendVoiture(ent.getKey());
                    return;
                }
            }
        } else
            throw new VoitureNexistePas();
    }

    public Agence(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    public void addVoiture(Voiture... v) {
        for (Voiture voiture : v) {
            if (voitures.indexOf(voiture) == -1) {
                voitures.add(voiture);
            }
        }
    }

    public Iterator<Client> lesLoueur() {
        Set<Client> Cn = Location.keySet();
        return Cn.iterator();
    }

    public Iterator<Voiture> selectionne(Critere c) {
        List<Voiture> selected = new ArrayList<Voiture>();
        for (Voiture voiture : voitures) {
            if (c.estSatisfaitPar(voiture)) {
                selected.add(voiture);
            }
        }
        return selected.iterator();
    }
    public List<Voiture> selectionne_Vec(Critere c) {
        List<Voiture> selected = new ArrayList<Voiture>();
        for (Voiture voiture : voitures) {
            if (c==null||c.estSatisfaitPar(voiture)) {
                selected.add(voiture);
            }
        }
        return selected;
    }

    public void afficheSelection(Critere c) {
        Iterator<Voiture> ite = selectionne(c);
        while (ite.hasNext()) {
            System.out.println(ite.next());
        }
    }

    public Iterator<Voiture> tousLesVoitures() {
        return voitures.iterator();
    }

    public Iterator<Voiture> lesVoituresLouees() {
        Collection<Voiture> ga = Location.values();
        return ga.iterator();
    }

    class VoitureDejaAlouer extends Exception {
        @Override
        public String toString() {
            return "\nLa voiture est déjà louée !!\n";
        }
    }

    class ClientDejaLoueur extends Exception {
        @Override
        public String toString() {
            return "\nLe client est déjà un Loueur !!\n";
        }
    }

    class VoitureNexistePas extends Exception {
        @Override
        public String toString() {
            return "\nLa voiture n'existe pas dans l'agence \n";
        }
    }

    class ClientNestPasLoeur extends Exception {
        @Override
        public String toString() {
            return "\nLe client n'est pas un Loueur \n";
        }

    }

    class Clientnotfound extends Exception {
        @Override
        public String toString() {
            return "\nLe client n'est n'existe pas \n";
        }
    }

    public static void main(String[] args) {
        Agence ag = readAgence("AgenceF");
        Scanner snScanner = new Scanner(System.in);
        System.out.println("0-Ajouter un Client");
        System.out.println("1-Ajouter une voiture");
        System.out.println("2-Louer une voiture a un client");
        System.out.println("3-rend une voiture");
        System.out.println("4-afficher la liste des voitures");
        System.out.println("5-afficher la liste des voitures louer");
        System.out.println("6-afficher la liste des loueurs");
        System.out.println("7-afficher la liste des voitures par des critere");
        System.out.println("8-afficher la liste des Clients");
        System.out.println("other-exit");
        int choix;
        String Cin;
        Client se = null;
        Iterator it;
        do {
            choix = 5000;
            System.out.println("donner votre choix :");
            try {
                choix = Integer.parseInt(snScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
            switch (choix) {
                case 5000:
                    break;
                case 0:
                    ag.ajouter_client(Client.creer_client(snScanner));
                    break;
                case 1:
                    ag.addVoiture(Voiture.create_Voiture(snScanner));
                    break;
                case 2:
                    choix = -1;
                    System.out.println("1-Ajouter nouveau  Client");
                    System.out.println("other-Client deja existe");
                    do {
                        try {
                            se = null;
                            choix = Integer.parseInt(snScanner.nextLine());
                            switch (choix) {
                                case 1:
                                    se = Client.creer_client(snScanner);
                                    ag.ajouter_client(se);
                                    break;
                                default:
                                    do {
                                        System.out.println("le CIN du Client :");
                                        Cin = snScanner.nextLine();
                                        try {
                                            se = ag.find_byCIN(Cin);
                                        } catch (Exception e) {
                                            System.out.println(e.toString());
                                        }
                                    } while (se == null);
                                    break;
                            }
                            choix = 1;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }

                    } while (choix == -1);

                    it = ag.tousLesVoitures();
                    int i = 0;
                    while (it.hasNext()) {
                        System.out.print("{" + i + "}");
                        System.out.println(it.next());
                        i++;
                    }
                    choix = -1;
                    do {
                        try {
                            choix = snScanner.nextInt();
                            if (choix < 0 || choix > i) {
                                System.out.println("l'indice doit etre entre 0 et " + i);
                            } else
                                break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    ag.loueVoiture_ParInd(se, choix);
                    break;
                case 3:
                    System.out.println("le CIN du loueur :");
                    Cin = snScanner.nextLine();
                    se = new Client(Cin);
                    try {
                        ag.rendVoiture(se);
                    } catch (ClientNestPasLoeur e) {
                        System.out.println(e);
                    }
                    break;
                case 4:
                    it = ag.tousLesVoitures();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 5:
                    it = ag.lesVoituresLouees();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 6:
                    it = ag.lesLoueur();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                case 7:
                    InterCritere critere = new InterCritere();
                    System.out.println("Donner le critere de Marque (0 pour aucune critere):");
                    String s = snScanner.nextLine();
                    if (!s.equals("0")) {
                        critere.addCritere(new CritereMarque(s));
                    }
                    System.out.println("Donner le critere d'annes de prodcution (0 pour un critere vide):");
                    do {
                        try {
                            choix = snScanner.nextInt();
                            if (choix != 0) {
                                critere.addCritere(new CritereAnne(choix));
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    System.out.println("Donner le critere du prix (0 pour aucune critere)");
                    do {
                        try {
                            choix = snScanner.nextInt();
                            if (choix != 0) {
                                critere.addCritere(new CriterePrix(choix));
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    ag.afficheSelection(critere);
                    break;
                case 8:
                    it = ag.tous_client();
                    while (it.hasNext()) {
                        System.out.println(it.next());
                    }
                    break;
                default:
                    ag.WriteAgence("AgenceF");
                    snScanner.close();
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}