package Tp1;
import java.util.Scanner;
import java.util.Vector;
public class aa {
	private static void Print_Vec(Vector<Livre> lisLivres)
	{
		StringBuffer sBuffer=new StringBuffer();
		
		for (Livre livre : lisLivres) {
			sBuffer.append("\n----------------\n");
			sBuffer.append(livre.toString());
			sBuffer.append("\n----------------\n");
		}
		System.out.print(sBuffer);
	}
	public static void main(String[] args) {	
		
		Scanner snScanner=new Scanner(System.in);
		int choix;
		boolean Bar=false;
		Biblio biblio=Biblio.readBiblio("Biblio",snScanner);
		System.out.println("1-afficher la capacite de la biblio ");
		System.out.println("2-afficher le nombre des livres dans la biblio ");
		System.out.println("3-ajouter un livre");
		System.out.println("4-afficher l'ensemble des livres contenus dans la bibliothèque");
		System.out.println("5-chercher un livre par auteur");
		System.out.println("other-exit");
		do 
		{
			choix=5000;
			System.out.println("donner votre choix :");
			try {
				choix=Integer.parseInt(snScanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Error n'est pas un nombre");
			}
			switch (choix) {
			case 5000:
				break;
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
					biblio.WriteBiblio("Biblio");
					Bar=true;
					break;
			}
			if(Bar)break;
		} while (true);
		snScanner.close();
	}
}
