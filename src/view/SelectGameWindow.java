package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import presenter.Presenter;

public class SelectGameWindow extends BasicWindow {

	public SelectGameWindow(Presenter presenter, int width, int height, String title) {
		super(presenter,width, height, title);		
	}

	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(1, false));
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");

		Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);

		MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
		shell.setMenuBar(menuBar);
		
		final Combo combo = new Combo(shell, SWT.READ_ONLY);	
		combo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
	    String items[] = { "8puzzle", "Maze","Hagana" };
	    combo.setItems(items);
	    
	    Button btnSelectModel = new Button(shell, SWT.PUSH);
	    btnSelectModel.setText("Start");
	    btnSelectModel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

	    exitItem.addSelectionListener(new SelectionListener() {

	    	@Override
	    	public void widgetSelected(SelectionEvent arg0) {
	    		shell.dispose();
	    	}

	    	@Override
	    	public void widgetDefaultSelected(SelectionEvent arg0) {
	    		// TODO Auto-generated method stub

	    	}
	    });

	    btnSelectModel.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// Choose window according to the game (using a factory)				
				if(combo.getText().equals("8puzzle")){
				UIView window = new EightPuzzleSelectionWindow(presenter, display, 250, 450, "8puzzle Game");
				window.run();
				}
				else if (combo.getText().equals("Maze")){
					UIView window = new MazeSelectionWindow(presenter, display, 300, 300, "Maze game");
					window.run();
					}
				else {
					UIView window = new HaganaAwindow(presenter, display, 300, 300, "HaganaA");
					window.run();
				}
		
			}
			
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    shell.setVisible(false);
	}

}
