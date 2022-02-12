package TP3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {
    public static Scanner Sn = new Scanner(System.in);
    List<Chambre> TabC = new ArrayList<Chambre>();
    private String fichier = null;

    public static Chambre Create_chambre() {
        int num = -1, cate = -1, capacity = -1;
        double Prix = 0;
        System.out.print("\nDonner le numero de chambre: ");
        do {
            try {
                num = Sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
        } while (num == -1);

        System.out.print("\nDonner la capacite de chambre: ");
        do {
            try {
                capacity = Sn.nextInt();
                if (capacity < 1 || capacity > 4) {
                    System.out.println("la capacité doit etre entre 1 et 4");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
        } while (capacity < 1 || capacity > 4);
        System.out.print("\nDonner le Prix de chambre: ");
        do {
            try {
                Prix = Sn.nextDouble();
                if (Prix <= 0) {
                    System.out.println("le prix qui doit être supérieur à 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
        } while (Prix <= 0);

        System.out.print("\nDonner la category de chambre: ");
        do {
            try {
                cate = Sn.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
        } while (cate == -1);
        return new Chambre(num, cate, capacity, Prix);
    }

    public Hotel(int numbrC) {
        Remplire_Tableau(numbrC);
    }

    public void Remplire_Tableau(int numbreC) {
        System.out.println("\nRemplire le Tableau: ");
        for (int i = 0; i < numbreC; i++) {
            this.Ajouter_Room();
        }
    }

    public Hotel(String nomFichier) {
        Read_file(nomFichier);
    }

    public void Afficher_Cat(int cat) {
        for (Chambre C : TabC) {
            if (C.TestCat(cat)) {
                System.out.println("\n--------------------------------------------------------\n");
                System.out.println(C);
            }
        }
    }

    public void Afficher_tout() {
        for (Chambre C : TabC) {
            System.out.println("\n--------------------------------------------------------\n");
            System.out.println(C);
        }
    }

    public void Trier() {
        Collections.sort(TabC);
    }

    public void WriteINDoc() {
        File F = new File(fichier);
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(F));
            out.writeInt(TabC.size());
            for (Chambre chambre : TabC) {
                chambre.Write_in_buffer(out);
            }
            out.flush();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("probleme d'ocloture le fichier");
            }
        }
    }

    public void Read_file(String nomFich) {
        if (fichier != null)
            WriteINDoc();
        fichier = nomFich;
        File F = new File(fichier);
        DataInputStream out = null;
        try {
            out = new DataInputStream(new FileInputStream(F));
            int NombreE = out.readInt();
            TabC.clear();
            for (int j = 0; j < NombreE; j++) {
                int num = out.readInt();
                int cate = out.readInt();
                int capacity = out.readInt();
                double Prix = out.readDouble();
                char etat = out.readChar();
                TabC.add(new Chambre(num, cate, capacity, Prix, etat));
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                System.out.println("probleme d'ocloture le fichier");
            }
        }
    }

    public void Delete_Room(int E) {
        for (int i = 0; i < TabC.size(); i++) {
            if (TabC.get(i).Test_num(E)) {
                TabC.remove(i);
                break;
            }
        }
        WriteINDoc();
    }

    private void Ajouter_Room() {
        System.out.println("\n--------------------------------------------------------\n");
        do {
            Chambre Be = Create_chambre();
            if (TabC.indexOf(Be) == -1) {
                TabC.add(Be);
                break;
            } else
                System.out.println("le numero du chambre deja existe !");
        } while (true);
    }

    public void Ajouter_Chambre() {
        Ajouter_Room();
        WriteINDoc();
    }

    public void modification_chambre(int chambre, char etat) {
        for (Chambre chambre2 : TabC) {
            if (chambre2.Test_num(chambre)) {
                if (etat == 'L')
                    chambre2.set_libre();
                else
                    chambre2.set_ocuper();
                return;
            }
        }
    }

    public void To_another_file(String FileName, int cat) {
        WriteINDoc();
        ArrayList<Chambre> tab = new ArrayList<Chambre>();
        for (Chambre C : TabC) {
            if (C.TestCat(cat)) {
                tab.add(C);
            }
        }
        this.TabC = tab;
        this.fichier = FileName;
        this.WriteINDoc();
        this.Read_file("chambres.dat");
    }

    public ArrayList<Chambre> chambres_Libre() {
        ArrayList<Chambre> tab = new ArrayList<Chambre>();
        for (Chambre C : TabC) {
            if (C.is_libre()) {
                tab.add(C);
            }
        }
        return tab;
    }

    public double Recette_max() {
        double somme = 0;
        for (Chambre ch : TabC) {
            somme += ch.Get_prix();
        }
        return somme;
    }

    public double Rectte_Jour() {
        double s = 0;
        for (Chambre chambre : TabC) {
            if (!chambre.is_libre()) {
                s += chambre.Get_prix();
            }
        }
        return s;
    }

    public static void afficher_Liste(ArrayList<Chambre> chambres) {
        for (Chambre C : chambres) {
            System.out.println("\n--------------------------------------------------------\n");
            System.out.println(C);
        }
    }

    public static void main(String[] args) {
        Hotel E = new Hotel("chambres.dat");
        System.out.println("0-Afficher tous les chambres");
        System.out.println("1-Afficher les chambres par categorie");
        System.out.println("2-Trier les chambres");
        System.out.println("3-Ajouter une Chambre");
        System.out.println("4-Ajouter plusieur chambres");
        System.out.println("5-Supprimer une chambre");
        System.out.println("6-Modifier l'etat d'une chambre");
        System.out.println("7-Changer du fichier");
        System.out.println("8-Afficher la liste des chambres libre");
        System.out.println("9-Calculer la recette maximale journalière");
        System.out.println("10-Calculer la recette réelle du jour");
        System.out.println("11-Copier toutes les chambres d'une catégorie données vers un fichier séquentiel.");
        System.out.println("12-sauvgarder");
        System.out.println("other-exit");
        int choix;
        int temp;
        char temp2;
        do {
            choix = 5000;
            System.out.println("donner votre choix :");
            try {
                choix = Integer.parseInt(Sn.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error n'est pas un nombre");
            }
            switch (choix) {
                case 5000:
                    break;
                case 0:
                    E.Afficher_tout();
                    break;
                case 1:
                    System.out.println("donner la categorie:");
                    do {
                        try {
                            temp = Integer.parseInt(Sn.nextLine());
                            E.Afficher_Cat(temp);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    break;
                case 2:
                    E.Trier();
                    break;
                case 3:
                    E.Ajouter_Chambre();
                    break;

                case 4:
                    System.out.println("donner le nombre des chambres:");
                    do {
                        try {
                            temp = Integer.parseInt(Sn.nextLine());
                            E.Remplire_Tableau(temp);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);

                    break;
                case 5:
                    System.out.println("donner le numero du chambre a supprimer:");
                    do {
                        try {
                            temp = Integer.parseInt(Sn.nextLine());
                            E.Delete_Room(temp);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    break;
                case 6:
                    System.out.println("donner le numero du chambre a modifier:");
                    do {
                        try {
                            temp = Integer.parseInt(Sn.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    System.out.println("donner l'etat du chambre a modifier:");
                    temp2 = Sn.nextLine().charAt(0);
                    E.modification_chambre(temp, temp2);
                    break;
                case 7:
                    System.out.println("donner le nom du fichier:");
                    E.Read_file(Sn.nextLine());
                    break;
                case 8:
                    afficher_Liste(E.chambres_Libre());
                    break;
                case 9:
                    System.out.println(E.Recette_max());
                    break;
                case 10:
                    System.out.println(E.Rectte_Jour());
                    break;
                case 11:
                    System.out.println("donner la categorie:");
                    do {
                        try {
                            temp = Integer.parseInt(Sn.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Error n'est pas un nombre");
                        }
                    } while (true);
                    System.out.println("donner le nom du fichier:");
                    E.To_another_file(Sn.nextLine(), temp);
                    break;
                case 12:
                    E.WriteINDoc();
                    break;
                default:
                    E.WriteINDoc();
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
