package model.bounding_box;

import org.apache.commons.lang3.tuple.Pair;

public class RectBoundingBox implements BoundingBox {

    private Pair<Double,Double> cornerUl;
    private Pair<Double,Double> cornerBR;
    
    public RectBoundingBox(final Pair<Double, Double> cornerUl,final Pair<Double, Double> cornerBR) {
        this.cornerUl = cornerUl;
        this.cornerBR = cornerBR;
    }

    public Pair<Double, Double> getULcorner(){
        return this.cornerUl;
    }

    public Pair<Double, Double> getBRcorner(){
        return this.cornerBR;
    }

    public void updateCorners(Pair<Double, Double> newUl, Pair<Double, Double> newBr){
        this.cornerUl = newUl;
        this.cornerBR = newBr;
    }

    @Override
    public boolean isColliding(final Pair<Double, Double> otherUL,final Pair<Double, Double> otherBR) {
        final double thisLeft = this.cornerUl.getLeft();
        final double thisTop = this.cornerUl.getRight();
        final double thisRight = this.cornerBR.getLeft();
        final double thisBottom = this.cornerBR.getRight();

        final double otherLeft = otherUL.getLeft();
        final double otherTop = otherUL.getRight();
        final double otherRight = otherBR.getLeft();
        final double otherBottom = otherBR.getRight();

    
        boolean noOverlap = thisRight < otherLeft || 
                            thisLeft > otherRight ||  
                            thisBottom < otherTop ||  
                            thisTop > otherBottom;    

        return !noOverlap;
    }
    
}
