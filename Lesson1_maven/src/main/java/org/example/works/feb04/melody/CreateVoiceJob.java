package org.example.works.feb04.melody;

import java.util.Date;
import java.util.concurrent.Phaser;

public class CreateVoiceJob  extends Thread
{
    private String text;
    public  String voiceContent;
    private Date start_at;
    private Date finish_at;
    private Phaser phaser;

    public CreateVoiceJob(String text, Phaser phaser) {
        super("Voice");
        this.text = text;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        super.run();
        this.phaser.register();
        start_at = new Date();
        this.voiceContent = " Voice for: " + text;
        this.phaser.arriveAndAwaitAdvance();
        this.phaser.arriveAndDeregister();

        System.out.println("Голос закончен");
        finish_at = new Date();
    }



}
