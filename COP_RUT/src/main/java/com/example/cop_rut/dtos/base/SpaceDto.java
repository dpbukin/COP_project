package com.example.cop_rut.dtos.base;

import com.example.cop_rut.model.enam.room.SpaceType;

public class SpaceDto {
    private SpaceType spaceType;
    private int numbersOfRums;
    private double area;

    public SpaceDto() {
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
        return "SpaceView{" +
                "spaceType=" + spaceType +
                ", numbersOfRums=" + numbersOfRums +
                ", area=" + area +
                '}';
    }
}
