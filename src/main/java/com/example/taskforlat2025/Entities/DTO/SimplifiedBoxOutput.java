package com.example.taskforlat2025.Entities.DTO;


import com.example.taskforlat2025.Entities.CollectionBox;

public class SimplifiedBoxOutput {
    private long id;
    private boolean empty;
    private boolean assigned;

    public SimplifiedBoxOutput() {
    }

    public SimplifiedBoxOutput(long id, boolean empty, boolean assigned) {
        this.id = id;
        this.empty = empty;
        this.assigned = assigned;
    }

    public SimplifiedBoxOutput(CollectionBox collectionBox) {
        this.id = collectionBox.getBoxId();
        this.empty = collectionBox.isEmpty();
        this.assigned = collectionBox.isAssigned();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public long getId() {
        return id;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isAssigned() {
        return assigned;
    }

}
