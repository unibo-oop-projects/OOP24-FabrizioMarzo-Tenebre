package input.input_controller;

/**
 * Implementation of {@link InputController} that interprets keyboard input.
 * 
 * <p>This controller maps specific key codes to movement directions using
 * the {@link Directions} enum. It updates its internal state based on
 * the key code received through {@link #notifyMove(int)}.</p>
 */
public class KeyboardInputController implements InputController {    
	
	private Directions direction = Directions.NONE;

	/**
     * Sets the current direction to UP.
     */
	private void isMoveUp() {
		this.direction = Directions.UP;
	}

	/**
     * Sets the current direction to DOWN.
     */
	private void isMoveDown() {
		this.direction = Directions.DOWN;
	}

	/**
     * Sets the current direction to LEFT.
     */
	private void isMoveLeft() {
		this.direction = Directions.LEFT;
	}

	/**
     * Sets the current direction to RIGHT.
     */
	private void isMoveRight() {
		this.direction = Directions.RIGHT;
	}

	/**
     * Sets the current direction to NONE (no input).
     */
	private void isMoveNone() {
		this.direction = Directions.NONE;
	}

	/**
     * Returns the current movement direction.
     *
     * @return the current {@link Directions} value representing the movement direction
     */
	@Override
	public Directions getDirections(){
		return this.direction;
	}
	
	/**
     * Notifies the controller of a new movement input based on the given key code.
     *
     * <p>The key codes are mapped as follows:</p>
     * <ul>
     *   <li>37 - LEFT</li>
     *   <li>38 - UP</li>
     *   <li>39 - RIGHT</li>
     *   <li>40 - DOWN</li>
     *   <li>Any other key - NONE</li>
     * </ul>
     *
     * @param keyCode the keyboard code for the movement direction
     */
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

	/**
     * Notifies the controller that no movement input is currently active.
     */
	@Override
	public void notifyNoMove() {
		isMoveNone();
	}


}
