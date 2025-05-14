package io.github.bilcitytycoon;

import java.util.ArrayList;

public class Store {
    private Leaderboard leaderboard;
    private BilCityTycoonGame game;
    private ArrayList<Faculty> unbuiltFaculties;
    private ArrayList<Faculty> builtFaculties;
    private ArrayList<Decoration> builtDecorations;
    private ArrayList<Decoration> unbuiltDecorations;
    private ArrayList<OtherBuilding> unbuiltOtherBuildings;
    private ArrayList<OtherBuilding> builtOtherBuildings;

    private Faculty computerScienceFaculty;
    private Faculty eeeBuilding;
    private Faculty biologyBuilding;
    private Faculty psychologyBuilding;
    private Faculty lawFaculty;
    private Faculty managementFaculty;
    private Faculty economicsBuilding;
    private Faculty physicsBuilding;
    private Faculty industrialEngineeringBuilding;
    private Faculty interiorArchitectureBuilding;
    private Faculty mechanicalEngineeringBuilding;
    private Faculty graphicDesignBuilding;
    private Faculty chemistryBuilding;
    private Faculty urbanDesignAndLandscapeBuilding;
    private Faculty politicalScienceBuilding;
    private Faculty amphitheatreBuilding;
    private Faculty mathematicsBuilding;
    private Faculty communicationAndDesignBuilding;
    private Faculty architectureBuilding;

    private OtherBuilding undemCafe;
    private OtherBuilding library;
    private OtherBuilding sportsHall;
    private OtherBuilding researchLab;
    private OtherBuilding healthCenter;
    private OtherBuilding stadium;
    private OtherBuilding commonDormitory;
    private OtherBuilding middlingDormitory;
    private OtherBuilding cafeteria;
    private OtherBuilding luxuryDormitory;
    private OtherBuilding crazyBuddyCafe;
    private OtherBuilding carPark;

    private Decoration ornamentalPool;
    private Decoration treesBushes;
    private Decoration scienceFacultyPool;

    public Store(){

        unbuiltFaculties = new ArrayList<Faculty>();
        builtFaculties = new ArrayList<Faculty>();
        unbuiltDecorations = new ArrayList<Decoration>();
        builtDecorations = new ArrayList<Decoration>();
        unbuiltOtherBuildings = new ArrayList<OtherBuilding>();
        builtOtherBuildings = new ArrayList<OtherBuilding>();


        initializeFaculties();
        initializeDecorations();
        initializeOtherBuildings();
        initializeUpgrades();
    }
//    public Store(){
//        this.game = null;
//        unbuiltFaculties = new ArrayList<Faculty>();
//        builtFaculties = new ArrayList<Faculty>();
//        unbuiltDecorations = new ArrayList<Decoration>();
//        builtDecorations = new ArrayList<Decoration>();
//        unbuiltOtherBuildings = new ArrayList<OtherBuilding>();
//        builtOtherBuildings = new ArrayList<OtherBuilding>();
//    }


    public void initializeFaculties(){
        computerScienceFaculty = new Faculty("Computer Science Faculty", 120, 70, 70, "sprites/Computer Science Faculty.png", 10, "Where students turn caffeine into code and build the future.", 150, 70);
        eeeBuilding = new Faculty("EEE Building", 110, 90, 90, "sprites/EEE Building.png", 10,"Where students learn to electrify their minds and brighten the future.", 120, 50);
        biologyBuilding = new Faculty("Biology Building", 80, 50, 20, "sprites/Biology Building.png", 9, "Where students explore the core details of life", 90, 30);
        psychologyBuilding = new Faculty("Psychology Building", 80, 40, 20,"sprites/Psychology Building.png", 7, "Where students meet and know their inner selves", 90, 30);
        lawFaculty = new Faculty("Law Faculty", 90, 60, 40, "sprites/Law Faculty.png", 8, "Where students learn their rights and the chronological evolution of law.", 110, 40);
        managementFaculty = new Faculty("Management Faculty", 70, 40, 20, "sprites/Management Faculty.png", 7, "Where students learn to manage their time and resources effectively.", 110, 40);
        economicsBuilding = new Faculty("Economics Building", 80, 50, 40, "sprites/Economics Building.png", 8, "Where students learn to navigate the complex world of money and markets.", 90, 40);
        physicsBuilding = new Faculty("Physics Building", 90, 70, 40, "sprites/Physics Building.png", 9, "Where students can dive into the amazing adventure of the universe", 90, 40);
        industrialEngineeringBuilding = new Faculty("Industrial Engineering Building", 90, 60, 40, "sprites/Industrial Engineering Building.png", 8, "Where students learn to optimize processes and make the world more efficient.", 100, 40);
        interiorArchitectureBuilding = new Faculty("Interior Architecture Building", 75, 45, 30, "sprites/Interior Architecture Building.png", 7, "Where students design inspiring and functional interior spaces.",90, 40);
        mechanicalEngineeringBuilding = new Faculty("Mechanical Engineering Building", 95, 65, 50, "sprites/Mechanical Engineering Building.png", 9, "Where students engineer machines and mechanisms that move the world.", 120, 60);
        graphicDesignBuilding = new Faculty("Graphic Design Building", 70, 30, 25, "sprites/Graphic Design Building.png", 7, "Where creativity meets technology to communicate visually.", 90, 35);
        chemistryBuilding = new Faculty("Chemistry Building", 85, 55, 45, "sprites/Chemistry Building.png", 8, "Where students explore reactions and molecules to unlock chemical secrets.", 90, 30);
        urbanDesignAndLandscapeBuilding = new Faculty("Urban Design and Landscape Building", 75, 45, 20, "sprites/Urban Design and Landscape Building.png", 7, "Where students shape sustainable cities and green spaces.", 90, 35);
        politicalScienceBuilding = new Faculty("Political Science Building", 70, 40, 20, "sprites/Political Science Building.png", 7, "Where students learn to navigate the complex world of politics.", 100, 40);
        amphitheatreBuilding = new Faculty("Amphitheatre Building", 60, 20, 5, "sprites/Amphitheatre Building.png", 6, "Where the university gathers for performances, lectures, and events.",  70, 20);
        mathematicsBuilding = new Faculty("Mathematics Building", 80, 50, 20, "sprites/Mathematics Building.png", 9, "Where abstract concepts become the language of the universe.", 100, 40);
        communicationAndDesignBuilding = new Faculty("Communication and Design Building", 70, 45, 25, "sprites/Communication and Design Building.png", 7, "Where ideas are crafted into compelling stories and visuals.",80, 30);
        architectureBuilding = new Faculty("Architecture Building", 75, 50, 30, "sprites/Architecture Building.png", 8, "Where students draft the future skyline with innovation and precision.",70, 30);

        unbuiltFaculties.add(computerScienceFaculty);
        unbuiltFaculties.add(eeeBuilding);
        unbuiltFaculties.add(biologyBuilding);
        unbuiltFaculties.add(psychologyBuilding);
        unbuiltFaculties.add(lawFaculty);
        unbuiltFaculties.add(managementFaculty);
        unbuiltFaculties.add(economicsBuilding);
        unbuiltFaculties.add(physicsBuilding);
        unbuiltFaculties.add(industrialEngineeringBuilding);
        unbuiltFaculties.add(interiorArchitectureBuilding);
        unbuiltFaculties.add(mechanicalEngineeringBuilding);
        unbuiltFaculties.add(graphicDesignBuilding);
        unbuiltFaculties.add(chemistryBuilding);
        unbuiltFaculties.add(urbanDesignAndLandscapeBuilding);
        unbuiltFaculties.add(politicalScienceBuilding);
        unbuiltFaculties.add(amphitheatreBuilding);
        unbuiltFaculties.add(mathematicsBuilding);
        unbuiltFaculties.add(communicationAndDesignBuilding);
        unbuiltFaculties.add(architectureBuilding);
    }

    public void initializeDecorations(){
        ornamentalPool = new Decoration("Ornamental Pool", 80, "sprites/Ornamental Pool.png", "A beautiful pool that adds a touch of elegance to the campus.", 4);
        treesBushes = new Decoration("Trees, bushes", 40, "sprites/Trees.png", "A collection of trees and bushes that enhance the campus's natural beauty.", 2);
        scienceFacultyPool = new Decoration("Science Faculty Pool", 50, "sprites/Science Faculty Pool.png", "A serene pool located in front of the Science Faculty.", 3);

        unbuiltDecorations.add(ornamentalPool);
        unbuiltDecorations.add(treesBushes);
        unbuiltDecorations.add(scienceFacultyPool);

    }

    public void initializeOtherBuildings(){
        //undemCafe = new OtherBuilding("Ündem Cafe", 100, 20, 30, 3, "sprites/Undem Cafe.png", "A cozy cafe where students can relax and recharge with a cup of coffee.", game);
        library = new OtherBuilding("Library", 200, 200,170, 10, "sprites/Library.png", "A quiet place filled with tons of books and researches to enlighten the minds of the students.", game);
        sportsHall = new OtherBuilding("Sports Hall", 120, 90,100, 8, "sprites/Sports Hall.png", "A place where students can engage in various sports and activities.", game);
        researchLab = new OtherBuilding("Research Lab", 140, 100, 120, 9, "sprites/Research Lab.png", "A facility equipped for scientific research and experiments.", game);
        healthCenter = new OtherBuilding("Health Center", 200, 100, 130, 8, "sprites/Health Center.png", "A medical facility providing healthcare services to students and staff.", game);
        stadium = new OtherBuilding("Stadium", 250, 100, 120, 9, "sprites/Stadium.png", "A large venue for sports events and gatherings.", game);
        commonDormitory = new OtherBuilding("Common Dormitory", 120, 0, 60, 4,"sprites/Common Dormitory.png", "A shared living space for students.", game);
        middlingDormitory = new OtherBuilding("Middling Dormitory", 150, 0, 80, 5,"sprites/Middling Dormitory.png", "A comfortable dormitory with basic amenities.", game);
        cafeteria = new OtherBuilding("Cafeteria", 100, 70, 90, 4, "sprites/Cafeteria.png", "A dining facility offering a variety of meals and snacks.", game);
        luxuryDormitory = new OtherBuilding("Luxury Dormitory", 180, 0, 100, 6, "sprites/Luxury Dormitory.png", "A high-end dormitory with premium amenities.", game);
        crazyBuddyCafe = new OtherBuilding("CrazyBuddy Cafe", 120, 60, 70, 4, "sprites/CrazyBuddy Cafe.png", "A trendy cafe where students can socialize and enjoy delicious treats.", game);
        carPark = new OtherBuilding("Car Park", 50, 0, 0, 2, "sprites/Car Park.png", "A parking facility for students and staff to park their vehicles.", game);

        //unbuiltOtherBuildings.add(undemCafe);
        unbuiltOtherBuildings.add(library);
        unbuiltOtherBuildings.add(sportsHall);
        unbuiltOtherBuildings.add(researchLab);
        unbuiltOtherBuildings.add(healthCenter);
        unbuiltOtherBuildings.add(stadium);
        unbuiltOtherBuildings.add(commonDormitory);
        unbuiltOtherBuildings.add(middlingDormitory);
        unbuiltOtherBuildings.add(cafeteria);
        unbuiltOtherBuildings.add(luxuryDormitory);
        unbuiltOtherBuildings.add(crazyBuddyCafe);
        unbuiltOtherBuildings.add(carPark);
    }

    public void initializeUpgrades(){
        for(Faculty faculty: unbuiltFaculties) {
            Upgrade ventilationUpgrade = new Upgrade(
                "Advanced Ventilation System",
                100,
                4,
                faculty.getImagePath(),
                "Improves air quality and comfort in classrooms.",
                faculty,
                "Ventilation"
            );

            Upgrade energyUpgrade = new Upgrade(
                "Energy Efficiency Retrofit",
                200,
                5,
                faculty.getImagePath(),
                "Reduces the building's energy consumption.",
                faculty,
                "Energy Efficiency"
            );

            Upgrade capacityUpgrade = new Upgrade(
                "Capacity Expansion",
                300,
                6,
                faculty.getImagePath(),
                "Adds more lecture halls, labs, and facilities.",
                faculty,
                "Capacity"
            );

            faculty.addUpgrade(ventilationUpgrade, 1);
            faculty.addUpgrade(energyUpgrade, 2);
            faculty.addUpgrade(capacityUpgrade, 3);
        }
    }

    public void buyFaculty(Faculty f, Player player){
        int index = unbuiltFaculties.indexOf(f);
        this.unbuiltFaculties.remove(index);
        this.builtFaculties.add(f);
        player.setCoin(player.getCoin() - f.getCost());
        updatePlayerReputation(player,f);
        updateStudentSatisfactionPoint(player,f);

    }

    public void buyDecoration(Decoration d, Player player){
        int index = unbuiltDecorations.indexOf(d);
        this.unbuiltDecorations.remove(index);
        this.builtDecorations.add(d);
        player.setCoin(player.getCoin() - d.getCost());


    }

    public void buyOtherBuilding(OtherBuilding b, Player player){
        int index = unbuiltOtherBuildings.indexOf(b);
        this.unbuiltOtherBuildings.remove(index);
        this.builtOtherBuildings.add(b);
        player.setCoin(player.getCoin() - b.getCost());
        updatePlayerReputation(player,b);
        updateStudentSatisfactionPoint(player,b);

    }

    private void updatePlayerReputation(Player player,Building building) {
        player.setUniversityReputationPoint(player.getUniversityReputationPoint() + building.universityReputationPoint);
    }


    private void updateStudentSatisfactionPoint(Player player,Building building) {
        player.addStudentSatisfactionPoint(building.studentSatisfactionPoint);
    }


    public ArrayList<Faculty> getUnbuiltFaculties(){
        return this.unbuiltFaculties;
    }

    public ArrayList<Faculty> getBuiltFaculties(){
        return this.builtFaculties;
    }

    public ArrayList<Decoration> getUnbuiltDecorations(){
        return this.unbuiltDecorations;
    }

    public ArrayList<Decoration> getBuiltDecorations(){
        return this.builtDecorations;
    }

    public ArrayList<OtherBuilding> getUnbuiltOtherBuildings(){
        return this.unbuiltOtherBuildings;
    }

    public ArrayList<OtherBuilding> getBuiltOtherBuildings(){
        return this.builtOtherBuildings;
    }


}

