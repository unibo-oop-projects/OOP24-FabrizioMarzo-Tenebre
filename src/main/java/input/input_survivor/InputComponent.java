package input.input_survivor;

import input.command.Command;

public interface InputComponent {
    Command update(final InputController c);
}
