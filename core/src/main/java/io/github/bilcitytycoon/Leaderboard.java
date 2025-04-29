package io.github.bilcitytycoon;

import java.util.ArrayList;
import java.util.Random;

public class Leaderboard {
    private ArrayList<Player> allUniversities;
    private BilCityTycoonGame bilCityTycoonGame;


    public Leaderboard() {
        allUniversities = new ArrayList<Player>();
        createBots();
    }


    public void createBots(){
        Random rd = new Random();
        //TODO: change university names
        String[] universityNames = {
            "Kanzi", "Delibas", "Undem", "Simsir", "Kafadar", "Yildirim", "Zeyno", "Zurna",
            "Ardent", "Velbridge", "Nexora", "Stonewall", "Ironspire", "Thalium", "Ravenfall", "Mintora",
            "Elarin", "Bronwyn", "Kairoth", "Solbridge", "Zentris", "Halveth", "Terranova", "Grantham",
            "Obsidian", "Luneth", "Ashbourne", "Cryden", "Novaris", "Thornevale", "Valcrest", "Redspire",
            "Averin", "Falkmore", "Vireon", "Pendwyn", "Brimley", "Zephyra", "Calchester", "Ironwell",
            "Stormmere", "Crestmoor", "Marenth", "Eloria", "Westford", "Myndale", "Duskmoor", "Halbridge",
            "Venthor", "Silvermark", "Brighthelm", "Norwyn", "Galderan", "Eastmere", "Draymar", "Borewyn",
            "Trivent", "Runestone", "Wyrnfall", "Ashmor", "Cindergate", "Starwyn", "Frostmoor", "Veriton",
            "Greymark", "Tarnhelm", "Quanthelm", "Blackmere", "Ebonridge", "Cloudmere", "Highvale", "Bronzefield",
            "Glenhaven", "Thornford", "Solwyn", "Vandale", "Mistbrook", "Ismere", "Redwyn", "Goldbarrow",
            "Newwyn", "Maveth", "Stonewyn", "Westoria", "Rynmere", "Netherford", "Vortexa", "Hawkmere",
            "Graveth", "Skyreach", "Cypresson", "Kryden", "Lorewyn", "Zenmere", "Altmore", "Brighthall",
            "Vistaryn", "Oakspire", "Stormreach"
        };

        for(int i=0;i<universityNames.length;i++) {
            //the random numbers are not final, please change this when the game is tested
            allUniversities.add(new OtherUniversity(universityNames[i], rd.nextInt(20, 501) * 10, rd.nextInt(20, 100), this));
        }
    }

    public ArrayList<Player> getAllUniversities() {
        return allUniversities;
    }
}
