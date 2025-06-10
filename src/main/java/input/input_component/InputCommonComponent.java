package input.input_component;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;

public class InputCommonComponent implements InputSurvivorComponent{
    
    private static final Pair<Double, Double> LEFT_DIRECTION = Pair.of(-1d, 0d);
    private static final Pair<Double, Double> RIGHT_DIRECTION = Pair.of(1d, 0d);
    private static final Pair<Double, Double> UP_DIRECTION = Pair.of(0d, 1d);
    private static final Pair<Double, Double> DOWN_DIRECTION = Pair.of(0d, -1d);
    
    @Override
    public void update(final Survivor sur, final InputController ctrl) {

        int inputCode = ctrl.getInputCode();

        if (inputCode == KeyCodes.KEY_W.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(UP_DIRECTION, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_UP);
            });
        } else if (inputCode == KeyCodes.KEY_S.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(DOWN_DIRECTION, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_DOWN);
            });
        } else if (inputCode == KeyCodes.KEY_A.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(LEFT_DIRECTION, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_LEFT);
            });
        } else if (inputCode == KeyCodes.KEY_D.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(RIGHT_DIRECTION, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_RIGHT);
            });
        } else if (inputCode == KeyCodes.NONE.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(Pair.of(0d, 0d));
                s.setState(SurvivorState.SURVIVOR_IDLE);
            });
        } else if (inputCode == KeyCodes.ARROW_UP.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_UP);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(UP_DIRECTION, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_DOWN.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_DOWN);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(DOWN_DIRECTION, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_LEFT.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_LEFT);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(LEFT_DIRECTION, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_RIGHT.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_RIGHT);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(RIGHT_DIRECTION, s.getCurrentPos());
            });
        }
    }
}
    
