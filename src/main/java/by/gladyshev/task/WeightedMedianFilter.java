package by.gladyshev.task;

import java.awt.*;
import java.util.Arrays;

public class WeightedMedianFilter extends MedianFilter{
    //Cуть этого фильтра в том, что из под маски мы достаем медиану множества пикселей под маской матрицы пикселей
    //Но у каждого элемента под маской свой вес (множитель),
    //И присваеваем его текущему пикселю
    private static WeightedMedianFilter instance;
    private WeightedMedianFilter(){
        mask = new int[3][];
        mask[0] = new int[]{0, 1, 0};
        mask[1] = new int[]{1, 3, 1};
        mask[2] = new int[]{0, 1, 0};
    }
    public static WeightedMedianFilter getInstance() {
        if(instance==null) {
            instance = new WeightedMedianFilter();
        }
        return instance;
    }
    @Override
    protected int findMed(Color[][] underMask){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = underMask[i][j].getBlue()*mask[i][j];
                if(temp>255)
                {
                    temp=255;
                }
                underMask[i][j] = new Color(temp, temp, temp);
            }
        }
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
