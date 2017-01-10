package simonVicki;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceVicki extends Clickable {
	public void setAction(Action a);
	void setColor(String colors);
	void setCoords(int x, int y);
	void turnOn();
	void turnOff();
	public Color getColor();
}
