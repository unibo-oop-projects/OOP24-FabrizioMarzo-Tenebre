package model.entities.survivor;

public enum SurvivorState {

    SURVIVOR_IDLE(0),

    SURVIVOR_MOVE_LEFT(1),

    SURVIVOR_MOVE_RIGHT(2),

    SURVIVOR_MOVE_UP(3),

    SURVIVOR_MOVE_DOWN(4),

    SURVIOR_SHOOT_LEFT(5),

    SURVIOR_SHOOT_RIGHT(6),

    SURVIOR_SHOOT_UP(7),

    SURVIOR_SHOOT_DOWN(8),

    SURVIVOR_SUFFER_DAMAGE(9),

    SURVIVOR_DEAD(10);

    private final int index;

    SurvivorState(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}


