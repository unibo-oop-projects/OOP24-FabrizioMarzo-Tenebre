package model.level.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.entities.survivor.Survivor;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieFactory;
import model.level.Level;

public class LevelManagerBase implements LevelManager{

    private static final int INIT_ZOMBIES_LEVEL = 5;
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_BETWEEN_WAVES = 25;
    private static final int ZOMBIES_FOR_WAVE = 1;
    private static final double SPAWN_POS_MIN_MULTIPLIER = -1.0;
    private static final double SPAWN_POS_MAX_MULTIPLIER = 2.0;

    private final Level level;
    private final ZombieFactory zombieFactory = new ZombieFactory();
    private final AtomicLong elapsedTime = new AtomicLong(0);
    private List<Munition> newMunitions = new ArrayList<>();

    private int currentWave;

    public LevelManagerBase(final Level level) {
        this.level = level;
        this.currentWave = 1;
        this.spawnZombies(INIT_ZOMBIES_LEVEL);
    }

    @Override
    public void update(final int dt) {
        this.checkSurvivorAttack(level.getSurvivorOnLevel(), dt);
        elapsedTime.addAndGet(dt);

        final int seconds = (int) (elapsedTime.get() / MILLIS_IN_SECOND);

        if (seconds >= (currentWave) * SECONDS_BETWEEN_WAVES) {
            spawnZombies(ZOMBIES_FOR_WAVE);
            currentWave++; 
        }
    }

    private void spawnZombies(final int count) {
        List<Zombie> newZombies = IntStream.range(0, count)
            .mapToObj(i -> zombieFactory.createClickerZombie(randomPos()))
            .collect(Collectors.toList());

        level.getZombieOnLevel().addAll(newZombies);
    }

    private Pair<Double, Double> randomPos() {
        double w = ThreadLocalRandom.current().nextDouble(
            SPAWN_POS_MIN_MULTIPLIER * level.getLevelWidth(),
            SPAWN_POS_MAX_MULTIPLIER * level.getLevelWidth());
    
        double h = ThreadLocalRandom.current().nextDouble(
            SPAWN_POS_MIN_MULTIPLIER * level.getLevelHeight(),
            SPAWN_POS_MAX_MULTIPLIER * level.getLevelHeight());
    
        return Pair.of(w, h);
    }

    private void checkSurvivorAttack(final Survivor sur, final int dt){
        if (sur.getState().getIndex() >=5 && sur.getState().getIndex() <=8) {
            this.newMunitions.addAll(sur.getWeapon().shoot(dt));
            if (!newMunitions.isEmpty()) {
                this.level.getProjectilesOnLevel().addAll(newMunitions);
            }
            this.newMunitions.clear();
        }
    }
}
