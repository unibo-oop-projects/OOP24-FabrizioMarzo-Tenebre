package input.command;

import model.entities.survivor.base.Survivor;

public class Move {
    
    public static void moves(final Survivor sur, final Command com){
        com.execute(sur);
    }
}
