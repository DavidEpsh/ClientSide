package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Solution;
import model.algorithm.Action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class EightPuzzleGameWindow extends BasicWindow implements View {
	private String userAction;
	private List lstActions;
	
	public EightPuzzleGameWindow(int width, int height, String title) {
		super(width, height, title);		
	}

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

	    Label lblPropertiesSelected = new Label(shell, SWT.NONE);
		lblPropertiesSelected.setText("Choosen game properties: ");
		
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
				
		btnSearch.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {	

				userAction = "SelectDomain " + comboPuzzleProperties.getText() ;	
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();	
				
				userAction = "SelectAlgorithm " + comboAlgorithm.getText();
				EightPuzzleGameWindow.this.setChanged();
				EightPuzzleGameWindow.this.notifyObservers();	
				
				userAction = "SolveDomain";
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

	@Override
	public void displaySolution(Solution solution) {
		for (Action a : solution.getActions()) {
			
			display.asyncExec(new Runnable() {
				
				@Override
				public void run() {					
					lstActions.add(a.toString());
				}
			});						
		}		
	}

	@Override
	public String getUserAction() {		
		return userAction;
	}

}
