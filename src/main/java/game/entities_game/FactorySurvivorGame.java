package game.entities_game;

import org.apache.commons.lang3.tuple.Pair;

import input.input_component.InputCommonComponent;
import model.entities.survivor.Common;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import view.graphics_component.GraphicsCommonComponent;

/**
 * Factory class responsible for creating instances of {@link IGameSurvivor} entities.
 * This class uses the {@link SurvivorFactory} to create a survivor and then wraps it
 * with the necessary input and graphics components for the game.
 */
public class FactorySurvivorGame {

    public IGameSurvivor gameSurvivorCommon(final Survivor common){
        return new GameSurvivor(common,
                     new GraphicsCommonComponent(common.getClass().getSimpleName()),
                     new InputCommonComponent());
    }
}
