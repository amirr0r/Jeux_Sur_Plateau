import java.util.Scanner;

public class Puissance4 {
	private static Scanner saisie;
	public static void rappelRegles() {
		System.out.println("\t\t\t*******RÈGLES DU JEU*******"
				+ "\nLe but du jeu est d'aligner 4 pions sur une grille comptant 6 rangées et 7 colonnes. \n"
				+ "Chaque joueur dispose de 21 pions ('X' ou 'O'). "
				+ "\nTour à tour les deux joueurs placent un pion dans la colonne de leur choix, "
				+ "\nle pion coulisse alors jusqu'à la position la plus basse possible dans la dite colonne "
				+ "\nà la suite de quoi c'est à l'adversaire de jouer. "
				+ "\nLe vainqueur est le joueur qui réalise le premier un alignement "
				+ "\n(horizontal, vertical ou diagonal) d'au moins quatre pions. "
				+ "\nSi, alors que toutes les cases de la grille de jeu sont remplies, "
				+ "\naucun des deux joueurs n'a réalisé un tel alignement, la partie est déclarée nulle.");
	}
	public static void affichePlateau(char [][] t) {
		System.out.println("\t    1\t   2     3     4     5     6    7");
		 for (int height = 0 ; height < t.length ; height++) {
			 System.out.print((height+1)+"\t");
			 for (int width = 0 ; width < t[height].length ; width++) {
				 if (t[height][width] == '\0')
					 t[height][width] = ' ';
				 if (width == 0) 
					 System.out.print(" |_ "+t[height][width]+" ");
				 else
				 	if (width == 1)
				 		System.out.print("_|_ "+t[height][width]);
				 	else
						System.out.print(" _|_ "+t[height][width]);
				 if (width==6)
					 System.out.print(" _|");
					 
			 }
		System.out.println();
		}
	}
	public static int saisiePion() {
		saisie = new Scanner(System.in);
		int colonne = -1;
		System.out.print("Saisissez la colonne dans laquelle vous voulez placez votre pion"
				+ "\nColonne n°");
		colonne = Integer.parseInt(saisie.nextLine());
		while (colonne < 1 || colonne > 7) {
			System.out.print("Erreur. Les colonnes vont de 1 à 7. Recommencez :");
			colonne = Integer.parseInt(saisie.nextLine());
		}
		colonne--;
		return colonne;
	}
	public static char[][] placement(int colonne, char c, char [][] t) {
		 for(int i = t.length -1 ; i != - 1; i--) {
			 if (t[i][colonne] != 'O' && t[i][colonne] != 'X') {
				 t[i][colonne] = c;
				 break;
			 }
		 }
		 return t;
	}
	public static boolean verifColonnePleine(char [][] t, int colonne) {
		boolean verdict = true;
		for (int height = 0 ; height < t.length ; height++) 
			if (t[height][colonne] == ' ')
				verdict = false;
		return verdict;
	}
	public static boolean tableauPlein(char[][] t) {
		boolean verdict = true;
		for (int height = 0 ; height < t.length ; height++) 
			for (int width = 0 ; width < t[height].length ; width++) 
			 	if (t[height][width] == ' ')
			 		verdict = false;
		return verdict;
	}
	public static boolean alignement(char [][] t, char c) {
		boolean verdict = false;
		///////HORIZONTAL
		for (int height = 0 ; height < t.length ; height++) 
			for (int width = 0 ; width < 4 ; width++)
				if (t[height][width] == c && t[height][width] == t[height][width + 1] && t[height][width] == t[height][width + 2] && t[height][width] == t[height][width + 3])
					verdict = true;
		///////VERTICAL
		for (int height = t.length -1 ; height > 2 ; height--) 
			for (int width = 0 ; width < 4 ; width++)
				if (t[height][width] == c && t[height][width] == t[height-1][width] && t[height][width] == t[height-2][width] && t[height][width] == t[height-3][width])
					verdict = true;
		///////DIAGONAL PARTANT DE LA GAUCHE
		for (int height = t.length -1 ; height > 3 ; height--) 
			for (int width = 0; width < 4 ; width++)
				if (t[height][width] == c && t[height][width] == t[height-1][width+1] && t[height][width] == t[height-2][width+2] && t[height][width] == t[height-3][width+3] )
					verdict = true;
		///////DIAGONAL PARTANT DE LA DROITE
		for (int height = t.length -1 ; height  > 3; height--) 
			for (int width = t[0].length -1; width > 3; width--)
				if (t[height][width] == c && t[height][width] == t[height-1][width-1] && t[height][width] == t[height-2][width-2] && t[height][width] == t[height-3][width-3] )
					verdict = true;
		return verdict;
	}
	
}
