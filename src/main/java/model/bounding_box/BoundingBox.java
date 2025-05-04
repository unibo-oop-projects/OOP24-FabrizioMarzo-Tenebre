package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

public interface BoundingBox {
    public boolean isColliding(Pair<Double,Double> cornerUL,Pair<Double,Double> cornerBR );
}
