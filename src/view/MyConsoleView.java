package view;

import java.util.Observable;
import java.util.Scanner;

import model.Solution;
import model.algorithm.Action;

public class MyConsoleView extends Observable implements View {

	private String action;
	
	@Override
	public void start() {
		System.out.println("Welcome to my project");
		action = "";
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("Enter command: ");
			action = scanner.nextLine();
			
			if (!(action.equals("exit")))
			{
				this.setChanged();
				this.notifyObservers();
			}
			
		} while (!(action.equals("exit")));
		
		
	}

	@Override
	public void displayCurrentState() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution solution) {
		for(Action a : solution.getActions())
			System.out.println(a);
	}

	@Override
	public String getUserAction() {		
		return action;
	}

}
