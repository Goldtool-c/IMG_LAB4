package by.gladyshev.task;

public class HomogeneousAveragingFilter extends Filter{
    private static HomogeneousAveragingFilter instance;

    private HomogeneousAveragingFilter(){
        mask = new int[3][3];
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                mask[i][j] = 1;
            }
        }
    }
    public static HomogeneousAveragingFilter getInstance() {
        if(instance==null) {
            instance = new HomogeneousAveragingFilter();
        }
        return instance;
    }
}
