package TP5;

import java.awt.*;
import java.awt.event.*;
class Dessin extends Canvas{
    public int x,y,z,d;
    class Mes extends MouseAdapter{
        Canvas Eq;
        public Mes(Canvas Lis){
            Eq=Lis;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            x=e.getX();
            y=e.getY();
        }
      /*  @Override
        public void mouseDragged(MouseEvent e) {
            z=e.getX();
            d=e.getY();
        }*/
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            z=e.getX()-x;
            if(z<0)e.getX();
            d=e.getY()-y;
            if(d<0)getY();
            Eq.repaint();
        }
    }
    Planche mPlanche;
    public Dessin(Planche mP){
        mPlanche=mP;
        addMouseListener(new Mes(this));
    }
    @Override
    public void paint(Graphics g) {
        String colore=mPlanche.Malist.getSelectedItem();
        Color color=null;
        switch (colore.toLowerCase()) {
            case "black":
                color = Color.BLACK;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "darkgray":
                color = Color.DARK_GRAY;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "green":
                color = Color.GREEN;
                break;
        
            case "yellow":
                color = Color.YELLOW;
                break;
            case "lightgray":
                color = Color.LIGHT_GRAY;
                break;
            case "magneta":
                color = Color.MAGENTA;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "pink":
                color = Color.PINK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "white":
                color = Color.WHITE;
                break;
        }
        g.setColor(color); 
        String For=mPlanche.formsChoice.getSelectedItem();
        if(For.equals("Rectangle")){
           if(!mPlanche.Remplisage.getState()) g.drawRect(x, y, z, d);
           else g.fillRect(x,  y, z, d);
        }else{
            if(!mPlanche.Remplisage.getState())   g.drawOval(x, y, z, d);
            else g.fillOval(x,  y, z, d);
        }
    }
}