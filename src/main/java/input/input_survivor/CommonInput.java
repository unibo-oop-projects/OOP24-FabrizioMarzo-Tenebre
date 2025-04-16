package input.input_survivor;

import org.apache.commons.lang3.tuple.Pair;

import input.command.Command;
import input.command.MoveDown;
import input.command.MoveLeft;
import input.command.MoveRight;
import model.PairUtils;
import model.entities.survivor.base.Survivor;
import input.command.Move;

public class CommonInput implements InputComponent{

    @Override
    public void update(final Survivor sur ,final InputController ctrl) {
        if (ctrl.isMoveUp()) {
            Move.moves(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(0d,11d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveDown()) {
            Move.moves(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(0d,-1d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveLeft()) {
            Move.moves(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(-1d,0d), PairUtils.module(s.getCurrentVel()))));
        } else if (ctrl.isMoveRight()) {
            Move.moves(sur, (s)->s.setVelocity(PairUtils.mul(Pair.of(1d,0d), PairUtils.module(s.getCurrentVel()))));
        }
    }
    }
    
