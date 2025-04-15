package input.input_survivor;

import input.command.Command;
import input.command.MoveDown;
import input.command.MoveLeft;
import input.command.MoveRight;
import input.command.MoveUp;

public class CommonInput implements InputComponent{

    @Override
    public Command update(InputController ctrl) {
        if (ctrl.isMoveUp()) {
            return new MoveUp();
        } else if (ctrl.isMoveDown()) {
            return new MoveDown();
        } else if (ctrl.isMoveLeft()) {
            return new MoveLeft();
        } else if (ctrl.isMoveRight()) {
            return new MoveRight();
        }
        return null;
    }
    }
    
