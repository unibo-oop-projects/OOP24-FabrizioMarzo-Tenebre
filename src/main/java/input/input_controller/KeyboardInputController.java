package input.input_controller;

public class KeyboardInputController implements InputController {    
	
	private Directions direction = Directions.NONE;

	@Override
	public void isMoveUp() {
		this.direction = Directions.UP;
	}

	@Override
	public void isMoveDown() {
		this.direction = Directions.DOWN;
	}

	@Override
	public void isMoveLeft() {
		this.direction = Directions.LEFT;
	}

	@Override
	public void isMoveRight() {
		this.direction = Directions.RIGHT;
	}

	@Override
	public void isMoveNone() {
		this.direction = Directions.NONE;
	}

	@Override
	public Directions getDirections(){
		return this.direction;
	}
	
	public void notifyMoveUp() {
		isMoveUp();
	}

	public void notifyMoveDown() {
		isMoveDown();
	}
	
	public void notifyMoveLeft() {
		isMoveLeft();
	}

	public void notifyMoveRight() {
		isMoveRight();
	}

	public void notifyNoMoreMove() {
		isMoveNone();
	}
	

}
