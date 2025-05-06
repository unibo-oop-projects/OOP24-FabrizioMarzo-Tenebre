package view.scene;

import input.input_controller.InputController;

public interface Scene {
    void render();

    void setInputController(InputController inputContl);
}
