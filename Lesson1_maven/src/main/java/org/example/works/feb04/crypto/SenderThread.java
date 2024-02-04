package org.example.works.feb04.crypto;

import java.util.concurrent.Exchanger;

public class SenderThread extends Thread
{
    private Exchanger<String> exchanger;
    public SenderThread(Exchanger<String> exchanger ){
        super("Sender");
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        super.run();
        String messageSend = "Keeper: 3d2438ec33773a0864bcce0f6d7e1ef1af351dfdb7d48fd7edb4093cc49c23bc";
        try {
            //..received message
            String messageReceived = exchanger.exchange(messageSend);
            System.out.println("Sender: " + messageReceived);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
