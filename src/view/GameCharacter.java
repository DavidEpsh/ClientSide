package view;



import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

class GameCharacter{
	   int x,y;
	   
	   public GameCharacter(int x,int y) {
		this.x=x;this.y=y;
	   }
	   Image image = new Image(null, "src/marco.jpg");
	   
	 
	   public void paint(PaintEvent e,int w,int h){
		e.gc.setForeground(new Color(null,255,0,0));
		e.gc.drawImage(image,0,0, image.getBounds().width, image.getBounds().height, x, y, w, h);
	   }
	   
	   // to do: add x and y functions;
	   
	}

