package partnerCodeHere;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import gui.components.Action;
import gui.components.Component;
import simonVicki.ButtonInterfaceVicki;

public class Button  extends Component implements ButtonInterfaceVicki{
	
	private Action action;
	private Color initColor;
	private boolean light;

	public Button() {
		super(0, 0, 700, 700);
		light = false;
	}

	@Override
	public void act() {
		action.act();
		
	}

	@Override
	public boolean isHovered(int x, int y) {
		return x>getX() && x<getX()+getWidth() && y > getY() && y<getY()+getHeight();
	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAction(Action action) {
		this.action = action;
		
	}

	@Override
	public void setCoords(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color c) {
		this.initColor = c;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	public void highlight() {
		light = true;
		update();
	}

	public void dim() {
		initColor = Color.gray;
		light = false;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(initColor);
	}

	@Override
	public void setX(int i) {
		// TODO Auto-generated method stub
		
	}

}
