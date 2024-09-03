package itstep.learning.async;

import java.util.Objects;
import java.util.concurrent.*;

public class AsyncDemo {

    private final ExecutorService pool = Executors.newFixedThreadPool(3);

    public void run() {
        //percentDemo();
        taskDemo();
    }

    private void taskDemo(){
        Callable<String> callable =  new Callable<String>() {
            @Override
            public String call() throws Exception {
                try{ TimeUnit.MILLISECONDS.sleep(500);}
                catch (InterruptedException ignore) {}
                return "Callable data";
            }
        };

        Callable<String> callable2 = () ->{
            try { TimeUnit.MILLISECONDS.sleep(500);}
            catch ( InterruptedException ignore ) {}
            return "Callable2 data";
        };

        Future<String> task1 = pool.submit( callable );
        Future<String> task2 = pool.submit( callable2 );
        Future<Double> task3 = pool.submit( ()-> getPercent(1) );

        try {
            String res1 = task1.get();
            System.out.println(res1);
            String res2 = task2.get();
            System.out.println(res2);
            Double res3 = task3.get();
            System.out.println(res3);

            sum = 100.0;
            Future<Double>[] tasks = new Future[12];
            for (int i = 1; i <= 12; i++){
                tasks[i-1] = pool.submit( new PercentCallable(i) );
            }

            for (int i = 1; i <= 12; i++){
                double percent =tasks[i-1].get();
                double factor = 1.0 + percent / 100.0;
                sum *= factor;
                System.out.printf( "month: %d, sum: %.2f\n", i, sum );
            }

        }
        catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }



        pool.shutdown();
        try { pool.awaitTermination(15000, TimeUnit.MILLISECONDS); }
        catch (InterruptedException ignore) {}
        pool.shutdownNow();
    }

    class PercentCallable implements Callable<Double> {
        private final int month;
        public PercentCallable(int month) {
            this.month = month;
        }
        @Override
        public Double call() throws Exception {
//            double factor = 1.0 + getPercent(month) / 100.0;
//            sum = sum * factor;
//            System.out.printf( "month: %d, sum: %.2f\n", month, sum );
            return getPercent(month);
        }
    }


    private double sum ;
    private final Object sumLocker = new Object();
    private int threadsCountdown;

    private void percentDemo() {
        sum = 100.0;
        threadsCountdown = 12;
        Thread[] threads = new Thread[threadsCountdown];
        for (int i = 1; i <= threadsCountdown; i++) {
            threads[i-1] = new Thread( new PercentRunnable(i) );
            threads[i-1].start();
        }
        for (int i = 1; i <= 12; i++) {
            try{ threads[i-1].join(); }         // чекання кількох потоків = чекання кожного
            catch( InterruptedException ignore ) {}
        }
        System.out.printf( "=============\ntotal: %.3f\n", sum );
    }

    // Nested class - клас, описаний в іншому класі
    private class PercentRunnable implements Runnable {
        private final int month;

        public PercentRunnable(int month) {
            this.month = month;
        }

        @Override
        public void run() {
            double factor = 1.0 + getPercent(month) / 100.0;
            double localSum;
            int localCountdown;
            synchronized (sumLocker) {   // C# -- lock() {...}
                sum = sum * factor;
                localSum = sum;
                localCountdown = --threadsCountdown;
            }
            // 1. getPercent(month)       | суть проблеми - у
            // 2. ()                      | проміжку часу від читання sum
            // 3. sum * () : read(sum)    | до його запису. У цей проміжок
            // 4. sum = ... : write(sum)  | інші потоки мають неправильне значення
            System.out.printf( "month: %d, sum: %.2f\n", month, localSum );

            if( localCountdown == 0 ) {
                System.out.printf( "----------------\ntotal: %.3f\n", localSum );
            }
        }
    }

    private double getPercent(int month) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException ignore) { }
        return 10.0;
    }

    private String strFromThread;

    private void threadDemo() {
        // Thread - основний клас для роботи з потоками
        // У Java вводиться поняття функціонального інтерфейсу -
        // типу даних з одним методом. Об'єкти функціональних
        // інтерфейсів грають роль функцій (не методів) і передаються
        // в інші об'єкти, які традиційно вимагають саме функції.
        Runnable runnable = new Runnable() {
            public void run() {
                strFromThread = "Hello from Runnable";
            }
        };
        Thread thread = new Thread(runnable);   // !! створення об'єкту не
        // запускає виконання

        thread.start();    // асинхронний запуск - в новому потоці
        // thread.run();   // синхронний запуск (без нового потоку)
        System.out.println("Hello from Main");
        System.out.println("Hello from Main");

        try { thread.join(); }   // .join() - очікування потоку (~ await)
        catch (InterruptedException ignore) {  }
        System.out.println(strFromThread);

        System.out.println("Hello from Main");
    }
}
/*
Асинхронне програмування
Синхронне виконання коду - послідовне, один за одним.
   ----- ====== ******
Асинхронне - будь-яке відхилення від синхронності
   -----   | Одночасне виконання
   =====   |

   - - -   | Паралельне, але не одночасне
    = = =  |

Реалізація асинхронності:
- багатозадачність (об'єкти мови програмування / платформи)
- багатопоточність (об'єкти операційної системи)
- багатопроцесність (---//--- за наявності відмінностей між потоком та процесом)
- мережні технології (grid-, network-), у т.ч. хмарні технології

"Інфляція" - за відомими показниками місячних коефіцієнтів інфляції
порахувати річне значення.
Мат.обгрунтування: чи однаковий результат операцій
(100 + 10%) + 20%   =?=  (100 + 20%) + 10%
100 * 1.1 * 1.2  ==  100 * 1.2 * 1.1  = 132
можливість "переставляння" - ідеально для асинхронності - немає різниці
у якій послідовності будуть враховані 12 місячних коефіцієнтів

Pandigital - число, яке складається з усіх цифр (0-9), кожна один раз
Задача: написати генератор Pandigital чисел на базі багатопоточності:
 кожен потік дописує одну цифру до спільного "буфера", що в решті стає числом
Забезпечити виведення підсумкового числа
 */
