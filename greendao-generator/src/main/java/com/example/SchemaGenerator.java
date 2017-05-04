package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class SchemaGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.diabetes.instameal.model");
        Entity meal = schema.addEntity("Meal");
        meal.addIdProperty();
        meal.addIntProperty("preGlycemia");
        meal.addIntProperty("posGlycemia");
        meal.addFloatProperty("unitInsulin");
        meal.addDateProperty("date");
        meal.addIntProperty("preExerciseLevel");
        meal.addStringProperty("description");
        meal.addStringProperty("pathImage");
        meal.addStringProperty("type");
        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "./app/src/main/java/");
    }
}
