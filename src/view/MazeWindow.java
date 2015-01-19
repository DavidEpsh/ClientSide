package view;

import model.Solution;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import presenter.Presenter;

public class MazeWindow extends UIView {

	public MazeWindow(Presenter presenter, Display display, int x, int y, String title) {
		super(presenter, display,  x, y, title);
		
	}
	
	String action;

	@Override
	public void initWidgets() {
		 shell.setLayout(new GridLayout(2, false));
		 
		 Button start = new Button(shell, SWT.PUSH);
		 start.setLayoutData(new GridData(SWT.FILL,SWT.TOP,false,false,1,1));
		 start.setText("start");
		 
		 Maze maze=new Maze(shell, SWT.BORDER);
		 maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true	,1,2));
		 
		 Button stop = new Button(shell, SWT.PUSH);
		 stop.setText("stop");
		 stop.setLayoutData( new GridData(SWT.FILL,SWT.TOP,false,false,1,1));
		 
		 
		 
		 start.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				maze.start();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUserAction() {
		return this.action;
	}

}
