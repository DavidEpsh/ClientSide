package model;

import java.util.Observable;

import networking.Client;

public class MyModel extends Observable implements Model {
	
	private Solution solution;
	private Problem problem;
	private String description;
	
	
	public MyModel()
	{
		problem = new Problem();
	}

	@Override
	public void selectDomain(String args) {

		String[] arr = args.split(":");
		
		String domainName = arr[0];
		
		if (arr.length>1){
			String domainArgs = arr[1];
			problem.setDomainName(domainName);
			problem.setDomainArgs(domainArgs);
		}
		
		else {
			problem.setDomainName(domainName);
			problem.setDomainArgs(null);
		}
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		problem.setAlgorithmName(algorithmName);
		
		if (algorithmName.equals("AStar"))
			selectHeuristic();
	}
	
	public void selectHeuristic(){
		if (problem.getDomainName().equals("8puzzle"))
			problem.setHeuristicName("8puzzle");
		
		if (problem.getDomainName().equals("Maze"))
			problem.setHeuristicName("Maze");
	}

	@Override
	public void solveDomain() {	
		
		Client client = new Client();
		solution = client.getSolution(problem);
		
		this.setChanged();
		this.notifyObservers();
	}

	public void getNewGame(){
		
		Client client = new Client();
		description = client.getNewGame(problem);
		
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public String getDescription(){
		return this.description;
	}
	
	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		solveDomain();
	}
}
