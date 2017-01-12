package simonVicki;

import gui.GUIApplication;

public class SimonGameVicki extends GUIApplication {

	public SimonGameVicki(int width, int height) {
		super(width, height);
	}

	@Override
	public void initScreen() {
		SimonScreenVicki ssv = new SimonScreenVicki(getWidth(), getHeight());
		setScreen(ssv);
	}
	
	public static void main(String[] args) {
		SimonGameVicki game = new SimonGameVicki(800,500);
		Thread app = new Thread(game);
		app.start();
	}
	
}
