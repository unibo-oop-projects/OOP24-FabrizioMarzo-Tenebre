package input.input_component;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.EntitieState;

/**
 * Common implementation of {@link InputSurvivorComponent} that updates a {@link Survivor}'s
 * movement and state based on directional input.
 * 
 * <p>This component interprets input from an {@link InputController} and applies
 * corresponding changes to the Survivor's velocity and state using {@link CommandSurvivor}.</p>
 */
public class InputCommonComponent implements InputSurvivorComponent{

    /**
     * Updates the given {@link Survivor} based on directional input provided by the {@link InputController}.
     *
     * <p>If the Survivor's current velocity is zero, it resets it to the base velocity.
     * Then, based on the current direction (UP, DOWN, LEFT, RIGHT, or NONE), it modifies
     * the Survivor's velocity vector and updates the movement state accordingly.</p>
     *
     * @param sur the {@link Survivor} to be updated
     * @param ctrl the {@link InputController} providing directional input
     */
    @Override
    public void update(final Survivor sur ,final InputController ctrl) {


        if (sur.getCurrentVel().equals(Pair.of(0d, 0d))){
            sur.setVelocity(sur.getBaseSurvivorVel());
        }

        if (ctrl.getInputCode() == KeyCodes.UP.getKeyCode()) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(0d,1d), PairUtils.module(s.getCurrentVel())));
                s.setState(EntitieState.MOVE_UP);
            });
        } else if (ctrl.getInputCode() == KeyCodes.DOWN.getKeyCode()) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(0d,-1d), PairUtils.module(s.getCurrentVel())));
                s.setState(EntitieState.MOVE_DOWN);
            });
        } else if (ctrl.getInputCode() == KeyCodes.LEFT.getKeyCode()) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(-1d,0d), PairUtils.module(s.getCurrentVel())));
                s.setState(EntitieState.MOVE_LEFT);
            });
        } else if (ctrl.getInputCode() == KeyCodes.RIGHT.getKeyCode()) {
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(PairUtils.mul(Pair.of(1d,0d), PairUtils.module(s.getCurrentVel())));
                s.setState(EntitieState.MOVE_RIGHT);
            });
        } else if (ctrl.getInputCode() == KeyCodes.NONE.getKeyCode()){
            CommandSurvivor.issue(sur, (s)-> {
                s.setVelocity(Pair.of(0d,0d));
                s.setState(EntitieState.IDLE);
            });
        }
    }


    
    }
    
