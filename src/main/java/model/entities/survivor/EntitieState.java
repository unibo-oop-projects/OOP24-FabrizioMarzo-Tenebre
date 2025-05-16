package model.entities.survivor;

/**
 * Enum representing the possible states of a survivor.
 * Each state corresponds to a particular action or condition that a survivor can be in during gameplay.
 */
public enum EntitieState {

    /**
     * Represents the idle state where the survivor is not performing any action.
     */
    IDLE(0),

    /**
     * Represents the state where the survivor is moving left.
     */
    MOVE_LEFT(1),

    /**
     * Represents the state where the survivor is moving right.
     */
    MOVE_RIGHT(2),

    /**
     * Represents the state where the survivor is moving upwards.
     */
    MOVE_UP(3),

    /**
     * Represents the state where the survivor is moving downwards.
     */
    MOVE_DOWN(4),

    /**
     * Represents the state where the survivor is performing an attack.
     */
    ATTACK(5),

    /**
     * Represents the state where the survivor has taken damage.
     */
    DAMAGE(6),

    /**
     * Represents the state where the survivor is dead.
     */
    DEAD(7);

    private final int index;

    /**
     * Constructor for initializing the {@link EntitieState} with a specific index.
     *
     * @param index the unique index associated with the state
     */
    EntitieState(final int index) {
        this.index = index;
    }

    /**
     * Gets the index associated with the survivor state.
     *
     * @return the index of the survivor state
     */
    public int getIndex() {
        return index;
    }
}
