package input;

import org.apache.commons.lang3.tuple.Pair;

import model.PairUtils;
import model.entities.survivor.base.Survivor;
import model.level.Level;

public class MoveLeft implements Command {

    @Override
    public void execute(Level level) {
        Survivor sur = level.getSurvivorOnLevel().getSurvivor();
        double speed = PairUtils.module(sur.getCurrentVel());
        sur.setVelocity(PairUtils.mul(Pair.of(-1.0,0.0), speed));
    }
    
}
