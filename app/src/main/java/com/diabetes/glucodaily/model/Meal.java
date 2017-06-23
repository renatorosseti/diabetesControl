package com.diabetes.glucodaily.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "MEAL".
 */
public class Meal {

    private Long id;
    private Integer preGlycemia;
    private Integer posGlycemia;
    private Float dosageInsulin;
    private java.util.Date date;
    private Integer sportLevel;
    private String description;
    private String pathImage;
    private Integer type;

    public Meal() {
    }

    public Meal(Long id) {
        this.id = id;
    }

    public Meal(Long id, Integer preGlycemia, Integer posGlycemia, Float dosageInsulin, java.util.Date date, Integer sportLevel, String description, String pathImage, Integer type) {
        this.id = id;
        this.preGlycemia = preGlycemia;
        this.posGlycemia = posGlycemia;
        this.dosageInsulin = dosageInsulin;
        this.date = date;
        this.sportLevel = sportLevel;
        this.description = description;
        this.pathImage = pathImage;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPreGlycemia() {
        return preGlycemia;
    }

    public void setPreGlycemia(Integer preGlycemia) {
        this.preGlycemia = preGlycemia;
    }

    public Integer getPosGlycemia() {
        return posGlycemia;
    }

    public void setPosGlycemia(Integer posGlycemia) {
        this.posGlycemia = posGlycemia;
    }

    public Float getDosageInsulin() {
        return dosageInsulin;
    }

    public void setDosageInsulin(Float dosageInsulin) {
        this.dosageInsulin = dosageInsulin;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public Integer getSportLevel() {
        return sportLevel;
    }

    public void setSportLevel(Integer sportLevel) {
        this.sportLevel = sportLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
