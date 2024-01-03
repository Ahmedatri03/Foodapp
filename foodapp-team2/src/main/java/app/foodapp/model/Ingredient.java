package app.foodapp.model;


public class Ingredient {


    private long id;
    private String aisle;
    private String name;
    private String unit;

    public Ingredient(long id, String aisle, String name, String unit) {
        this.id = id;
        this.aisle = aisle;
        this.unit = unit;
        this.name = name;

    }

    public long getId() {
        return id;
    }

    public String getAisle() {
        return aisle;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    @Override

    public String toString() {
        return name;
    }
}
