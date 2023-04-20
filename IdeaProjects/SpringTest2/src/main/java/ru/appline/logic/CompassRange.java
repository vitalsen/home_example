package ru.appline.logic;

public class CompassRange {

    private String side;
    private int startDegree;
    private int endDegree;

    public CompassRange(String side, int startDegree, int endDegree) {
        this.side = side;
        this.startDegree = startDegree;
        this.endDegree = endDegree;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setStartDegree(int startDegree) {
        this.startDegree = startDegree;
    }

    public void setEndDegree(int endDegree) {
        this.endDegree = endDegree;
    }

    public String getSide() {
        return side;
    }

    public int getStartDegree() {
        return startDegree;
    }

    public int getEndDegree() {
        return endDegree;
    }
}