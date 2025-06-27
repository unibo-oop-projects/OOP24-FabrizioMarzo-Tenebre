package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

public interface Scaler {
    
    void setScaleDimensions(final int height, final int width);

    int scaleX(final Pair<Double,Double> posX);

    int scaleY(final Pair<Double,Double> posY);

    int getScaledHeight();

    double getRatioX();

    double getRatioY();

}
