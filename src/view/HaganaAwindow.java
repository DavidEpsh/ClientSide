package view;
import hagana.view.GameBoard;
import model.Solution;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;

import presenter.Presenter;

public class HaganaAwindow extends UIView {
	
	public HaganaAwindow(Presenter presenter, Display display, int x, int y, String title) {
		super(presenter, display,  x, y, title);
}

	GameBoard gb;
	String action;
	String description;
	
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1, false));
		
		gb = new GameBoard(shell, SWT.BORDER, 8, 8);
		gb.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		
		Button btnSolve = new Button(shell, SWT.PUSH);
		btnSolve.setText("Solve!");
		btnSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 
		gb.placeBlue(1, 3);
		int redX = gb.getRedCol();
		int redY = gb.getRedRow();
		
		btnSolve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				updateDescription();
				
				action = "SD Chess:" + description;
				HaganaAwindow.this.setChanged();
				HaganaAwindow.this.notifyObservers();	
				
				action = "SA " + "AStar";
				HaganaAwindow.this.setChanged();
				HaganaAwindow.this.notifyObservers();	
				
				action = "SlD";
				HaganaAwindow.this.setChanged();
				HaganaAwindow.this.notifyObservers();
					
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void updateDescription(){
		
		int rX = gb.getRedCol();
		int rY = gb.getRedRow();
		int bX = gb.getBlueCol();
		int bY = gb.getBlueRow();
		
		description = "" + Integer.toString(bX) + "," + Integer.toString(bY) + "-->" + Integer.toString(rX) + "," + Integer.toString(rY);
	}
}
