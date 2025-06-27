package view.graphics_util;

public interface ScalerFactory {

    Scaler viewScaler(final int modelH, final int modelW, final int viewH, final int viewW);

}
