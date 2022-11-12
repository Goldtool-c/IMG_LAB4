package by.gladyshev.task;

public class LaplasFilter extends Filter{
    private static LaplasFilter instance;
    private LaplasFilter(){
        mask = new int[3][];
        mask[0] = new int[]{0, -1, 0};
        mask[1] = new int[]{-1, 5, -1};
        mask[2] = new int[]{0, -1, 0};
    }
    public static LaplasFilter getInstance() {
        if(instance==null) {
            instance = new LaplasFilter();
        }
        return instance;
    }
}
