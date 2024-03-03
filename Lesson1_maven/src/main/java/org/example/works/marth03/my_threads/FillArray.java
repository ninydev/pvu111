package org.example.works.marth03.my_threads;

import java.util.ArrayList;
import java.util.Random;

public class FillArray implements Runnable
{
    private final ArrayResource resource;
    private final Random random;

    public  FillArray(ArrayResource resource) {
        this.resource = resource;
        this.random = new Random();
    }

    /**
     * Перед запуском потока я блокирую ресурс,
     * с которым собираюсь работать
     */
    @Override
    public void run() {
        System.out.print("Начинаю наполнение массива => ");
        synchronized (resource) {
            handler();
            // Сообщить всем - что процесс наполнения завершен
            System.out.println("Массив наполнен");
            resource.setFill(true);
            resource.notifyAll();
        }
    }

    /**
     * Полезная нагрузка - то, что конкретно должен делать класс
     */
    private void handler() {
        int size = random.nextInt(5) + 5;
        ArrayList<Integer> data = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            data.add(random.nextInt(15) + 5 );
        }

        resource.setData(data);
    }
}
