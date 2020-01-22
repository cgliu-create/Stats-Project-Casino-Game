// Christopher Liu
// P8 AP Compsci
// this is the runner file that sets up the gui
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
	// theoretical probability = 0.397
	// theoretical bread = 0.323
public class Runner {
	// created using WindowBuilder wizard
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Runner window = new Runner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public Runner() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	// SlowMode	
		SlowMode run = new SlowMode();
		boolean playit = true;
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
	// Dice labels
		JLabel lbldiceA = new JLabel("diceA");
		lbldiceA.setBounds(656, 131, 97, 97);
		frame.getContentPane().add(lbldiceA);
		
		JLabel lbldiceB = new JLabel("diceB");
		lbldiceB.setBounds(765, 131, 97, 97);
		frame.getContentPane().add(lbldiceB);
		
	// Card labels
		JLabel lbldeckA = new JLabel("deckA");
		lbldeckA.setBounds(656, 240, 97, 127);
		frame.getContentPane().add(lbldeckA);
		
		JLabel lbldeckB = new JLabel("deckB");
		lbldeckB.setBounds(765, 240, 97, 127);
		frame.getContentPane().add(lbldeckB);
	
	//Spinner label
		JLabel lblSpin = new JLabel("Spin");
		lblSpin.setBounds(656, 380, 206, 147);
		frame.getContentPane().add(lblSpin);
		
	// Coins labels
		JLabel lblcoinB = new JLabel("coinB");
		lblcoinB.setBounds(736, 539, 77, 77);
		frame.getContentPane().add(lblcoinB);
		
		JLabel lblcoinA = new JLabel("coinA");
		lblcoinA.setBounds(656, 539, 77, 77);
		frame.getContentPane().add(lblcoinA);
		
		JLabel lblcoinC = new JLabel("coinC");
		lblcoinC.setBounds(816, 539, 77, 77);
		frame.getContentPane().add(lblcoinC);
	
	// Sets default image pieces
		try {
			/* this explains BufferedImage and ImageIcon, adding images
			http://www.java2s.com/Tutorials/Java/Graphics_How_to/Image/Display_Image_with_Swing_GUI.htm
			*/
			BufferedImage one = ImageIO.read(new File("one.jpg"));
			ImageIcon o = new ImageIcon(one);
			Image imgo = o.getImage();
			Image newimg = imgo.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newo = new ImageIcon(newimg);
			lbldiceA.setIcon(newo);
			lbldiceB.setIcon(newo);
			BufferedImage card = ImageIO.read(new File("card.jpg"));
			ImageIcon c = new ImageIcon(card);
			Image imgc = c.getImage();
			Image newimgc = imgc.getScaledInstance(97, 127,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newc = new ImageIcon(newimgc);
			lbldeckA.setIcon(newc);
			BufferedImage cardempty = ImageIO.read(new File("cardempty.jpg"));
			ImageIcon ce = new ImageIcon(cardempty);
			Image imgce = ce.getImage();
			Image newimgce = imgce.getScaledInstance(97, 127,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newce = new ImageIcon(newimgce);
			lbldeckB.setIcon(newce);
			BufferedImage spinone = ImageIO.read(new File("spin1.jpg"));
			ImageIcon spino = new ImageIcon(spinone);
			Image imgspin = spino.getImage();
			Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newspin = new ImageIcon(newimgspin);
			lblSpin.setIcon(newspin);
			BufferedImage heads = ImageIO.read(new File("heads.jpg"));
			ImageIcon coinh = new ImageIcon(heads);
			Image imgcoinh = coinh.getImage();
			Image newimgcoinh = imgcoinh.getScaledInstance(77, 77,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newcoinh = new ImageIcon(newimgcoinh);
			lblcoinB.setIcon(newcoinh);
			lblcoinA.setIcon(newcoinh);
			lblcoinC.setIcon(newcoinh);
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	// Display for what the player needs to do
		JPanel panel = new JPanel(); // creates a box around label
		panel.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(346, 79, 530, 40);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHello = new JLabel("Welcome to the Road to El Dorado! To start, hit play!");
		lblHello.setBounds(6, 18, 518, 16); // sets up default message for when application is run for the first time
		panel.add(lblHello);
	
	// Display for bet and outcome
		JPanel panel_1 = new JPanel(); // creates box for results labels
		panel_1.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(388, 435, 142, 73);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
	
		JLabel lblOutcome = new JLabel("Outcome:0"); // sets up default message for Outcome
		lblOutcome.setBounds(6, 41, 130, 26);
		panel_1.add(lblOutcome);

		JLabel lblBet = new JLabel("Bet:0"); // sets up default message for Bet
		lblBet.setBounds(6, 18, 130, 26);
		panel_1.add(lblBet);
	
	// Buttons for player "input"
		JButton btnRoll = new JButton("roll");
		btnRoll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// can only roll if a bet was made + a roll hasn't already been made + game is not finished
				if(!(lblOutcome.getText().equals("Bet:0")) && run.getOutcomeofroll() == 0 && run.getAmountbet() != 0 && lblOutcome.getText().equals("Outcome:0")) {
					int x = run.rollthedice(lbldiceA); // dice A
					int y = run.rollthedice(lbldiceB); // dice B
					boolean z = run.checkrollthedice(x, y); // outcome value based of dice A and B
					int outcome = 0;
					if (z) {
						outcome = 1;
					}
					if (!(z)) {
						outcome = 2;
					}
					// outcome value changes gui label in "console"
					if (outcome == 1) {
						lblHello.setText("Now, draw a card");
					}
					if (outcome == 2) {
						lblOutcome.setText("Outcome:-"+run.getAmountbet());
						lblHello.setText("Sorry, you lost. Hit play to play again");
					}
					// updates variables in slowmode object
					run.setOutcomeofroll(outcome);
					// checks the situation of the game and updates trial number if a game is finished
					run.setTrial(run.checkrunslow(playit));
				}
			}
		});
		btnRoll.setBounds(584, 628, 83, 29);
		frame.getContentPane().add(btnRoll);
		
		JButton btnDraw = new JButton("draw");
		btnDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// can only draw if a roll was made + a draw hasn't already been made + game is not finished
				if(run.getOutcomeofdraw() == 0 && run.getOutcomeofroll() != 0 && lblOutcome.getText().equals("Outcome:0")) {
					int outcome = run.drawthecard(lbldeckB);
					// outcome value changes gui label in "console"
					if (outcome == 1) {
						lblHello.setText("Now, spin the wheel");
					}
					if (outcome == 2) {
						lblOutcome.setText("Outcome:"+ 2*run.getAmountbet());
						lblHello.setText("Congrats, you win. Hit play to play again");
						run.setWin();
					}
					if (outcome == 3) {
						lblOutcome.setText("Outcome:-"+run.getAmountbet());
						lblHello.setText("Sorry, you lost. Hit play to play again");
					}
					// updates variables in slowmode object
					run.setOutcomeofdraw(outcome);
					// checks the situation of the game and updates trial number if a game is finished
					run.setTrial(run.checkrunslow(playit));
				}
	
			}
		});
		btnDraw.setBounds(660, 628, 83, 29);
		frame.getContentPane().add(btnDraw);
			
		JButton btnSpin = new JButton("spin");
		btnSpin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// can only spin if a draw was made + a spin hasn't already been made + game is not finished
				if(run.getOutcomeofspin() == 0  && run.getOutcomeofdraw() != 0 && lblOutcome.getText().equals("Outcome:0")) {
					boolean z = run.spinthewheel(lblSpin);
					// outcome value changes gui label in "console"
					int outcome = 0;
					if (z) {
						outcome = 1;
					}
					if (!(z)) {
						outcome = 2;
					}
					if (outcome == 1) {
						lblHello.setText("Now, flip the coins");
					}
					if (outcome == 2) {
						lblOutcome.setText("Outcome:-"+run.getAmountbet());
						lblHello.setText("Sorry, you lost. Hit play to play again");
					}
					// updates variables in slowmode object
					run.setOutcomeofspin(outcome);
					// checks the situation of the game and updates trial number if a game is finished
					run.setTrial(run.checkrunslow(playit));
				}
			}
		});
		btnSpin.setBounds(736, 628, 83, 29);
		frame.getContentPane().add(btnSpin);		
				
		JButton btnFlip = new JButton("flip");
		btnFlip.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// can only flip if a spin was made + a flip hasn't already been made + game is not finished
				if(run.getOutcomeofflip() == 0 && run.getOutcomeofspin() != 0 && lblOutcome.getText().equals("Outcome:0")) {
					int a = run.flipthecoin(lblcoinA);
					int b = run.flipthecoin(lblcoinB);
					int c = run.flipthecoin(lblcoinC);
					boolean z = run.checkflipthecoin(a, b, c);
					// outcome value changes gui label in "console"
					int outcome = 0;
					if (z) {
						outcome = 1;
					}
					if (!(z)) {
						outcome = 2;
					}
					if (outcome == 1) {
						lblOutcome.setText("Outcome:"+ 8*run.getAmountbet());
						lblHello.setText("Congrats, you win. Hit play to play again");
						run.setWin();
					}
					if (outcome == 2) {
						lblOutcome.setText("Outcome:"+ 2*run.getAmountbet());
						lblHello.setText("Congrats, you win. Hit play to play again");
						run.setWin();
					}
					// updates variables in slowmode object
					run.setOutcomeofflip(outcome);
					// checks the situation of the game and updates trial number if a game is finished
					run.setTrial(run.checkrunslow(playit));
				}
			}
		});
		btnFlip.setBounds(810, 628, 83, 29);
		frame.getContentPane().add(btnFlip);
		
	// Buttons for making bets
		JButton btn10 = new JButton("$10");
		btn10.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//run.resetgame();		//uncomment to disable changing bet mid-game
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				/* this is where i got the sound files
				leshylabs.com/apps/sfMaker/
				*/
				lblBet.setText("Bet:10");
				// updates variable in slowmode object
				run.setAmountbet(10);
				// changes gui labels
				if(run.getOutcomeofroll() == 0)
					lblHello.setText("First, roll the dice");
			}
		});
		btn10.setBounds(417, 231, 83, 29);
		frame.getContentPane().add(btn10);
		
		JButton btn20 = new JButton("$20");
		btn20.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//run.resetgame();
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				lblBet.setText("Bet:20");
				// updates variable in slowmode object
				run.setAmountbet(20);
				// changes gui labels
				if(run.getOutcomeofroll() == 0)
					lblHello.setText("First, roll the dice");
			}
		});
		btn20.setBounds(417, 272, 83, 29);
		frame.getContentPane().add(btn20);
		
		JButton btn30 = new JButton("$30");
		btn30.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//run.resetgame();
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				lblBet.setText("Bet:30");
				// updates variable in slowmode object
				run.setAmountbet(30);
				// changes gui labels
				if(run.getOutcomeofroll() == 0)
					lblHello.setText("First, roll the dice");
			}
		});
		btn30.setBounds(417, 313, 83, 29);
		frame.getContentPane().add(btn30);
		
		JButton btn40 = new JButton("$40");
		btn40.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//run.resetgame();
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				lblBet.setText("Bet:40");
				// updates variable in slowmode object
				run.setAmountbet(40);
				// changes gui labels
				if(run.getOutcomeofroll() == 0)
					lblHello.setText("First, roll the dice");
			}
		});
		btn40.setBounds(417, 355, 83, 29);
		frame.getContentPane().add(btn40);
		
		JButton btn50 = new JButton("$50");
		btn50.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//run.resetgame();
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				lblBet.setText("Bet:50");
				// updates variable in slowmode object
				run.setAmountbet(50);
				// changes gui labels
				if(run.getOutcomeofroll() == 0)
					lblHello.setText("First, roll the dice");
			}
		});
		btn50.setBounds(417, 396, 83, 29);
		frame.getContentPane().add(btn50);
	// PLAY button
		JButton btnPLAY = new JButton("PLAY");
		btnPLAY.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				run.resetgame();
				lblHello.setText("Now, select a bet");
				lblBet.setText("Bet:0");
				lblOutcome.setText("Outcome:0");
				// resets gui labels
				BufferedImage cardempty;
				try {
				// makes "cards" look like default so it looks "shuffled"
					cardempty = ImageIO.read(new File("cardempty.jpg"));
					ImageIcon ce = new ImageIcon(cardempty);
					Image imgce = ce.getImage();
					Image newimgce = imgce.getScaledInstance(97, 127,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newce = new ImageIcon(newimgce);
					lbldeckB.setIcon(newce);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPLAY.setBounds(398, 520, 127, 29);
		frame.getContentPane().add(btnPLAY);
		
	// TEST button
		JButton btnTest = new JButton("TEST");
		btnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//step sound
				SlowMode sound = new SlowMode();
				sound.playsound("step.wav");
				FastMode test = new FastMode();
				// runs fast mode a million times ... heh
				test.fastmodenum(1000000, lblHello);
			}
		});
		btnTest.setBounds(398, 551, 127, 29);
		frame.getContentPane().add(btnTest);
		
	// Background label
		try {
			BufferedImage back = ImageIO.read(new File("instructions.jpg"));
			ImageIcon b = new ImageIcon(back);
/* source for background image
http://www.yorikoito.com/home/background-painting/the-road-to-el-dorado/
*/
			JLabel background = new JLabel();
			background.setBounds(6, 6, 900, 650);
			frame.getContentPane().add(background);
			background.setIcon(b);
			
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
//fin