package view;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import presenter.Presenter;
import tasks.Task;

public class EightPuzzleTimedGameWindow extends UIView {
	
	public EightPuzzleTimedGameWindow (Presenter presenter,Display display, int width, int height, String title,String description) {
		super(presenter,display, width, height, title);

		this.description = description;
		this.presenter = presenter;
		this.display = display;
}
	Display display;
	Presenter presenter;
	String action;
	String description;
	Timer timer;
	TimerTask task;
	EightPuzzle puzzle;
	
	@Override
	public void start() {
		
		getSolution();
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getUserAction() {
		return action;
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2, false));
	
		Button btnPause = new Button(shell, SWT.PUSH);
		btnPause.setText("Pause");
		btnPause.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,1));
		
		puzzle = new EightPuzzle(shell, SWT.BORDER , description);
		puzzle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		
		Button btnContinue = new Button(shell, SWT.PUSH);
		btnContinue.setText("Continue");
		btnContinue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,1));
		
		Button btnClose = new Button(shell, SWT.PUSH);
		btnClose.setText("Close");
		btnClose.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1,1));

	}
	
	@Override
	public void displaySolution(Solution solution) {
		timer = new Timer();
		ArrayList<String> actionArray = new ArrayList<String>();
		
		for (Action a : solution.getActions()) {
			
			String[] temp = a.getDescription().split("-->");
			actionArray.add(temp[1]);
			
		}
		int i = 0;
		
		while( i < actionArray.size())
		{	
			int j = i;
			task = new TimerTask() {
				
				@Override
				public void run() {
					shell.getDisplay().syncExec(new Runnable() {
						
						@Override
						public void run() {
							puzzle.updateDescription(actionArray.get(j));
							puzzle.redraw();
							
						}
					});

				}
				
			};
			i++;

			timer = new Timer();
			timer.schedule(task, 500*i);
		}
//			String[] temp = a.getDescription().split("-->");
//			
//			shell.getDisplay().syncExec(new Runnable() {
//				
//				@Override
//				public void run() {	
//					puzzle.updateDescription(temp[1]);
//					shell.redraw();
//			//		lstActions.add(a.toString());
//				}
//				
//			});	
//			
//			try {
//				wait(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
		}
		
	public void getSolution(){
		action = "SD 8puzzle:" + description ;	
		EightPuzzleTimedGameWindow.this.setChanged();
		EightPuzzleTimedGameWindow.this.notifyObservers();	
		
		action = "SA AStar";
		EightPuzzleTimedGameWindow.this.setChanged();
		EightPuzzleTimedGameWindow.this.notifyObservers();	
		
		action = "SlD";
		EightPuzzleTimedGameWindow.this.setChanged();
		EightPuzzleTimedGameWindow.this.notifyObservers();
	}
}
