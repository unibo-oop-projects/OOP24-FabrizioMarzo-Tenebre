package input.input_controller;

/**
 * Implementation of {@link InputController} that interprets keyboard input.
 * 
 * <p>This controller maps specific key codes to movement directions using
 * the {@link KeyCodes} enum. It updates its internal state based on
 * the key code received through {@link #notifyInput(int)}.</p>
 */
public class KeyboardInputController implements InputController {    
	
	private KeyCodes keyCode = KeyCodes.NONE;

	/**
     * Sets the current direction to UP.
     */
	private void isMoveUp() {
		this.keyCode = KeyCodes.UP;
	}

	/**
     * Sets the current direction to DOWN.
     */
	private void isMoveDown() {
		this.keyCode = KeyCodes.DOWN;
	}

	/**
     * Sets the current direction to LEFT.
     */
	private void isMoveLeft() {
		this.keyCode = KeyCodes.LEFT;
	}

	/**
     * Sets the current direction to RIGHT.
     */
	private void isMoveRight() {
		this.keyCode = KeyCodes.RIGHT;
	}

	/**
     * Sets the current direction to NONE (no input).
     */
	private void isMoveNone() {
		this.keyCode = KeyCodes.NONE;
	}

	/**
     * Sets the SPACE.
     */
	private void isPresSpace(){
		this.keyCode = KeyCodes.SPACE;
	}

	/**
     * Returns the current movement direction.
     *
     * @return the current {@link KeyCodes} value representing the movement direction
     */
	@Override
	public int getInputCode(){
		return this.keyCode.getKeyCode();
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
	public void notifyInput(final int keyCode){
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
			case 32:
				isPresSpace();
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
	public void notifyNoInput() {
		isMoveNone();
	}


}
