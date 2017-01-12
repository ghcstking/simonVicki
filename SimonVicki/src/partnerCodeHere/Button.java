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

	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		action.act();
		
	}

	@Override
	public boolean isHovered(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
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
		this.c = c;
		
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	public void highlight() {
		if(c != null) displayColor = c;
		highlight = true;
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

}
