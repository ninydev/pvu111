package org.example.works.marth03;

import org.example.works.marth03.my_threads.ArrayResource;
import org.example.works.marth03.my_threads.CalcMultiply;
import org.example.works.marth03.my_threads.CalcSum;
import org.example.works.marth03.my_threads.FillArray;

public class ClassWork0303 implements Runnable
{
    @Override
    public void run() {
        System.out.println("Start Class work");
        doNotify();

    }

    private void doNotify(){

        ArrayResource resource = new ArrayResource();

        CalcSum calcSum = new CalcSum(resource);
        Thread threadCalcSum = new Thread(calcSum);

        CalcMultiply calcMultiply = new CalcMultiply(resource);
        Thread threadCalcMultiply = new Thread(calcMultiply);

        FillArray fillArray = new FillArray(resource);
        Thread threadFillArray = new Thread(fillArray);

        threadFillArray.start();
//        threadCalcMultiply.start();
//        threadCalcSum.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        threadFillArray.start();
        threadCalcMultiply.start();
        threadCalcSum.start();


        try {
            threadCalcSum.join();
            threadCalcMultiply.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
