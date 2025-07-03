package input.input_component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import utils.PairUtils;
import input.command.CommandSurvivor;
import input.input_controller.KeyCodes;
import input.input_controller.InputController;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorState;

public class InputCommonComponent implements InputSurvivorComponent {

    private static final Pair<Double, Double> LEFT_DIRECTION = Pair.of(-1d, 0d);
    private static final Pair<Double, Double> RIGHT_DIRECTION = Pair.of(1d, 0d);
    private static final Pair<Double, Double> UP_DIRECTION = Pair.of(0d, 1d);
    private static final Pair<Double, Double> DOWN_DIRECTION = Pair.of(0d, -1d);

    private final Map<Integer, Consumer<Survivor>> movementActions = new HashMap<>();
    private final Map<Integer, Consumer<Survivor>> shootingActions = new HashMap<>();

    public InputCommonComponent() {
        movementActions.put(KeyCodes.KEY_W.getKeyCode(),
                s -> moveSurvivor(s, UP_DIRECTION, SurvivorState.SURVIVOR_MOVE_UP));
        movementActions.put(KeyCodes.KEY_S.getKeyCode(),
                s -> moveSurvivor(s, DOWN_DIRECTION, SurvivorState.SURVIVOR_MOVE_DOWN));
        movementActions.put(KeyCodes.KEY_A.getKeyCode(),
                s -> moveSurvivor(s, LEFT_DIRECTION, SurvivorState.SURVIVOR_MOVE_LEFT));
        movementActions.put(KeyCodes.KEY_D.getKeyCode(),
                s -> moveSurvivor(s, RIGHT_DIRECTION, SurvivorState.SURVIVOR_MOVE_RIGHT));
        movementActions.put(KeyCodes.NONE.getKeyCode(), s -> {
            s.setVelocity(Pair.of(0d, 0d));
            s.setState(SurvivorState.SURVIVOR_IDLE);
        });

        shootingActions.put(KeyCodes.ARROW_UP.getKeyCode(),
                s -> shootInDirection(s, UP_DIRECTION, SurvivorState.SURVIOR_SHOOT_UP));
        shootingActions.put(KeyCodes.ARROW_DOWN.getKeyCode(),
                s -> shootInDirection(s, DOWN_DIRECTION, SurvivorState.SURVIOR_SHOOT_DOWN));
        shootingActions.put(KeyCodes.ARROW_LEFT.getKeyCode(),
                s -> shootInDirection(s, LEFT_DIRECTION, SurvivorState.SURVIOR_SHOOT_LEFT));
        shootingActions.put(KeyCodes.ARROW_RIGHT.getKeyCode(),
                s -> shootInDirection(s, RIGHT_DIRECTION, SurvivorState.SURVIOR_SHOOT_RIGHT));
    }

    @Override
    public void update(final Survivor sur, final InputController ctrl) {
        int inputCode = ctrl.getInputCode();

        if (movementActions.containsKey(inputCode)) {
            CommandSurvivor.issue(sur, movementActions.get(inputCode));
        } else if (shootingActions.containsKey(inputCode)) {
            CommandSurvivor.issue(sur, shootingActions.get(inputCode));
        }
    }

    private void moveSurvivor(Survivor s, Pair<Double, Double> dir, SurvivorState state) {
        s.setVelocity(PairUtils.mulScale(dir, PairUtils.module(s.getBaseSurvivorVel())));
        s.setState(state);
    }

    private void shootInDirection(Survivor s, Pair<Double, Double> dir, SurvivorState state) {
        s.setState(state);
        s.setVelocity(Pair.of(0d, 0d));
        s.getWeapon().aim(dir, s.getCurrentPos());
    }
}
