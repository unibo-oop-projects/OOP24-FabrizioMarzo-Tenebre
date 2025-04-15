package input.command;

import model.entities.survivor.base.Survivor;

public interface Command {
    void execute(final Survivor sur);
}
