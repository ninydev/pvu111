package org.example;

import org.example.works.feb04.MyThread;
import org.example.works.feb04.crypto.CryptoContractJob;
import org.example.works.feb04.crypto.CryptoShop;
import org.example.works.feb04.melody.MusicController;
import org.example.works.jan28.StreamApi;
import org.example.works.marth03.ClassWork0303;

public class Main {
    public static void main(String[] args) {
        Runnable classWork = new ClassWork0303();
        classWork.run();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("App finish");
    }


}