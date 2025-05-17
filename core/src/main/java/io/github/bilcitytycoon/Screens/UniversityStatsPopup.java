package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.bilcitytycoon.Player;

public class UniversityStatsPopup {

    public static void show(Stage stage, Skin skin, Player player) {
        Dialog dialog = new Dialog("", skin);

        Pixmap pixmap = new Pixmap(600, 400, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("DDF5FF"));
        pixmap.fill();
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        Texture backgroundTexture = new Texture(pixmap);
        pixmap.dispose();

        dialog.getContentTable().setBackground(new TextureRegionDrawable(backgroundTexture));
        dialog.getContentTable().pad(20);

        Label title = new Label("UNIVERSITY STATS", skin);
        title.setFontScale(0.7f);
        title.setColor(Color.BLACK);
        dialog.getContentTable().add(title).padBottom(20).center().row();

        Table header = new Table();
        Label universityName = new Label(player.getName() + "\nBilCity University", skin);
        universityName.setFontScale(0.5f);
        universityName.setColor(Color.BLACK);
        header.add(universityName).center();
        dialog.getContentTable().add(header).padBottom(20).center().row();

        Table statsTable = new Table();
        statsTable.defaults().padBottom(15);

        statsTable.add(new Label("Student Count: " + player.getStudentCount(), skin)).left().row();
        statsTable.add(new Label("Dormitory Occupancy:", skin)).left().row();
        statsTable.add(new Label(player.getDormOccupancy() + "/" + player.getDormCapacity(), skin)).left().row();
        statsTable.add(new Label("Student Satisfaction Rate: %" + player.getStudentSatisfactionRate(), skin)).left().row();
        statsTable.add(new Label("University Reputation Points: " + player.getReputationPoints(), skin)).left().row();

        for (Cell<?> cell : statsTable.getCells()) {
            if (cell.getActor() instanceof Label) {
                ((Label) cell.getActor()).setFontScale(0.5f);
                ((Label) cell.getActor()).setColor(Color.BLACK);
            }
        }

        dialog.getContentTable().add(statsTable).row();

        dialog.button("Close", true).padTop(20);
        dialog.show(stage);
    }
}
