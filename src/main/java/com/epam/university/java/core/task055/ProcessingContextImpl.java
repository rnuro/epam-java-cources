package com.epam.university.java.core.task055;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProcessingContextImpl implements ProcessingContext {
    private final Collection<HouseDefinitionImpl> data;

    public ProcessingContextImpl(Collection<HouseDefinitionImpl> data) {
        this.data = data;
    }


    @Override
    public Collection<HouseDefinition> oldestHouses() {
        int minYear = 2020;
        for (HouseDefinitionImpl item : data) {
            if (item.getYear() < minYear && item.getYear() > 0) {
                minYear = item.getYear();
            }
        }
        List<HouseDefinition> oldest = new ArrayList<>();
        for (HouseDefinitionImpl item : data) {
            if (item.getYear() == minYear) {
                oldest.add(item);
            }
        }
        return oldest;
    }

    @Override
    public int averageAge(String district) {
        double avgAge = data.stream()
                .filter(d -> {
                    if (district.equals("Город") && d.getYear() != 0) {
                        return true;
                    }
                    if (d.getYear() == 0) {
                        return false;
                    }
                    return d.getAddress().equals(district);
                })
                .mapToInt(d -> 2020 - d.getYear())
                .average()
                .getAsDouble();
        return (int) Math.floor(avgAge);
    }

    @Override
    public HouseDefinition biggestTotalFloorSpace() {
        HouseDefinition result = null;
        double maxArea = 0;
        for (HouseDefinitionImpl house : data) {
            if (house.getArea() > maxArea) {
                maxArea = house.getArea();
                result = house;
            }
        }
        return result;
    }

    @Override
    public HouseDefinition smallestTotalFloorSpace() {
        HouseDefinition result = null;
        double minArea = Double.MAX_VALUE;
        for (HouseDefinitionImpl house : data) {
            if (house.getArea() < minArea && house.getArea() != 0) {
                minArea = house.getArea();
                result = house;
            }
        }
        return result;
    }

    @Override
    public int totalCountHouses() {
        return data.size();
    }

    @Override
    public int totalCountHousesWithCommunalFlats() {
        return (int) data.stream().filter(h -> h.isHasCommunal()).count();
    }
}
