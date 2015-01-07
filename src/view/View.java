package view;

import model.Solution;

public interface View {
	void start();
	void displayCurrentState();
	void displaySolution(Solution solution);
	String getUserAction();
}
