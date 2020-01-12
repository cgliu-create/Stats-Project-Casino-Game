import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MinimumViableProduct {
	
	private int totalamountbet;
	private int totaloutcome;
	private int amountbet;
	private int outcome;
	private int trial;
	private int numwin;
	private double prob;
	private double expected;
	private String report;
	
	public void recordwin(int rate) {
		this.numwin++;
		this.outcome = this.amountbet * rate - 10; // gets 70 when winning 80
		this.totalamountbet = this.totalamountbet + this.amountbet;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		this.report = " Trial:" + this.trial +
					"\n Bet:" + this.amountbet +
					"\n Outcome:" + this.outcome +
					"\n Win Percentage:" + this.prob +
					"\n Expected Bread for house per $10 bet:" + (-this.expected) + "\n";
		System.out.println(report);	
	}
	public void recordloss() {
		this.outcome = 0 - this.amountbet;
		this.totalamountbet = this.totalamountbet + this.amountbet;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		this.report = " Trial:" + this.trial +
				"\n Bet:" + this.amountbet +
				"\n Outcome:" + this.outcome +
				"\n Win Percentage:" + this.prob +
				"\n Expected Bread for house per $10 bet:" + (-this.expected) + "\n";
		System.out.println(report);	
	}
	
	public boolean checkvalidbet(int bet){
		if (bet == 10 || bet == 20 || bet == 30 || bet == 40 || bet == 50) {
			return true;
		}
		return false;
	}
	
	public void runagame(JFrame mvp, boolean play) {
		while(play == true) {
			this.trial++; // keeps track of trial number
			ProbMethods runop = new ProbMethods();
			do {
			String a = JOptionPane.showInputDialog(mvp, "Start by making a bet."
					+ "\n You can only bet increments of 10 and no more than 50."
					+ "\n Enter bet (no decimals):");
			this.amountbet = Integer.parseInt(a);
			if(!(this.checkvalidbet(this.amountbet))) {
				JOptionPane.showMessageDialog(mvp, "Sorry, this is not a valid bet");
			}
			}while(!(this.checkvalidbet(this.amountbet)));
		// STEP ONE 
			JOptionPane.showMessageDialog(mvp, "First, we will roll two dice."
					+ "\n If you roll other than double, then you move on to the next step."
					+ "\n If you roll a double, then you lose.");
			boolean opa = true;
			int x = runop.rolldice();
			int y = runop.rolldice();
			if (x == y) {
				opa = false;
			}
			String result = "";
			if(opa) 
				result = " You Continue...";
			if(!(opa) )
				result = " You Lose.";
			JOptionPane.showMessageDialog(mvp, "You got a "+ x + " and a "+ y + "." 
					+ "\n" + result);
			if(!(opa) ) {
				break; // continue playing?
			}
		// STEP TWO
			JOptionPane.showMessageDialog(mvp, "Second, we will now draw a card from a shuffled deck."
					+"\n If even then you win 2X your bet."
					+"\n If odd then you lose."
					+"\n If face or ace then you move on to the next step."); 
			int z = runop.drawacard() - 1;
		//suit	0 - 3:	[s, c, d, h]
			int s = z/13;
			String suit = "";
			if(s == 0)
				suit = "Spades";
			if(s == 1)
				suit = "Clubs";
			if(s == 2)
				suit = "Diamonds";
			if(s == 3)
				suit = "Hearts";
		//value 0 - 12: [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
			int v = z%13;
			String value = v+1 + ""; //spot 1= 2
			if(v == 0)
				value = "Ace";
			if(v == 10)
				value = "Jack";
			if(v == 11)
				value = "Queen";
			if(v == 12)
				value = "King";
		//outcome
			int outcomeb = 3; 
			if(v%2 == 1)
				outcomeb = 2;	// even (represents)
			if(v%2 == 0)
				outcomeb = 3;	// odd
			if(v == 0 || v == 10 || v == 11 || v == 12) 
				outcomeb = 1;	// faces and ace
		// 1 = continue, 2 = win x2, 3 = lose
			if (outcomeb == 3) {
				JOptionPane.showMessageDialog(mvp, "You got a "+ value + " of "+ suit + "." 
						+"\n You lose.");
				this.recordloss();// lose
				break; // continue playing?
			}
			if (outcomeb == 2) {
				JOptionPane.showMessageDialog(mvp, "You got a "+ value + " of "+ suit + "." 
						+"\n You win 2X your bet!");
				this.recordwin(2); // win
				break; // continue playing?
			}
			if (outcomeb == 1) {
			JOptionPane.showMessageDialog(mvp, "You got a "+ value + " of "+ suit + "." 
					+"\n You Continue...");
			}
		// STEP THREE
			JOptionPane.showMessageDialog(mvp, "Third, we will now spin a ten sided spinner(1-10)"
					+"\n If the side number is a multiple of 3, then you move on to the next step."
					+"\n If not, then you lose."); 
			int spin = runop.spinner();
			boolean opc = true;
			if(spin == 3 || spin == 6 || spin == 9) {
				opc = false;
			}
			if (opc) {
				JOptionPane.showMessageDialog(mvp, "You got a "+ spin +"." 
						+"\n You Lose.");
				this.recordloss(); // lose
				break; // continue playing?
			}
			JOptionPane.showMessageDialog(mvp, "You got a "+ spin +"." 
					+"\n You Continue...");
		// STEP FOUR
			JOptionPane.showMessageDialog(mvp, "Finally, we will now flip 3 coins."
					+"\n If there is only two heads then you win 8X your bet."
					+"\n If not, then you win 2X your bet."); 
			boolean opd = false;
			int coina = runop.coinflip();
			int coinb = runop.coinflip();
			int coinc = runop.coinflip();
			if(( coina == 1 && coinb == 1 && coinc == 2) ||
			   ( coina == 2 && coinb == 1 && coinc == 1) ||
			   ( coina == 1 && coinb == 2 && coinc == 1)) {
				opd = true;
			}
			String facea = "H";
			if(coina == 2)
				facea = "T"	;
			String faceb = "H";
			if(coinb == 2)
				faceb = "T"	;
			String facec = "H";
			if(coinc == 2)
				facec = "T"	;
			
			if(opd) {
				JOptionPane.showMessageDialog(mvp, "You got a "+ facea + ", a " + faceb + ", and a " + facec + "." 
					+"\n You win 8X your bet!");
				this.recordwin(8); // win x8
				break; // continue playing?
			}
			if(!(opd)) {
			JOptionPane.showMessageDialog(mvp, "You got a "+ facea + ", a" + faceb + ", and a" + facec + "." 
					+"\n You win 2X your bet!");
				this.recordwin(2); // win x2
				break; // continue playing?
			}
		}
	}
	public static void main(String[] args) {
		
	MinimumViableProduct run = new  MinimumViableProduct();
	JFrame frame = new JFrame("Input Dialog");
	boolean play = true;
	do {
	run.runagame(frame, play);
	String ex = JOptionPane.showInputDialog(frame, "Do yo want to continue playing? y/n");
	if(ex.equalsIgnoreCase("no")||ex.equalsIgnoreCase("n")) {
		play = false; }
	}while (play == true);
	
}	
}
