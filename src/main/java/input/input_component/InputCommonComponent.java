package input.input_component;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;

public class InputCommonComponent implements InputSurvivorComponent{
    
    private Pair<Double, Double> lastAimDirection;
    private Pair<Double, Double> lastSurviorDirection;


    @Override
    public void update(final Survivor sur, final InputController ctrl) {

        int inputCode = ctrl.getInputCode();

        if (inputCode == KeyCodes.KEY_W.getKeyCode()) {
            lastSurviorDirection = Pair.of(0d, 1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastSurviorDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_UP);
            });
        } else if (inputCode == KeyCodes.KEY_S.getKeyCode()) {
            lastSurviorDirection = Pair.of(0d, -1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastSurviorDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_DOWN);
            });
        } else if (inputCode == KeyCodes.KEY_A.getKeyCode()) {
            lastSurviorDirection = Pair.of(-1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastSurviorDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_LEFT);
            });
        } else if (inputCode == KeyCodes.KEY_D.getKeyCode()) {
            lastSurviorDirection = Pair.of(1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(PairUtils.mulScale(lastSurviorDirection, PairUtils.module(s.getBaseSurvivorVel())));
                s.setState(SurvivorState.SURVIVOR_MOVE_RIGHT);
            });


        } else if (inputCode == KeyCodes.NONE.getKeyCode()) {
            CommandSurvivor.issue(sur, (s) -> {
                s.setVelocity(Pair.of(0d, 0d));
                s.setState(SurvivorState.SURVIVOR_IDLE);
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());

            });



        } else if (inputCode == KeyCodes.ARROW_UP.getKeyCode()) {
            lastAimDirection = Pair.of(0d, 1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_UP);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_DOWN.getKeyCode()) {
            lastAimDirection = Pair.of(0d, -1d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_DOWN);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_LEFT.getKeyCode()) {
            lastAimDirection = Pair.of(-1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_LEFT);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
        } else if (inputCode == KeyCodes.ARROW_RIGHT.getKeyCode()) {
            lastAimDirection = Pair.of(1d, 0d);
            CommandSurvivor.issue(sur, (s) -> {
                s.setState(SurvivorState.SURVIOR_SHOOT_RIGHT);
                s.setVelocity(Pair.of(0d, 0d));
                s.getWeapon().aim(lastAimDirection, s.getCurrentPos());
            });
    }

}
}
    
