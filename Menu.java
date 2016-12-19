import java.util.Scanner;

public class Menu {
	public static void menu() {
		int choix;
		Scanner sc = new Scanner(System.in);
		System.out.print("***************** JeuxSurTableaux.java ****************"
				+ "\nVoici les différents jeux : "
				+ "\n0.Sortir"
				+ "\n1.Bataille Navale"
				+ "\n2.Puissance 4"
				+ "\n3.Othello"
				+ "\nEntrez votre choix : ");
		choix = Integer.parseInt(sc.nextLine());
		while (choix < 0 || choix > 3) {
			System.out.print("Entrez une option du menu (entre 0 et 3) : ");
			choix = Integer.parseInt(sc.nextLine());
		}
		switch (choix) {
			case 1 :
				BatailleNavale.rappelRègles();
				char [][] sauvegarde = new char[10][10];
				boolean stop = false;
				System.out.println("\nJoueur n°1 c'est à vous !");
				int[] j1boat = BatailleNavale.saisieBateau();
				BatailleNavale.affiche30ln();
				System.out.println("\nJoueur n°2 c'est à vous !");
				int[] j2boat = BatailleNavale.saisieBateau();
				BatailleNavale.affiche30ln();
				while (stop != true)	{
					System.out.println("\nJoueur n°1 c'est à vous !");
					int[] j1bomb = BatailleNavale.saisieMissile();
					while (sauvegarde[j1bomb[0]][j1bomb[1]] == '☢' || sauvegarde[j1bomb[0]][j1bomb[1]] == '☣') {
						System.out.print("Placement déja effectué ! Try again !\n");
						j1bomb = BatailleNavale.saisieMissile();
					}
					System.out.println("Votre missile sera le suivant : ☢"
							+ "\nIl affichera le bateau en cas de touché coulé !");
					if (BatailleNavale.toucheCoule(j2boat, j1bomb)==false) {
						sauvegarde[j1bomb[0]][j1bomb[1]] = '☢';
						BatailleNavale.affichePlateau(sauvegarde);	
					}
					if (BatailleNavale.toucheCoule(j2boat, j1bomb)) {
						sauvegarde[j1bomb[0]][j1bomb[1]] = '⛵';
						BatailleNavale.affichePlateau(sauvegarde);
						stop = true;
					}
					if (stop != true) {
						System.out.println("\nJoueur n°2 c'est à vous !");
						int[] j2bomb = BatailleNavale.saisieMissile();
						while (sauvegarde[j1bomb[0]][j1bomb[1]] == sauvegarde[j2bomb[0]][j2bomb[1]]) {
							System.out.print("Placement déja effectué ! Try again !\n");
							j2bomb = BatailleNavale.saisieMissile();
						}
						if (BatailleNavale.toucheCoule(j1boat, j2bomb)==false)
							sauvegarde[j2bomb[0]][j2bomb[1]] = '☣';
						System.out.println("Votre missile sera le suivant : ☣");
						BatailleNavale.affichePlateau(sauvegarde);
						if (BatailleNavale.toucheCoule(j1boat, j2bomb)){
							sauvegarde[j2bomb[0]][j2bomb[1]] = '⛴';
							BatailleNavale.affichePlateau(sauvegarde);
							stop = true;
						}
					}
				}
				break;
			case 2 :
				System.out.println("En cours de développement...Sorry");
				break;
			case 3 :
				System.out.println("En cours de développement...Sorry");
				break;
			default :
				break;
		}
		System.out.println("\nBye Bye !");
		sc.close();
	}
}
