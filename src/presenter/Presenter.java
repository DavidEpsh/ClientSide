package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import presenter.UserCommands.Command;
import model.Model;
import model.MyModel;
import model.Solution;
import view.MyConsoleView;
import view.SelectGameWindow;
import view.View;

public class Presenter implements Observer {
	
	private Model model; // the current model
	private View view;
	private UserCommands commands;
	private ArrayList<Model> models; // all running models
	
	public Presenter(Model model) {
		this(model, null);
	}
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{
			Solution solution = ((Model)observable).getSolution();
			view.displaySolution(solution);
		}
		else if (observable == view)
		{
			String action = view.getUserAction();
			String[] arr = action.split(" ");
			
			String commandName = arr[0];
			
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			
			Command command = commands.selectCommand(commandName);
			Model m = command.doCommand(model, args);
			
			// Check if we got a new model from the command
			if (m != model) {
				this.model = m;
				models.add(m);
				m.addObserver(this);
			}
		}
	}
	
	public static void main(String[] args) {
		MyModel model = new MyModel();
		//MyConsoleView view = new MyConsoleView();
		SelectGameWindow view = new SelectGameWindow(300, 300, "Select game");
		Presenter presenter = new Presenter(model);
		
		model.addObserver(presenter);
//		view.addObserver(presenter);
		
		view.run();
	}
	
}
