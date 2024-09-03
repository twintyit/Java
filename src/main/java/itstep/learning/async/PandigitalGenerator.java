package itstep.learning.async;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class PandigitalGenerator {
    private final StringBuffer buffer = new StringBuffer();
    private final List<Integer> digits = new ArrayList<>();

    private final ExecutorService pool = Executors.newFixedThreadPool(9);

    public PandigitalGenerator() {
        for (int i = 0; i <= 9; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        System.out.println("Start array " + digits);
    }


    public void run() {
        generate();
    }

    public String generate() {
        buffer.setLength(0);
        try {
            Future<Integer>[] tasks = new Future[digits.size()];

            for (int i = 1; i <= digits.size(); i++) {
                tasks[i-1] = pool.submit( new BufferCallable(i-1) );
            }

            for (int i = 1; i <= digits.size(); i++){
                Integer number = tasks[i-1].get();
                buffer.append(number);
            }
        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        pool.shutdown();
        try { pool.awaitTermination(2000, TimeUnit.MILLISECONDS); }
        catch (InterruptedException ignore) {}
        pool.shutdownNow();

        System.out.println(buffer);
        return buffer.toString();
    }

    class BufferCallable implements Callable<Integer> {
        private final int number;
        public BufferCallable(int number) {
            this.number = number;
        }
        @Override
        public Integer call() throws Exception {
            return digits.get(number);
        }
    }

}
