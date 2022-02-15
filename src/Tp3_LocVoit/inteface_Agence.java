package Tp3_LocVoit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import com.formdev.flatlaf.*;

import Tp3_LocVoit.Agence.ClientDejaLoueur;
import Tp3_LocVoit.Agence.ClientNestPasLoeur;
import Tp3_LocVoit.Agence.Clientnotfound;
import Tp3_LocVoit.Agence.VoitureDejaAlouer;
import Tp3_LocVoit.Agence.VoitureNexistePas;
import SpringE.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

public class inteface_Agence extends JFrame {
    Color bg = null;

    class AjouteClientDiag extends JDialog {
        private JButton jButton1;
        private JTextField jTextField1;
        private JTextField jTextField2;
        private JTextField jTextField4;
        private JLabel jLabel1 = new JLabel("Nom"), jLabel2 = new JLabel("Prenom"), jLabel3 = new JLabel("CIN"),
                jLabel4 = new JLabel("Civilite");
        private JComboBox<String> jComboBox1;
        int a = -1;

        public AjouteClientDiag(int a, List<Voiture> voits) {
            jTextField1 = new JTextField(20);
            jTextField2 = new JTextField(20);
            jTextField4 = new JTextField(20);
            jComboBox1 = new JComboBox<String>();
            jButton1 = new JButton();
            jTextField1.setText("Nom");
            jTextField2.setText("CIN");
            jTextField4.setText("Prenom");
            jComboBox1.addItem("M.");
            jComboBox1.addItem("Mme.");
            jComboBox1.addItem("Mlle.");
            jButton1.setText("Ajouter");
            jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean at = true;
                    String nom = jTextField1.getText();
                    if (nom.equals(""))
                        at = false;
                    String CIN = jTextField2.getText();
                    if (CIN.equals(""))
                        at = false;
                    String Prenom = jTextField4.getText();
                    if (Prenom.equals(""))
                        at = false;
                    String com = jComboBox1.getSelectedItem().toString();
                    if (at) {
                        ag.ajouter_client(new Client(nom, Prenom, CIN, com));
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");
                        if (a == 1)
                            new Louer_voit_Diag(voits);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "Veuillez Remplir tous les champs");
                    }

                }
            });
            JPanel content = new JPanel();
            Container content1 = getContentPane();
            content1.setLayout(new BorderLayout());
            content.setLayout(new SpringLayout());
            content.add(jLabel1);
            content.add(jTextField1);
            content.add(jLabel2);
            content.add(jTextField4);
            content.add(jLabel3);
            content.add(jTextField2);
            content.add(jLabel4);
            content.add(jComboBox1);
            content1.add(jButton1, BorderLayout.SOUTH);
            SpringUtilities.makeCompactGrid(content, 4, 2, 6, 6, 6, 50);
            content1.add(content, BorderLayout.CENTER);
            pack();

            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    class Ajouter_VoitureDiag extends JDialog {
        private JButton jButton2;
        private JTextField jTextField3;

        private JTextField jTextField5;
        private JTextField jTextField6;
        private JTextField jTextField7;
        private JLabel lab1 = new JLabel("Marque"), lab2 = new JLabel("Model"), lab3 = new JLabel("Prix par jour"),
                lab4 = new JLabel("Anne de production");

        public Ajouter_VoitureDiag() {

            jTextField3 = new JTextField();
            jTextField5 = new JTextField();
            jTextField6 = new JTextField();
            jTextField7 = new JTextField();
            jButton2 = new JButton();

            jTextField3.setText("Marque");
            jTextField5.setText("Model");

            jTextField6.setText("Prix de Jour ");

            jTextField7.setText("Annee de Production");

            jButton2.setText("Ajouter");
            jButton2.setBackground(bg.GREEN);

            jButton2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    boolean att = true;
                    String marque = jTextField3.getText();
                    if (marque.equals(""))
                        att = false;
                    String model = jTextField5.getText();
                    if (model.equals(""))
                        att = false;
                    int prix = 0, anne = 0;
                    try {
                        anne = Integer.parseInt(jTextField7.getText());
                        prix = Integer.parseInt(jTextField6.getText());
                    } catch (NumberFormatException e) {
                        att = false;
                    }
                    if (att) {
                        ag.addVoiture(new Voiture(marque, model, prix, anne));
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");

                        dispose();
                    } else {

                        JOptionPane.showMessageDialog(getParent(), "Veuillez Remplir tous les champs");
                    }
                }
            });

            JPanel content = new JPanel();
            Container content1 = getContentPane();
            content1.setLayout(new BorderLayout());
            content.setLayout(new SpringLayout());
            content.add(lab1);
            content.add(jTextField3);
            content.add(lab3);
            content.add(jTextField6);
            content.add(lab4);
            content.add(jTextField7);
            content.add(lab2);
            content.add(jTextField5);
            content1.add(jButton2, BorderLayout.SOUTH);
            SpringUtilities.makeCompactGrid(content, 4, 2, 50, 5, 50, 25);
            content1.add(content, BorderLayout.CENTER);
            pack();

            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class Louer_voit_Diag extends JDialog {
        private JButton jButton3, jButton4;
        private JLabel jLabel1, jLabel2;
        private JComboBox<Voiture> VoitCombo;
        private JComboBox<Client> ClientCom;

        public Louer_voit_Diag(List<Voiture> a1) {
            VoitCombo = new JComboBox<Voiture>();
            jLabel1 = new JLabel();
            ClientCom = new JComboBox<Client>();
            jLabel2 = new JLabel();
            jButton3 = new JButton();
            jButton4 = new JButton();
            Iterator<Voiture> a = a1.iterator();
            while (a.hasNext()) {
                Voiture v = a.next();
                if (!ag.estLoue(v)) {
                    VoitCombo.addItem(v);
                }
            }
            Iterator<Client> clie = ag.tous_client();
            while (clie.hasNext()) {
                Client vt = clie.next();
                if (!ag.estLoueur(vt)) {
                    ClientCom.addItem(vt);
                }
            }
            jLabel1.setText("Voiture");
            jLabel2.setText("Client");
            jButton3.setText("Louer");
            jButton3.setBackground(bg.GREEN);
            jButton4.setText("Ajouter un Client");
            jButton4.setBackground(bg.ORANGE);
            jButton4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    new AjouteClientDiag(1, a1);
                    dispose();
                }
            });
            jButton3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        ag.loueVoiture((Client) ClientCom.getSelectedItem(), (Voiture) VoitCombo.getSelectedItem());
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");
                        Refrech_Table();
                    } catch (VoitureDejaAlouer | ClientDejaLoueur | VoitureNexistePas e) {
                    } finally {
                        dispose();
                    }
                }
            });
            Container content = this.getContentPane();
            content.setLayout(new SpringLayout());
            content.add(jLabel1);
            content.add(VoitCombo);
            content.add(jLabel2);
            content.add(ClientCom);
            content.add(jButton4);
            content.add(jButton3);
            setSize(480, 200);
            setLocationRelativeTo(null);
            SpringUtilities.makeCompactGrid(content, 3, 2, 6, 6, 6, 30);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class Critere_Diag extends JDialog {
        private JLabel jLabel3;
        private JLabel jLabel4;
        private JLabel jLabel5;
        private JTextField jTextField10;
        private JTextField jTextField8;
        private JTextField jTextField9;
        String CMarque;
        InterCritere critere = new InterCritere();
        int Canne = 0, Cprix = 0;
        private JButton jButton5;

        public Critere_Diag(int a) {
            jLabel3 = new JLabel();
            jLabel4 = new JLabel();
            jLabel5 = new JLabel();
            jButton5 = new JButton();
            jLabel3.setText("Critere de Marque");
            jLabel4.setText("Critere d'annes prodcution ");
            jLabel5.setText("Critere du prix");
            jTextField8 = new JTextField();
            jTextField9 = new JTextField();
            jTextField10 = new JTextField();
            jButton5.setText("Ok");
            jButton5.setBackground(bg.GREEN);

            jButton5.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    CMarque = jTextField10.getText();
                    try {
                        Canne = Integer.parseInt(jTextField8.getText());
                    } catch (NumberFormatException e) {
                        if (!jTextField8.getText().equals(""))
                            JOptionPane.showMessageDialog(getParent(), "Critere d'annes prodcution est un nombre");
                    }
                    try {
                        Cprix = Integer.parseInt(jTextField9.getText());
                    } catch (NumberFormatException e) {
                        if (!jTextField9.getText().equals(""))
                            JOptionPane.showMessageDialog(getParent(), "Critere du prix est un nombre");
                    }
                    if (!CMarque.equals("")) {
                        critere.addCritere(new CritereMarque(CMarque));
                    }
                    if (Canne != 0) {
                        critere.addCritere(new CritereAnne(Canne));
                    }
                    if (Cprix != 0) {
                        critere.addCritere(new CriterePrix(Cprix));
                    }
                    if (a == 1)
                        new Voiture_Afficher(ag.selectionne_Vec(critere));
                    else
                        new Louer_voit_Diag(ag.selectionne_Vec(critere));
                    dispose();
                }

            });

            JPanel content = new JPanel();
            Container content1 = getContentPane();
            content1.setLayout(new BorderLayout());
            content.setLayout(new SpringLayout());
            content.add(jLabel3);
            content.add(jTextField10);
            content.add(jLabel4);
            content.add(jTextField8);
            content.add(jLabel5);
            content.add(jTextField9);
            content1.add(jButton5, BorderLayout.SOUTH);
            SpringUtilities.makeCompactGrid(content, 3, 2, 6, 6, 6, 50);
            content1.add(content, BorderLayout.CENTER);
            setSize(350, 250);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class Voiture_Afficher extends JDialog {
        private JButton jButton6;
        private JScrollPane jScrollPane1;
        private JTable jTable1;

        public Voiture_Afficher(List<Voiture> iter) {
            jButton6 = new JButton();
            jScrollPane1 = new JScrollPane();
            jTable1 = new JTable();
            jButton6.setText("Ok");
            jButton6.setBackground(bg.GREEN);

            jButton6.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }

            });
            String[][] sq = new String[iter.size()][4];
            int i = 0;
            for (Voiture voiture : iter) {
                sq[i][0] = voiture.getMarque();
                sq[i][1] = voiture.getModel();
                sq[i][2] = "" + voiture.getPrixJ();
                sq[i][3] = "" + voiture.getAnneeProduction();
                i++;
            }
            jTable1.setModel(new DefaultTableModel(
                    sq, new String[] {
                            "Marque", "Model", "Prix de Jour", "Annee de Production"
                    }));
            jTable1.disable();
            jScrollPane1.setViewportView(jTable1);
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            content.add(jScrollPane1, BorderLayout.CENTER);
            content.add(jButton6, BorderLayout.SOUTH);
            setSize(350, 350);

            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class Client_Afficher extends JDialog {
        private JButton jButton6;
        private JScrollPane jScrollPane1;
        private JTable jTable1;

        public Client_Afficher(List<Client> iter) {
            jButton6 = new JButton();
            jScrollPane1 = new JScrollPane();
            jTable1 = new JTable();
            jButton6.setText("Ok");
            jButton6.setBackground(bg.GREEN);

            jButton6.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }

            });
            String[][] sq = new String[iter.size()][4];
            int i = 0;
            for (Client client : iter) {
                sq[i][0] = client.getNom();
                sq[i][1] = client.getPrenom();
                sq[i][2] = client.getCIN();
                sq[i][3] = client.getCivilite();
                i++;
            }
            jTable1.setModel(new DefaultTableModel(
                    sq, new String[] {
                            "Nom", "Prenom", "CIN", "Civilite"
                    }));
            jScrollPane1.setViewportView(jTable1);
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            content.add(jScrollPane1, BorderLayout.CENTER);
            content.add(jButton6, BorderLayout.SOUTH);
            setSize(350, 350);
            jTable1.disable();
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    class Rendre_Diag extends JDialog {
        private JComboBox<String> jComboBox4;
        private JButton jButton11;
        private JLabel jLabel6;

        Rendre_Diag(Iterator<Client> clis) {
            jComboBox4 = new JComboBox<String>();
            jButton11 = new JButton();
            jLabel6 = new JLabel();
            while (clis.hasNext())
                jComboBox4.addItem(clis.next().getCIN());
            jButton11.setText("Rendre");
            jButton11.setBackground(bg.GREEN);
            jButton11.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        ag.rendVoiture(ag.find_byCIN(jComboBox4.getSelectedItem().toString()));
                        Refrech_Table();
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");
                        dispose();
                    } catch (ClientNestPasLoeur e) {
                    } catch (Clientnotfound e) {
                    }
                }
            });
            jLabel6.setText("CIN du Loeur");
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            JPanel pan = new JPanel(new SpringLayout());
            content.add(jButton11, BorderLayout.SOUTH);
            content.add(pan, BorderLayout.CENTER);
            pan.add(jLabel6);
            pan.add(jComboBox4);
            SpringUtilities.makeCompactGrid(pan, 1, 2, 6, 6, 6, 1);
            setSize(300, 100);
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    class SupprimerClient_Diag extends JDialog {
        private JComboBox<String> jComboBox4;
        private JButton jButton11;
        private JLabel jLabel6;

        SupprimerClient_Diag(Iterator<Client> clis) {
            jComboBox4 = new JComboBox<String>();
            jButton11 = new JButton();
            jLabel6 = new JLabel();
            while (clis.hasNext())
                jComboBox4.addItem(clis.next().getCIN());
            jButton11.setText("Supprimer");
            jButton11.setBackground(Color.RED);
            jButton11.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        Client cli = ag.find_byCIN(jComboBox4.getSelectedItem().toString());
                        if (ag.estLoueur(cli)) {
                            ag.rendVoiture(cli);
                            Refrech_Table();
                        }
                        ag.Clints.remove(cli);
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");
                        dispose();
                    } catch (ClientNestPasLoeur e) {
                    } catch (Clientnotfound e) {
                    }
                }
            });
            jLabel6.setText("CIN du Client");
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            JPanel pan = new JPanel(new SpringLayout());
            content.add(jButton11, BorderLayout.SOUTH);
            content.add(pan, BorderLayout.CENTER);
            pan.add(jLabel6);
            pan.add(jComboBox4);
            SpringUtilities.makeCompactGrid(pan, 1, 2, 6, 6, 6, 1);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    class SupprimerVoiture_Diag extends JDialog {
        private JComboBox<Voiture> jComboBox4;
        private JButton jButton11;
        private JLabel jLabel6;

        SupprimerVoiture_Diag(Iterator<Voiture> voit) {
            jComboBox4 = new JComboBox<Voiture>();
            jButton11 = new JButton();
            jLabel6 = new JLabel();
            while (voit.hasNext())
                jComboBox4.addItem(voit.next());
            jButton11.setText("Supprimer");
            jButton11.setBackground(bg.RED);

            jButton11.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        Voiture voit = (Voiture) jComboBox4.getSelectedItem();
                        if (ag.estLoue(voit)) {
                            ag.rendVoiture(voit);
                            Refrech_Table();
                        }
                        ag.voitures.remove(voit);
                        JOptionPane.showMessageDialog(getParent(), "Bien Fait!");
                        dispose();
                    } catch (VoitureNexistePas e) {
                    } catch (ClientNestPasLoeur e) {
                    }
                }
            });
            jLabel6.setText("Voiture");
            Container content = this.getContentPane();
            content.setLayout(new BorderLayout());
            JPanel pan = new JPanel(new SpringLayout());
            content.add(jButton11, BorderLayout.SOUTH);
            content.add(pan, BorderLayout.CENTER);
            pan.add(jLabel6);
            pan.add(jComboBox4);
            SpringUtilities.makeCompactGrid(pan, 1, 2, 6, 6, 6, 1);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }

    }

    class Termin extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            ag.WriteAgence("AgenceF");
            System.exit(0);
        }
    }

    void Refrech_Table() {
        String[][] sq = new String[ag.Location.size()][2];

        Set<Entry<Client, Voiture>> ed = ag.Location.entrySet();
        int i = 0;
        for (Entry<Client, Voiture> entry : ed) {
            sq[i][0] = entry.getKey().toString();
            sq[i][1] = entry.getValue().toString();
            i++;
        }
        jTable2.setModel(new DefaultTableModel(sq, new String[] { "Loeur", "Voiture louer" }));
        // Jtable2.setColor("Blue");
        jTable2.setBackground(bg.PINK);
        jTable2.setSize(300, 330);

        jTable2.disable();
    }

    Agence ag;
    private JMenuItem jButton10, jButton12, jButton13, jButton7, jButton8, jButton9, jButton14, jButton15, jButton16,
            jButton17, jButton18;
    private JScrollPane jScrollPane2;
    JTable jTable2;
    JLabel jLabel5;

    public inteface_Agence() {
        super("Agence");
        ag = Agence.readAgence("AgenceF");
        jScrollPane2 = new JScrollPane();
        jTable2 = new JTable();
        jButton7 = new JMenuItem();
        jButton8 = new JMenuItem();
        jButton9 = new JMenuItem();
        jButton10 = new JMenuItem();
        jButton12 = new JMenuItem();
        jButton13 = new JMenuItem();
        jButton14 = new JMenuItem();
        jButton15 = new JMenuItem();
        jButton16 = new JMenuItem();
        jButton17 = new JMenuItem();
        jButton18 = new JMenuItem();

        jLabel5 = new JLabel("bonkour");
        Refrech_Table();
        jScrollPane2.setViewportView(jTable2);
        jButton14.setText("Afficher les voiture celon des critere");
        jButton7.setText("Ajouter une Voiture");
        jButton8.setText("Ajouter un Client");
        jButton9.setText("Rendre");
        jButton10.setText("Louer");
        jButton12.setText("Afficher tous les voiture");
        jButton13.setText("Afficher tous les clients");
        jButton15.setText("Supprimer Client");
        jButton16.setText("Supprimer une Voiture");
        jButton17.setText("Enregistrer");
        jButton18.setText("Exit");
        JMenuBar menu = new JMenuBar();
        JMenu voi = new JMenu("Voitures");
        JMenu fich = new JMenu("Fichier");
        JMenu clien = new JMenu("Clients");
        JMenu loi = new JMenu("Location");
        JMenu aficha = new JMenu("Afficher");
        menu.add(fich);
        menu.add(voi);
        menu.add(clien);
        menu.add(loi);
        menu.add(aficha);
        voi.add(jButton7);
        clien.add(jButton8);
        loi.add(jButton9);
        loi.add(jButton10);
        aficha.add(jButton12);
        aficha.add(jButton13);
        aficha.add(jButton14);
        clien.add(jButton15);
        voi.add(jButton16);
        fich.add(jButton17);
        fich.add(jButton18);
        this.setJMenuBar(menu);

        jButton17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ag.WriteAgence("AgenceF");
                JOptionPane.showMessageDialog(getParent(), "Bien Enregistrer!");

            }
        });

        jButton18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ag.WriteAgence("AgenceF");
                System.exit(0);
            }
        });

        jButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Critere_Diag(1);
            }
        });
        jButton13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client_Afficher(ag.Clints);
            }
        });
        jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rendre_Diag(ag.lesLoueur());
            }
        });
        jButton12.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Voiture_Afficher(ag.voitures);
            }

        });
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Ajouter_VoitureDiag();
            }
        });
        jButton8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new AjouteClientDiag(0, null);
            }
        });
        jButton10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Critere_Diag(0);
            }
        });
        jButton15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new SupprimerClient_Diag(ag.tous_client());
            }
        });
        jButton16.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new SupprimerVoiture_Diag(ag.tousLesVoitures());
            }
        });

        jScrollPane2.setSize(100, 50);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(jScrollPane2, BorderLayout.NORTH);

        addWindowListener(new Termin());
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        // FlatIntelliJLaf.setup();
        new inteface_Agence();
    }

}