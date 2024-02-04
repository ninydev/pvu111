package org.example.works.feb04.crypto;

import java.util.concurrent.Exchanger;

public class CryptoContractJob implements Runnable
{


    @Override
    public void run() {
        try {
            exchangeCrypto();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void exchangeCrypto() throws InterruptedException {
        // Обменник - спецальнй класс который позволяет 2 потокам меняться информацией
        Exchanger<String> exchanger = new Exchanger<>();
        SenderThread sender = new SenderThread(exchanger);
        sender.start();

        Thread.sleep(1000);

        WaiterThread waiter = new WaiterThread(exchanger);
        waiter.start();

    }
}
