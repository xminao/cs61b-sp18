package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] buckets = new int[M];
        for (Oomage o : oomages) {
            int code = (o.hashCode() & 0x7FFFFFFF) % M;
            buckets[code] += 1;
        }

        boolean nice = true;
        double min = oomages.size() / 50;
        double max = oomages.size() / 2.5;
        for (int i = 0; i < M; i += 1) {
            if (buckets[i] < min || buckets[i] > max) {
                nice = false;
            }
        }
        return nice;
    }
}
