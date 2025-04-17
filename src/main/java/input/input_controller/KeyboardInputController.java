package input.input_controller;

public class KeyboardInputController implements InputController {    
	
	private Directions direction = Directions.NONE;

	private void isMoveUp() {
		this.direction = Directions.UP;
	}

	private void isMoveDown() {
		this.direction = Directions.DOWN;
	}

	private void isMoveLeft() {
		this.direction = Directions.LEFT;
	}

	private void isMoveRight() {
		this.direction = Directions.RIGHT;
	}

	private void isMoveNone() {
		this.direction = Directions.NONE;
	}

	@Override
	public Directions getDirections(){
		return this.direction;
	}
	
	@Override
	public void notifyMove(final int keyCode){
		switch (keyCode) {
			case 37: 
				isMoveLeft();
				break;
			case 38: 
				isMoveUp();
				break;
			case 39: 
				isMoveRight();
				break;
			case 40: 
				isMoveDown();
				break;
			default:
				isMoveNone(); 
				break;
		}

	}

	@Override
	public void notifyNoMove() {
		isMoveNone();
	}


}
