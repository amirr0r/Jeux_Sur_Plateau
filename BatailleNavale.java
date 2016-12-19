import java.util.Scanner;

public class BatailleNavale {
	private static Scanner saisie;
	
	public static void main(String[] args) {
		
	}
	public static void rappelRègles() {
		System.out.println("Rappel des règles :"
				+ "\nLa bataille navale se joue sur un tableau à 10 cases"
				+ "\nTout d'abord, les joueurs placent leurs bateaux respectifs"
				+ "\nEnsuite ils envoient des missiles dans le but de couler le bateau de leur adversaire"
				+ "\nC'est parti !");
	}
	/**
	 * 
	 * @return les coordonnées x y d'un bateau
	 */
	public static int[] saisieBateau() {
		int [] t = new int[2];
		saisie = new Scanner(System.in);
		System.out.println("\t* Saissez les coordonnées de votre bateau *");
		System.out.print("Ligne n°");
		t[0] = Integer.parseInt(saisie.nextLine()) - 1;
		while (t[0] < 0 || t[0] >= 10) {
			System.out.print("Entrez une ligne du plateau (entre 1 et 10) : ");
			t[0] = Integer.parseInt(saisie.nextLine()) -1;
		}
		System.out.print("Colonne n°");
		t[1] = Integer.parseInt(saisie.nextLine()) -1;
		while (t[1] < 0 || t[1] >= 10) {
			System.out.print("Entrez une colonne du plateau (entre 1 et 10) : ");
			t[1] = Integer.parseInt(saisie.nextLine()) -1;
		}
		return t;
	}
	/**
	 * 
	 * @return les coordonnées x y d'un missile
	 */
	public static int[] saisieMissile() {
		saisie = new Scanner(System.in);
		int [] t = new int[2]; 
		System.out.println("\t* Coordonnées de votre missile *");
		System.out.print("Ligne n°");
		t[0] = Integer.parseInt(saisie.nextLine()) -1;
		while (t[0] < 0 || t[0] >= 10) {
			System.out.print("Entrez une ligne du plateau (entre 1 et 10) : ");
			t[0] = Integer.parseInt(saisie.nextLine());
		}
		System.out.print("Colonne n°");
		t[1] = Integer.parseInt(saisie.nextLine()) -1;
		while (t[1] < 0 || t[1] >= 10) {
			System.out.print("Entrez une colonne du plateau (entre 1 et 10) : ");
			t[1] = Integer.parseInt(saisie.nextLine()) -1;
		}
		return t;
	}
	/*
	 * place les données saisies par l'utilisateur
	public static char[][] placementSaisie(int [] sc, char c) {
		saisie = new Scanner(System.in);
		char [][] plateau = new char[10][10];
		int [] position = sc;
		int ligne=position[0] -1;
		int colonne=position[1] -1;
		plateau[ligne][colonne] = c;
		affichePlateau(plateau);
		return plateau;
	}
	 */
	public static void affiche30ln() {
		int i= 50;
		while(i != 0) {
			System.out.println();
			i--;
		}
	}
	public static void affichePlateau(char [][] t) {
		System.out.println("\t  A\tB    C    D    E     F    G    H   I    J");
		 for (int height = 0 ; height < t.length ; height++) {
			 System.out.print((height+1)+"\t");
			 for (int width = 0 ; width < t[height].length ; width++) {
				 if (t[height][width] == '\0')
					 t[height][width] = ' ';
				 if (width == 0) 
					 System.out.print(" |_"+t[height][width]+" ");
				 else
					 System.out.print("_|_"+t[height][width]+" ");
				 if (width==9)
					 System.out.print("_|");
					 
			 }
			 System.out.println();
		 }
	}
	public static boolean toucheCoule(int [] bateau, int [] missile) {
		boolean touche = false;
		int ligneBateau =  bateau[0] -1;
		int ligneMissile = missile[0] -1;
		int colonneBateau =  bateau[1] -1;
		int colonneMissile = missile[1] -1;
		if (ligneBateau==ligneMissile && colonneBateau==colonneMissile) {
			System.out.println("\tTouché Coulé");
			touche = true;
		}
//		else {
//			if (ligneBateau==ligneMissile || colonneBateau==colonneMissile) //
//				System.out.println("\tTouché !");
			else
				System.out.println("\tÀ l'eau !!!");
//		}
		return touche;
	}
	 public static void afficheTableau(int[] t) {
		 for (int height = 0 ; height < t.length ; height++) 
				 System.out.print(t[height] + "\t");
		 
	 }
}
