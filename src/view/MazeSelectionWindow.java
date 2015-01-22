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

public class MazeSelectionWindow extends UIView {
	
	public MazeSelectionWindow(Presenter presenter,Display display, int width, int height, String title) {
		super(presenter,display, width, height, title);		
	}
	
	private String action;
	String description;
	private List lstActions;
	
	@Override
	void initWidgets() {
		
		shell.setLayout(new GridLayout(1, false));

		Label lblProperties = new Label(shell, SWT.NONE);
		lblProperties.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		lblProperties.setText("Choose game properties: ");
		
		final Combo comboPuzzleProperties = new Combo(shell, SWT.READ_ONLY);	
		comboPuzzleProperties.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
	    String itemsProperties[] = { "Random", "User Defind" };
	    comboPuzzleProperties.setItems(itemsProperties);
	    
	    final Text txtGameDescription = new Text(shell, SWT.BORDER);
		txtGameDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true ,2, 1));
		txtGameDescription.setEnabled(false);
		
		Button btnUserGame = new Button(shell, SWT.PUSH);
		btnUserGame.setText("Let Me Play!");
		btnUserGame.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
						
		btnUserGame.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				action = "SD Maze:" + txtGameDescription.getText() ;	
				MazeSelectionWindow.this.setChanged();
				MazeSelectionWindow.this.notifyObservers();	
				
				action = "GetNewGame";	
				MazeSelectionWindow.this.setChanged();
				MazeSelectionWindow.this.notifyObservers();	
				
				UIView window = new MazeGameWindow( currPresenter, currDisplay, 500, 500, "HAVE FUN", description);
				window.run();
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				action = "SD Maze:";	
				MazeSelectionWindow.this.setChanged();
				MazeSelectionWindow.this.notifyObservers();	
				
				action = "GetNewGame";	
				MazeSelectionWindow.this.setChanged();
				MazeSelectionWindow.this.notifyObservers();	
				
				UIView window = new MazeGameWindow( currPresenter, currDisplay, 500, 500, "My 8puzzle Game", description);
				window.run();
				shell.dispose();
				
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
	public void updateDescription(String description){
		this.description = description;
	}
}
