package view;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;

import presenter.Presenter;

public class EightPuzzleGameWindow extends UIView {
	
	public EightPuzzleGameWindow (Presenter presenter,Display display, int width, int height, String title,String description) {
		super(presenter,display, width, height, title);	
		
		this.description = description;
}
	private String action;
	private String description;
	private List lstActions;
	
	@Override
	public void start() {
		run();	
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void displaySolution(Solution solution) {
		for (Action a : solution.getActions()) {
			
			this.shell.getDisplay().syncExec(new Runnable() {
				
				@Override
				public void run() {					
					lstActions.add(a.toString());
				}
			});						
		}
	}

	@Override
	public String getUserAction() {
		return action;
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(3, false));
		
		Button btnUp = new Button(shell, SWT.PUSH);
		btnUp.setText("Up");
		btnUp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2,2));
		
//		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
//		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1 , 6 ));
		
		EightPuzzle puzzle=new EightPuzzle(shell, SWT.BORDER | SWT.V_SCROLL, description);
		puzzle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,8));
		
		Button btnLeft = new Button(shell, SWT.PUSH);
		btnLeft.setText("Left");
		btnLeft.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2));
		
		Button btnRight = new Button(shell, SWT.PUSH);
		btnRight.setText("Right");
		btnRight.setLayoutData(new GridData(SWT.RIGHT, SWT.LEFT, false, false, 1, 2));
		
		Button btnDown = new Button(shell, SWT.PUSH);
		btnDown.setText("Down");
		btnDown.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 2, 1));
		
		Button btnGiveUpShowSolution = new Button(shell, SWT.PUSH);
		btnGiveUpShowSolution.setText("I Give Up, Just Show Me The Solution");
		btnGiveUpShowSolution.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		Button btnGiveUpShowTimedSolution = new Button(shell, SWT.PUSH);
		btnGiveUpShowTimedSolution.setText("I Give Up, Show Me Step By Step");
		btnGiveUpShowTimedSolution.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 6));
		lstActions.setVisible(false);
		
		
		btnUp.addSelectionListener(new SelectionListener() {			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				puzzle.zeroUp();
				puzzle.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnDown.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				puzzle.zeroDown();
				puzzle.redraw();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnLeft.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				puzzle.zeroLeft();
				puzzle.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnRight.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				puzzle.zeroRight();
				puzzle.redraw();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnGiveUpShowSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				puzzle.setVisible(false);
				lstActions.setVisible(true);
				
				action = "SD 8puzzle:" + puzzle.description ;	
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();	
				
				action = "SA AStar";
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();	
				
				action = "SlD";
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	
		btnGiveUpShowTimedSolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				UIView window = new EightPuzzleTimedGameWindow( currPresenter, currDisplay, 500, 500, "HAVE FUN", puzzle.description);
				window.run();
				shell.dispose();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void displayTimedSolution() {
		// TODO Auto-generated method stub
		super.displayTimedSolution();
	}
}