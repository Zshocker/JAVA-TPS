package TP5;

import java.awt.*;

public class telephone extends Frame {
    public telephone(){
        super();
        GridLayout Lay=new GridLayout(4,3);
        this.setLayout(Lay);
        for (int i = 1; i <= 9; i++) {
            Button A=new Button();
            A.setLabel(Integer.toString(i));
            add(A);
        }
        Button A=new Button();
        A.setLabel("Bis");
        add(A);
        A=new Button();
        add(A);
    }


}
