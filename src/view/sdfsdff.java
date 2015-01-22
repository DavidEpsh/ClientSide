addKeyListener(new KeyListener() 
		{
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e)
			{
				getDisplay().syncExec(new Runnable() 
				{
					@Override
					public void run() 
					{
						int width=getSize().x;
						int height=getSize().y;
						switch (e.keyCode) 
						{
						case SWT.ARROW_UP:
						{
							character.moveUp(maze, width, height);
							redraw();
						}
						break;
						case SWT.ARROW_RIGHT:
						{
							character.moveRight(maze, width, height);
							redraw();
						}
						break;
						case SWT.ARROW_DOWN:
						{
							character.moveDown(maze, width, height);
							redraw();
						}
						break;
						case SWT.ARROW_LEFT:
						{
							character.moveLeft(maze, width, height);
							redraw();
						}
						break;
						}
						boolean b1=(character.getRow()==maze.length-1);
						boolean b2=(character.getColumn()==maze[0].length-1);
						if(b1&&b2)
						{
							MessageBox messageBox = new MessageBox(getShell(), SWT.ICON_ERROR);
						    messageBox.setMessage("You won! ");
						    int rc = messageBox.open();
						}
					}
				});
			}
		});
	}
	public void start()
	{
		getDisplay().syncExec(new Runnable()
		{
			@Override
			public void run() 
			{
				character.setToStart();
				flag=false;
				redraw();
			}
		});
	}
	public void reset(int[][] arr)
	{
		generateMaze(arr);
		start();
	}
	
	public void generateMaze(int[][] arr)
	{
		this.maze=new int[arr.length][arr[0].length];
		for (int i = 0; i < maze.length; i++) 
		{
			for (int j = 0; j < maze[0].length; j++) 
			{
				this.maze[i][j]=arr[i][j];
			}
		}
		this.character=new GameCharacter();
	}
	public boolean move(Action a)//get an action, if it fits to the possible actions, changes the puzzle by the move, and returns true, else, returns false
	{
		switch(a.getAction())
		{
			case "up":return moveUp();
			case "right":return moveRight();
			case "down":return moveDown();
			case "left":return moveLeft();
			default:return false;
		}
	}
	public boolean moveUp()
    {
    	boolean flag= character.moveUp(maze, getSize().x, getSize().y);
    	getDisplay().syncExec(new Runnable()
    	{	
			@Override
			public void run() 
			{
				redraw();
			}
		});
    	return flag;
    }
    public boolean moveRight()
    {
    	boolean flag= character.moveRight(maze, getSize().x, getSize().y);
    	getDisplay().syncExec(new Runnable()
    	{	
			@Override
			public void run() 
			{
				redraw();
			}
		});
    	return flag;
    }
    public boolean moveDown()
    {
    	boolean flag= character.moveDown(maze, getSize().x, getSize().y);
    	getDisplay().syncExec(new Runnable()
    	{	
			@Override
			public void run() 
			{
				redraw();
			}
		});
    	return flag;
    }
    public boolean moveLeft()
    {
    	boolean flag= character.moveLeft(maze, getSize().x, getSize().y);
    	getDisplay().syncExec(new Runnable()
    	{	
			@Override
			public void run() 
			{
				redraw();
			}
		});
    	return flag;
    }
    public int getProblemStartRow()
    {
    	return character.getRow();
    }
    public int getProblemStartColumn()
    {
    	return character.getColumn();
    