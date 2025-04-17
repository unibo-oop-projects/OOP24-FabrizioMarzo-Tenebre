package input.input_controller;

public enum Directions {
    LEFT(37),
    UP(38),
    RIGHT(39),
    DOWN(40),
    NONE(-1);

    private final int keyCode;

    Directions(int keyCode) {
        this.keyCode = keyCode;
    }
    
    public int getKeyCode() {
        return keyCode;
    }

}
