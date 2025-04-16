package input.input_survivor;

import input.input_controller.InputController;
import model.entities.survivor.base.Survivor;

public interface InputComponent {
    void update(final Survivor sur,final InputController c);
}
