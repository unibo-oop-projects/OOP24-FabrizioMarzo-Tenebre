package input.input_controller;

public interface InputController {
    void isMoveUp();
    void isMoveDown();
    void isMoveLeft();
    void isMoveRight();
    void isMoveNone();
    public Directions getDirections();
}
