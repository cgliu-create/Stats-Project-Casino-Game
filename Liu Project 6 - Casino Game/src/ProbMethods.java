// Christopher Liu
// P8 AP Compsci
// this is the probability methods that are the basis for steps in the game
public class ProbMethods {
	// STEP ONE
	/*
	Roll two dice
	If roll a double then lose
	Else continue to step 2
	 */
	public int rolldice() {
		// random integer (1,6)
		int roll = (int)(Math.random()*6+1);
		return roll;	
	}
	public boolean roll2ncheck(){
		int a = this.rolldice();
		int b = this.rolldice();
		if( a == b)
			return false;
		// false = lose 
		else {
			return true;
		}
	}
	// STEP TWO
	/*
	Draw one card from a randomly shuffled deck of cards
	If draw odd # then lose
	If draw even # then win 2:1 pay out
	If draw face/ace then continue to step 3
	 */
	public int drawacard() {
		// random integer (1,52)
		int card = (int)(Math.random()*52+1);
		return card;	
	}
	/*
	0 - 12: 
	 0  1  2  3  4  5  6  7  8  9  10  11 12
	[A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	0 - 3, 4:	[s, c, d, h]
	x/13 = suit
	x%13 = face/value
	 */
	public int checkcard(int draw){
		draw = draw - 1;
		int value = draw%13;
		if(value == 0 || value == 10 || value == 11 || value == 12) {
			return 1;	// faces and ace
		}
		else if(value%2 == 1) {
			return 2;	// even (spot represents)
		}
		else {
			return 3;	// odd
		}
	}
	public int drawcardncheck(){
		// 1 = continue, 2 = win x2, 3 = lose 
		int draw = this.drawacard() - 1; 
		int value = draw%13;
		if(value == 0 || value == 10 || value == 11 || value == 12) {
			return 1;	// faces and ace
		}
		else if(value%2 == 1) {
			return 2;	// even (spot represents)
		}
		else {
			return 3;	// odd
		}
	}
	// STEP THREE
	/*
	Spin a ten sided spinner
	If #side is a multiple of 3 then continue to step 4
	Else lose
	 */
	public int spinner() {
		// random integer (1,10)
		int spin = (int)(Math.random()*10+1);
		return spin;	
	}
	public boolean checkspinner(int spin) {
		if(spin == 3 || spin == 6 || spin == 9) {
			return true;
		}
		// false = lose 
		return false;
	}
	public boolean spinnerncheck() {
		int spin = this.spinner();
		if(spin == 3 || spin == 6 || spin == 9) {
			return true;
		}
		// false = lose 
		return false;
	}
	// STEP FOUR
	/*
	Coin 1 flip, Coin 2 flip, Coin 3 flip
	If two heads then win 8:1 pay out
	else win 2:1 pay out
	 */
	public int coinflip() {
		// random integer (1,2)
		int coin = (int)(Math.random()*2+1);
		return coin;	
		// 1 = heads and 2 = tails
	}
	public boolean coinflip3ncheck() {
		int a = this.coinflip();
		int b = this.coinflip();
		int c = this.coinflip();
		if(( a == 1 && b == 1 && c == 2) ||
		   ( a == 2 && b == 1 && c == 1) ||
		   ( a == 1 && b == 2 && c == 1)) {
			return true;
			// true = x8 win
		}
		return false;
		// true = x2 win
	}
	public static void main(String [] args) {
		ProbMethods test = new ProbMethods();
		// testing every step
		// rolling not a double
		int wina = 0;
		int triala = 0;
		int numa = 100000;
		for(int x = 0; x < numa; x++) {

			if(test.roll2ncheck()) {
				wina++;
			}
			triala++;
		}
		System.out.println("rolling not a double");
		System.out.println(wina/(double)triala);
		System.out.println(30.0/36);
		// drawing a face or ace card
		int winb = 0;
		int trialb = 0;
		int numb = 100000;
		for(int x = 0; x < numb; x++) {
	
			if(test.drawcardncheck()==1) {
				winb++;
			}
			trialb++;
		}
		System.out.println("drawing a face or ace card");
		System.out.println(winb/(double)trialb);
		System.out.println(16.0/52);
		// drawing a odd card
		int winc = 0;
		int trialc = 0;
		int numc = 1000000;
		for(int x = 0; x < numc; x++) {
				
			if(test.drawcardncheck()==3) {
				winc++;
			}
			trialc++;
		}
		System.out.println("drawing a odd card");
		System.out.println(winc/(double)trialc);
		System.out.println(16.0/52);
		// drawing a even card
		int wind = 0;
		int triald = 0;
		int numd = 1000000;
		for(int x = 0; x < numd; x++) {
						
			if(test.drawcardncheck()==2) {
				wind++;
			}
			triald++;
		}
		System.out.println("drawing a even card");
		System.out.println(wind/(double)triald);
		System.out.println(20.0/52);	
		// spin 3,6, or 9
		int wine = 0;
		int triale = 0;
		int nume = 1000000;
		for(int x = 0; x < nume; x++) {
							
			if(test.spinnerncheck()) {
				wine++;
			}
			triale++;
		}
		System.out.println("spin 3,6, or 9");
		System.out.println(wine/(double)triale);
		System.out.println(3.0/10);	
		// spin other 1-10
		int winf = 0;
		int trialf = 0;
		int numf = 100000;
		for(int x = 0; x < numf; x++) {
							
			if(!(test.spinnerncheck())) {
				winf++;
			}
			trialf++;
		}
		System.out.println("spin other 1-10");
		System.out.println(winf/(double)trialf);
		System.out.println(7.0/10);	
		// flip 3 coins and get only two heads 
		int wing = 0;
		int trialg = 0;
		int numg = 100000;
		for(int x = 0; x < numg; x++) {
							
			if(test.coinflip3ncheck()) {
				wing++;
			}
			trialg++;
		}
		System.out.println("flip 3 coins and only two heads");
		System.out.println(wing/(double)trialg);
		System.out.println(3.0/8);	
		// flip 3 coins and get other combination
		int winh = 0;
		int trialh = 0;
		int numh = 100000;
		for(int x = 0; x < numh; x++) {
							
			if(!(test.coinflip3ncheck())) {
				winh++;
			}
			trialh++;
		}
		System.out.println("flip 3 coins and get other combination");	
		System.out.println(winh/(double)trialh);
		System.out.println(5.0/8);	
		System.out.println();	
		System.out.println((30.0/36)*(20.0/52)+(30.0/36)*(16.0/52)*(3.0/10));
		System.out.println((wina/(double)triala)*(wind/(double)triald)+
				(wina/(double)triala)*(winb/(double)trialb)*(wine/(double)triale));
		
		// ok everything here checks out
	}	

}
//fin
