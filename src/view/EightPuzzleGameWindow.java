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

public class EightPuzzleGameWindow extends UIView {
	
	public EightPuzzleGameWindow(Presenter presenter,Display display, int width, int height, String title) {
		super(presenter,display, width, height, title);		
	}
	
	private String action;
	private List lstActions;
	
	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(2, false));
		
/*		Label lblStartWord = new Label(shell, SWT.NONE);
		lblStartWord.setText("Enter start word: ");
		
		final Text txtStartWord = new Text(shell, SWT.BORDER);
		txtStartWord.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblEndWord = new Label(shell, SWT.NONE);
		lblEndWord.setText("Enter end word: ");
		
		final Text txtEndWord = new Text(shell, SWT.BORDER);
		txtEndWord.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
*/		

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
		
		lstActions = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		lstActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
				
		EightPuzzle puzzle=new EightPuzzle(shell, SWT.BORDER);
		 puzzle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		 
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
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();	
				
				action = "SA " + comboAlgorithm.getText();
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
	}

	@Override
	public void start() {		
		run();
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub
		
	}
	/*
	public void displaySolution(Solution solution) {
		for(Action a : solution.getActions())
			System.out.println(a);
	}
	*/

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

}
