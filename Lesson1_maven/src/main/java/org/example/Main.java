package org.example;

import org.example.works.feb04.MyThread;
import org.example.works.jan28.StreamApi;

public class Main {
    public static void main(String[] args) {
        Runnable classWork = new MyThread();
        classWork.run();
        System.out.println("App finish");
    }


}