package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class EightPuzzle extends Canvas{

	//GameCharacter c;
	Timer timer;
	TimerTask task;
	int[][] gameState = new int[3][3];
	String description;
	String[] descriptionArray = new String[9];

	 public EightPuzzle(Composite parent, int style, String description) {
		super(parent, style);
		//c = new GameCharacter(10, 10);
		//set a white background (red,green,blue)
		setBackground(new Color(null,255,255,255));
		this.description = description;
		
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
				        e.gc.drawText(descriptionArray[k], x , y );
				        k++;
				    }
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

	/*	addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		
		});
		
		} */
	/*
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
	*/
	public void stop() {
		if(task != null)
			task.cancel();
		if(timer != null)
			timer.cancel();
	}

}

