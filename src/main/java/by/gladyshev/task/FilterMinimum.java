package by.gladyshev.task;

import java.awt.*;

public class FilterMinimum extends MedianFilter{
    //Cуть этого фильтра в том, что из под маски мы достаем минимальный элемент матрицы пикселей
    //И присваеваем его текущему пикселю
    private static FilterMinimum instance;
    private FilterMinimum(){
    }
    public static FilterMinimum getInstance() {
        if(instance==null) {
            instance = new FilterMinimum();
        }
        return instance;
    }
    @Override
    protected int findMed(Color[][] underMask){
        int min = underMask[0][0].getBlue();
        for (int i = 0; i < underMask.length; i++) {
            for (int j = 0; j < underMask[0].length; j++) {
                if(underMask[i][j].getBlue()<min)
                {
                    min = underMask[i][j].getBlue();
                }
            }
        }
        return min;
    }
}
