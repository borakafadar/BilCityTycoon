package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.bilcitytycoon.*;

import java.util.ArrayList;


public class LeaderboardScreen implements Screen {
    private FitViewport fitViewport;
    private StretchViewport stretchViewport; //for background
    private Stage backgroundStage;
    private Stage mainStage;
    private Skin skin;
    private BilCityTycoonGame game;
    private Main mainGame;
    private Leaderboard leaderboard;

    public LeaderboardScreen(BilCityTycoonGame game, Main mainGame, GameScreen gameScreen){
        this.game = game;
        this.mainGame = mainGame;
        this.mainStage = new Stage();
        this.fitViewport = new FitViewport(1920,1080);
        this.leaderboard = game.getLeaderboard();
        this.leaderboard.updateRanking();

        //for test
        //this.leaderboard = new Leaderboard(/*this.game,*/null);

        this.stretchViewport = new StretchViewport(1366,768);
        this.backgroundStage = new Stage();

        backgroundStage.setViewport(stretchViewport);
        mainStage.setViewport(fitViewport);

        FreeTypeFontGenerator bigFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter  titleFontParameter = generateFontParameter(75,2);
        FreeTypeFontGenerator.FreeTypeFontParameter bigFontParameter = generateFontParameter(35,1);

        FreeTypeFontGenerator.FreeTypeFontParameter smallFontParameter = generateFontParameter(16,0);

        FreeTypeFontGenerator.FreeTypeFontParameter smallestFontParameter = generateFontParameter(14,1);



        //TODO: please clean this code up, it works but it is really garbage

        BitmapFont bigFont = bigFontGenerator.generateFont(bigFontParameter);
        BitmapFont smallFont = bigFontGenerator.generateFont(smallFontParameter);
        BitmapFont smallestFont = bigFontGenerator.generateFont(smallestFontParameter);
        BitmapFont titleFont = bigFontGenerator.generateFont(titleFontParameter);

        skin = new Skin();
        skin.add("PressStart2P", bigFont);
        skin.add("PressStart2P-small", smallFont);
        skin.add("PressStart2P-smallest", smallestFont);
        skin.add("PressStart2P-big", titleFont);
        skin.addRegions(new TextureAtlas(Gdx.files.internal("skin1.atlas")));
        skin.load(Gdx.files.internal("skin1.json"));

        //TODO: temp, to test the table feature


        //test code
        OtherUniversity otherUniversity1 = new OtherUniversity("Bombardino University",3100,94);
        OtherUniversity otherUniversity2 = new OtherUniversity("Crocodilo University",2500,83);
        Player player = new Player("Zeynel Yildirim BilCity University",2300,69,10,10,10);
        OtherUniversity otherUniversity3 = new OtherUniversity("Tralello University",2000,58);
        OtherUniversity otherUniversity4 = new OtherUniversity("Yasar University",1500,47);

        ArrayList<University> universities = game.getLeaderboard().getAllUniversities();

        Table buttonTable = createButtonTable(universities);
        buttonTable.setFillParent(true);

        Image panelBackground = new Image(new Texture(Gdx.files.internal("panelBackground.png")));
        backgroundStage.addActor(panelBackground);
        panelBackground.setSize(1920,1080);



        ScrollPane scrollPane = new ScrollPane(buttonTable,skin);
        scrollPane.setHeight(100);
        scrollPane.setScrollingDisabled(true,false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setClamp(true);
        scrollPane.setOverscroll(false,true);

        Table rootTable = new Table();
        rootTable.setFillParent(true);
        buttonTable.bottom();
        buttonTable.pack();

        Label titleLabel = new Label("Leaderboard",skin,"title-label");
        titleLabel.setAlignment(Align.center);
        rootTable.add(titleLabel).expandX().fillX().padTop(90).padBottom(50);
        rootTable.row();
        rootTable.add(scrollPane).expand().fill();
        ImageButton backButton = new ImageButton(skin,"back-button");
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainGame.setScreen(gameScreen);
            }
        });




        mainStage.addActor(rootTable);

        mainStage.addActor(backButton);
        backButton.setPosition(100,920);
        backButton.setSize(100,100);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(mainStage);
        game.getLeaderboard().updateRanking();
        refresh();
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        backgroundStage.act();
        stretchViewport.apply();
        backgroundStage.draw();

        mainStage.act();
        fitViewport.apply();
        mainStage.draw();

    }
    @Override
    public void resize(int width, int height) {
        fitViewport.update(width,height,true);
        stretchViewport.update(width, height,true);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        skin.dispose();
        mainStage.dispose();

    }


     private Button createUniversityButton(University university){
        if(university instanceof OtherUniversity){
            Button button = new Button(skin,"store-button");
            Table mainTable = new Table();
            mainTable.pad(10);
            mainTable.padBottom(20);
            //left panel
            Table rankingTable = new Table(skin);

            Label rankingLabel = new Label(university.getRanking()+"#",skin,"title-label");
            rankingTable.add(rankingLabel).expandX().fillX().padBottom(20);

            //mid panel
            Table textTable = new Table(skin);


            Label facultyLabel = new Label(university.getName(), skin,"default");
            facultyLabel.setWrap(true);
            facultyLabel.setAlignment(Align.center);
            textTable.add(facultyLabel).width(/*facultyLabel.getText().length*20*/660).height(50).padBottom(20).expandX().fillX();
            textTable.row();

            //right info panel
            Table infoTable = new Table(skin);
            infoTable.defaults().pad(10);

            Table reputationTable = new Table(skin);
            reputationTable.defaults().pad(10);
            reputationTable.add(new Image(new Texture(Gdx.files.internal("icons/reputationIcon.png")))).width(40).height(40);
            Label reputationLabel = new Label("University Reputation Point: "+university.getUniversityReputationPoint(),skin,"small-label");
            reputationLabel.setWrap(true);
            reputationTable.add(reputationLabel).size(200,50);

            Table satisfactionTable = new Table(skin);
            satisfactionTable.defaults().pad(10);
            satisfactionTable.add(new Image(new Texture(Gdx.files.internal("icons/satisfactionIcon.png")))).width(40).height(40);
            Label satisfactionLabel = new Label("Student Satisfaction Rate: "+university.getStudentSatisfactionRate()+"%",skin,"small-label");
            satisfactionLabel.setWrap(true);
            satisfactionTable.add(satisfactionLabel).size(220,50);


            infoTable.add(reputationTable);
            infoTable.add(satisfactionTable);
            infoTable.row();



            mainTable.defaults().pad(40);
            mainTable.top();
            mainTable.add(rankingTable).expand().fill().align(Align.left).top();
            mainTable.add(textTable).expand().fill().width(textTable.getWidth()).align(Align.center).expandX().fillX();
            mainTable.add(infoTable).expand().fill().align(Align.right).size(700,50);



            mainTable.center();
            mainTable.defaults().pad(100);

            button.add(mainTable).expand().fill().align(Align.left).top();

            return button;
        } else if(university instanceof Player){
            Button button = new Button(skin,"player-button");
            Table mainTable = new Table();
            mainTable.pad(10);
            mainTable.padBottom(20);
            //left panel
            Table rankingTable = new Table(skin);

            Label rankingLabel = new Label(university.getRanking()+"#",skin,"title-label");
            rankingTable.add(rankingLabel).expandX().fillX().padBottom(20);


            //mid panel
            Table textTable = new Table(skin);


            Label facultyLabel = new Label(university.getName(), skin,"default");
            facultyLabel.setWrap(true);
            facultyLabel.setAlignment(Align.center);
            textTable.add(facultyLabel).width(facultyLabel.getText().length*200).height(50).padBottom(20);
            textTable.row();
            textTable.row();

            //right info panel
            Table infoTable = new Table(skin);
            infoTable.defaults().pad(10).fillX().expand();

            Table reputationTable = new Table(skin);
            reputationTable.defaults().pad(10);
            reputationTable.add(new Image(new Texture(Gdx.files.internal("icons/reputationIcon.png")))).width(40).height(40);
            Label reputationLabel = new Label("Your University Reputation Point: "+university.getUniversityReputationPoint(),skin,"small-label");
            reputationLabel.setWrap(true);
            reputationTable.add(reputationLabel).size(250,50);

            Table satisfactionTable = new Table(skin);
            satisfactionTable.defaults().pad(10);
            satisfactionTable.add(new Image(new Texture(Gdx.files.internal("icons/satisfactionIcon.png")))).width(40).height(40);
            Label satisfactionLabel = new Label("Your Student Satisfaction Rate: "+university.getStudentSatisfactionRate()+"%",skin,"small-label");
            satisfactionLabel.setWrap(true);
            satisfactionTable.add(satisfactionLabel).size(220,50);


            infoTable.add(reputationTable);
            infoTable.add(satisfactionTable);
            infoTable.row();


            mainTable.defaults().pad(40);
            mainTable.top();
            mainTable.add(rankingTable).expand().fill().align(Align.left);
            mainTable.add(textTable).expand().fill().width(textTable.getWidth()).align(Align.center);
            mainTable.add(infoTable).expand().fill().align(Align.right).size(700,50);



            mainTable.center();
            mainTable.defaults().pad(100);

            button.add(mainTable).expand().fill().align(Align.left).top();
            button.pack();
            return button;
        }
        //to shut the compiler up
        return null;
    }

    private Table createButtonTable(ArrayList<University> universities){
        if(universities == null || universities.isEmpty()){
            return new Table();
        }

        Table buttonTable = new Table();
        for(University university : universities){
            buttonTable.add(createUniversityButton(university)).width(1700).height(200).pad(10);
            buttonTable.row();
        }
        return buttonTable;
    }


    private FreeTypeFontGenerator.FreeTypeFontParameter generateFontParameter(int size, int borderWidth){
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.borderWidth = borderWidth;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.BLACK;
        fontParameter.spaceX = 2;
        fontParameter.spaceY = 5;
        fontParameter.minFilter = Texture.TextureFilter.Nearest;
        fontParameter.magFilter = Texture.TextureFilter.Nearest;
        fontParameter.shadowOffsetX = 0;
        fontParameter.shadowOffsetY = 0;
        fontParameter.shadowColor = Color.BLACK;
        fontParameter.borderStraight = false;
        fontParameter.borderColor = Color.WHITE;
        fontParameter.gamma = 20f;

        return fontParameter;
    }
    private void refresh() {
        leaderboard.updateRanking(); // 🔁 sıralamayı güncelle

        ArrayList<University> universities = leaderboard.getAllUniversities();
        Table newButtonTable = createButtonTable(universities);

        ScrollPane scrollPane = new ScrollPane(newButtonTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        Table rootTable = (Table) mainStage.getActors().get(0); // ilk table rootTable'dır
        rootTable.clear(); // önceki içeriği temizle

        Label titleLabel = new Label("Leaderboard", skin, "title-label");
        titleLabel.setAlignment(Align.center);
        rootTable.add(titleLabel).expandX().fillX().padTop(90).padBottom(50);
        rootTable.row();
        rootTable.add(scrollPane).expand().fill();
    }

}


