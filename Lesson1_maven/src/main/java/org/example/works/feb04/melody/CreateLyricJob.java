package org.example.works.feb04.melody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;

public class CreateLyricJob extends Thread
{
    private List<String> wishes;
    public String text;

    private Date start_at;
    private Date finish_at;
    Phaser phaser;

    public CreateLyricJob(List<String> wishes, Phaser phaser ){
        super("Create Lyric");
        this.wishes = wishes;
        this.phaser = phaser;
        // регистрирую фазовый подход

    }

    @Override
    public void run() {
        super.run();
        this.phaser.register();
        start_at = new Date();
        this.text = "";
        for (String s: this.wishes) {
            this.text += s + ",";
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Стихи закончены");
        this.phaser.arrive();
        // this.phaser.arriveAndDeregister();
//        this.phaser.arriveAndAwaitAdvance();
//        this.phaser.arriveAndDeregister();
        finish_at = new Date();
    }
}
