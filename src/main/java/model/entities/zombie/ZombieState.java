package model.entities.zombie;


public enum ZombieState {
     
    ZOMBIE_IDLE(0),

    ZOMBIE_MOVE_LEFT(1),

    ZOMBIE_MOVE_RIGHT(2),

    ZOMBIE_MOVE_UP(3),

    ZOMBIE_MOVE_DOWN(4),

    ZOMBIE_ATTACK(5),

    ZOMBIE_SUFFER_DAMAGE(6),

    ZOMBIE_DEAD(7);

    private final int index;

    ZombieState(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

}
