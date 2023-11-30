package fmi.plovdiv.application.model;

public class Festival {

    private final String id;

    private final String name;

    private final String date;

    private final String location;


    public Festival(String id, String name, String date, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
