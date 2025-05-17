package io.github.bilcitytycoon.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.bilcitytycoon.Player;

/**
 * A utility class for displaying a styled economy pop-up window that shows
 * the player's income, expenses, and net balance using a pastel-themed UI.
 */
public class EconomyPopUp {

    /**
     * Displays a modal dialog with income, expenses, and net economy data.
     * The dialog uses a custom pastel background and includes labels with styled font and color.
     *
     * @param stage the stage to attach the dialog to
     * @param skin the UI skin to style labels and buttons
     * @param player the player whose economy data will be shown
     */
    public static void show(Stage stage, Skin skin, Player player) {
        Dialog dialog = new Dialog("", skin);

        // Kare köşeli, pastel uyumlu degrade arka plan + ince çerçeve
        Pixmap pixmap = new Pixmap(600, 400, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf("DDF5FF")); // pastel açık mavi-beyaz arası
        pixmap.fill();
        pixmap.setColor(Color.LIGHT_GRAY); // çok ince border için
        pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        Texture backgroundTexture = new Texture(pixmap);
        pixmap.dispose();

        dialog.getContentTable().setBackground(new TextureRegionDrawable(backgroundTexture));
        dialog.getContentTable().pad(20);

        // Başlık (GameScreen ile uyumlu font scale ve siyah renk)
        Label title = new Label("ECONOMY", skin, "labelStyle");
        title.setFontScale(0.7f);
        title.setColor(Color.BLACK);
        dialog.getContentTable().add(title).padBottom(20).center().row();

        // Üst Bilgi
        Table header = new Table();
        Label universityName = new Label(player.getName() + "\nBilCity University", skin, "labelStyle");
        universityName.setColor(Color.BLACK);
        Label balanceLabel = new Label(player.getMoneyHandler().getBalance() + " BilCoins", skin, "labelStyle");
        balanceLabel.setColor(Color.BLACK);
        header.add(universityName).left().expandX();
        header.add(balanceLabel).right();
        dialog.getContentTable().add(header).fillX().padBottom(15).row();

        // Gelir tablosu
        Table incomeTable = new Table();
        Label incTitle = new Label("Incomes", skin);
        incTitle.setColor(Color.valueOf("009966"));
        incomeTable.add(incTitle).padBottom(10).colspan(2).center().row();
        incomeTable.add(new Label("Corporate:", skin)).left().padRight(20);
        incomeTable.add(new Label("5000", skin)).right().row();
        incomeTable.add(new Label("Donations:", skin)).left().padRight(20);
        incomeTable.add(new Label("3000", skin)).right().row();
        incomeTable.add(new Label("Grants:", skin)).left().padRight(20);
        incomeTable.add(new Label("5000", skin)).right().row();
        incTitle.setFontScale(0.6f);

        // Gider tablosu
        Table expenseTable = new Table();
        Label expTitle = new Label("Expenses", skin);
        expTitle.setColor(Color.RED);
        expenseTable.add(expTitle).padBottom(10).colspan(2).center().row();
        expenseTable.add(new Label("Utilities:", skin)).left().padRight(20);
        expenseTable.add(new Label("3000", skin)).right().row();
        expenseTable.add(new Label("Staff:", skin)).left().padRight(20);
        expenseTable.add(new Label("2500", skin)).right().row();
        expTitle.setFontScale(0.6f);

        int net = player.getMoneyHandler().getIncome() - player.getMoneyHandler().getExpense();
        Label netLabel = new Label("Overall: " + (net >= 0 ? "+" : "") + net + " BilCoins", skin, "labelStyle");
        netLabel.setColor(net >= 0 ? Color.valueOf("009966") : Color.RED);
        netLabel.setFontScale(0.6f);

        // Ortak gövde
        Table body = new Table();
        body.add(incomeTable).padRight(50);
        body.add(expenseTable);
        dialog.getContentTable().add(body).row();
        dialog.getContentTable().add(netLabel).padTop(15);

        dialog.button("Close", true).padTop(20);
        dialog.show(stage);
    }
}
