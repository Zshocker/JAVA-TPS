package Tp3_LocVoit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.omg.IOP.ExceptionDetailMessage;

public class Agence {
    List<Voiture> voitures=new ArrayList<Voiture>();
    Map<Client,Voiture> Location = new HashMap<Client,Voiture>();
    
    public Agence() {
    }
    public boolean estLoueur(Client client)
    {
        return Location.get(client)!=null;
    }
    public boolean estLoue(Voiture v)
    {
        List<Voiture> ga=(List<Voiture>)Location.values();
        return ga.indexOf(v)!=-1;
    }
    public boolean exist(Voiture v)
    {
        if(voitures.indexOf(v)!=-1){
            return true;
        }        
        return false;
    }
    public void loueVoiture(Client client, Voiture v) throws VoitureDejaAlouer, ClientDejaLoueur, VoitureNexistePas
    {
        if(!exist(v))throw new VoitureNexistePas();
        if(estLoueur(client))throw new ClientDejaLoueur();
        if(estLoue(v))throw new VoitureDejaAlouer();
        Location.put(client, v);
    }     
    public void rendVoiture(Client client) throws ClientNestPasLoeur
    {
        if(estLoueur(client)){
            Location.remove(client);
        }else throw new ClientNestPasLoeur();
    }   
    
    public Agence(List<Voiture> voitures) {
        this.voitures = voitures;
    }
    public void addVoiture(Voiture... v)
    {
        for (Voiture voiture : v) {
            if(voitures.indexOf(voiture)==-1){
                voitures.add(voiture);
            }
        } 
    }
    public Iterator<Voiture> selectionne(Critere c)
    {
        List<Voiture> selected=new ArrayList<Voiture>();
        for (Voiture voiture : voitures) {
            if(c.estSatisfaitPar(voiture)){
                selected.add(voiture);
            }
        }
        return selected.iterator();
    }
   public void afficheSelection(Critere c){
       Iterator<Voiture> ite = selectionne(c);
       while(ite.hasNext())
       {
            System.out.println(ite.next()); 
       }
    }
    public Iterator<Voiture> lesVoituresLouees(){
        List<Voiture> ga=(List<Voiture>)Location.values();
        return ga.iterator();
    }
    class VoitureDejaAlouer extends Exception{
        @Override
        public String toString() {
            return "\nLa voiture est déjà louée !!\n";
        }
    } 
    class ClientDejaLoueur extends Exception{
        @Override
        public String toString() {
            return "\nLe client est déjà un Loueur !!\n";
        }
    } 
    class VoitureNexistePas extends Exception{
        @Override
        public String toString() {
            return "\nLa voiture n'existe pas dans l'agence \n";
        }
    } 
    class ClientNestPasLoeur extends Exception{
        @Override
        public String toString() {
            return "\nLe client n'est pas un Loueur \n";
        }
    } 
    public static void main(String[] args) {
        Agence ag= new Agence();
        
        ag.addVoiture(new Voiture("Dacia","Logan",175,2015),new Voiture("Renaut", "Megan", 99, 2009), new Voiture("Renaut","Clio",99,2009));
        //q5
        //ag.afficheSelection(new CriterePrix(100));
        //q7
        InterCritere inte = new InterCritere();
        inte.addCritere(new CritereAnne(2009),new CriterePrix(100),new CritereMarque("Renaut"));
        ag.afficheSelection(inte);
    }
    
   
    
}