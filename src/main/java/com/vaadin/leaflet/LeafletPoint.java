package com.vaadin.leaflet;

public class LeafletPoint {

    private int id;
    private final double latitude;
    private final double longitude;

    public LeafletPoint(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
