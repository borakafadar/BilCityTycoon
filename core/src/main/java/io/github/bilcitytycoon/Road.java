package io.github.bilcitytycoon;

public class Road extends Building {
    private boolean upgraded;

    public Road(boolean upgraded) {
        super("Road", 0, 0);
        this.upgraded = upgraded;
    }

    @Override
    public String getInfo() {
        return upgraded ? "Upgraded Road" : "Basic Road";
    }

    public boolean isUpgraded() {
        return upgraded;
    }
}
