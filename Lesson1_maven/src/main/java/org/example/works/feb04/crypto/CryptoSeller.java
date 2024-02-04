package org.example.works.feb04.crypto;

public class CryptoSeller extends Thread {
    private MyWallet wallet;
    public CryptoSeller (MyWallet wallet){
        this.wallet = wallet;
    }

    @Override
    public void run() {
        System.out.println("Жду валюту");
        super.run();
        wallet.doSellerOne();
        System.out.println("Обмен произошел ");
    }
}
