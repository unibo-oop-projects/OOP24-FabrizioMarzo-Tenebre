package view.graphics_util;

import org.apache.commons.lang3.tuple.Pair;

public class ScalerFactoryImpl implements ScalerFactory {

    private class ScalerTemplate implements Scaler{

        private int sourceHeight; 
        private int sourceWidth;
        private int targetHeight;
        private double ratioX;
        private double ratioY;

        protected ScalerTemplate(final int sourceHeight, final int sourceWidth, final int targetHeight, final int targetWidth){
            this.sourceHeight = sourceHeight;
            this.sourceWidth = sourceWidth;
            this.targetHeight = targetHeight;
            this.ratioX = (double) targetWidth / sourceWidth;
            this.ratioY = (double) targetHeight / sourceHeight;
        }

        @Override
        public void setScaleDimensions(final int height,final int width) {
            this.targetHeight = height;
            this.ratioX = (double) width / sourceWidth;
            this.ratioY = (double) height / sourceHeight;
        }

        @Override
        public int scaleX(final Pair<Double, Double> posX) {
            return (int) Math.round(posX.getLeft() * ratioX);
        }

        @Override
        public int scaleY(final Pair<Double, Double> posY) {
            return (int) Math.round(posY.getRight() * ratioY);
        }

        @Override
        public int getScaledHeight() {
            return this.targetHeight;
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

    @Override
    public Scaler viewScaler(final int modelH, final int modelW, final int viewH, final int viewW) {
        return new ScalerTemplate(modelH, modelW, viewH, viewW);
    }
    
}
