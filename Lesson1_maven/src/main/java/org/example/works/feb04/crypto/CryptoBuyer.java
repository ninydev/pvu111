package org.example.works.feb04.crypto;

public class CryptoBuyer extends Thread{
    private MyWallet wallet;
    public CryptoBuyer(MyWallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public void run() {
        super.run();

        wallet.doBue(3);
    }
}
