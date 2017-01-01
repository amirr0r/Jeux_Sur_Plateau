import java.util.Scanner;

public class Othello {
	private static Scanner saisie;
	/**
	 * 
	 * @return les coordonnées x y d'un pion
	 */
	public static void affichePlateau(char [][] t) {
		System.out.println("\t    1\t   2     3     4     5     6    7    8");
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
				if (width==7)
					System.out.print(" _|");	 
			}
			System.out.println();
		}
	}
	/**
	 * 
	 * @return les coordonnees du pion saisies par l'utlisateur 
	 */
	public static int[] saisiePion() {
		int [] t = new int[2];
		saisie = new Scanner(System.in);
		System.out.println("\t* Saissez les coordonnées de votre pion *");
		System.out.print("Ligne n°");
		t[0] = Integer.parseInt(saisie.nextLine()) - 1;
		while (t[0] < 0 || t[0] > 7) {
			System.out.print("Entrez une ligne du plateau (entre 1 et 8) : ");
			t[0] = Integer.parseInt(saisie.nextLine()) -1;
		}
		System.out.print("Colonne n°");
		t[1] = Integer.parseInt(saisie.nextLine()) -1;
		while (t[1] < 0 || t[1] > 7) {
			System.out.print("Entrez une colonne du plateau (entre 1 et 8) : ");
			t[1] = Integer.parseInt(saisie.nextLine()) -1;
		}
		return t;
	}
	/**
	 * 
	 * @param t : tableau de char(othellier)
	 * @param tab : coordonnees du pion
	 * @return true si le pion peut se placer aux coordonnees indiques 
	 */
	public static boolean verifPion(char [][] t, int [] tab ) {
		boolean verdict = true;
		int x = tab[0], y = tab[1];
		if (t[x][y] == 'O' || t[x][y] == 'X')
			verdict = false;
		else {
			///extremités du tableau vertical
			if (x==0 ) {
				if (y == 0) {
					if (t[x][y+1] == ' ' && t[x+1][y] == ' ' && t[x+1][y+1] == ' ')
						verdict = false;
				}
				else if (y==7) {
					if (t[x][y-1] == ' ' && t[x+1][y] == ' ' && t[x+1][y-1] == ' ')
						verdict = false;
				}
				else {
					if (t[x][y-1] == ' ' && t[x][y+1] == ' ' && t[x+1][y] == ' ' && t[x+1][y-1] == ' ' && t[x+1][y+1] == ' ')
						verdict = false;
				}
			}
			else if (x==7) {
				if (y == 0) {
					if (t[x][y+1] == ' ' && t[x-1][y] == ' ' && t[x-1][y+1] == ' ')
						verdict = false;
				}
				else if (y==7) {
					if (t[x][y-1] == ' ' && t[x-1][y] == ' ' && t[x-1][y-1] == ' ')
						verdict = false;
				}
				else {
					if (t[x][y-1] == ' ' && t[x][y+1] == ' ' && t[x-1][y] == ' ' && t[x-1][y-1] == ' ' && t[x-1][y+1] == ' ')
						verdict = false;
				}
				
			}
			///extrémités du tableau horizontal
			else if (y==0) {
				if (t[x][y+1] == ' ' && t[x-1][y] == ' ' && t[x+1][y] == ' ' && t[x-1][y+1] == ' ' && t[x+1][y+1] == ' ')
					verdict = false;
			}
			else if (y==7) {
				if (t[x][y-1] == ' ' && t[x-1][y] == ' ' && t[x+1][y] == ' ' && t[x-1][y-1] == ' ' && t[x+1][y-1] == ' ')
					verdict = false;
			}
			// intérieur du tableau
			else {
				if (t[x-1][y] == ' ' && t[x+1][y] == ' ' && t[x][y-1] == ' ' && t[x][y+1] == ' ' && t[x-1][y-1] == ' ' && t[x-1][y+1] == ' ' && t[x+1][y-1] == ' ' && t[x+1][y+1] == ' ')
					verdict = false;	
			}
		}
		return verdict;
	}
	/*
	 * place le pion sur l'othellier
	 */
	public static void placementPion(char [][] t, int [] tab, char c) {
		int x = tab[0], y = tab[1];
		t [x][y] = c;
	}
	/**
	 * 
	 * @param t tableau de char 2D
	 * @return true si le tableau est plein
	 */
	public static boolean tableauPlein(char[][] t) {
		boolean verdict = true;
		for (int height = 0 ; height < t.length ; height++) 
			for (int width = 0 ; width < t[height].length ; width++) 
				if (t[height][width] == ' ')
					verdict = false;
		return verdict;
	}
	/*
	 * convertit les pions pris en sandwich
	 */
	public static void convert(char [][] t, char c, int [] tab) {
		char c2 = ' ';
		int x = tab[0], y = tab[1];
		if (c == 'X')
			c2 = 'O';
		else // c=='O'
			c2 = 'X';
		///////////////////////////////HORIZONTAL
		if (y != 7) {
			if (t[x][y+1] == c2) {
				if (y==0 || y==1 || y==2 || y==3 || y==4) {
					if (t[x][y+2] == c)
						t[x][y+1] = c;
					else if (t[x][y+2] == c2 && t[x][y+3] == c) {
						t[x][y+1] = c;
						t[x][y+2] = c;
					}
				}
				if (y==0 || y==1 || y==2 || y==3) {
					if (t[x][y+2] == c2 && t[x][y+3] == c2 && t[x][y+4] == c) {
						t[x][y+1] = c;
						t[x][y+2] = c;
						t[x][y+3] = c;
					}
				}
				if (y==0 || y==1 || y==2) {
					if (t[x][y+2] == c2 && t[x][y+3] == c2 && t[x][y+4] == c2 && t[x][y+5] == c) {
						t[x][y+1] = c;
						t[x][y+2] = c;
						t[x][y+3] = c;
						t[x][y+4] = c;
					}
				}
				if(y==0 || y==1) {
					if (t[x][y+2] == c2 && t[x][y+3] == c2 && t[x][y+4] == c2 && t[x][y+5] == c2 && t[x][y+6] == c) {
						t[x][y+1] = c;
						t[x][y+2] = c;
						t[x][y+3] = c;
						t[x][y+4] = c;
						t[x][y+5] = c;
					}
				}
				if (y==0) {
					if (t[x][y+2] == c2 && t[x][y+3] == c2 && t[x][y+4] == c2 && t[x][y+5] == c2 && t[x][y+6] == c2 && t[x][y+7] == c) {
						t[x][y+1] = c;
						t[x][y+2] = c;
						t[x][y+3] = c;
						t[x][y+4] = c;
						t[x][y+5] = c;
						t[x][y+6] = c;
					}
				}
			}
		}
		if (y != 0 && y != 1) {
			if (t[x][y-1] == c2) {
				if (t[x][y-2] == c)
					t[x][y-1] = c;
				if (y==3 || y==4 || y==5 || y==6 || y==7) {
					if (t[x][y-2] == c2 && t[x][y-3] == c) {
						t[x][y-1] = c;
						t[x][y-2] = c;
					}
				}
				if (y==4 || y==5 || y==6 || y==7) {
					if (t[x][y-2] == c2 && t[x][y-3] == c2 && t[x][y-4] == c) {
						t[x][y-1] = c;
						t[x][y-2] = c;
						t[x][y-3] = c;
					}

				}
				if (y==5 || y==6 || y==7) {
					if (t[x][y-2] == c2 && t[x][y-3] == c2 && t[x][y-4] == c2 && t[x][y-5] == c) {
						t[x][y-1] = c;
						t[x][y-2] = c;
						t[x][y-3] = c;
						t[x][y-4] = c;
					}

				}
				if (y==6 || y==7) {
					if (t[x][y-2] == c2 && t[x][y-3] == c2 && t[x][y-4] == c2 && t[x][y-5] == c2 && t[x][y-6] == c) {
						t[x][y-1] = c;
						t[x][y-2] = c;
						t[x][y-3] = c;
						t[x][y-4] = c;
						t[x][y-5] = c;
					}
				}
				if (y==7) {
					if (t[x][y-2] == c2 && t[x][y-3] == c2 && t[x][y-4] == c2 && t[x][y-5] == c2 && t[x][y-6] == c2 && t[x][y-7] == c) {
						t[x][y-1] = c;
						t[x][y-2] = c;
						t[x][y-3] = c;
						t[x][y-4] = c;
						t[x][y-5] = c;
						t[x][y-6] = c;
					}
				}
			}
		}
		////////////////////////////////////////////////VERTICAL
		if(x==0 || x==1 || x==2 || x==3 || x==4 || x==5 || x==6) {
			if (t[x+1][y] == c2) {
				if (x != 6 && t[x+2][y] == c)
					t[x+1][y] = c;
				if (x==0 || x==1 || x==2 || x==3 || x==4) {
					if (t[x+2][y] == c2 && t[x+3][y] == c) {
						t[x+1][y] = c;
						t[x+2][y] = c;
					}
				}
				if (x==0 || x==1 || x==2 || x==3) {
					if (t[x+2][y] == c2 && t[x+3][y] == c2 && t[x+4][y] == c) {
						t[x+1][y] = c;
						t[x+2][y] = c;
						t[x+3][y] = c;
					}
				}
				if (x==0 || x==1 || x==2) {
					if (t[x+2][y] == c2 && t[x+3][y] == c2 && t[x+4][y] == c2 && t[x+5][y] == c) {
						t[x+1][y] = c;
						t[x+2][y] = c;
						t[x+3][y] = c;
						t[x+4][y] = c;
					}
				}
				if(x==0 || x==1) {
					if (t[x+2][y] == c2 && t[x+3][y] == c2 && t[x+4][y] == c2 && t[x+5][y] == c2 && t[x+6][y] == c) {
						t[x+1][y] = c;
						t[x+2][y] = c;
						t[x+3][y] = c;
						t[x+4][y] = c;
						t[x+5][y] = c;
					}
				}
				if (x==0) {
					if (t[x+2][y] == c2 && t[x+3][y] == c2 && t[x+4][y] == c2 && t[x+5][y] == c2 && t[x+6][y] == c2 && t[x+7][y] == c) {
						t[x+1][y] = c;
						t[x+2][y] = c;
						t[x+3][y] = c;
						t[x+4][y] = c;
						t[x+5][y] = c;
						t[x+6][y] = c;
					}
				}
			}
		}

		if (x != 0 && x != 1) {
			if (t[x-1][y] == c2) {
				if (t[x-2][y] == c)
					t[x-1][y] = c;
				if (t[x-1][y] == c2) {
					if (x==3 || x==4 || x==5 || x==6 || x==7) {
						if (t[x-2][y] == c2 && t[x-3][y] == c) {
							t[x-1][y] = c;
							t[x-2][y] = c;
						}
					}
					if (x==4 || x==5 || x==6 || x==7) {
						if (t[x-2][y] == c2 && t[x-3][y] == c2 && t[x-4][y] == c) {
							t[x-1][y] = c;
							t[x-2][y] = c;
							t[x-3][y] = c;
						}
					}
					if (x==5 || x==6 || x==7) {
						if (t[x-2][y] == c2 && t[x-3][y] == c2 && t[x-4][y] == c2 && t[x-5][y] == c) {
							t[x-1][y] = c;
							t[x-2][y] = c;
							t[x-3][y] = c;
							t[x-4][y] = c;
						}
					}
					if (x==6 || x==7) {
						if (t[x-2][y] == c2 && t[x-3][y] == c2 && t[x-4][y] == c2 && t[x-5][y] == c2 && t[x-6][y] == c) {
							t[x-1][y] = c;
							t[x-2][y] = c;
							t[x-3][y] = c;
							t[x-4][y] = c;
							t[x-5][y] = c;
						}
					}
					if (x==7) {
						if (t[x-2][y] == c2 && t[x-3][y] == c2 && t[x-4][y] == c2 && t[x-5][y] == c2 && t[x-6][y] == c2 && t[x-7][y] == c) {
							t[x-1][y] = c;
							t[x-2][y] = c;
							t[x-3][y] = c;
							t[x-4][y] = c;
							t[x-5][y] = c;
							t[x-6][y] = c;
						}
					}
				}
			}
		}
		////DIAGONAL
		if ((x==7 || x== 6 || x==5 || x==4 || x==3 || x==2)) {
			if (y!=7 && t[x][y] == c && t[x-1][y+1] == c2) {
				if (x != 0 && x != 1 && y != 6 && y != 7 && t[x-2][y+2] == c)
					t[x-1][y+1] = c;
				if ((y==0 || y==1 || y==2 || y==3 || y==4) && (x==7 || x== 6 || x==5 || x==4 || x==3)) {
					if (t[x-2][y+2] == c2 && t[x-3][y+3] == c) {
						t[x-1][y+1] = c;
						t[x-2][y+2] = c;
					}
				}
				if ((y==0 || y==1 || y==2 || y==3) && (x==7 || x== 6 || x==5 || x==4)) {
					if (t[x-2][y+2] == c2 && t[x-3][y+3] == c && t[x-4][y+4] == c) {
						t[x-1][y+1] = c;
						t[x-2][y+2] = c;
						t[x-3][y+3] = c;
					}
				}
				if ((y==0 || y==1 || y==2) && (x==7 || x== 6 || x==5)) {
					if (t[x-2][y+2] == c2 && t[x-3][y+3] == c && t[x-4][y+4] == c2 && t[x-5][y+5] == c) {
						t[x-1][y+1] = c;
						t[x-2][y+2] = c;
						t[x-3][y+3] = c;
						t[x-4][y+4] = c;
					}
				}
				if((y==0 || y==1) && (x==7 || x== 6)) {
					if (t[x-2][y+2] == c2 && t[x-3][y+3] == c && t[x-4][y+4] == c2 && t[x-5][y+5] == c2 && t[x-6][y+6] == c) {
						t[x-1][y+1] = c;
						t[x-2][y+2] = c;
						t[x-3][y+3] = c;
						t[x-4][y+4] = c;
						t[x-5][y+5] = c;
					}
				}
				if (y == 0 && x==7) {
					if (t[x-2][y+2] == c2 && t[x-3][y+3] == c && t[x-4][y+4] == c2 && t[x-5][y+5] == c2 && t[x-6][y+6] == c2 && t[x-7][y+7] == c) {
						t[x-1][y+1] = c;
						t[x-2][y+2] = c;
						t[x-3][y+3] = c;
						t[x-4][y+4] = c;
						t[x-5][y+5] = c;
						t[x-6][y+6] = c;
					}
				}
			}
		}
		if (x!= 7 && y != 0 && t[x][y] == c && t[x+1][y-1]==c2) {
			if (x!= 6 && y!= 1 && t[x+2][y-2] == c)
				t[x+1][y-1] = c;
			if ((x==0 || x==1 || x==2 || x==3 || x==4) && (y==7 || y== 6 || y==5 || y==4 || y==3)) {
				if (t[x+2][y-2] == c2 && t[x+3][y-3] == c) {
					t[x+1][y-1] = c;
					t[x+2][y-2] = c;
				}
			}
			if ((x==0 || x==1 || x==2 || x==3) && (y==7 || y== 6 || y==5 || y==4)) {
				if (t[x+2][y-2] == c2 && t[x+3][y-3] == c && t[x+4][y-4] == c) {
					t[x+1][y-1] = c;
					t[x+2][y-2] = c;
					t[x+3][y-3] = c;
				}
			}
			if ((x==0 || x==1 || x==2) && (y==7 || y== 6 || y==5)) {
				if (t[x+2][y-2] == c2 && t[x+3][y-3] == c && t[x+4][y-4] == c2 && t[x+5][y-5] == c) {
					t[x+1][y-1] = c;
					t[x+2][y-2] = c;
					t[x+3][y-3] = c;
					t[x+4][y-4] = c;
				}
			}
			if((x==0 || x==1) && (y==7 || y== 6)) {
				if (t[x+2][y-2] == c2 && t[x+3][y-3] == c && t[x+4][y-4] == c2 && t[x+5][y-5] == c2 && t[x+6][y-6] == c) {
					t[x+1][y-1] = c;
					t[x+2][y-2] = c;
					t[x+3][y-3] = c;
					t[x+4][y-4] = c;
					t[x+5][y-5] = c;
				}
			}
			if (x== 0 && y==7) {
				if (t[x+2][y-2] == c2 && t[x+3][y-3] == c && t[x+4][y-4] == c2 && t[x+5][y-5] == c2 && t[x+6][y-6] == c2 && t[x+7][y-7] == c) {
					t[x+1][y-1] = c;
					t[x+2][y-2] = c;
					t[x+3][y-3] = c;
					t[x+4][y-4] = c;
					t[x+5][y-5] = c;
					t[x+6][y-6] = c;
				}
			}
		}
		if (x!= 0 && y!=0 && t[x][y]==c && t[x-1][y-1] == c2) {
			if (x!= 1 && y!=1 && t[x-2][y-2] == c)
				t[x-1][y-1] = c;
			if ((y==7 || y==6 || y==5 || y==4 || y==3) && (x==7 || x== 6 || x==5 || x==4 || x==3)) {
				if (t[x-2][y-2] == c2 && t[x-3][y-3] == c) {
					t[x-1][y-1] = c;
					t[x-2][y-2] = c;
				}
			}
			if ((y==7 || y== 6 || y==5 || y==4) && (x==7 || x== 6 || x==5 || x==4)) {
				if (t[x-2][y-2] == c2 && t[x-3][y-3] == c2 && t[x-4][y-4] == c) {
					t[x-1][y-1] = c;
					t[x-2][y-2] = c;
					t[x-3][y-3] = c;
				}
			}
			if ((y==7 || y== 6 || y==5) && (x==7 || x== 6 || x==5)) {
				if (t[x-2][y-2] == c2 && t[x-3][y-3] == c2 && t[x-4][y-4] == c2 && t[x-5][y-5] == c) {
					t[x-1][y-1] = c;
					t[x-2][y-2] = c;
					t[x-3][y-3] = c;
					t[x-4][y-4] = c;
				}
			}
			if ((y==7 || y== 6) && (x==7 || x== 6)) {
				if (t[x-2][y-2] == c2 && t[x-3][y-3] == c2 && t[x-4][y-4] == c2 && t[x-5][y-5] == c2 && t[x-6][y-6] == c) {
					t[x-1][y-1] = c;
					t[x-2][y-2] = c;
					t[x-3][y-3] = c;
					t[x-4][y-4] = c;
					t[x-5][y-5] = c;
				}
			}
			if (y==7 && x==7) {
				if (t[x-2][y-2] == c2 && t[x-3][y-3] == c2 && t[x-4][y-4] == c2 && t[x-5][y-5] == c2 && t[x-6][y-6] == c2 && t[x-7][y-7] == c) {
					t[x-1][y-1] = c;
					t[x-2][y-2] = c;
					t[x-3][y-3] = c;
					t[x-4][y-4] = c;
					t[x-5][y-5] = c;
					t[x-6][y-6] = c;
				}
			}
		}
		if (x!= 7 && y != 7 && t[x][y]==c && t[x+1][y+1] == c2) {
			if (x!= 6 && y !=6 && t[x+2][y+2] == c)
				t[x+1][y+1] = c;
			if ((y==0 || y==1 || y==2 || y==3 || y==4) && (x==0 || x== 1 || x==2 || x==3 || x==4)) {
				if (t[x+2][y+2] == c2 && t[x+3][y+3] == c) {
					t[x+1][y+1] = c;
					t[x+2][y+2] = c;
				}
			}
			if ((y==0 || y==1 || y==2 || y==3) && (x==0 || x== 1 || x==2 || x==3)) {
				if (t[x+2][y+2] == c2 && t[x+3][y+3] == c2 && t[x+4][y+4] == c) {
					t[x+1][y+1] = c;
					t[x+2][y+2] = c;
					t[x+3][y+3] = c;
				}
			}
			if ((y==0 || y==1 || y==2) && (x==0 || x== 1 || x==2)) {
				if (t[x+2][y+2] == c2 && t[x+3][y+3] == c2 && t[x+4][y+4] == c2 && t[x+5][y+5] == c) {
					t[x+1][y+1] = c;
					t[x+2][y+2] = c;
					t[x+3][y+3] = c;
					t[x+4][y+4] = c;
				}
			}
			if ((y==0 || y==1 || y==2) && (x==0 || x== 1)) {
				if (t[x+2][y+2] == c2 && t[x+3][y+3] == c2 && t[x+4][y+4] == c2 && t[x+5][y+5] == c2 && t[x+6][y+6] == c) {
					t[x+1][y+1] = c;
					t[x+2][y+2] = c;
					t[x+3][y+3] = c;
					t[x+4][y+4] = c;
					t[x+5][y+5] = c;
				}
			}
			if (y==0 && x==0)
				if (t[x+2][y+2] == c2 && t[x+3][y+3] == c2 && t[x+4][y+4] == c2 && t[x+5][y+5] == c2 && t[x+6][y+6] == c2 && t[x+7][y+7] == c) {
					t[x+1][y+1] = c;
					t[x+2][y+2] = c;
					t[x+3][y+3] = c;
					t[x+4][y+4] = c;
					t[x+5][y+5] = c;
					t[x+6][y+6] = c;
				}
		}
	}
	/*
	 * affiche le nombre d'occurences des pions X et O
	 */
	public static void winner(char [][] t) {
		int nbC1 = 0, nbC2 = 0;
		for (int ligne=0; ligne < t.length; ligne++) {
			for (int colonne=0; colonne < t[0].length; colonne++) {
				if (t[ligne][colonne] == 'X')
					nbC1 = nbC1 + 1;
				else if (t[ligne][colonne] == 'O')
					nbC2 = nbC2 + 1;
			}
		}
		if (nbC1 > nbC2)
			System.out.println("Joueur n°1 WINS !!!"
					+ "\n\tX : "+nbC1
					+ "\n\tO : "+nbC2);
		else if (nbC1 < nbC2)
			System.out.println("Joueur n°2 WINS !!!"
					+ "\n\tX : "+nbC1
					+ "\n\tO : "+nbC2);
		else
			System.out.println("MATCH NUL !!!"
					+ "\n\tX : "+nbC1
					+ "\n\tO : "+nbC2);
	}
}
