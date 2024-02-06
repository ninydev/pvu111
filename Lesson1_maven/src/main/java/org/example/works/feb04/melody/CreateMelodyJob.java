package org.example.works.feb04.melody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;

public class CreateMelodyJob extends Thread {

    private List<String> wishes;
    public String melodyContent;
    private Date start_at;
    private Date finish_at;
    Phaser phaser;

    CreateMelodyJob(List<String> wishes, Phaser phaser ) {
        super("Create Melody");
        this.wishes = wishes;
        this.phaser = phaser;

    }

    @Override
    public void run() {
        super.run();
        this.phaser.register();
        start_at = new Date();
        this.melodyContent = " melody for: ";
        for (String s: this.wishes) {
            this.melodyContent += s + ",";
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        finish_at = new Date();
        System.out.println("Мелодия готова ");
        // Завершаем текущую фазу
        phaser.arriveAndAwaitAdvance();
        this.phaser.arriveAndDeregister();
    }

}
