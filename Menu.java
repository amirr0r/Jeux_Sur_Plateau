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
				Puissance4.rappelRegles();
				char [][] save = new char[6][7];
				boolean finish = false;
				System.out.println("\nJoueur n°1 c'est à vous !");
				int j1Pion = Puissance4.saisiePion();
				Puissance4.placement(j1Pion, 'O', save);
				Puissance4.affichePlateau(save);
				System.out.println("\nJoueur n°2 c'est à vous !");
				int j2Pion = Puissance4.saisiePion();
				Puissance4.placement(j2Pion, 'X', save);
				Puissance4.affichePlateau(save);
				while (!finish) {
					System.out.println("\nJoueur n°1 c'est à vous !");
					j1Pion = Puissance4.saisiePion();
					while (Puissance4.verifColonnePleine(save, j1Pion)) {
						System.out.print("Colonne pleine. Try une autre :");
						j1Pion = Puissance4.saisiePion();
					}
					Puissance4.placement(j1Pion, 'O', save);
					if (Puissance4.tableauPlein(save) || Puissance4.alignement(save, 'O'))
						finish = true;
					Puissance4.affichePlateau(save);
					if (Puissance4.alignement(save, 'O'))
						System.out.println("Joueur n°1 a gagné !");
					if (finish != true) {
						System.out.println("\nJoueur n°2 c'est à vous !");
						j2Pion = Puissance4.saisiePion();
						while (Puissance4.verifColonnePleine(save, j2Pion)) {
							System.out.print("Colonne pleine. Try une autre :");
							j2Pion = Puissance4.saisiePion();
						}
						Puissance4.placement(j2Pion, 'X', save);
						if (Puissance4.tableauPlein(save) || Puissance4.alignement(save, 'X'))
							finish = true;
						Puissance4.affichePlateau(save);	
						if (Puissance4.alignement(save, 'X'))
							System.out.println("Joueur n°2 a gagné !");
					}
				}
				System.out.println("Partie finie !");
				break;
			case 3 :
				boolean fin = false;
				char [][] othellier = new char [8][8];
				othellier [3][3] = 'O';
				othellier [3][4] = 'X';
				othellier [4][4] = 'O';
				othellier [4][3] = 'X';
				Othello.affichePlateau(othellier);
				System.out.println("Joueur n°1 c'est à vous !!!");
				int [] croix = Othello.saisiePion();
				while(!Othello.verifPion(othellier, croix)) {
					System.out.println(Othello.verifPion(othellier, croix));
					System.out.println("Placement impossible...try again pliz");
					croix = Othello.saisiePion();
				}
				Othello.placementPion(othellier, croix, 'X');
				Othello.convert(othellier, 'X', croix);
				Othello.affichePlateau(othellier);
				System.out.println("Joueur n°2 c'est à vous !!!");
				int [] rond = Othello.saisiePion();
				while(!Othello.verifPion(othellier, rond)) {
					System.out.println(Othello.verifPion(othellier, rond));
					System.out.println("Placement impossible...try again pliz");
					rond = Othello.saisiePion();
				}
				Othello.placementPion(othellier, rond, 'O');
				Othello.convert(othellier, 'O', rond);
				Othello.affichePlateau(othellier);
				while (!fin) {
					System.out.println("Joueur n°1 c'est à vous !!!");
					croix = Othello.saisiePion();
					while(!Othello.verifPion(othellier, croix)) {
						System.out.println(Othello.verifPion(othellier, croix));
						System.out.println("Placement impossible...try again pliz");
						croix = Othello.saisiePion();
					}
					Othello.placementPion(othellier, croix, 'X');
					Othello.convert(othellier, 'X', croix);
					Othello.affichePlateau(othellier);
					System.out.println("Joueur n°2 c'est à vous !!!");
					rond = Othello.saisiePion();
					while(!Othello.verifPion(othellier, rond)) {
						System.out.println(Othello.verifPion(othellier, rond));
						System.out.println("Placement impossible...try again pliz");
						rond = Othello.saisiePion();
					}
					Othello.placementPion(othellier, rond, 'O');
					Othello.convert(othellier, 'O', rond);
					Othello.affichePlateau(othellier);
				}
				Othello.winner(othellier, 'X', 'O');
				break;
			default :
				break;
		}
		System.out.println("\nBye Bye !");
		sc.close();
	}
}
