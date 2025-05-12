package io.github.bilcitytycoon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

public class Event {
    public String info;
    public int cost;
    public transient Image avatar;
    public String imagePath;

    public Event(String info, int cost, String imagePath) {
        this.info = info;
        this.cost = cost;
        this.imagePath = imagePath;
        this.avatar = new Image(new Texture(Gdx.files.internal(imagePath)));
    }

    public String getInfo(){
        return this.info;
    }

    public int getCost(){
        return this.cost;
    }

    public String getAvatar(){
        String[] avatars = {
            "Bora_surprised.png",
            "Eylül_surprised.png",
            "Vural_surprised.png",
            "Yaşar_surprised.png",
            "Zeynel_surprised.png",
        };
        Random rand = new Random();
        int randomIndex = rand.nextInt(avatars.length);
        return avatars[randomIndex];
    }
}
