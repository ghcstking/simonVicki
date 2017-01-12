package simonVicki;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceVicki extends Clickable {
	public void turnOn();
	public void turnOff();
	public void setAction(Action action);
	public void setCoords(int x, int y);
	public void setColor(Color c);
	public Color getColor();
	public void setX(int i);
}
