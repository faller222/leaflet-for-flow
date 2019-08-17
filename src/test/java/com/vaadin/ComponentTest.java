package com.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.leaflet.LeafletComponent;
import com.vaadin.leaflet.LeafletPoint;
import com.vaadin.leaflet.LeafletPointSelector;
import com.vaadin.leaflet.TileEnum;

import java.util.Arrays;
import java.util.stream.Stream;

@Route("component")
public class ComponentTest extends Div {

    private static final String GEO = "{ \"type\":\"FeatureCollection\", \"features\":[ { \"type\":\"Feature\", \"geometry\":{ \"type\":\"LineString\", \"coordinates\":[ [ -105.00341892242432, 39.75383843460583 ], [ -105.0008225440979, 39.751891803969535 ] ] }, \"properties\":{ \"popupContent\":\"This is a free bus line that will take you across downtown.\", \"underConstruction\":false }, \"id\":1 } ] }";
    private static final String GEO2 = "{ \"type\":\"FeatureCollection\", \"features\":[{ \"type\":\"Feature\", \"geometry\":{ \"type\":\"LineString\", \"coordinates\":[ [ -104.99820470809937, 39.74979664004068 ], [ -104.98689651489258, 39.741052354709055 ] ] }, \"properties\":{ \"popupContent\":\"This is a free bus line that will take you across downtown.\", \"underConstruction\":false }, \"id\":3 } ] }";

    protected LeafletComponent map;
    boolean alternate = true;

    public ComponentTest() {
        map = new LeafletComponent();
        map.setHeight("580px");

        LeafletPoint initialPoint = new LeafletPoint(-34.9076, -56.2082);
        map.goTo(initialPoint);
        add(map);

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
            LeafletPoint zabala = new LeafletPoint(-34.9076, -56.2082);
            map.goTo(zabala);
        });
        add(b5);

        Button b6 = new Button("Center (50, 10)", e -> {
            LeafletPoint createPoint = new LeafletPoint(50, 10);
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
            LeafletPoint gotoXY = new LeafletPoint(39.7519, -105.00082);
            map.goTo(gotoXY);
            if (alternate) {
                map.addGeoJSON("line", GEO);
            } else {
                map.addGeoJSON("line", GEO2);
            }
            alternate = !alternate;
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
