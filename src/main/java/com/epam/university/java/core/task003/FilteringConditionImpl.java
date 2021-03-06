package com.epam.university.java.core.task003;

/**
 * Created by Romin Nuro on 19.08.2020 1:28.
 */
public class FilteringConditionImpl implements FilteringCondition {
    /**
     * Check if element satisfies condition.
     *
     * @param element element
     * @return is satisfied, or false if element not provided
     */
    @Override
    public boolean isValid(String element) {
        return element.length() > 3;
    }
}