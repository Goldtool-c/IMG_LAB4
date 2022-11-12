package by.gladyshev;

import by.gladyshev.task.*;
import by.gladyshev.util.BMPtoMatrixParser;
import by.gladyshev.util.ColorBrightnessDecorator;
import by.gladyshev.util.FindGreyMinMax;
import by.gladyshev.util.PixelsToFileConverter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    //Коментов мало, ибо мне жутко лень их писать
    //11 утра субботы, ребят
    //Если что не понятно, пишите мне
    //Мне гораздо легче будет записать голосовое
    public static Color[][] grey;
    public static void main(String ...args) throws IOException {
        File file = new File("sw1.bmp");
        Color[][] pixels = new Color[0][0];
        try {
            pixels = BMPtoMatrixParser.getInstance().parse(file);//Преобразуем файл в матрицу пикселей
        } catch (IOException e) {
            e.printStackTrace();
        }
        setGrey(pixels);//Переводим изображение в чб
        FindGreyMinMax.getInstance().find(grey);
        PixelsToFileConverter.getInstance().convertAndSave(grey, "bw.jpg");//Созраняем чб
        Color[][] HAF = HomogeneousAveragingFilter.getInstance().filter(pixels);//Однородный усредняющий фильтр
        PixelsToFileConverter.getInstance().convertAndSave(HAF, "HAF.jpg");
        Color[][] HF = HaussFilter.getInstance().filter(pixels);//Фильтр Гаусса
        PixelsToFileConverter.getInstance().convertAndSave(HF, "HF.jpg");
        Color[][] LF = LaplasFilter.getInstance().filter(pixels);//Фильтр на основе Лапласиана
        PixelsToFileConverter.getInstance().convertAndSave(LF, "LF.jpg");
        Color[][] BM = UnsharpMask.getInstance().filter(grey, HAF);//Фильтрация методом нерезкого маскирования
        PixelsToFileConverter.getInstance().convertAndSave(BM, "UM.jpg");
        Color[][] MF = MedianFilter.getInstance().filter(grey);//Медианный фильтр
        PixelsToFileConverter.getInstance().convertAndSave(MF, "MF.jpg");
        Color[][] WMF = WeightedMedianFilter.getInstance().filter(grey);//Взвешенный медианный фильтр
        PixelsToFileConverter.getInstance().convertAndSave(WMF, "WMF.jpg");
        //Фильтры минимума и максимума
        Color[][] FMin = FilterMinimum.getInstance().filter(grey);
        PixelsToFileConverter.getInstance().convertAndSave(FMin, "FMin.jpg");
        Color[][] FMax = FilterMaximum.getInstance().filter(grey);
        PixelsToFileConverter.getInstance().convertAndSave(FMax, "FMax.jpg");

    }
    private static void setGrey(Color[][] pixels)
    {
        grey = new Color[pixels.length][pixels[0].length];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                int temp = (int) new ColorBrightnessDecorator(pixels[i][j]).getBrightness();
                grey[i][j] = new Color(temp, temp, temp);
            }
        }
    }
}
