package org.example.works.marth03.my_threads;

import java.util.ArrayList;

public class CalcMultiply implements Runnable
{

    private final ArrayResource resource;

    public CalcMultiply(ArrayResource resource) {
        this.resource = resource;

    }

    @Override
    public void run() {
        System.out.println("Жду наполнения массива для умножения");

        synchronized (resource) {

            try {
                // Вариант - флаг, который говорит о готовности ресусра
                if(!resource.isFill())
                    resource.wait();
                // Вариант анализа - если данных еще нет - ждать их
                if(resource.getData() == null)
                    resource.wait(10000);
            } catch (InterruptedException e) {
                System.out.println(" Wait Error");
                throw new RuntimeException(e);
            }

            handler();
        }
    }

    private void handler(){
        int multiply = 1;
        ArrayList<Integer> data = resource.getData();
        for (Integer datum : data) {
            multiply *= datum;
        }

        System.out.println("Multiply: " + multiply);
    }
}
