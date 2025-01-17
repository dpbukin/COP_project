package com.example.cop_rut.model;

import com.example.cop_rut.model.enam.room.SpaceType;

public class Space {
    private SpaceType spaceType;
    private int numbersOfRums;
    private double area;

    public Space() {
    }

    public Space(SpaceType spaceType, int numbersOfRums, double area) {
        this.spaceType = spaceType;
        this.numbersOfRums = numbersOfRums;
        this.area = area;
    }

    public SpaceType getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(SpaceType spaceType) {
        this.spaceType = spaceType;
    }

    public int getNumbersOfRums() {
        return numbersOfRums;
    }

    public void setNumbersOfRums(int numbersOfRums) {
        this.numbersOfRums = numbersOfRums;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Space{" +
                "spaceType=" + spaceType +
                ", numbersOfRums=" + numbersOfRums +
                ", area=" + area +
                '}';
    }
}
