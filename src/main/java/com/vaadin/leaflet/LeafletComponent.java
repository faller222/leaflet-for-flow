package com.vaadin.leaflet;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.function.SerializableConsumer;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Tag("div")
@JavaScript("//unpkg.com/leaflet@1.5.1/dist/leaflet.js")
@StyleSheet("//unpkg.com/leaflet@1.5.1/dist/leaflet.css")
@JavaScript("frontend://leafletConnector.js")
@StyleSheet("frontend://leafletCssHacks.css")
public class LeafletComponent extends Component implements HasSize {

    private final String id = UUID.randomUUID().toString();

    public LeafletComponent() {
        setId(id);
    }

    public void setMaxZoom(int val) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.maxZoom", val)
        );
    }

    public void setMinZoom(int val) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.minZoom", val)
        );
    }

    public void setZoom(int val) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.setZoom", val)
        );
    }

    public void goTo(Point p) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.goto", p.getCoordinate().y, p.getCoordinate().x)
        );
    }

    public void setTile(TileEnum t) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.setTile", t.getUrl(), t.getAttribution())
        );
    }

    public void addGeoJSON(String name, String geoJSON) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.addGeoJSON", name, geoJSON)
        );
    }

    public void removeGeoJSON(String name) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.removeGeoJSON", name)
        );
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initConnector();
    }

    private void initConnector() {
        runBeforeClientResponse(ui ->
                ui.getPage().executeJs("window.Vaadin.Flow.leafletConnector.initLazy($0)", getElement()));
    }

    void runBeforeClientResponse(SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

}