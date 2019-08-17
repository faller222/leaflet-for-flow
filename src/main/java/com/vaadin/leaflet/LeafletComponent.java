package com.vaadin.leaflet;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.function.SerializableConsumer;

import java.io.Serializable;
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

    protected void callJS(final String functionName, final Serializable... arguments) {
        runBeforeClientResponse(ui -> getElement().callJsFunction(functionName, arguments));
    }

    public void setMaxZoom(final int val) {
        callJS("$connector.maxZoom", val);
    }

    public void setMinZoom(final int val) {
        callJS("$connector.minZoom", val);
    }

    public void setZoom(final int val) {
        callJS("$connector.setZoom", val);
    }

    public void goTo(final LeafletPoint p) {
        callJS("$connector.goto", p.getLatitude(), p.getLongitude());
    }

    public void setTile(final TileEnum t) {
        callJS("$connector.setTile", t.getUrl(), t.getAttribution());
    }

    public void addGeoJSON(final String name, final String geoJSON) {
        callJS("$connector.addGeoJSON", name, geoJSON);
    }

    public void removeGeoJSON(final String name) {
        callJS("$connector.removeGeoJSON", name);
    }

    @Override
    protected void onAttach(final AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initConnector();
    }

    protected void initConnector() {
        runBeforeClientResponse(ui -> ui.getPage()
                .executeJs("window.Vaadin.Flow.leafletConnector.initLazy($0)", getElement()));
    }

    void runBeforeClientResponse(final SerializableConsumer<UI> command) {
        getElement().getNode().runWhenAttached(ui -> ui
                .beforeClientResponse(this, context -> command.accept(ui)));
    }

}