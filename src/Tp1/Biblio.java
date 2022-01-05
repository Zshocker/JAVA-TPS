package Tp1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Vector;

public class Biblio implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	public static Biblio readBiblio(String fichier,Scanner snScanner) {
		ObjectInputStream ne=null;
		FileInputStream fileInputStream=null;
		Biblio B=null;
		try {
			fileInputStream=new FileInputStream(fichier);
			ne=new ObjectInputStream(fileInputStream);
			B=(Biblio)ne.readObject();
		}
		catch (FileNotFoundException e) {
		}
		catch (Exception e) {
		} finally {
			try {
				if (fileInputStream!=null) {
					fileInputStream.close();
					ne.close();
				}
			} 
			catch (IOException e) {
			}
		}
		if(B==null) {
			 snScanner=new Scanner(System.in);
			System.out.println("aucune instance est trouver dans le fichier \n donner le nombre maximum des livres de la biblio ");
			B=new Biblio(Integer.parseInt(snScanner.nextLine()));
		}
		return  B;
	}
	
	public void WriteBiblio(String ficher)
	{
		ObjectOutputStream OS=null;
		try {
			OS=new ObjectOutputStream(new FileOutputStream(ficher));
			OS.writeObject(this);
			OS.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				OS.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private Vector<Livre> lisLivres=new Vector<Livre>();
	private int numMax;
	public Biblio(int numMax) 
	{
		this.numMax=numMax;
	}
	public void name() {
		
	} int capacity() {
		return numMax;
	}
	public int size()
	{
		return lisLivres.size();
	}
	public boolean ajouteLivre (Livre E) 
	{
		if(check_book_ex(E)) return false;
		if(isFilled())return false;
		lisLivres.add(E);
		return true;
	}
	public boolean isFilled() {
		return capacity()==size();
	}

	public String toString () {
		StringBuffer sBuffer=new StringBuffer();
		
		for (Livre livre : lisLivres) {
			sBuffer.append("\n----------------\n");
			sBuffer.append(livre.toString());
			sBuffer.append("\n----------------");
		}
		
		return sBuffer.toString();
	}
	public Vector<Livre> chercher(String auteur) 
	{
	  Vector<Livre>	lis=new Vector<Livre>();
	  for (Livre livre : lisLivres) {
		if(livre.IsOfAutor(auteur))lis.add(livre);
	  }
	  return lis;
	}
	private boolean check_book_ex(Livre E){
		for (Livre livre : lisLivres) {
			if(livre.equals(E))return true;
		}
		return false;
	}
}


