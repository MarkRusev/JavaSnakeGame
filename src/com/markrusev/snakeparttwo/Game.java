package com.markrusev.snakeparttwo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//array of integers to navigate and move the snake
	private int[] xLength = new int[900];
	private int[] yLength = new int[900];
	
    //coordinates that can enemies be spawned on,they are +25 because the images of the head and body are 25 by 25 pixels.
	private int[] xAppleSpawn = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,
	                            625,650,675,700,725,750,775,800,825,850};
	private int[] yAppleSpawn = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
		
	//for spawning the apple in random positions
	private Random random = new Random();
		
	//returns random number from 0 till 34 and 23  respectively to get an element from the arrays above
	private int xCoordinates = random.nextInt(xAppleSpawn.length);
	private int yCoordinates = random.nextInt(yAppleSpawn.length);
	
	//boolean variables to check the position of the snake,if right==true move to the right...
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	
	//if move = 0 then set default position
	private int move = 0;
	
	//timer to start the game and delay to set the movement speed of the snake,the speed is best at 100 delay imo.
	private Timer timer;
	private int delay=100;
	
	//if snake hits the apple length++
	private static int snakeLength = 3;
	
	//to use the images which i made by myself(very impressive)
	private ImageIcon rightHead;
	private ImageIcon leftHead;
	private ImageIcon upHead;
	private ImageIcon downHead;
	private ImageIcon snakeBody;
	private ImageIcon enemyApple;
	private ImageIcon title;
	
	//to save the score ;)))
	private int score;
    
	
	//constructor
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(getDelay(),this);
		timer.start();	
	}
	
	//setters
	public void setRightMovement(boolean right) {
		this.right=right;
	}
	public void setLeftMovement(boolean left) {
		this.left=left;
	}
	public void setUpMovement(boolean up) {
		this.up=up;
	}
	public void setDownMovement(boolean down) {
		this.down=down;
	}
	
	public void setRightHead(ImageIcon rightHead) {
		this.rightHead= new ImageIcon("righthead.png");
	}
	public void setLeftHead(ImageIcon leftHead) {
		this.leftHead= new ImageIcon("lefthead.png");
	}
	public void setUpHead(ImageIcon upHead) {
		this.upHead= new ImageIcon("uphead.png");
	}
	public void setDownHead(ImageIcon downHead) {
		this.downHead= new ImageIcon("downhead.png");
	}
	public void setSnakeBody(ImageIcon snakeBody) {
		this.snakeBody = new ImageIcon("snakebody.png");
	}
	public void setTitleImage(ImageIcon title) {
		this.title = new ImageIcon("snaketitle.jpg");
	}
	public void setAppleImage(ImageIcon apple) {
		this.enemyApple = new ImageIcon("apple.png");
	}
	
	public void setDelay(int delay) {
		if (delay<100 || delay>150) {
			throw new IllegalArgumentException();
		}
		this.delay=delay;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public void setMoves(int move) {
		this.move=move;
	}
	public void setSnakeLength(int length) {
		if (snakeLength<3) {
			throw new IllegalArgumentException();
		}
		Game.snakeLength=length;
	}
	
	
	//getters
	public boolean  getRightMovement() {
		return this.right;
	}
	public boolean  getLeftMovement() {
		return this.left;
	}
	public boolean  getUpMovement() {
		return this.up;
	}
	public boolean  getDownMovement() {
		return this.down;
	}
	public int getDelay() {
		return this.delay;
	}
	public int getScoreCounter() {
		return this.score;
	}
	public int getMoves() {
		return this.move;
	}
	public int getLength() {
		return Game.snakeLength;
	}
	
	public boolean checkMoves() {
		if(getMoves()==0) {
			return true;
		}
		return false;
	}
	
	
	//method to illustrate everything
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		if(checkMoves()) {
			xLength[0] = 200;
			xLength[1] = 175;
			xLength[2] = 150;
			
			yLength[0] = 300;
			yLength[1] = 300;
			yLength[2] = 300;
			
			}
		//drawing the title border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851,55);
		
		//setting in the image in the title border
		setTitleImage(title);
		title.paintIcon(this, g, 25,11);
		
		//gameplay border
		g.setColor(Color.WHITE);
		g.drawRect(25, 74, 851,577);
		
		//the filled gameplay pole
		g.setColor(Color.BLACK);
		g.fillRect(26, 75, 850, 575);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,14));
		g.drawString("Score: "+getScoreCounter(),780,30);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,14));
		g.drawString("Length "+getLength(),780,50);
		
		setRightHead(rightHead);
		rightHead.paintIcon(this, g, xLength[0], yLength[0]);
		
		for (int i = 0; i <snakeLength; i++) {
			if (i==0 && right) {
				setRightHead(rightHead);
				rightHead.paintIcon(this, g, xLength[i], yLength[i]);			
				}
			if (i==0 && left) {
				setLeftHead(leftHead);
				leftHead.paintIcon(this, g, xLength[i], yLength[i]);
			}
			if (i==0 && up) {
				setUpHead(upHead);
				upHead.paintIcon(this, g, xLength[i], yLength[i]);
			}
			if (i==0 && down) {
				setDownHead(downHead);
				downHead.paintIcon(this, g, xLength[i], yLength[i]);
			}
			if (i!=0) {
				setSnakeBody(snakeBody);
				snakeBody.paintIcon(this, g, xLength[i], yLength[i]);
			}
		}
		setAppleImage(enemyApple);
		
		if((xAppleSpawn[xCoordinates]==xLength[0] && yAppleSpawn[yCoordinates]==yLength[0])) {
			score++;
			snakeLength++;
			xCoordinates = random.nextInt(xAppleSpawn.length);
			yCoordinates = random.nextInt(yAppleSpawn.length);
		}
		enemyApple.paintIcon(this, g, xAppleSpawn[xCoordinates],yAppleSpawn[yCoordinates]);
		for(int i =1;i<snakeLength;i++) {
			if(xLength[i]==xLength[0] && yLength[i]==yLength[0]) {
				setLeftMovement(false);
				setRightMovement(false);
				setUpMovement(false);
				setDownMovement(false);
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("Arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("Arial",Font.BOLD,25));
				g.drawString("SPACE to restart", 350, 340);
			}
		}
		
		g.dispose();
	}
	
	//method which is automatic called when timer starts
	@Override
	public void actionPerformed(ActionEvent e) {
		if(right) {
			for (int position=snakeLength-1;position>=0;position--) {
				yLength[position+1]=yLength[position];
			}
			for (int position = snakeLength; position>=0; position--) {
				if (position==0) {
					xLength[position]=xLength[position]+25;
				}else {
					xLength[position]=xLength[position-1];
				}
				if (xLength[position]>850) {
					xLength[position]=25;
				}
			}
			repaint();
		}
		if(left) {
			for (int position=snakeLength-1;position>=0;position--) {
				yLength[position+1]=yLength[position];
			}
			for (int position = snakeLength; position>=0; position--) {
				if (position==0) {
					xLength[position]=xLength[position]-25;
				}else {
					xLength[position]=xLength[position-1];
				}
				if (xLength[position]<25) {
					xLength[position]=850;
				}
				
			}
			repaint();
		}
		if(up) {
			for (int position=snakeLength-1;position>=0;position--) {
				xLength[position+1]=xLength[position];
			}
			for (int position = snakeLength; position>=0; position--) {
				if (position==0) {
					yLength[position]=yLength[position]-25;
				}else {
					yLength[position]=yLength[position-1];
				}
				if (yLength[position]<75) {
					yLength[position]=625;
				}
				
			}
			repaint();
		}
		if(down) {
			for (int position=snakeLength-1;position>=0;position--) {
				xLength[position+1]=xLength[position];
			}
			for (int position = snakeLength; position>=0; position--) {
				if (position==0) {
					yLength[position]=yLength[position]+25;
				}else {
					yLength[position]=yLength[position-1];
				}
				if (yLength[position]>625) {
					yLength[position]=75;
				}	
				
			}
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	//make something if key is pressed
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			setMoves(0);
			setScore(0);
			setSnakeLength(3);
			repaint();
			
			
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			move++;
			setRightMovement(true);
			if (!getLeftMovement()) {
				setRightMovement(true);
			    
			}
			else {
				setRightMovement(false);
				setLeftMovement(true);
			}
			setUpMovement(false);
			setDownMovement(false);
			
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			move++;
			setLeftMovement(true);
			if (!getRightMovement()) {
				setLeftMovement(true);
			}
			else {
				setLeftMovement(false);
				setRightMovement(true);
					
			}
			setUpMovement(false);
			setDownMovement(false);
		
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			move++;
			setUpMovement(true);
			if (!getDownMovement()) {
				setUpMovement(true);    
			}
			else {
				setUpMovement(false);
				setDownMovement(true);
					
			}
			setRightMovement(false);
			setLeftMovement(false);
		
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			move++;
			setDownMovement(true);
			if (!getUpMovement()) {
				setDownMovement(true);
			   
			}
			else {
				setDownMovement(false);
				setUpMovement(true);
				
					
			}
			setRightMovement(false);
			setLeftMovement(false);
		
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}


	