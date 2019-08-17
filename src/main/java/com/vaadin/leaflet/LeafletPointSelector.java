package com.vaadin.leaflet;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import org.github.legioth.field.Field;
import org.github.legioth.field.ValueMapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.UUID;

@Tag("div")
public class LeafletPointSelector extends LeafletComponent implements Field<LeafletPointSelector, Point> {

    private final String id = UUID.randomUUID().toString();

    private static final GeometryFactory gf = new GeometryFactory();
    private final ValueMapper<Point> valueMapper;

    public LeafletPointSelector() {
        setId(id);
        valueMapper = Field.init(this, null, this::sendPointToClient);
    }

    protected void sendPointToClient(Point p) {
        runBeforeClientResponse(ui -> getElement()
                .callJsFunction("$connector.setPoint", p.getCoordinate().y, p.getCoordinate().x)
        );
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initConnector();
        if (getValue() != null) {
            sendPointToClient(getValue());
        }
    }

    private void initConnector() {
        runBeforeClientResponse(ui ->
                ui.getPage().executeJs("window.Vaadin.Flow.leafletConnector.initLazy($0)", getElement()));
    }

    @ClientCallable
    private void updatePosition(double lat, double lon) {
        Point point = gf.createPoint(new Coordinate(lon, lat));
        valueMapper.setModelValue(point, true);
    }

}