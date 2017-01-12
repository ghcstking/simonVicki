package simonVicki;

import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.Button;
import gui.components.TextLabel;
import gui.components.Visible;
import simonVicki.MoveInterfaceVicki;

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
		changeText("Updating... my turn");
		label.setText("");
		showColors();
		changeText("Your turn!");
		label.setText("");
		playerClickIndex = 0;
		inputNow = true;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		int numOfButtons = 6;
		String[] colors = {"new Color(205,154,154)", "new Color(154,164,205)", "new Color(164,205,154)",
				"new Color(160,60,155)", "new Color(225,225,155)", "new Color(225,155,155)"};
		buttons = new ButtonInterfaceVicki[numOfButtons];
		for (int i = 0; i < colors.length; i++) {
			buttons[i] = getAButton();
			buttons[i].setColor(colors[i]);
			buttons[i].setCoords(160 + (int)(100*Math.cos(i*2*Math.PI/(numOfButtons))), 
					(200 - (int)(100*Math.sin(i*2*Math.PI/(numOfButtons)))));
			ButtonInterfaceVicki b = buttons[i];
			buttons[i].setAction(new Action(){
				public void act(){
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
					if (playerClickIndex == moves.size()) {
						Thread game = new Thread(SimonScreenVicki.this);
						game.start();
					}
					if(playerClickIndex < moves.size()) {
						if(b.getColor() == moves.get(playerClickIndex).getButton().getColor()) {
							playerClickIndex++;
						}
						else {
							progress.gameOver();
							label = new TextLabel(130, 230, 300, 40, "Game over!");
						}
					}
				}
		});
		viewObjects.add(buttons[i]);
	}
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceVicki>();
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
}

	private MoveInterfaceVicki randomMove() {
		int random = (int) (Math.random() * buttons.length);
		if (random == lastSelectedButton) {
			random = (int) (Math.random() * buttons.length);
		}
		lastSelectedButton = random;
		return Move(buttons[lastSelectedButton]);
		// must create Move class
	}
	
	private ProgressInterfaceVicki getProgress() {
		return new Progress();
		// must create Progress class
	}
	
	private ButtonInterfaceVicki getAButton() {
		return new Button();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.getButton().turnOff();
		}
	}
}
