package view;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Maze extends Canvas {
	
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
		//set a white background (red,green,blue)
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

				 for(int y1=0 ;y1<mazeData.length ;y1++)
				    for(int x1=0 ; x1<mazeData[0].length ; x1++){
				        int x=x1*len;
				        int y=y1*h;
				        if(mazeData[y1][x1]==1 || (y1==0&&x1==0) || (x1==mazeLength-1 && y1== mazeHeigth-1))
				            e.gc.fillRectangle(x,y,len,h);
				    }
				 
				 c.paint(e, len, h);
				}
			});
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		});
		
		}
	
//	public void start() {
//		timer = new Timer();
//		task = new TimerTask() {
//			
//			@Override
//			public void run() {
//				getDisplay().syncExec(new Runnable() {
//					
//					@Override
//					public void run() {
//						Random r = new Random();
//						c.x += -5 + r.nextInt(11);
//						c.y += -5 + r.nextInt(11);
//						redraw();
//					}
//				});
//
//			}
//		};
//		timer.scheduleAtFixedRate(task, 0, 500);
//
//	}
	
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
		if(currentState[1]==0)
			return;
		else{
			currentState[1]--;
		}
	}
	
	public void down(){
		if(currentState[1]==mazeHeigth-1)
			return;
		else{
			currentState[1]++;
		}
	}
	
	public void left(){
		if(currentState[0]==0)
			return;
		else{
			currentState[0]--;
		}
	}
	
	public void right(){
		if(currentState[0]==mazeLength-1)
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
		int k =0;
		for(int y=0 ; y<mazeHeigth ; y++){
			for(int x=0; x<mazeLength; x++){
				mazeData[y][x] = Integer.parseInt(tempArray[k++]);
			}
		}
	}
	
	public void updateDescription(){
		String temp="";
		int k =0;
		temp += Integer.toString(currentState[0]);
		temp += Integer.toString(currentState[1]);
		
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

}
