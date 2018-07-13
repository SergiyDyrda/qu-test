package com.qualityunit.task.entity;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
abstract public class FieldOfRecord {

    private String id;

    public FieldOfRecord(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (id == null) return true;

        FieldOfRecord that = (FieldOfRecord) o;

        return id.equals(that.id);
    }

}
