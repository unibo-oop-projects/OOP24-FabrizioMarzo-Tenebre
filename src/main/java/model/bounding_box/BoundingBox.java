package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

public interface BoundingBox {

    public boolean isColliding(final Pair<Double,Double> cornerUL,final Pair<Double,Double> cornerBR );

    public Pair<Double, Double> getULcorner();

    public Pair<Double, Double> getBRcorner();

    public void setUlcorner(Pair<Double, Double> cornerUl);
    
    public void setBRcorner(Pair<Double, Double> cornerBR);
}
