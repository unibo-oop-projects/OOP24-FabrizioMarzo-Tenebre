package physics.physics_component;

import model.entities.entities_base.survivor_base.Survivor;
import model.level.Level;

public interface PhysicsSurvivorComponent {
    public void update(final Survivor sur, final int dt, final Level lvl);
}
