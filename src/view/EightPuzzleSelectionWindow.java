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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter;

public class EightPuzzleSelectionWindow extends UIView {
	
	public EightPuzzleSelectionWindow(Presenter presenter,Display display, int width, int height, String title) {
		super(presenter,display, width, height, title);		
	}
	
	private String action;
	private List lstActions;
	
	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(2, false));

		Label lblProperties = new Label(shell, SWT.NONE);
		lblProperties.setText("Choose game properties: ");
		
		final Combo comboPuzzleProperties = new Combo(shell, SWT.READ_ONLY);	
		comboPuzzleProperties.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
	    String itemsProperties[] = { "Random", "User Defind" };
	    comboPuzzleProperties.setItems(itemsProperties);
	    
	    final Text txtGameDescription = new Text(shell, SWT.BORDER);
		txtGameDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true ,2, 1));
		txtGameDescription.setEnabled(false);
		
		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Choose algorithm: ");
		
		final Combo comboAlgorithm = new Combo(shell, SWT.READ_ONLY);	
		comboAlgorithm.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
	    String itemsAlgorithm[] = { "BFS", "AStar" };
	    comboAlgorithm.setItems(itemsAlgorithm);
		
		Button btnSearch = new Button(shell, SWT.PUSH);
		btnSearch.setText("Search");
		btnSearch.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		Button btnUserGame = new Button(shell, SWT.PUSH);
		btnUserGame.setText("Shall We Play A Game ?");
		btnUserGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		
		Button btnTimedGame = new Button(shell, SWT.PUSH);
		btnTimedGame.setText("Im a NOOB, Show Me How To Play");
		btnTimedGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		
		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
						

		 
		btnUserGame.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				UIView window = new EightPuzzleGameWindow( currPresenter, currDisplay, 500, 500, "My 8puzzle Game", "1,2,3,4,5,6,0,7,8");
				window.run();
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		comboPuzzleProperties.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(comboPuzzleProperties.getText().equals("User Defind")){
					txtGameDescription.setEnabled(true);
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub	
			}
		});
		 
		btnSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	

				action = "SD 8puzzle:" + txtGameDescription.getText() ;	
				EightPuzzleSelectionWindow.this.setChanged();
				EightPuzzleSelectionWindow.this.notifyObservers();	
				
				action = "SA " + comboAlgorithm.getText();
				EightPuzzleSelectionWindow.this.setChanged();
				EightPuzzleSelectionWindow.this.notifyObservers();	
				
				action = "SlD";
				EightPuzzleSelectionWindow.this.setChanged();
				EightPuzzleSelectionWindow.this.notifyObservers();	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
								
			}
		});
	}

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
	public void displayTimesSolution() {
		// TODO Auto-generated method stub
		super.displayTimesSolution();
	}

}
