package by.gladyshev.task;

import java.awt.*;

public class FilterMaximum extends MedianFilter{
    //Cуть этого фильтра в том, что из под маски мы достаем максимальный элемент матрицы пикселей
    //И присваеваем его текущему пикселю
    private static FilterMaximum instance;
    private FilterMaximum(){
    }
    public static FilterMaximum getInstance() {
        if(instance==null) {
            instance = new FilterMaximum();
        }
        return instance;
    }
    @Override
    protected int findMed(Color[][] underMask){
        int max = 0;
        for (int i = 0; i < underMask.length; i++) {
            for (int j = 0; j < underMask[0].length; j++) {
                if(underMask[i][j].getBlue()>max)
                {
                    max = underMask[i][j].getBlue();
                }
            }
        }
        return max;
    }
}
