package view;

import java.awt.Image;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

class GameCharacter extends Composite{
	   int x,y;
	   public GameCharacter(int x,int y) {
		this.x=x;this.y=y;
	   }
	   public void paint(PaintEvent e,int w,int h){
		
		Image image = new image 
		e.gc.drawImage(image, w, h);
		e.gc.setForeground(new Color(null,255,0,0));
		e.gc.drawOval(x,y, w, h);
	   }
	}

