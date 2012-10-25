package models;

/**
 * Author: nanne
 */
public class Thought {
    private String notes;

    public Thought(String notes) {
        this.notes = notes;
    }

    public void toTask() {}
    public void save() {}
    public void remove() {}
    public static void create() {}

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
