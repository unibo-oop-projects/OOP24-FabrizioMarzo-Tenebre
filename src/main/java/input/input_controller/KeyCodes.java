package input.input_controller;

/**
 * Enumeration representing directional inputs mapped to specific key codes.
 * 
 * <p>Each direction corresponds to an arrow key or a "no input" state,
 * identified by its associated key code.</p>
 */
public enum KeyCodes {

    /** Represents the left arrow key (key code 37). */
    LEFT(37),

    /** Represents the up arrow key (key code 38). */
    UP(38),

    /** Represents the right arrow key (key code 39). */
    RIGHT(39),

    /** Represents the down arrow key (key code 40). */
    DOWN(40),

    /** Represents the absence of any directional input (key code -1). */
    NONE(-1);

    private final int keyCode;

    /**
     * Constructs a new {@code Direction} with the specified key code.
     *
     * @param keyCode the key code associated with this direction
     */
    KeyCodes(final int keyCode) {
        this.keyCode = keyCode;
    }
    
    /**
     * Returns the key code associated with this direction.
     *
     * @return the key code
     */
    public int getKeyCode() {
        return keyCode;
    }

}
