package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

public class ViewScale implements IViewScale {

    private int modelH; 
    private int modelW;
    private double ratioX;
    private double ratioY;

    public ViewScale(final int modelH, final int modelW, final int viewH, final int viewW) {
        this.modelH = modelH;
        this.modelW = modelW;
        this.ratioX = (double) viewW / modelW;
        this.ratioY = (double) viewH / modelH;
    }

    public void setNewRatio(final int newPanelH, final int newPanelW){
        this.ratioX = (double) newPanelW / modelW;
        this.ratioY = (double) newPanelH / modelH;
    }

    public int getXinPixel(final Pair<Double,Double> pos){
        return (int) Math.round(pos.getLeft() * ratioX);
    }

    public int getYinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getRight() * ratioY);
    }

    @Override
    public double getRatioX() {
        return this.ratioX;
    }

    @Override
    public double getRatioY() {
        return this.ratioY;
    }

    

}
