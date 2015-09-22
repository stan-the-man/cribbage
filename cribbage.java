import java.util.*;
import java.nio.*;
import java.io.*;

public class cribbage { 
	public static void main(String Args[]){
		Scanner in = new Scanner(System.in);
		String p1name, p2name;
		int set_crib;
		boolean p1crib;

		System.out.println("Player one name?");
		p1name = in.next();
		System.out.println("Player two name?");
		p2name = in.next();

		System.out.println("Who's crib (1/2)?");
		set_crib = in.nextInt();
		if(set_crib == 1) 
			p1crib = true;
		else
			p1crib = false;
					
		Player p1 = new Player(p1name, p1crib);
		Player p2 = new Player(p2name, !p1crib);


		print_tha_roolz();	
		while(true) {
		crib_manager(p1, p2);
		//point_manager(p1, p2, in);
		turn(p1, p2, in);
		scoreboard(p1, p2);
		}


	}
	public static class Player {
		private int score;
		private String name;
		private boolean crib = false;
		private int victory = 120;
		
		// constructor
		public Player(String _name, boolean _crib){
			this.name = _name;
			this.crib = _crib;
		}

		// setters
		public void set_score(int points){ this.score += points; }
		public void set_crib() { this.crib = !this.crib; }

		// getters
		public int get_score() { return this.score; }
		public boolean get_crib() { return this.crib; } 
		public String get_name() { return this.name; }
		
		public void winCheck(Player p) {
			if(this.score > victory) {
				System.out.println(this.name + " Wins!!!");
				scoreboard(this, p);
				System.exit(0);
			}
		}
	}
	public static void turn(Player p1, Player p2, Scanner in) {
		int choice, points;
		System.out.println("Menu: ");
		System.out.println("1 -- " + p1.get_name() + " scored.");
		System.out.println("2 -- " + p2.get_name() + " scored.");
		System.out.println("3 -- End turn");
		
		while(true) {
		System.out.println("What happened?");
		choice = in.nextInt();
			switch(choice) {
				case 1: System.out.print("Scored: ");
						points = in.nextInt();
						p1.set_score(points);
						p1.winCheck(p2);
						break;	
				case 2: System.out.print("Scored: ");
						points = in.nextInt();
						p2.set_score(points);
						p2.winCheck(p1);
						break;
				case 3: scoreboard(p1, p2);
						return;
			}
		}
	}


	public static void print_tha_roolz(){
		System.out.println("-----The Rules of Cribbage-----");
		System.out.println("2 pts -- Pair");
		System.out.println("2 pts -- Cards add to 15");
		System.out.println("x pts -- x card straight");
		System.out.println("4 pts -- 4 card flush (or 4 in crib and turn card)");
		System.out.println("6 pts -- tripples");
		System.out.println("8 pts -- 3 card straight + pair with 1 of straight cards");
		System.out.println("12 pts -- quads");
		System.out.println("------------------------------\n\n");


	}

	public static void scoreboard(Player p1, Player p2) {
		String p1Score = p1.get_name() + " " + Integer.toString(p1.get_score());
		String p2Score = p2.get_name() + " " + Integer.toString(p2.get_score());
		System.out.println("\n" + p1Score + " to " + p2Score + "\n");
	}
	public static void crib_manager(Player p1, Player p2) {
		String crib;
		if(p1.get_crib())
			crib = p1.get_name();
		else
			crib = p2.get_name();
		System.out.println(crib + "'s crib!");
		p1.set_crib(); p2.get_crib();
	}
	public static void point_manager(Player p1, Player p2, Scanner in) {
		// crib token currently ahead by 1 turn.
		// therefore whoever does not have token has crib.

		int points;
		if(p1.get_crib()){
			System.out.println(p1.get_name() + " Scored: ");
			points = in.nextInt();
			p1.set_score(points);
			p1.winCheck(p2);
			
			System.out.println(p2.get_name() + " Scored: ");
			points = in.nextInt();
			p2.set_score(points);
			p2.winCheck(p1);
		}
		else {
			System.out.println(p2.get_name() + " Scored: ");
			points = in.nextInt();
			p2.set_score(points);
			p2.winCheck(p1);
			
			System.out.println(p1.get_name() + " Scored: ");
			points = in.nextInt();
			p1.set_score(points);
			p1.winCheck(p2);
		}
	}		
}
