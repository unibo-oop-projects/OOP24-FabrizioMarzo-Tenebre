package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

public interface BoundingBox {

    boolean isColliding(final BoundingBox otherBBox);
    
    Pair<Double, Double> getULcorner();

    Pair<Double, Double> getBRcorner();

    void updateBBox(final Pair<Double,Double> newPos);
}
