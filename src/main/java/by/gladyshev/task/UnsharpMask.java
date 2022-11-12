package by.gladyshev.task;

import java.awt.*;

public class UnsharpMask {
    //Вычитаем из A*оригинал какое-нибудь расфокусированное изображение
    private static UnsharpMask instance;
    private UnsharpMask(){ }
    public static UnsharpMask getInstance() {
        if(instance==null) {
            instance = new UnsharpMask();
        }
        return instance;
    }
    public Color[][] filter(Color[][] original, Color[][] blurred)
    {
        Color[][] result = new Color[original.length][original[0].length];
        //int c = 0;
        //int c1 = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                int orig = original[i][j].getBlue();
                int blur = blurred[i][j].getBlue();
                int diff = 2*orig - blur;
                if(diff<0)
                {
                    //System.out.println("alert<0 "+i +" "+j+" "+c+" "+diff);
                    diff = 0;
                }
                if(diff>255)
                {
                    //System.out.println("alert>255 "+i +" "+j+" "+c1+" "+diff);
                    diff = 255;
                }
                result[i][j] = new Color(diff, diff, diff);
            }
        }
        //System.out.println(c1+c);
        return result;
    }
}
