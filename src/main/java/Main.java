
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;


public class Main {

    public static final int THREADS_COUNT = 3;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Long> myCallable1 = new MyCallable("В магазине 1");
        Callable<Long> myCallable2 = new MyCallable("В магазине 2");
        Callable<Long> myCallable3 = new MyCallable("В магазине 3");

        final ExecutorService threadPool = Executors.newFixedThreadPool(THREADS_COUNT);

        final Future<Long> task1 = threadPool.submit(myCallable1);
        final Future<Long> task2 = threadPool.submit(myCallable2);
        final Future<Long> task3 = threadPool.submit(myCallable3);

        showMassage(myCallable1, task1);
        showMassage(myCallable2, task2);
        showMassage(myCallable3, task3);

        LongAdder report = new LongAdder();
        addReport(report, task1);
        addReport(report, task2);
        addReport(report, task3);

        System.out.println("\nОтчет для налоговой: " +
                "\nСумма выручки со всех магазинов за день: " + report.sum() + " руб ");

        threadPool.shutdown();
    }

    public static void showMassage(Callable<Long> myCallable, Future<Long> task) throws ExecutionException, InterruptedException {
        System.out.println(myCallable + " сумма покупок за день " + task.get() + " руб ");
    }

    public static void addReport(LongAdder report, Future<Long> task) throws ExecutionException, InterruptedException {
        report.add(task.get());
    }
}
