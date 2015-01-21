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
		int [] tempIntegers = new int[]{1,2,3,4,5,6,7,8};
		// ArrayList<Integer> temp = new ArrayList<Integer>(){1,2,3,4,5,6,7,8,0};
		for ( int i=0 ; i<8 ; i++){
			int rX = (int)(Math.random()*3);
			int rY = (int)(Math.random()*3);
			
			if (eightGameState[rY][rX] == 0)
				eightGameState[rY][rX]=tempIntegers[i];
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

			int[][] goalState = new int[][]{{1,2,3},{4,5,6},{7,8,0}};
			int distance=0,tempInt;
			
			for (int y=0 ; y<3 ; y++){
				for(int x=0 ; x<3 ; x++){	
					if(eightGame[y][x]==0)
						continue;
					
					tempInt = eightGame[y][x];
					
					for (int y1=0 ; y1<3 ; y1++){
						for (int x1=0 ; x1<3 ; x1++){
							if (tempInt == goalState[y1][x1])
								distance += Math.abs(x1-x)+Math.abs(y1-y);
						}
					}
				}
			}
		
			return (distance%2==0 || distance == 1); 
		}
	
	public void updateDescription(int[][] gameDescription, int x, int y){
		String temp =  "";
		for(int y1=0 ; y1<y ; y1++){
			for (int x1=0 ; x1<x ; x1++){
				if(x1 == x-1 && y1 == y-1){
					temp += Integer.toString(gameDescription[y1][x1]);
				}
				else{
				temp += Integer.toString(gameDescription[y1][x1])+",";
				}
			}
		}
		
		description = temp;
	}

	public void createMazeGame(String args){
		
		String[] mazeProperties = args.split(",");
		int length = Integer.parseInt( mazeProperties[0] );
		int heigth = Integer.parseInt( mazeProperties[1] );
		int blocks = Integer.parseInt( mazeProperties[2] );
			
		int[][] maze= new int[heigth][length];
		
		for (int y=0; y<heigth ; y++ ){
			for(int x=0 ; x<length ; x++) {
				maze[y][x]=1;
			}
		}
		
		for (int k=0 ; k < blocks ; k++){
			int rX = (int) (Math.random()*length);
			int rY = (int) (Math.random()*heigth);
			
			if ((rX==0 && rY==0) || (rX == length-1 && rY == heigth -1 ) ){
				k--;
			}
			
			else if (maze[rY][rX]==1)
						maze[rY][rX]=0;
			
			else 
				k--;
		}
		
		setMazeGameDescription(length,heigth,maze);
		
	}
	
	public void createRandomMazeGame() {
		createMazeGame("8,8,20");
		
	}
	
	public void setMazeGameDescription(int length, int height, int[][] maze){
		
		String mazeGameDescription = "";
		mazeGameDescription += Integer.toString(length)+",";
		mazeGameDescription += Integer.toString(height)+",";

		for(int y=0 ; y<height ; y++){
			for (int x =0 ; x<length ; x++){
				if(x==length-1 && y== height-1){
					mazeGameDescription += Integer.toString(maze[y][x]);
					}
				
				mazeGameDescription += Integer.toString(maze[y][x]) + ","; 	
				}
			}
		
		description =mazeGameDescription;
	}
}
