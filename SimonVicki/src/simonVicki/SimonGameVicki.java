package simonVicki;

import gui.practice.GUIApplication;

public class SimonGameVicki extends GUIApplication {

	public SimonGameVicki() {
		super();
	}

	@Override
	public void initScreen() {
		SimonScreenVicki ssv = new SimonScreenVicki(getWidth(), getHeight());
		setScreen(ssv);
	}
	
	public static void main(String[] args) {
		SimonGameVicki game = new SimonGameVicki();
		Thread app = new Thread(game);
		app.start();
	}
	
}
