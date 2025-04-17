package model.entities.survivor;

public enum SurvivorState {
    IDLE(0),
    MOVE_LEFT(1),
    MOVE_RIGHT(2),
    MOVE_UP(3),
    MOVE_DOWN(4),
    ATTACK(5),
    DAMAGE(6),
    DEAD(7);

    private final int index;

    SurvivorState(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
