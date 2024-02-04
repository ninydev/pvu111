package org.example.works.feb04;

import java.util.concurrent.Semaphore;

public class ApiRequestSemaphoreJob implements Runnable
{
    private Semaphore semaphore;
    private String url;
    public ApiRequestSemaphoreJob (Semaphore semaphore, String url) {
        this.semaphore = semaphore;
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println("Я начал выполеннеи потока - память выделена - код загружен");
        try {
            // Я жду - когда можно начать выполнять код
            this.semaphore.acquire();
            // Я выполняю полезную нагрузку
            this.work();
            // Я сообщаю - что я все закончил
            this.semaphore.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Полезная нагрузка
     */
    private void work(){
        System.out.println("Я выполняю запрос к серверу");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
