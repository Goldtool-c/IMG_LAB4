package by.gladyshev.task;

public class HaussFilter extends Filter{
    private static HaussFilter instance;
    private HaussFilter(){
        mask = new int[3][];
        mask[0] = new int[]{1, 4, 1};
        mask[1] = new int[]{4, 12, 4};
        mask[2] = new int[]{1, 4, 1};
    }
    public static HaussFilter getInstance() {
        if(instance==null) {
            instance = new HaussFilter();
        }
        return instance;
    }
}
