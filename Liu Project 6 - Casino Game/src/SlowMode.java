import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SlowMode {

	private int totalamountbet;
	private int totaloutcome;
	private int amountbet;
	private int outcome;
	private int trial = 1;
	private int numwin;
	private double prob;
	private double expected;
	private String report;
// all the outcomes that are recorded by Runner
	private int outcomeofroll;
	private int outcomeofdraw;	
	private int outcomeofspin;
	private int outcomeofflip;
	
// set value methods from outside this class
	public void setTrial(int curTrial) {
		this.trial = curTrial;
	}
	public void setWin() {
		this.numwin = this.numwin+1;
	}
	// resets outcomes per game, play button
	public void resetgame() {
		this.outcomeofroll = 0;
		this.outcomeofdraw = 0;
		this.outcomeofspin = 0;
		this.outcomeofflip = 0;
	}
	public void setOutcomeofroll(int outcomeofroll) {
		this.outcomeofroll = outcomeofroll;
	}
	public void setOutcomeofdraw(int outcomeofdraw) {
		this.outcomeofdraw = outcomeofdraw;
	}
	public void setOutcomeofspin(int outcomeofspin) {
		this.outcomeofspin = outcomeofspin;
	}
	public void setOutcomeofflip(int outcomeofflip) {
		this.outcomeofflip = outcomeofflip;
	}
	public void setAmountbet(int amountbet) {
		this.amountbet = amountbet;
	}
// get value methods from outside this class
	public int getOutcomeofroll() {
		return this.outcomeofroll;
	}
	public int getOutcomeofdraw() {
		return this.outcomeofdraw;
	}
	public int getOutcomeofspin() {
		return this.outcomeofspin;
	}
	public int getOutcomeofflip() {
		return this.outcomeofflip;
	}
	public int getAmountbet() {
		return this.amountbet;
	}
// this method checks the status of the game based off the outcome values
// this is where the rules of the game are implemented
	public int checkrunslow(boolean play) {
		// win, lose, step sounds
		while(play == true) {
		// STEP ONE
			int Sone = this.outcomeofroll;
			if(Sone == 0) {
				return this.trial;  // no answer
			}
			if(Sone == 1) {
				this.playsound("step.wav");	// you continue, step sound
			}
			if(Sone == 2) {
				this.playsound("lose.wav");	
				this.recordloss();	// you lose, lose sound
				return this.trial + 1;
			}
		// STEP TWO
			int Stwo = this.outcomeofdraw;
			if(Stwo == 0) {
				return this.trial;  // no answer
			}
			if(Stwo == 1) {
				this.playsound("step.wav");	// you continue, step sound
			}
			if(Stwo == 2) {
				this.playsound("win.wav");	
				this.recordwin(2);	// you win2, win sound
				return this.trial + 1;
			}
			if(Stwo == 3) {
				this.playsound("lose.wav");	
				this.recordloss();	// you lose, lose sound
				return this.trial + 1;
			}
		// STEP THREE
			int Sthree = this.outcomeofspin;
			if(Sthree == 0) {
				return this.trial;  // no answer
			}
			if(Sthree == 1) {
				this.playsound("step.wav");	// you continue, step sound
			}
			if(Sthree == 2) {
				this.playsound("lose.wav");	
				this.recordloss();	// you lose, lose sound
				return this.trial + 1;
			}
		// STEP FOUR
			int Sfour = this.outcomeofflip;
			if(Sfour == 0) {
				return this.trial;  // no answer
			}
			if(Sfour == 1) {		// you win8, win sound
				this.playsound("win.wav");
				this.recordwin(8);
				return this.trial + 1;
			}
			if(Sfour == 2) {		// you win2, win sound
				this.playsound("win.wav");
				this.recordwin(2);
				return this.trial + 1;
			}
		}
		return this.trial + 1;
	}
// these methods record win/loss to console
	public void recordwin(int rate) {
		this.outcome = this.amountbet * rate - this.amountbet; // gets 70 when winning 80 betting 10
		this.totalamountbet = this.totalamountbet + this.amountbet;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / (double)this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		String bread = String.format("%.2f", (-this.expected));
		this.report = " Trial:" + this.trial +
					"\n Bet:" + this.amountbet +
					"\n Outcome for house:" + (-this.outcome) +
					"\n Win Rate for player:" + this.prob +
					"\n Expected Bread for house per $10 bet:" + bread + "\n";
		System.out.println(report);	
	}
	public void recordloss() {
		this.outcome = 0 - this.amountbet;
		this.totalamountbet = this.totalamountbet + this.amountbet;
		this.totaloutcome = this.totaloutcome + this.outcome;
		this.prob = this.numwin / (double)this.trial;
		this.expected = this.totaloutcome / (double)this.trial;
		// prints out report per trial
		String bread = String.format("%.2f", (-this.expected));
		this.report = " Trial:" + this.trial +
				"\n Bet:" + this.amountbet +
				"\n Outcome for house:" + (-this.outcome) +
				"\n Win Rate for player:" + this.prob +
				"\n Expected Bread for house per $10 bet:" + bread + "\n";
		System.out.println(report);	
	}
	
	
// rolls dice, gives number, updates image
	public int rollthedice(JLabel label) {
		ProbMethods run = new ProbMethods();
		int num = run.rolldice();
		if (num == 1) {
			try {
				BufferedImage one = ImageIO.read(new File("one.jpg"));
				ImageIcon x = new ImageIcon(one);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (num == 2) {
			try {
				BufferedImage two = ImageIO.read(new File("two.jpg"));
				ImageIcon x = new ImageIcon(two);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (num == 3) {
			try {
				BufferedImage three = ImageIO.read(new File("three.jpg"));
				ImageIcon x = new ImageIcon(three);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (num == 4) {
			try {
				BufferedImage four = ImageIO.read(new File("four.jpg"));
				ImageIcon x = new ImageIcon(four);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (num == 5) {
			try {
				BufferedImage five = ImageIO.read(new File("five.jpg"));
				ImageIcon x = new ImageIcon(five);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (num == 6) {
			try {
				BufferedImage six = ImageIO.read(new File("six.jpg"));
				ImageIcon x = new ImageIcon(six);
				Image imgx = x.getImage();
				Image newimg = imgx.getScaledInstance(97, 97,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newx = new ImageIcon(newimg);
				label.setIcon(newx);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return num;
	}
// checks outcome of three rolled dice
	public boolean checkrollthedice(int rolla, int rollb) {
		int a = rolla;
		int b = rollb;
		if( a == b)
			return false;
		// false = lose 
		else {
			return true;
		}
	}
// draws a card, gives outcome based off number, updates image
	public int drawthecard(JLabel label) {
		ProbMethods run = new ProbMethods();
		int num = run.drawacard();
		int spot = num-1;
		String [] cards = {
				"sa","s2","s3","s4","s5","s6","s7","s8","s9","s10","sJ","sQ","sK",
				"ha","h2","h3","h4","h5","h6","h7","h8","h9","h10","hJ","hQ","hK",
				"da","d2","d3","d4","d5","d6","d7","d8","d9","d10","dJ","dQ","dK",
				"ca","c2","c3","c4","c5","c6","c7","c8","c9","c10","cJ","cQ","cK"};

		String cur = cards[spot];
		try {
			
			BufferedImage y = ImageIO.read(new File(cur+".jpg"));
			ImageIcon yy = new ImageIcon(y);
			Image imgy = yy.getImage();
			Image newimgy = imgy.getScaledInstance(97, 127,  java.awt.Image.SCALE_SMOOTH);
			ImageIcon newy = new ImageIcon(newimgy);
			label.setIcon(newy);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return run.checkcard(num);
	}
// spins the wheel, gives outcome based off number, updates image
	public boolean spinthewheel(JLabel label) {
		ProbMethods run = new ProbMethods();
		int num = run.spinner();
			if (num == 1) {
				try {
					BufferedImage one = ImageIO.read(new File("spin1.jpg"));
					ImageIcon y = new ImageIcon(one);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 2) {
				try {
					BufferedImage two = ImageIO.read(new File("spin2.jpg"));
					ImageIcon y = new ImageIcon(two);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 3) {
				try {
					BufferedImage three = ImageIO.read(new File("spin3.jpg"));
					ImageIcon y = new ImageIcon(three);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 4) {
				try {
					BufferedImage four = ImageIO.read(new File("spin4.jpg"));
					ImageIcon y = new ImageIcon(four);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 5) {
				try {
					BufferedImage five = ImageIO.read(new File("spin5.jpg"));
					ImageIcon y = new ImageIcon(five);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 6) {
				try {
					BufferedImage six = ImageIO.read(new File("spin6.jpg"));
					ImageIcon y = new ImageIcon(six);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 7) {
				try {
					BufferedImage seven = ImageIO.read(new File("spin7.jpg"));
					ImageIcon y = new ImageIcon(seven);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 8) {
				try {
					BufferedImage eight = ImageIO.read(new File("spin8.jpg"));
					ImageIcon y = new ImageIcon(eight);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 9) {
				try {
					BufferedImage nine = ImageIO.read(new File("spin9.jpg"));
					ImageIcon y = new ImageIcon(nine);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (num == 10) {
				try {
					BufferedImage ten = ImageIO.read(new File("spin10.jpg"));
					ImageIcon y = new ImageIcon(ten);
					Image imgspin = y.getImage();
					Image newimgspin = imgspin.getScaledInstance(206, 147,  java.awt.Image.SCALE_SMOOTH);
					ImageIcon newspin = new ImageIcon(newimgspin);
					label.setIcon(newspin);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		return run.checkspinner(num);
	}
// flips a coin, gives number, updates image
	public int flipthecoin(JLabel label) {
		ProbMethods run = new ProbMethods();
		int num = run.coinflip();
		if (num == 1) {
			try {
				BufferedImage one = ImageIO.read(new File("heads.jpg"));
				ImageIcon x = new ImageIcon(one);
				Image imgcoinh = x.getImage();
				Image newimgcoinh = imgcoinh.getScaledInstance(77, 77,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newcoinh = new ImageIcon(newimgcoinh);
				label.setIcon(newcoinh);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return 1;
		}
		else{
			try {
				BufferedImage two = ImageIO.read(new File("tails.jpg"));
				ImageIcon x = new ImageIcon(two);
				Image imgcoint = x.getImage();
				Image newimgcoint = imgcoint.getScaledInstance(77, 77,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon newcoint = new ImageIcon(newimgcoint);
				label.setIcon(newcoint);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return 2;
		}
	}
// checks outcome of three coin numbers
	public boolean checkflipthecoin(int flipa, int flipb, int flipc) {
		int a = flipa;
		int b = flipb;
		int c = flipc;
		if(( a == 1 && b == 1 && c == 2) ||
		   ( a == 2 && b == 1 && c == 1) ||
		   ( a == 1 && b == 2 && c == 1)) {
			return true;
			// true = x8 win
		}
		return false;
		// true = x2 win
	}
/*
Creating simple graphics using Google draw:
Deck
https://docs.google.com/drawings/d/1cwU_Jdj-w7WsCFy6oJQOwpTyoF1qhY04hSA0jpnVdEg/edit?usp=sharing
Dice
https://docs.google.com/drawings/d/1l3UJPnWWRhGSTYFIEl0zfgY2v-OqXpcGeYml7UDkb_8/edit?usp=sharing
Spinner
https://docs.google.com/drawings/d/1IlNMG2pKlv1z7CAzVO-YY5nm0ZKi07BZkizy6ThDCLc/edit?usp=sharing
Coin
https://docs.google.com/drawings/d/1uC9l2c1Q51ae_jJRXS7XOm8YxdvH9zCnsFG5gXZLDfY/edit?usp=sharing
 */
	public void playsound(String soundName) {
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
