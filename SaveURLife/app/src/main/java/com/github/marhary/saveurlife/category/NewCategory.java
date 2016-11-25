package com.github.marhary.saveurlife.category;

/**
 * Created by Margo on 11/21/2016.
 */

public class NewCategory {

    private long id;
    private String name;
    private int colour;

    public NewCategory(long id, String name, int colour) {
        this.id = id;
        this.name = name;
        this.colour = colour;
    }

    public NewCategory() {
    }

    public NewCategory(String name, int colour) {
        this.name = name;
        this.colour = colour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}
