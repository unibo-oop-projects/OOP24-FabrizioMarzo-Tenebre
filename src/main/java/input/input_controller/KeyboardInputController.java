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
	private void isShootUp() {
		this.keyCode = KeyCodes.ARROW_UP;
	}

	/**
     * Sets the current direction to DOWN.
     */
	private void isShootDown() {
		this.keyCode = KeyCodes.ARROW_DOWN;
	}

	/**
     * Sets the current direction to LEFT.
     */
	private void isShootLeft() {
		this.keyCode = KeyCodes.ARROW_LEFT;
	}

	private void isMoveUp() {
		this.keyCode = KeyCodes.KEY_W;
	}

	private void isMoveDown() {
		this.keyCode = KeyCodes.KEY_S;
	}

	private void isMoveLeft() {
		this.keyCode = KeyCodes.KEY_A;
	}

	private void isMoveRight() {
		this.keyCode = KeyCodes.KEY_D;
	}

	/**
     * Sets the current direction to RIGHT.
     */
	private void isShootRight() {
		this.keyCode = KeyCodes.ARROW_RIGHT;
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
				isShootLeft();
				break;
			case 38: 
				isShootUp();
				break;
			case 39: 
				isShootRight();
				break;
			case 40: 
				isShootDown();
				break;
			case 32:
				isPresSpace();
				break;
			case 87:
				isMoveUp();;
				break;
			case 65:
				isMoveLeft();
				break;
			case 83:
				isMoveDown();
				break;
			case 68:
				isMoveRight();
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
