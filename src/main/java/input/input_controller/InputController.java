package input.input_controller;

public interface InputController {
    public void notifyMove(final int keyCode);
    public void notifyNoMove();
    public Directions getDirections();
}
