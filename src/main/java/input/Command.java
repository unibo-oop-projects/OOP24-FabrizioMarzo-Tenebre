package input;

import model.level.Level;

public interface Command {
    void execute(final Level level);
}
