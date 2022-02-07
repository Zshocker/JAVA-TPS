package TP5;
import java.awt.*;
import java.awt.event.*;

public class Planche extends Frame{
    List Malist=new List();
    Choice formsChoice=new Choice();
    Checkbox Remplisage =new Checkbox("Remplissage");
    public Planche(){
        super();
        MenuBar Bar=new MenuBar();
        Menu Fichier=new Menu("fichier");
        Bar.add(Fichier);
        MenuItem Exit=new MenuItem("Exit");
        Exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              System.exit(0);
            }
        });
        Fichier.add(Exit);
        this.setMenuBar(Bar);
        setVisible(true);
        this.setLayout(new BorderLayout());
        Canvas canvas=new Dessin(this);
        canvas.setSize(300,300);
        add(canvas,BorderLayout.CENTER);
        formsChoice.add("Rectangle");
        formsChoice.add("Cercle");
        Panel sPanel=new Panel();
        sPanel.setLayout(new FlowLayout());
        add(sPanel,BorderLayout.SOUTH);
        sPanel.add(formsChoice);
        Malist.add("Red");
        Malist.add("Green");
        Malist.add("Blue");
        Malist.select(0);
        sPanel.add(Malist);
        sPanel.add(Remplisage);
        setSize(400,400);
    }
    public static void main(String[] args) {
        new Planche();
    }
}
