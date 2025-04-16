package input.input_survivor;

import org.apache.commons.lang3.tuple.Pair;

import model.PairUtils;
import model.entities.survivor.base.Survivor;
import input.command.CommandSurvivor;
import input.input_controller.InputController;

public class CommonInput implements InputComponent{

    @Override
    public void update(final Survivor sur ,final InputController ctrl) {
        if (ctrl.isMoveUp()) {
            CommandSurvivor.issue(sur, (s)-> s.setVelocity(PairUtils.mul(Pair.of(0d,-1d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveDown()) {
            CommandSurvivor.issue(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(0d,1d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveLeft()) {
            CommandSurvivor.issue(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(-1d,0d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveRight()) {
            CommandSurvivor.issue(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(1d,0d), PairUtils.module(s.getCurrentVel()))));
        }
    }
    }
    
