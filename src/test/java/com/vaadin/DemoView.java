package com.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import com.vaadin.leaflet.LeafletPointSelector;
import com.vaadin.leaflet.TileEnum;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.Arrays;
import java.util.stream.Stream;

@Route
public class DemoView extends Div {

    private static final String GEO = "{ \"type\":\"FeatureCollection\", \"features\":[ { \"type\":\"Feature\", \"geometry\":{ \"type\":\"LineString\", \"coordinates\":[ [ -105.00341892242432, 39.75383843460583 ], [ -105.0008225440979, 39.751891803969535 ] ] }, \"properties\":{ \"popupContent\":\"This is a free bus line that will take you across downtown.\", \"underConstruction\":false }, \"id\":1 }, { \"type\":\"Feature\", \"geometry\":{ \"type\":\"LineString\", \"coordinates\":[ [ -105.0008225440979, 39.751891803969535 ], [ -104.99820470809937, 39.74979664004068 ] ] }, \"properties\":{ \"popupContent\":\"This is a free bus line that will take you across downtown.\", \"underConstruction\":true }, \"id\":2 }, { \"type\":\"Feature\", \"geometry\":{ \"type\":\"LineString\", \"coordinates\":[ [ -104.99820470809937, 39.74979664004068 ], [ -104.98689651489258, 39.741052354709055 ] ] }, \"properties\":{ \"popupContent\":\"This is a free bus line that will take you across downtown.\", \"underConstruction\":false }, \"id\":3 } ] }";

    protected LeafletPointSelector map;

    public DemoView() {
        map = new LeafletPointSelector();
        map.setHeight("580px");

        Point initialPoint = new GeometryFactory().createPoint(new Coordinate(-56.20826804624813, -34.90763080315059));
        map.setValue(initialPoint);

        add(map);

        Button b1 = new Button("Show content", e -> {
            Notification.show(map.getValue().toString());
        });
        add(b1);

        Button b2 = new Button("Limit zoom(2 - 20)", e -> {
            map.setMinZoom(2);
            map.setMaxZoom(20);
        });
        add(b2);

        Button b3 = new Button("Limit zoom(10 - 18)", e -> {
            map.setMinZoom(10);
            map.setMaxZoom(18);
        });
        add(b3);

        Button b4 = new Button("Open Dialog", e -> {
            Dialog dialog = new Dialog();
            final LeafletPointSelector leafletMap = new LeafletPointSelector();
            leafletMap.setHeight("300px");
            leafletMap.setWidth("500px");
            dialog.add(leafletMap);
            dialog.open();
        });
        add(b4);

        Button b5 = new Button("Center zabala", e -> {
            Point zabala = new GeometryFactory().createPoint(new Coordinate(-56.20826804624813, -34.90763080315059));
            map.goTo(zabala);
        });
        add(b5);

        Button b6 = new Button("Center (50, 10)", e -> {
            Point createPoint = new GeometryFactory().createPoint(new Coordinate(10, 50));
            map.goTo(createPoint);
        });
        add(b6);

        Button b7 = new Button("MaxZoom (18)", e -> {
            map.setZoom(18);
        });
        add(b7);

        Button b8 = new Button("MinZoom (5)", e -> {
            map.setZoom(5);
        });
        add(b8);

        Button b9 = new Button("AddGeoJSON", e -> {
            Point gotoXY = new GeometryFactory().createPoint(new Coordinate(-105.0008225440979, 39.751891803969535));
            map.goTo(gotoXY);
            map.addGeoJSON("line", GEO);
        });
        add(b9);

        Button b0 = new Button("RemoveGeoJSON", e -> {
            map.removeGeoJSON("line");
        });
        add(b0);

        final Stream<String> tileStream = Arrays.stream(TileEnum.values()).map(Enum::name);
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setItems(tileStream);
        comboBox.addValueChangeListener(event -> {
            if (!event.getSource().isEmpty()) {
                map.setTile(TileEnum.valueOf(event.getValue()));
            }
        });
        add(comboBox);

    }
}
