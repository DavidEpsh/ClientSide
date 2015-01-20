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
	int mazeWidth;


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

				 int width=getSize().x;
				 int height=getSize().y;

				 int w=width/mazeData[0].length;
				 int h=height/mazeData.length;

				 for(int i=0;i<mazeData.length;i++){
				    for(int j=0;j<mazeData[i].length;j++){
				        int x=j*w;
				        int y=i*h;
				        if(mazeData[i][j]==1 || (i==0&&j==0) || (i==mazeLength-1 && j== mazeWidth-1))
				            e.gc.fillRectangle(x,y,w,h);
				    }
				 }
				 
				 c.setX(currentState[0]);
				 c.setY(currentState[1]);
				 c.paint(e, w, h);
				 

				}
			});
		addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		});
		
		}
	
	public void start() {
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {
				getDisplay().syncExec(new Runnable() {
					
					@Override
					public void run() {
						Random r = new Random();
						c.x += -5 + r.nextInt(11);
						c.y += -5 + r.nextInt(11);
						redraw();
					}
				});

			}
		};
		timer.scheduleAtFixedRate(task, 0, 500);

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
		if(currentState[1]==0)
			return;
		else{
			currentState[1]--;
		}
	}
	
	public void down(){
		if(currentState[1]==mazeWidth-1)
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
		mazeWidth = Integer.parseInt(tempArray[1]);
		
		mazeData = new int[mazeLength][mazeWidth];
		int k =2;
		for(int i=0; i<mazeLength; i++){
			for(int j=0 ; j<mazeWidth ; j++){
				mazeData[i][j] = Integer.parseInt(tempArray[k]);
				k++;
			}
		}
		
	}
	
	public void updateDescription(){
		String temp="";
		int k =4;
		temp += Integer.toString(currentState[0]) + ",";
		temp += Integer.toString(currentState[1]) + ",";
		temp += Integer.toString(mazeLength) + ",";
		temp += Integer.toString(mazeWidth) + ",";
		
		for(int i=0; i<mazeLength; i++){
			for(int j=0 ; j<mazeWidth ; j++){
				if(i==mazeLength-1 && j== mazeWidth-1){
					temp += mazeData[i][j];
					}
				else{
					temp += mazeData[i][j] + ",";
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

}
