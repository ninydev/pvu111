package org.example.works.marth03.my_threads;

import java.util.ArrayList;
import java.util.Random;

public class CalcSum implements Runnable
{

    private final ArrayResource resource;

    public  CalcSum(ArrayResource resource) {
        this.resource = resource;

    }

    @Override
    public void run() {
        System.out.println("Жду наполнения массива для суммирования");

        synchronized (resource) {

            try {
                if(!resource.isFill())
                    resource.wait();
            } catch (InterruptedException e) {
                System.out.println(" Wait Error");
                throw new RuntimeException(e);
            }

            handler();
        }
    }

    private void handler(){
        int sum = 0;
        ArrayList<Integer> data = resource.getData();
        for (Integer datum : data) {
            sum += datum;
        }

        System.out.println("Sum: " + sum);
    }
}
