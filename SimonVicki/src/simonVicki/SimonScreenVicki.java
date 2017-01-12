package simonVicki;

import java.awt.Color;
import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;
import simonVicki.Move;
import simonVicki.Progress;
import simonVicki.MoveInterfaceVicki;
import simonVicki.ButtonInterfaceVicki;
import simonVicki.Button;

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
		changeText("Let's play Simon!");
		changeText("My turn");
		label.setText("");
		showColors();
		changeText("Your turn!");
		label.setText("");
		playerClickIndex = 0;
		inputNow = true;
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		int numOfButtons = 4;
		Color[] colors = {Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN};
		buttons = new ButtonInterfaceVicki[numOfButtons];
		progress = getProgress();
		for (int i = 0; i < numOfButtons; i++) {
			buttons[i] = getAButton();
			buttons[i].setX((75 * i) + 250);
			buttons[i].setY(50);
			buttons[i].setColor(colors[i]);
			final ButtonInterfaceVicki b = buttons[i];
			b.setAction(new Action(){
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
					if(playerClickIndex<moves.size()-1){
						if(b.getColor() == moves.get(playerClickIndex).getButton().getColor()){
							playerClickIndex++;
						}
						else{
							progress.gameOver();
						}
					}
					else{
						Thread game = new Thread(SimonScreenVicki.this);
						game.start();
					}
					// this isnt working yet
				}
		});
		viewObjects.add(buttons[i]);
	}
		label = new TextLabel(325,250,300,40,"");
		moves = new ArrayList<MoveInterfaceVicki>();
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		viewObjects.add(progress);
		viewObjects.add(label);
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
			System.out.println(m.getButton().getColor());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.getButton().turnOff();
		}
	}
}
