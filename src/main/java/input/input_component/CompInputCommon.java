package input.input_component;

import org.apache.commons.lang3.tuple.Pair;

import model.PairUtils;
import model.entities.survivor.SurvivorState;
import model.entities.survivor.base.Survivor;
import input.command.CommandSurvivor;
import input.input_controller.Directions;
import input.input_controller.InputController;

public class CompInputCommon implements CompInputSurvivor{

    @Override
    public void update(final Survivor sur ,final InputController ctrl) {


        if (sur.getCurrentVel().equals(Pair.of(0d, 0d))){
            sur.setVelocity(sur.getBaseSurvivorVel());
        }

        if (ctrl.getDirections().equals(Directions.UP)) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(0d,-1d), PairUtils.module(s.getCurrentVel())));
                s.setState(SurvivorState.MOVE_UP);
            });
        } else if (ctrl.getDirections().equals(Directions.DOWN)) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(0d,1d), PairUtils.module(s.getCurrentVel())));
                s.setState(SurvivorState.MOVE_DOWN);
            });
        } else if (ctrl.getDirections().equals(Directions.LEFT)) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(-1d,0d), PairUtils.module(s.getCurrentVel())));
                s.setState(SurvivorState.MOVE_LEFT);
            });
        } else if (ctrl.getDirections().equals(Directions.RIGHT)) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(1d,0d), PairUtils.module(s.getCurrentVel())));
                s.setState(SurvivorState.MOVE_RIGHT);
            });
        } else if (ctrl.getDirections().equals(Directions.NONE)){
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(Pair.of(0d,0d));
                s.setState(SurvivorState.IDLE);
            });
        }
    }


    
    }
    
