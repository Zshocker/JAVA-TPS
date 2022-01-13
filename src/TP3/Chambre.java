package TP3;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Chambre implements Comparable {
    private int num,cate,capacity;
    private double Prix;
    private char etat='L';

    public Chambre(int num, int cate, int capacity, double Prix) 
    {
        this.num = num;
        this.cate = cate;
        this.capacity = capacity;
        this.Prix = Prix;
    } 

    public Chambre(int num, int cate, int capacity, double Prix, char etat) {
        this.num = num;
        this.cate = cate;
        this.capacity = capacity;
        this.Prix = Prix;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "{" +
            " num='" + num + "'" +
            ", cate='" + cate + "'" +
            ", capacity='" + capacity + "'" +
            ", Prix='" + Prix + "'" +
            ", etat='" + etat + "'" +
            "}";
    }
    public boolean TestCat(int cat){
        return cat==cate;
    }

    @Override
    public int compareTo(Object o) {
        Chambre C =(Chambre)o;
        if(capacity>C.capacity)return 1;
        else if(capacity<C.capacity)return -1;
        return 0;
    }
    public void Write_in_buffer(DataOutputStream o) throws IOException
    {
        o.writeInt(num);
        o.writeInt(cate);
        o.writeInt(capacity);
        o.writeDouble(Prix);
        o.writeChar(etat);
    }
    public Boolean Test_num(int num){
        return num==this.num;
    }
}
