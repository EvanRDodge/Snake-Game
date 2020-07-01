package Snake;
import java.util.ArrayList;
//this class models the multi-segment snake
public class Snake {
	private ArrayList<SnakeSegment> segments;
	//constructor
	public Snake(int xStart, int yStart) {
		SnakeSegment temp = new SnakeSegment(xStart, yStart);
		segments = new ArrayList<SnakeSegment>();
		segments.add(temp);
	}
	//shifts the snake forward starting with moving the head to the provided position
	public void slither(int x, int y) {
		SnakeSegment tempSegment = new SnakeSegment();
		//save head position and move head
		tempSegment.setxPos(segments.get(0).getxPos());
		tempSegment.setyPos(segments.get(0).getyPos());
		segments.get(0).setxPos(x);
		segments.get(0).setyPos(y);		
		//inserts last segment of the snake behind the head in the list of segments and into the previous coordinates of the head
		if(segments.size() > 1) {
			segments.add(1, segments.get(segments.size() - 1));
			segments.remove(segments.size() - 1);
			segments.get(1).setxPos(tempSegment.getxPos());
			segments.get(1).setyPos(tempSegment.getyPos());
		}
	}
	//adds a new element to the snake head
	public void eat(int x, int y) {
		SnakeSegment tempSegment = new SnakeSegment(x, y);
		segments.add(0, tempSegment);
	}
	//returns true if the snake's head segment overlaps another body segment and returns false
	public boolean isBitingSelf() {
		for(int i = 1; i < segments.size(); i++) {
			if(segments.get(0).getxPos() == segments.get(i).getxPos() && segments.get(0).getyPos() == segments.get(i).getyPos()) {
				return true;
			}
		}
		return false;
	}
	//removes the segment with the provided index
	public void remove(int i) {
		segments.remove(i);
	}
	//returns the length of the snake
	public int getLength() {
		return segments.size();
	}
	//returns a copy of a segment of the snake
	public SnakeSegment getSegment(int i) {
		return new SnakeSegment(segments.get(i).getxPos(), segments.get(i).getyPos());
	}
}
