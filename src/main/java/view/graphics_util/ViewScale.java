package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

public class ViewScale implements IViewScale {

    private int levelH; 
    private int levelW;
    private int panelH;
    private int panleW;
    private double ratioX;
    private double ratioY;

    public ViewScale(final int levelH, final int levelW, final int panelH, final int panleW) {
        this.levelH = levelH;
        this.levelW = levelW;
        this.panelH = panelH;
        this.panleW = panleW;
    }

    public void setNewRatio(final int newPanelH, final int newPanelW){
        this.ratioX = (double) panleW / levelW;
        this.ratioY = (double) panelH / levelH;
    }

    public int getXinPixel(final Pair<Double,Double> pos){
        return (int) Math.round(pos.getLeft() * ratioX);
    }

    public int getYinPixel(Pair<Double,Double> pos){
        return (int) Math.round(pos.getRight() * ratioY);
    }

}
