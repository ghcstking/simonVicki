package partnerCodeHere;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import gui.components.Action;
import gui.components.Component;
import simonVicki.ButtonInterfaceVicki;

public class Button extends Component implements ButtonInterfaceVicki {

	private Action action;
	private Color c;
	private boolean highlight;

	public Button() {
		super(0, 0, 80, 80);
		highlight = false;
	}

	@Override
	public boolean isHovered(int x, int y) {
		return x>getX() && x<getX()+getWidth() && y > getY() && y<getY()+getHeight();
	}

	@Override
	public void act() {
		action.act();
	}

	@Override
	public void setAction(Action a) {
		this.action = a;
	}

	@Override
	public void setColor(Color i) {
		this.c = i;
	}

	@Override
	public void update(Graphics2D g) {
		g = clear();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(highlight) {
			g.setColor(this.c);
			g.fillRect(0, 0, 65, 65);
			g.setColor(Color.black);
			g.drawRect(0, 0, 65, 65);
		}
		else {
			g.setColor(Color.gray);
			g.fillRect(0, 0, 65, 65);
			g.setColor(Color.black);
			g.drawRect(0, 0, 65, 65);
		}
	}


	public void turnOn() {
		highlight = true;
		update();
	}

	@Override
	public void turnOff() {
		highlight = false;
		update();
	}

	@Override
	public Color getColor() {
		return c;
	}

}

