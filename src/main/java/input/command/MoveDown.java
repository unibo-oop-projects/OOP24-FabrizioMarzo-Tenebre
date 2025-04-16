package input.command;

import org.apache.commons.lang3.tuple.Pair;

import model.PairUtils;
import model.entities.survivor.base.Survivor;

public class MoveDown implements Command {

    @Override
    public void execute(final Survivor sur) {
        //double speed = PairUtils.module(sur.getCurrentVel());
        sur.setVelocity(PairUtils.mul(Pair.of(0d,-1d), PairUtils.module(sur.getCurrentVel())));

    }
    
}
