package view;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import presenter.Presenter;
import tasks.Task;

public class EightPuzzleTimedGameWindow extends UIView {
	
	public EightPuzzleTimedGameWindow (Presenter presenter,Display display, int width, int height, String title,String description) {
		super(presenter,display, width, height, title);

		this.description = description;
		this.presenter = presenter;
		this.display = display;
}
	
	Thread thread;
	Display display;
	Presenter presenter;
	String action;
	String description;
	TimerTask task;
	EightPuzzle puzzle;
	boolean keepGoing = true;
	
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
	
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);

		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		shell.setMenuBar(menuBar);
		
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
		btnClose.setLayoutData(new GridData(SWT.FILL, SWT.UP, false, false, 1,1));

		exitItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				thread.interrupt();
				shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btnPause.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				keepGoing = false;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		btnContinue.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			keepGoing = true;
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		
		btnClose.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				keepGoing = false;
				thread.interrupt();
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}
	
	@Override
	public void displaySolution(Solution solution) {
			
		int len = solution.getLength();
		ArrayList<Action> actions = solution.getActions();
		
		thread = new Thread() {
			@Override
			public void run() {
				
				for (int i=0; i<len ; i++) {
					if(keepGoing == true){
						Action a = actions.get(i);
						String[] temp = a.getDescription().split("-->");
				
					shell.getDisplay().syncExec(new Runnable() {
						
						@Override
						public void run() {
							puzzle.updateDescription(temp[1]);
							puzzle.redraw();
						}
					});
					
					}
					else{
						i--;
					}
					try {
						sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			};


		};
		
		thread.start();
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
