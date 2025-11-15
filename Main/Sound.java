package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0]= getClass().getResource("/Audio/Pallet_Town.wav");
        soundURL[1]= getClass().getResource("/Audio/Door.wav");
        soundURL[2]= getClass().getResource("/Audio/Scroll.wav");
        soundURL[3]= getClass().getResource("/Audio/shoes.wav");
        soundURL[4]= getClass().getResource("/Audio/Victory.wav");
        soundURL[5]= getClass().getResource("/Audio/coin.wav");
        soundURL[6]= getClass().getResource("/Audio/scoin.wav");
        soundURL[7]= getClass().getResource("/Audio/coin.wav");


}
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
        }
    }
    public void play(){
        clip.start();

    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
        }
}
