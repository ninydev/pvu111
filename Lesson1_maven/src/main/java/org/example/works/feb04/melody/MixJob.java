package org.example.works.feb04.melody;

import java.util.Date;
import java.util.concurrent.Phaser;

public class MixJob extends Thread{

    String voiceContent;
    String melodyContent;
    String coverContent;

    public String result;

    private Date start_at;
    private Date finish_at;
    private Phaser phaser;

    public MixJob(String voiceContent, String melodyContent, String coverContent, Phaser phaser) {
        super("Mix");
        this.coverContent = coverContent;
        this.melodyContent = melodyContent;
        this.voiceContent = voiceContent;
        this.phaser = phaser;
    }



    @Override
    public void run() {
        super.run();
        this.phaser.register();
        start_at = new Date();
        this.result = " Full Jobs: \n"
            + this.coverContent
            + this.melodyContent
            + this.coverContent;
        finish_at = new Date();
        this.phaser.arriveAndAwaitAdvance();
        this.phaser.arriveAndDeregister();
    }


}
