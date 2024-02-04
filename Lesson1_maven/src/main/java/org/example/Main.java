package org.example;

import org.example.works.feb04.MyThread;
import org.example.works.feb04.crypto.CryptoContractJob;
import org.example.works.feb04.crypto.CryptoShop;
import org.example.works.jan28.StreamApi;

public class Main {
    public static void main(String[] args) {
        Runnable classWork = new CryptoShop();
        classWork.run();
        System.out.println("App finish");
    }


}