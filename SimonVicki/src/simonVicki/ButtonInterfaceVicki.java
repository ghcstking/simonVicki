package simonVicki;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceVicki extends Clickable {
	void turnOn();
	void turnOff();
	void setAction(Action action);
	void setCoords(int x, int y);
	public Color getColor();
	void setColor(Color c);
}
