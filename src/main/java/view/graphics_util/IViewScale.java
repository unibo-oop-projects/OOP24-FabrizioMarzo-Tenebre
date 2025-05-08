package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

public interface IViewScale {
    
    void setNewRatio(final int newPanelH, final int newPanelW);

    int getXinPixel(final Pair<Double,Double> pos);

    int getYinPixel(final Pair<Double,Double> pos);

}
