package by.gladyshev.task;

import java.awt.*;
import java.util.Arrays;

public class MedianFilter extends Filter {
    //Cуть этого фильтра в том, что из под маски мы достаем медиану множества пикселей под маской матрицы пикселей
    //И присваеваем его текущему пикселю
    private static MedianFilter instance;
    protected MedianFilter(){
    }
    public static MedianFilter getInstance() {
        if(instance==null) {
            instance = new MedianFilter();
        }
        return instance;
    }
    @Override
    public Color[][] filter(Color[][] pixels)
    {
        Color[][] result = new Color[pixels.length][pixels[0].length];
        for (int i = 1; i < pixels.length-1; i++) {
            for (int j = 1; j < pixels[0].length-1; j++) {
                Color[][] underMask = getUnderMask(pixels, i, j);
                int grey = findMed(underMask);
                result[i][j] = new Color(grey, grey, grey);
            }
        }
        for (int i = 0; i < pixels.length; i++) {
            result[i][0] = pixels[i][0];
            result[i][result[0].length-1] = pixels[i][result[0].length-1];
        }
        for (int i = 0; i < pixels[0].length; i++) {
            result[0][i] = pixels[0][i];
            result[result.length-1][i] = pixels[result.length-1][i];
        }
        return result;
    }
    protected int findMed(Color[][] underMask)
    {
        int arr[] = new int[9];
        for (int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                arr[i*3+j] = underMask[i][j].getBlue();
            }
        }
        Arrays.sort(arr);
        return arr[4];
    }

}
