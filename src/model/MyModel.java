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
		
		String gameDomain = problem.getDomainName();
		String gameArgs = problem.getDomainArgs();
		
		if(gameDomain.equals("8puzzle")){
			if (gameArgs==null || gameArgs.equals("")){
				createRandomEightPuzzleState();
			}
			else{
				createUserEightPuzzleState(gameArgs);
			}
		}
		else if(gameDomain.equals("Maze")){
			if (gameArgs==null || gameArgs.equals("")){
				createRandomMazeGame();
			}
			else{
				createMazeGame(gameArgs);
			}
		}
		this.setChanged();
		this.notifyObservers();
		
	}
	
	@Override
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
	
	public void createRandomEightPuzzleState() {     // Default Constructor when puzzle not given
		int[][] eightGameState = new int[3][3];
		int [] tempIntegers;
		tempIntegers = new int[]{1,2,3,4,5,6,7,8};
		
		for ( int i=0 ; i<8 ; i++){
			int rX = (int)(Math.random()*3);
			int rY = (int)(Math.random()*3);
			
			if (eightGameState[rX][rY] == 0)
				eightGameState[rX][rY]=tempIntegers[i];
			else
				i--;
		}
		
		if(!isSolvable(eightGameState)){
			createRandomEightPuzzleState();
		}
		else {
			updateDescription(eightGameState,3,3);
		}
	}
	
	public void createUserEightPuzzleState(String args){       // Constructor when puzzle is given
		String[] EightPuzzleProperties = args.split(",");
		int[][] eightGameState = new int[3][3];
		
		int movingIndex=0;
		
		for (int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++){
				eightGameState[i][j] = Integer.parseInt(EightPuzzleProperties[movingIndex]);
				movingIndex++;
			}
		}
		
		if(!isSolvable(eightGameState)){
			System.out.println("Not Solvabe: Here's A New Game");
			createRandomEightPuzzleState();
		}
		else {
			updateDescription(eightGameState,3,3);
		}
	}
	
	public boolean isSolvable(int[][] eightGame){
		
		
		int distance=0,tempInt;
		
		for(int x=0 ; x<3 ; x++){
			for (int y=0 ; y<3 ; y++){
				if(eightGame[x][y]==0)
					continue;
				
				tempInt = eightGame[x][y];
				
				for (int i=0 ; i<3 ; i++){
					for (int j=0 ; j<3 ; j++){
						if (tempInt == eightGame[i][j])
							distance += Math.abs(i-x)+Math.abs(y-j);
					}
				}
			}
		}
		if (distance == 1)
			return true;
		
			return distance%2==0; 
		}
	
	public void updateDescription(int[][] gameDescription, int x, int y){
		String temp =  "";
		for(int i=0 ; i<x ; i++){
			for (int j=0 ; j<y ; j++){
				if(i == x-1 && j == y-1){
					temp += Integer.toString(gameDescription[i][j]);
				}
				else{
				temp += Integer.toString(gameDescription[i][j])+",";
				}
			}
		}
		
		description = temp;
	}

	public void createMazeGame(String args){
		
		String[] mazeProperties = args.split(",");
		int length = Integer.parseInt( mazeProperties[0] );
		int width = Integer.parseInt( mazeProperties[1] );
		int blocks = Integer.parseInt( mazeProperties[2] );
			
		int[][] maze= new int[length][width];
		
		for(int i=0 ; i<length ; i++) {
			for (int j=0; j<width ; j++ ){
				maze[i][j]=1;
			}
		}
		
		for (int x=0 ; x < blocks ; x++){
			int rX = (int) (Math.random()*length);
			int rY = (int) (Math.random()*width);
			
			if ((rX==0 && rY==0) || (rX == length-1 && rY == width -1 ) ){
				x--;
			}
			
			else if (maze[rX][rY]==1)
						maze[rX][rY]=0;
			
			else 
				x--;
		}
		
		setMazeGameDescription(length,width,maze);
		
	}
	
	public void createRandomMazeGame() {
		createMazeGame("8,8,20");
		
	}
	
	public void setMazeGameDescription(int length, int width, int[][] maze){
		
		String mazeGameDescription = "";
		mazeGameDescription += Integer.toString(length)+",";
		mazeGameDescription += Integer.toString(width)+",";
		
		
		for (int i =0 ; i<length ; i++){
			for(int j=0 ; j<width ; j++){
				
				if(i==length-1 && j== width-1){
					mazeGameDescription += Integer.toString(maze[i][j]);
					}
				else{
					mazeGameDescription += Integer.toString(maze[i][j]) + ","; 	
				}
			  }
			}
		
		description =mazeGameDescription;
	}
}
