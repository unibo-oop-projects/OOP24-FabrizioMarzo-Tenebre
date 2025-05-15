package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

public class RectBoundingBox implements BoundingBox {

    private Pair<Double,Double> cornerUl;
    private Pair<Double,Double> cornerBR;
    
    public RectBoundingBox(final Pair<Double, Double> cornerUl,final Pair<Double, Double> cornerBR) {
        this.cornerUl = cornerUl;
        this.cornerBR = cornerBR;
    }

    @Override
    public boolean isColliding(final Pair<Double, Double> otherUL,final Pair<Double, Double> otherBR) {
        final double tux = this.cornerUl.getLeft();
        final double tuy = this.cornerUl.getRight();
        final double tbx = this.cornerBR.getLeft();
        final double tby = this.cornerBR.getRight();

        final double oux = otherUL.getLeft();
        final double ouy = otherUL.getRight();
        final double obx = otherBR.getLeft();
        final double oby = otherBR.getRight();

        boolean overlapY = tuy > oby && tby < ouy ;
        boolean overlapX = tux < obx && tbx > oux ; 

        System.out.println("overlapX " + overlapX + " overlapY " + overlapY);
        return overlapX && overlapY;
    }

    @Override
    public void setUlcorner(final Pair<Double, Double> cornerUl) {
        this.cornerUl = cornerUl;
    }
    
    @Override
    public void setBRcorner(final Pair<Double, Double> cornerBR) {
        this.cornerBR = cornerBR;
    }

    @Override
    public Pair<Double, Double> getULcorner(){
        return this.cornerUl;
    }

    @Override
    public Pair<Double, Double> getBRcorner(){
        return this.cornerBR;
    }

    


    
}
