package org.example.works.feb04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MyThread implements Runnable
{
    @Override
    public void run() {

        getCurrentThread();
        byRes();

    }

    private void byRes() {
        Semaphore s = new Semaphore(2);
        CommonRes res = new CommonRes();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i <10; i++) {
            ApiRequestSemaphoreJob job = new ApiRequestSemaphoreJob(s, "https://api.novaposhta.ua/v2.0/json/", res);
            Thread t = new Thread(job);
            t.start();
            // Фиксируем поток в коллекции потоков
            threads.add(t);
        }

        // Ожидание завершения всех потоков
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void bySemaphore(){
//        // Я созаю семафор - так, что бы одновременно можно было выполнять не более 1 задачи
//        Semaphore sNovaPoshta = new Semaphore(2);
//
//        for (int i = 0; i <10; i++) {
//            ApiRequestSemaphoreJob job = new ApiRequestSemaphoreJob(sNovaPoshta, "https://api.novaposhta.ua/v2.0/json/");
//            Thread t = new Thread(job);
//            t.start();
//        }
//
//        Semaphore sPrivatBank = new Semaphore(2);
//
//        for (int i = 0; i <10; i++) {
//            ApiRequestSemaphoreJob job = new ApiRequestSemaphoreJob(sPrivatBank, "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5 ");
//            Thread t = new Thread(job);
//            t.start();
//        }


    }

    private void createThread() {
        CalcMeteorJob job = new CalcMeteorJob(" My Job");
        // В данном случае метод будет выполняться в том же потоке
        job.run();

        Thread t = new Thread(job);
        t.start();

        CalcMeteorAsChildrenJob p = new CalcMeteorAsChildrenJob("Child", "Child Job");
        p.start();
    }



    private void getCurrentThread() {
        Thread t = Thread.currentThread();
        System.out.println("Main thread " + t);
    }
}
