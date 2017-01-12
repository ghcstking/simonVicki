package partnerCodeHere;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import gui.components.Component;
import simonVicki.ProgressInterfaceVicki;

public class Progress extends Component implements ProgressInterfaceVicki{

	private boolean gameOver;
	private String roundNum;
	
	public Progress(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAnimated() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void gameOver() {
		gameOver =true;
		update();
		
	}

	@Override
	public void updateInfo(int roundNumber, int x) 
	{
		roundNum = "round" + roundNumber;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(gameOver)
		{
			System.out.println("game over");
			
		}
		else 
			if(roundNum !=null)
			{
				g.drawString(roundNum,20, 40);
			}
		update();
		
	}

}
