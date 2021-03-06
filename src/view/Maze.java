package view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

public class Maze extends Canvas implements KeyListener{
	
	int[][] mazeData;
	String action;
	GameCharacter c;
	String description;
	Timer timer;
	TimerTask task;
	int[] currentState = new int[]{0,0};
	int mazeLength;
	int mazeHeigth;


	public Maze(Composite parent, int style , String description) {
		super(parent, style);
		
		this.description = description;
		updateMazeData();
		c = new GameCharacter(10, 10);
		setBackground(new Color(null,0,0,0));
		
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				 e.gc.setForeground(new Color(null,255,255,255));
				 e.gc.setBackground(new Color(null,255,255,255));

				 int length=getSize().x;
				 int height=getSize().y;

				 int len=length/mazeData[0].length;
				 int h=height/mazeData.length;

				 for(int y1=0 ;y1<mazeData.length ;y1++){
				    for(int x1=0 ; x1<mazeData[0].length ; x1++){
				        int x=x1*len;
				        int y=y1*h;
				        if(mazeData[y1][x1]==1 || (y1==0&&x1==0) || (x1==mazeLength-1 && y1== mazeHeigth-1))
				            e.gc.fillRectangle(x,y,len,h);

				    }
				 }
					c.setX(currentState[0]);
					c.setY(currentState[1]);
					c.setXM(mazeData[0].length-1);
					c.setYM(mazeData.length-1);
				    c.paint(e, len, h);
				    
				    if(currentState[0]==mazeData[0].length-1 && currentState[1] == mazeData.length-1)
					{
						MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_WORKING);
					    messageBox.setMessage("You Found Your Mommy !!!!");
					    messageBox.open();
					}

				}
			});
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		});
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {

						if(e.keyCode == SWT.ARROW_RIGHT){

							right();
							
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
							left();
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
							up();
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
							down();
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
	
	public void stop() {
		if(task != null)
			task.cancel();
		if(timer != null)
			timer.cancel();
	}
	public void setCurrentstate(int[] currState){
		currentState[0] = currState[0];
		currentState[1] = currState[1];
	}
	public void up(){
		if(currentState[1]==0|| mazeData[currentState[1]-1][currentState[0]] ==0)
			return;
		else{
			currentState[1]--;
		}
	}
	
	public void down(){
		if(currentState[1]==mazeHeigth-1|| mazeData[currentState[1]+1][currentState[0]] ==0)
			return;
		else{
			currentState[1]++;
		}
	}
	
	public void left(){
		if(currentState[0]==0 || mazeData[currentState[1]][currentState[0]-1] ==0)
			return;
		else{
			currentState[0]--;
		}
		

	}
	
	public void right(){
		if(currentState[0]==mazeLength-1 || mazeData[currentState[1]][currentState[0]+1] ==0)
			return;
		else{
			currentState[0]++;
		}
	}
	
	public void updateMazeData(){
		String[] tempArray = description.split(",");
		mazeLength = Integer.parseInt(tempArray[0]);
		mazeHeigth = Integer.parseInt(tempArray[1]);
		
		mazeData = new int[mazeHeigth][mazeLength];
		int k =2;
		for(int y=0 ; y<mazeHeigth ; y++){
			for(int x=0; x<mazeLength; x++){
				mazeData[y][x] = Integer.parseInt(tempArray[k++]);
			}
		}
	}
	
	public void updateDescription(){
		String temp="";
		int k =4;
		temp += Integer.toString(currentState[0]) + ",";
		temp += Integer.toString(currentState[1]) + ",";
		temp += Integer.toString(mazeLength) + ",";
		temp += Integer.toString(mazeHeigth) + ",";
		
		for(int y=0; y<mazeHeigth; y++){
			for(int x=0 ; x<mazeLength ; x++){
				if(y== mazeHeigth && x== mazeLength-1){
					temp += mazeData[y][x];
					}
				else{
					temp += mazeData[y][x] + ",";
					k++;
				}
			}
		}
		
		description = temp;
	}
	
	public void resetMaze(String description){
		this.description = description;
		updateMazeData();
		currentState[0] = 0;
		currentState[1] = 0;
		redraw();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
 

}
