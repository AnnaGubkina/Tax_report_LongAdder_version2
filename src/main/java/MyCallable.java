import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {

    String name;
    public static int ARRAY_SIZE = 50;
    public static int RANDOM_GENERATION = 1000;

    public MyCallable(String threadname) {
        this.name = threadname;
    }

    @Override
    public Long call() {
        long[] array = new long[ARRAY_SIZE];
        generationArray(array);
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void generationArray(long[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(RANDOM_GENERATION);
        }
    }
}

