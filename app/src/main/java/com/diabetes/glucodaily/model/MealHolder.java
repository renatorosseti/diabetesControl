package com.diabetes.glucodaily.model;

public class MealHolder {

    private Meal meal;

    private Boolean selected;

    public MealHolder(Meal meal) {
        this.meal = meal;
        this.selected = false;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
