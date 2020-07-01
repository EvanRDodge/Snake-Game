package Snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
//this class controls the graphical display of the game screen as well as the game logic
public class SnakeGameController extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;

	private final int MAX_FOOD = 20;
	private final int FOOD_SPAWN_DELAY = 10;
	private Timer timer;
	int counter = 0;
	int x = 250;
	int y = 250;
	int moveSpeed = 10;
	int moveDirection = 0;
	int foodCollected = 0;
	Snake snake;
	Random numberGenerator;
	ArrayList<SnakeSegment> foodList;
	boolean gameOver;
	//constructor
	public SnakeGameController() {
		setBackground(Color.BLACK);
		timer = new Timer(200, this);
		snake = new Snake(x, y);
		foodList = new ArrayList<SnakeSegment>();
		numberGenerator = new Random();
		gameOver = false;
		addKeyListener(this);
	}
	//draws graphical elements of the ui in the panel
	public void paintComponent(Graphics g) {  
		super.paintComponent(g);
    	//draw text
    	g.setColor(Color.WHITE);
        g.drawString("Controls: wasd or arrow keys changes snake direction; ENTER = restart game.", 50, 20);
        g.drawString("Score: " + foodCollected, 50, 35);
    	//draw boundary
    	g.setColor(Color.LIGHT_GRAY);
    	g.drawRect(50, 50, 500, 500);
    	
        if(gameOver == false) {
	        //draw snake
	        g.setColor(Color.GREEN);
	        for(int i = 0; i < snake.getLength(); i++) {
	        	SnakeSegment ts = snake.getSegment(i);
	        	g.fillRect(ts.getxPos(), ts.getyPos(), 10, 10);
	        }
	        //draw food
	        	g.setColor(Color.RED);
	        for(int i = 0; i < foodList.size(); i++) {
	        	g.fillOval(foodList.get(i).getxPos(), foodList.get(i).getyPos(), 10, 10);
	        }
	        timer.start();
        }
        else {
        	//snake turns yellow when it bites itself
        	if(snake.getLength() > 0) {
        		g.setColor(Color.YELLOW);
    	        for(int i = 0; i < snake.getLength(); i++) {
    	        	SnakeSegment ts = snake.getSegment(i);
    	        	g.fillRect(ts.getxPos(), ts.getyPos(), 10, 10);
    	        }
        	}
        	//draw food
        	g.setColor(Color.RED);
	        for(int i = 0; i < foodList.size(); i++) {
	        	g.fillOval(foodList.get(i).getxPos(), foodList.get(i).getyPos(), 10, 10);
	        }
	        //print game over message
	        g.setColor(Color.WHITE);
        	g.drawString("GAME OVER", 270, 270);
        	timer.stop();
        }
	}
	//event handling
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == timer) {
			//move snake position
			if(moveDirection != 0) {
				if(moveDirection == 1) {
					y -= moveSpeed;
				}
				else if(moveDirection == 2) {
					x += moveSpeed;
				}
				else if(moveDirection == 3) {
					y += moveSpeed;
				}
				else if(moveDirection == 4) {
					x -= moveSpeed;
				}
				//check if snake is about to eat one of the food pellets
				for(int i = 0; i < foodList.size(); i++) {
					if(foodList.get(i).getxPos() == x && foodList.get(i).getyPos() == y) {
						snake.eat(x, y);
						foodCollected++;
						foodList.remove(i);
						return;
					}
				}
				
				if(snake.getLength() > 0) {
					snake.slither(x, y);
					//if moving out of bounds let it move at the cost of a segment
					if(x < 50 || x > 540 || y < 50 || y > 540) {
						snake.remove(0);
					}
					//check for game over conditions
					if(snake.isBitingSelf() || snake.getLength() < 1) {
						gameOver = true;
					}
				}
			}
			//replenish food pellets
			counter--;
			if(foodList.size() < MAX_FOOD && counter <= 0) {
				int newFoodX = numberGenerator.nextInt(50) * 10;
				int newFoodY = numberGenerator.nextInt(50) * 10;
				foodList.add(new SnakeSegment(newFoodX + 50, newFoodY + 50));
				//reset counter for food spawning
				counter = FOOD_SPAWN_DELAY;
			}
			repaint();
		}
	}
	//keyboard input handling
	@Override
	public void keyPressed(KeyEvent key) {
		//read arrow keys to change direction snake head is moving
		if(key.getKeyCode() == KeyEvent.VK_UP || key.getKeyChar() == 'w') {
			System.out.println("up");
			moveDirection = 1;
		}
		else if(key.getKeyCode() == KeyEvent.VK_RIGHT || key.getKeyChar() == 'd') {
			System.out.println("right");
			moveDirection = 2;
		}
		else if(key.getKeyCode() == KeyEvent.VK_DOWN || key.getKeyChar() == 's') {
			System.out.println("down");
			moveDirection = 3;
		}
		else if(key.getKeyCode() == KeyEvent.VK_LEFT || key.getKeyChar() == 'a') {
			System.out.println("left");
			moveDirection = 4;
		}
		//enter key restarts the game
		if(key.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("enter pressed");
			foodList.clear();
			gameOver = false;
			foodCollected = 0;
			moveDirection = 0;
			x = 250;
			y = 250;
			snake = new Snake(x, y);
			timer.restart();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}
