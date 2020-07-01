package Snake;

//this class models a single segment in the snake's body
public class SnakeSegment {
	private int xPos;
	private int yPos;
	//constructors
	public SnakeSegment() {
		xPos = 0;
		yPos = 0;
	}
	public SnakeSegment(int x, int y) {
		xPos = x;
		yPos = y;
	}
	//setters and getters for x and y positions
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
}
