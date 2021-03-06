package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import presenter.UserCommands.Command;
import model.Model;
import model.MyModel;
import model.Solution;
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
	
	public void setView(View view) {
		this.view = view;
	}
	
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{
			
			Solution solution = ((Model)observable).getSolution();
			
			if (solution == null){
				view.updateDescription(((Model)observable).getDescription());
				}
			
			else
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
		
		Presenter presenter = new Presenter(model);
		SelectGameWindow view = new SelectGameWindow(presenter, 400, 400, "Select game");
		
		model.addObserver(presenter);
		
		view.run();
	}
	
}
