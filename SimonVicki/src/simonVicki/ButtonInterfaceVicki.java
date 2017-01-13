package simonVicki;

import java.awt.Color;

import gui.components.Action;
import gui.components.Clickable;

public interface ButtonInterfaceVicki extends Clickable {
	public void setAction(Action a);
	void setColor(Color i);
	void turnOn();
	void turnOff();
	Color getColor();
	void setX(int i);
	void setY(int i);
}
