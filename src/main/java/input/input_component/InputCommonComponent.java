package input.input_component;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;

public class InputCommonComponent implements InputSurvivorComponent{
    
    private Pair<Double, Double> lastAimDirection = Pair.of(0d, -1d);

    @Override
    public void update(final Survivor sur, final InputController ctrl) {

        int inputCode = ctrl.getInputCode();

        if (inputCode == KeyCodes.UP.getKeyCode()) {
            lastAimDirection = Pair.of(0d, 1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastAimDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_UP);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.DOWN.getKeyCode()) {
            lastAimDirection = Pair.of(0d, -1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastAimDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIOR_SHOOT_DOWN);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.LEFT.getKeyCode()) {
            lastAimDirection = Pair.of(-1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastAimDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_LEFT);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.RIGHT.getKeyCode()) {
            lastAimDirection = Pair.of(1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastAimDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIOR_SHOOT_RIGHT);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.NONE.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(Pair.of(0d, 0d));
                s.setState(SurvivorState.SURVIVOR_IDLE);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());

            });
        } else if (inputCode == KeyCodes.SPACE.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                Pair<Double,Double> direction = PairUtils.normalize(s.getCurrentVel());
                if (direction.equals(Pair.of(1d,0d))){
                    s.setState(SurvivorState.SURVIOR_SHOOT_RIGHT);
                    s.setVelocity(Pair.of(0d, 0d));
                } else if(direction.equals(Pair.of(-1d,0d))) {
                    s.setState(SurvivorState.SURVIOR_SHOOT_LEFT);
                    s.setVelocity(Pair.of(0d, 0d));
                } else if(direction.equals(Pair.of(0d,1d))) {
                    s.setState(SurvivorState.SURVIOR_SHOOT_UP);
                    s.setVelocity(Pair.of(0d, 0d));
                } else {
                    s.setState(SurvivorState.SURVIOR_SHOOT_DOWN);
                    s.setVelocity(Pair.of(0d, 0d));
                }
            });
        }
    }

}
    
