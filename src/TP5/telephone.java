package TP5;

import java.awt.*;
import java.awt.event.*;
public class telephone extends Frame implements ActionListener {
    
    TextField Field=new TextField();
    String feature="";
    class Terminator extends WindowAdapter
    {
        Terminator(){}
        public void windowClosing (WindowEvent e) {System.exit(0);}
    }
    class MiniTele extends Panel {
        public MiniTele(ActionListener a){
            super();
            GridLayout Lay=new GridLayout(4,3);
            this.setLayout(Lay);
            for (int i = 1; i <= 9; i++) {
                Button A=new Button();
                A.addActionListener(a);
                A.setLabel(Integer.toString(i));
                this.add(A);
            }
            Button A=new Button();
            A.addActionListener(a);
            A.setLabel("Bis");
            add(A);
            A=new Button();
            A.addActionListener(a);
            A.setLabel("0");
            add(A);
            A=new Button();
            A.addActionListener(a);
            A.setLabel("Reset");
            add(A);
        }
    }
    public telephone(){
        super();
        this.setLayout(new BorderLayout());
        this.add(Field,BorderLayout.NORTH);
        this.add(new MiniTele(this),BorderLayout.CENTER);
        this.setSize(400, 400);
        this.addWindowListener(new Terminator());
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
      Button b=(Button)e.getSource();
      String t=b.getLabel();
        if(t.equals("Reset"))
        {
            Field.setText("");  
            feature="";
        }else if(t.equals("Bis")){
            Field.setText(feature);
        }else
        {
            Field.setText(t);
            feature+=t;  
        }
    }
    public static void main(String[] args) {
        new telephone();
    }
}
