package simonVicki;

import java.util.ArrayList;

import gui.ClickableScreen;
import gui.components.Action;
import gui.components.TextLabel;
import gui.components.Visible;

public class SimonScreenVicki extends ClickableScreen implements Runnable {
	
	private ProgressInterfaceVicki progress;
	private ButtonInterfaceVicki[] buttons;
	private ArrayList<MoveInterfaceVicki> moves;
	private int roundNumber;
	private boolean acceptedInput;
	private TextLabel label;
	private int lastSelectedButton;
	private int sequenceIndex;
	
	public SimonScreenVicki(int width, int height) {
		super(width, height);
		Thread one = new Thread(this);
		one.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initAllObjects(ArrayList<Visible> viewObjects) {
		addButtons();
		progress = getProgress();
		label = new TextLabel(130,230,300,40,"Let's play Simon!");
		moves = new ArrayList<MoveInterfaceVicki>();
		lastSelectedButton = -1;
		moves.add(randomMove());
		moves.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
		
		final Button b = getAButton();
		// must create Button class
		b.setAction(new Action(){

		public void act(){

		Thread blink = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		//code for turning on, sleeping for 800ms and turning back off again goes here.
		});
		blink.start();
		}

		});
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
	
	/**
	Placeholder until partner finishes implementation of ProgressInterface
	*/
	private ProgressInterfaceVicki getProgress() {
		return new Progress();
	}

	private void addButtons() {
		int numOfButtons = 6;
		String[] colors = {"new Color(205,154,154)", "new Color(154,164,205)", "new Color(164,205,154)",
				"new Color(160,60,155)", "new Color(225,225,155)", "new Color(225,155,155)"};
		for (int i = 0; i < colors.length; i++) {
			buttons[i] = getAButton();
			buttons[i].setColor(i);
			buttons[i].setX(160 + (int)(100*Math.cos(i*2*Math.PI/(numOfButtons))));
			buttons[i].setY(200 - (int)(100*Math.sin(i*2*Math.PI/(numOfButtons))));
			buttons[i].setAction(new Action(){

				public void act(){

				}

				});
		}
	}

	private ButtonInterfaceVicki getAButton() {
		return null;
	}
}
