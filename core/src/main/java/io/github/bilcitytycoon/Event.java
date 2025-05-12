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
    private int studentSatisfactionPoint;
    private BilCityTycoonGame game;

    public Event(String info, int cost, BilCityTycoonGame game, int studentSatisfactionRateAffection) {
        this.info = info;
        this.cost = cost;
        this.imagePath = imagePath;
        this.avatar = new Image(new Texture(Gdx.files.internal(imagePath)));
        this.game = game;
    }

    public void burning(Building b){
        this.info = "A fire broke out in " + b.getName() + "! Do you want to pay " + b.getCost() + " coins to put out the fire?";
        this.cost = b.getCost();
        this.studentSatisfactionPoint = -100;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        updatePlayer();
    }

    public void protest(){
        this.info = "Students are protesting against the current state of the university! Try upgrading the buildings or adding new ones!";
        this.cost = 0;
        this.studentSatisfactionPoint = -60;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        updatePlayer();
    }

    public void donation(){
        this.info = "A generous donor has donated " + randomDonation() + " BilCoins to the university!";
        this.cost = 0;
        this.studentSatisfactionPoint = 10;
        this.avatar = new Image(new Texture(Gdx.files.internal(getAvatar())));
        this.game.getPlayer().setCoin(this.game.getPlayer().getCoin() + randomDonation());
        updatePlayer();
    }

    public String getInfo(){
        return this.info;
    }

    public int getCost(){
        return this.cost;
    }

    public int randomDonation(){
        Random rand = new Random();
        int random = rand.nextInt(50,150);
        return random;
    }

    public String getAvatar(){
        String[] avatars = {
           "libgdx.png"
        };
        Random rand = new Random();
        int randomIndex = rand.nextInt(avatars.length);
        return avatars[randomIndex];
    }

    public void updatePlayer(){
        this.game.getPlayer().setStudentSatisfactionRate(game.getPlayer().getStudentSatisfactionRate() + studentSatisfactionPoint);
    }
}
