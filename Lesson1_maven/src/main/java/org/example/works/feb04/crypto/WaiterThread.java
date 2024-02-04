package org.example.works.feb04.crypto;

import java.util.concurrent.Exchanger;

public class WaiterThread extends Thread
{
    private Exchanger<String> exchanger;
    public WaiterThread(Exchanger<String> exchanger ){
        super("Waiter");
        this.exchanger = exchanger;
    }

    public void run() {
        super.run();
        String messageSend = "Дмитрия кошелек 2a8001314eff8128bf3590d78f7ea465411cadae3f0650a52b1a427774664952";
        try {
            //..received message
            String messageReceived = exchanger.exchange(messageSend);
            System.out.println("Waiter: " + messageReceived);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
