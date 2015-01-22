package view;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;


public class EightPuzzle extends Canvas implements KeyListener{

	//GameCharacter c;
	Timer timer;
	TimerTask task;
	int[][] gameState = new int[3][3];
	String description;
	String[] descriptionArray = new String[9];
	Image[] buttonsArray;

	
	 public EightPuzzle(Composite parent, int style, String description) {
		super(parent, style);
		//c = new GameCharacter(10, 10);
		//set a white background (red,green,blue)
		setBackground(new Color(null,255,255,255));
		this.description = description;
		setImages();
		descriptionArray = description.split(",");
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				 e.gc.setForeground(new Color(null,0,0,0));
				 e.gc.setBackground(new Color(null,255,255,255));
				
				int width=getSize().x;
				 int height=getSize().y;

				 int w=width/3;
				 int h=height/3;

				 int k=0;
				 for(int y1=0;y1<3;y1++)
				    for(int x1=0;x1<3;x1++){
				    	int x=x1*w;
				        int y=y1*h;
				        Image tempImage = buttonsArray[Integer.parseInt(descriptionArray[k])];
				        e.gc.drawImage(tempImage , 0, 0, tempImage.getBounds().width, tempImage.getBounds().height, x, y, w, h);
				        k++;
				    }
				}
			});
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {

						if(e.keyCode == SWT.ARROW_RIGHT){

							zeroRight();
							
							getDisplay().syncExec(new Runnable()
							{	
								@Override
								public void run() 
								{
									redraw();
								}
							});
						}
						else if (e.keyCode == SWT.ARROW_LEFT){
							zeroLeft();
							getDisplay().syncExec(new Runnable()
							{	
								@Override
								public void run() 
								{
									redraw();
								}
							});
						}
						else if (e.keyCode == SWT.ARROW_UP){
							zeroUp();
							getDisplay().syncExec(new Runnable()
							{	
								@Override
								public void run() 
								{
									redraw();
								}
							});
						}
						else if (e.keyCode == SWT.ARROW_DOWN){
							zeroDown();
							getDisplay().syncExec(new Runnable()
							{	
								@Override
								public void run() 
								{
									redraw();
								}
							});
						}

					}
				});
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	
	 }
	 
	 public void zeroUp(){
		 int zeroState = getZero();
		 
		 if (zeroState <= 2)
			 return;
		 
		 descriptionArray[zeroState] = descriptionArray[zeroState-3];
		 descriptionArray[zeroState-3] = "0";
		 
		 updateDescription();
	}

	public void zeroDown(){
		int zeroState = getZero();
		
		if (zeroState >= 6 )
			 return;
		 
		 descriptionArray[zeroState] = descriptionArray[zeroState+3];
		 descriptionArray[zeroState+3] = "0";
		 
		 updateDescription();
	}
	
	public void zeroLeft(){
		
		int zeroState = getZero();
		
		if (zeroState == 6 || zeroState == 3 || zeroState == 0)
			 return;
		 
		 descriptionArray[zeroState] = descriptionArray[zeroState-1];
		 descriptionArray[zeroState-1] = "0";
		 
		 updateDescription();
		
	}
	
	public void zeroRight(){
		
		int zeroState = getZero();
		
		if (zeroState == 2 || zeroState == 5 || zeroState == 8)
			 return;
		 
		 descriptionArray[zeroState] = descriptionArray[zeroState+1];
		 descriptionArray[zeroState+1] = "0";
		 
		 updateDescription();
	}
	
	public int getZero(){
		for(int i=0 ; i<9 ; i++){
			if (descriptionArray[i].equals("0"))
				return i;
		}
		return -1;
	}


	public void updateDescription(){
		String temp = "";
		for(int i=0 ; i<9 ; i++){
			if(i==8)
				temp += descriptionArray[i];
			else {
				temp += descriptionArray[i] + ",";
			}
		}
		
		description = temp;
	}

	public void updateDescription(String description){
		this.description = description;
		descriptionArray = description.split(",");
	}

	public void stop() {
		if(task != null)
			task.cancel();
		if(timer != null)
			timer.cancel();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setImages(){
		buttonsArray = new Image[9];
		buttonsArray[0] = new Image(null, "Images/button0.jpeg");
		buttonsArray[1] = new Image(null, "Images/button1.jpeg");
		buttonsArray[2] = new Image(null, "Images/button2.jpeg");
		buttonsArray[3] = new Image(null, "Images/button3.jpeg");
		buttonsArray[4] = new Image(null, "Images/button4.jpeg");
		buttonsArray[5] = new Image(null, "Images/button5.jpeg");
		buttonsArray[6] = new Image(null, "Images/button6.jpeg");
		buttonsArray[7] = new Image(null, "Images/button7.jpeg");
		buttonsArray[8] = new Image(null, "Images/button8.jpeg");
	}

}

