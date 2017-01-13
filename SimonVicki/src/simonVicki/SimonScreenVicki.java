package simonVicki;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import gui.components.ClickableScreen;
import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import partnerCodeHere.Button;
import partnerCodeHere.Move;
import partnerCodeHere.Progress;

public class SimonScreenVicki extends ClickableScreen implements Runnable {

	private ProgressInterfaceVicki progress;
	private ButtonInterfaceVicki[] buttons;
	private ArrayList<MoveInterfaceVicki> moves;
	private int roundNumber;
	private boolean inputNow;
	private TextLabel label;
	private int lastSelectedButton;
	private int playerClickIndex;

	public SimonScreenVicki(int width, int height) {
		super(width, height);
		roundNumber = 0;
		Thread game = new Thread(this);
		game.start();
	}

	@Override
	public void run() {
		inputNow = false;
		roundNumber++;
		moves.add(randomMove());
		progress.updateInfo(roundNumber, moves.size());
		changeText("Simon's turn!");
		label.setText("");
		showColors();
		changeText("Your turn!");
		label.setText("");
		playerClickIndex = 0;
		inputNow = true;
	}


	public ButtonInterfaceVicki getAButton() {
		return new Button();
	}

	private MoveInterfaceVicki randomMove() {
		int random = (int) (Math.random() * buttons.length);
		if (random == lastSelectedButton) {
			random = (int) (Math.random() * buttons.length);
		}
		lastSelectedButton = random;
		return new Move(buttons[lastSelectedButton]);
	}

	private ProgressInterfaceVicki getProgress() {
		return new Progress();
	}

	public void changeText(String s) {
		label.setText(s);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void showColors() {
		for(MoveInterfaceVicki m: moves){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.getButton().turnOn();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.getButton().turnOff();
		}
	}

	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		// TODO Auto-generated method stub
		int numOfButtons = 4;
		Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
		buttons = new ButtonInterfaceVicki[numOfButtons];
		label = new TextLabel(325,250,300,40,"");
		progress = getProgress();
		for (int i = 0; i < numOfButtons; i++) {
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setX((75 * i) + 250);
			buttons[i].setY(50);
			final ButtonInterfaceVicki b = buttons[i];
			b.setAction(new Action(){
				public void act(){
					if(inputNow){
						Thread blink = new Thread(new Runnable(){
							@Override
							public void run() {
								b.turnOn();
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								b.turnOff();
							}
						});
						blink.start();
					}
					if(playerClickIndex < moves.size()){
						if(inputNow){
							if (b == moves.get(playerClickIndex).getButton()) {
								playerClickIndex++;
							}
							else{
								progress.gameOver();
								inputNow = false;
							}
						}
					}
					if (playerClickIndex == moves.size()) {
						Thread game = new Thread(SimonScreenVicki.this);
						game.start();
					}
				}
			});
			viewObjects.add(buttons[i]);
		}
		moves = new ArrayList<MoveInterfaceVicki>();
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
	}
}

