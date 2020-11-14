package com.epam.university.java.core.task055;

public class HouseDefinitionImpl implements HouseDefinition {
    private String address;
    private int year;
    private double area;
    private boolean hasCommunal;

    public boolean isHasCommunal() {
        return hasCommunal;
    }

    public void setHasCommunal(boolean hasCommunal) {
        this.hasCommunal = hasCommunal;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }
}
