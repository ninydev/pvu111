package org.example.works.feb04.melody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;

public class CreateCoverJob extends Thread {

    private List<String> wishes;

    public String imgContent;

    private Date start_at;
    private Date finish_at;
    private Phaser phaser;

    public CreateCoverJob(List<String> wishes, Phaser phaser ){
        super("Create Cover");
        this.wishes = wishes;
        this.phaser = phaser;

    }


    @Override
    public void run() {
        super.run();
        this.phaser.register();
        start_at = new Date();
        this.imgContent = " Image for: ";
        for (String s: this.wishes) {
            this.imgContent += s + ",";
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        finish_at = new Date();
        System.out.println("Обложка для песни готова ");
        // Завершаем текущую фазу
        phaser.arriveAndAwaitAdvance();
        this.phaser.arriveAndDeregister();
    }

}
