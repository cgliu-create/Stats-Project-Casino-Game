import javax.swing.JLabel;

public class FastMode {
	// assume bet = 10
	private int totalamountbet;
	private int totaloutcome;
	private int outcome;
	private int trial = 1;
	private int numwin;
	private double prob;
	private double expected;
	private String report;
	// these methods record win/loss to console
	public void recordwin(int rate) {
		this.numwin++;
		this.outcome = 10 * rate - 10; // gets 70 when winning 80
		this.totalamountbet = this.totalamountbet + 10;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / (double)this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		this.report = " Trial:" + this.trial +
					"\n Bet:" + 10 +
					"\n Outcome for house:" + (-this.outcome) +
					"\n Win Rate for player:" + this.prob +
					"\n Expected Bread for house per $10 bet:" + (-this.expected) + "\n";
		System.out.println(report);	
	}
	public void recordloss() {
		this.outcome = 0 - 10;
		this.totalamountbet = this.totalamountbet + 10;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / (double)this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		this.report = " Trial:" + this.trial +
				"\n Bet:" + 10 +
				"\n Outcome for house:" + (-this.outcome) +
				"\n Win Rate for player:" + this.prob +
				"\n Expected Bread for house per $10 bet:" + (-this.expected) + "\n";
		System.out.println(report);	
	}
	// this method checks the status of the game based off the results of chance methods
	// this is where the rules of the game are implemented
	public int runfast(boolean play) {
		while(play == true) {
		ProbMethods runf = new ProbMethods();
		// STEP ONE
			boolean sone = runf.roll2ncheck();
			if(sone) {
				// continue
			}
			if(!(sone)) {
				//lose
				this.recordloss();
				return this.trial + 1;
			}
		// STEP TWO
			int stwo = runf.drawcardncheck();
			if(stwo == 1) {
				// continue
			}
			if(stwo == 2) {
				//win2
				this.recordwin(2);
				return this.trial + 1;
			}
			if(stwo == 3) {
				//lose
				this.recordloss();
				return this.trial + 1;
			}
		// STEP THREE
			boolean sthree = runf.spinnerncheck();
			if(!(sthree)){
				//lose
				this.recordloss();
				return this.trial + 1;
			}
		// STEP FOUR
			boolean sfour = runf.coinflip3ncheck();
			if(sfour) {
				//win8
				this.recordwin(8);
				return this.trial + 1;
			}
			if(!(sfour)) {
				//win2
				this.recordwin(2);
				return this.trial + 1;
			}
		}
		return this.trial + 1;
	}
	
	// this method tests multiple games
	public void fastmodenum(int num) {
		FastMode run = new FastMode();
		for(int y = 0; y < num; y++) {
			run.trial = run.runfast(true);
		}
	}
	public void fastmodenum(int num, JLabel label) {
		FastMode run = new FastMode();
		for(int y = 0; y < num; y++) {
			run.trial = run.runfast(true);
		}
		String strprob = String.format("%.4f", run.prob);
		String strexpected = String.format("%.4f", (-run.expected));
		label.setText("Win Rate:" + strprob +
		"Expected Bread for house per $10 bet:" + strexpected);
	}
	//testing
	public static void main(String[] args) {
		FastMode test = new FastMode();
		int num = 1000000;
		for(int x = 0; x < num; x++) {
			test.trial = test.runfast(true);
		}
}
}
