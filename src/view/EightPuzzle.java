package view;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout.Alignment;
import javax.swing.text.StyledEditorKit.AlignmentAction;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class EightPuzzle extends Canvas{

	//GameCharacter c;
	Timer timer;
	TimerTask task;
	int[][] gameState;
	String description;

	 public EightPuzzle(Composite parent, int style) {
		super(parent, style);
		//c = new GameCharacter(10, 10);
		//set a white background (red,green,blue)
		setBackground(new Color(null,255,255,255));
		description = "1,2,3,4,5,6,7,0,8";
		
		String[] tempS = description.split(",");
		
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
				 for(int i=0;i<3;i++)
				    for(int j=0;j<3;j++){
				    	int x=j*w;
				        int y=i*h;
				        e.gc.drawText(tempS[k], x , y );
				        k++;
				    }
				}
			});
	 }
}
	/*	addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				stop();
			}
		
		});
		
		}
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
	
	public void stop() {
		if(task != null)
			task.cancel();
		if(timer != null)
			timer.cancel();
	}

}
*/
