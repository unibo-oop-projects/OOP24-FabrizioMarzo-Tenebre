package input.command;

import java.util.function.Consumer;

import model.entities.survivor.base.Survivor;

public class CommandSurvivor {

    public static void issue(final Survivor sur, Consumer<Survivor> command){
        command.accept(sur);
    }
    
}
