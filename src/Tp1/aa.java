package Tp1;
import java.util.Scanner;
import java.util.Vector;
public class aa {
	private static void Print_Vec(Vector<Livre> lisLivres)
	{
		StringBuffer sBuffer=new StringBuffer();
		
		for (Livre livre : lisLivres) {
			sBuffer.append("----------------");
			sBuffer.append(livre.toString());
			sBuffer.append("----------------");
		}
		System.out.print(sBuffer);
	}
	public static void main(String[] args) {	
		
		Scanner snScanner=new Scanner(System.in);
		int choix;
		Vector<Livre> Temp;
		boolean Bar=false;
		System.out.println("donner le nombre maximum des livres de la biblio ");
		Biblio biblio=new Biblio(Integer.parseInt(snScanner.nextLine()));
		System.out.println("1-afficher la capacite de la biblio ");
		System.out.println("2-afficher le nombre des livres dans la biblio ");
		System.out.println("3-ajouter un livre");
		System.out.println("4-afficher l'ensemble des livres contenus dans la bibliothèque");
		System.out.println("5-chercher un livre par auteur");
		System.out.println("other-exit");
		do 
		{
			System.out.println("donner votre choix :");
			choix=Integer.parseInt(snScanner.nextLine());
			switch (choix) {
				case 1:
					System.out.println(biblio.capacity());
					break;
				case 2:
					System.out.println(biblio.size());
					break;
				case 3:
					if(biblio.ajouteLivre(Livre.creaLivre(snScanner)))System.out.println("Done!");
					else System.out.println("not Done!");
					break;
				case 4:
					System.out.println(biblio);
					break;	
				case 5:
					System.out.println("Donner le nom d'auteur ou juste son prefix");
					String temp=snScanner.nextLine();
					aa.Print_Vec(biblio.chercher(temp));
				 	break;	
				default:
					Bar=true;
					break;
			}
			if(Bar)break;
		} while (true);
		snScanner.close();
	}
}
