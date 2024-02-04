package org.example.works.feb04;

public class CalcMeteorJob implements Runnable
{
    private String data;

    /**
     * Я принимаю все данные, с которыми я собираюсь работать внутри потока
     * Без этих данных сущетсование потока будет бессысленно
     * @param data
     */
    public CalcMeteorJob(String data) {
        this.data = data;
    }

    /**
     * Реализация Runnable позволяет указать точку старта потока
     */
    @Override
    public void run() {
        System.out.println("Work with : " + data);
        getCurrentThread();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentThread() {
        Thread t = Thread.currentThread();
        System.out.println("Work in " + t);
    }
}
