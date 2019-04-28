package ru.cft.focusstart.param;

public class ParamsTriangle {
    private String type;
    private double sideA;
    private double sideB;
    private double sideC;

    private double alfaSideA;
    private double bettaSideB;
    private double gammaSideC;

    public ParamsTriangle(Pair pair) {
        this.sideA = pair.params.get(0);
        this.sideB = pair.params.get(1);
        this.sideC = pair.params.get(2);
        alfaSideA = 0;
        bettaSideB = 0;
        gammaSideC = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSideA() {
        return sideA;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    public double getAlfaSideA() {
        return alfaSideA;
    }

    public void setAlfaSideA(double alfaSideA) {
        this.alfaSideA = alfaSideA;
    }

    public double getBettaSideB() {
        return bettaSideB;
    }

    public void setBettaSideB(double bettaSideB) {
        this.bettaSideB = bettaSideB;
    }

    public double getGammaSideC() {
        return gammaSideC;
    }

    public void setGammaSideC(double gammaSideC) {
        this.gammaSideC = gammaSideC;
    }
}
