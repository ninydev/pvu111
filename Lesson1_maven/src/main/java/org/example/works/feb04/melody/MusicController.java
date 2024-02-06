package org.example.works.feb04.melody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class MusicController implements Runnable
{
    @Override
    public void run() {
        // Я буду использовать учет фаз
        Phaser phaser = new Phaser(4);

        List<String> wishes = new ArrayList<>();
        wishes.add("sex");
        wishes.add("drugs");
        wishes.add("rock'n'roll");

        CreateCoverJob c = new CreateCoverJob(wishes, phaser);
        c.start();
        CreateMelodyJob m = new CreateMelodyJob(wishes, phaser);
        m.start();

        CreateLyricJob l = new CreateLyricJob(wishes, phaser);
        l.start();
        // Жду окончания первой фазы
        phaser.arriveAndAwaitAdvance();

        CreateVoiceJob v = new CreateVoiceJob(l.text, phaser);
        phaser.arriveAndAwaitAdvance();

        MixJob mix = new MixJob(v.voiceContent, m.melodyContent, c.imgContent,phaser);

        mix.start();
    }

    // Получая желения мы запускаем генерацию картинки, стихов и мелодии
    // После того, как стихи будут готовы - генерируем голос
    // После голоса - mix
}
