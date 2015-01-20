package view;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import presenter.Presenter;

public class MazeGameWindow extends UIView {

	public MazeGameWindow(Presenter presenter, Display display, int x, int y, String title,String description) {
		super(presenter, display,  x, y, title);
		
		this.description = description;
	}
	Maze maze;
	String action;
	String description;

	@Override
	public void initWidgets() {
		 shell.setLayout(new GridLayout(4, false));
		 
		 Button btnUp = new Button(shell, SWT.PUSH);
	 	 btnUp.setText("Up");
	 	 btnUp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,3,1));
		 
		 maze=new Maze(shell, SWT.BORDER, description);
		 maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,8));
		 
		 Button btnLeft = new Button(shell, SWT.PUSH);
		 btnLeft.setText("Left");
		 btnLeft.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
			
		 Button btnRight = new Button(shell, SWT.PUSH);
		 btnRight.setText("Right");
	 	 btnRight.setLayoutData(new GridData(SWT.RIGHT, SWT.LEFT, false, false, 1, 1));
			
		 Button btnDown = new Button(shell, SWT.PUSH);
		 btnDown.setText("Down");
		 btnDown.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 3, 1));
			
		 Label lblProperties = new Label(shell, SWT.NONE);
		 lblProperties.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 3, 1));
		 lblProperties.setText("Choose Algorithm");
		 
		 final Combo comboAlgorithm = new Combo(shell, SWT.READ_ONLY);	
		 comboAlgorithm.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 3, 1));
		 String itemsAlgorithm[] = { "BFS", "AStar" };
		 comboAlgorithm.setItems(itemsAlgorithm);
		 
		 Button btnShowTimedSolution = new Button(shell, SWT.PUSH);
		 btnShowTimedSolution.setText("Show Me Step By Step");
		 btnShowTimedSolution.setLayoutData( new GridData(SWT.FILL,SWT.TOP,false,false,3,1));
		 
		 Button stop = new Button(shell, SWT.PUSH);
		 stop.setText("stop");
		 stop.setLayoutData( new GridData(SWT.FILL,SWT.TOP,false,false,3,1));
		 
		 Button reset = new Button(shell, SWT.PUSH);
		 reset.setText("Reset Game");
		 reset.setLayoutData( new GridData(SWT.FILL,SWT.TOP,false,false,3,1));
		 
		 btnUp.addSelectionListener(new SelectionListener() {			
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					maze.up();
					maze.redraw();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
		 btnDown.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					maze.down();
					maze.redraw();
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
	  	 btnLeft.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					maze.left();
					maze.redraw();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			btnRight.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					maze.right();
					
					maze.redraw();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
	   	 stop.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				maze.stop();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   	 
	   	 btnShowTimedSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
		
				maze.updateDescription();
				action = "SD Maze:" + maze.description;
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();	
				
				action = "SA " + comboAlgorithm.getText();
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();	
				
				action = "SlD";
				MazeGameWindow.this.setChanged();
				MazeGameWindow.this.notifyObservers();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
		
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution solution) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				
				for (Action a : solution.getActions()) {
			
					String[] temp = a.getDescription().split("-->");
					String[] tempState = temp[1].split(",");
					int[] currState = new int[]{Integer.parseInt(tempState[0]),Integer.parseInt(tempState[1])};
					shell.getDisplay().syncExec(new Runnable() {
						
						@Override
						public void run() {
							maze.setCurrentstate(currState);
							maze.redraw();
							
							try {
								sleep(400);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});

				}
				
			};
			
			
		};
		thread.start();
		
	}

	@Override
	public String getUserAction() {
		return this.action;
	}
	
	public void updateDescription(String description){
		this.description = description;
	}
	
	
}
