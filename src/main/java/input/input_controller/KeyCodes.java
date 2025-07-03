package input.input_controller;

/**
 * Enumeration representing directional inputs mapped to specific key codes.
 * 
 * <p>
 * Each direction corresponds to an arrow key or a "no input" state,
 * identified by its associated key code.
 * </p>
 */
public enum KeyCodes {

    ARROW_UP(38),
    ARROW_DOWN(40),
    ARROW_LEFT(37),
    ARROW_RIGHT(39),

    KEY_W(87),
    KEY_A(65),
    KEY_S(83),
    KEY_D(68),

    SPACE(32),
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
