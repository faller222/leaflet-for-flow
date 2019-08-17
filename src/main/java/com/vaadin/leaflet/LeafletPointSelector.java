package com.vaadin.leaflet;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import org.github.legioth.field.Field;
import org.github.legioth.field.ValueMapper;

import java.util.UUID;

@Tag("div")
public class LeafletPointSelector extends LeafletComponent implements Field<LeafletPointSelector, LeafletPoint> {

    private final transient ValueMapper<LeafletPoint> valueMapper;

    public LeafletPointSelector() {
        setId(UUID.randomUUID().toString());
        valueMapper = Field.init(this, null, this::sendPointToClient);
    }

    protected void sendPointToClient(final LeafletPoint p) {
        callJS("$connector.setPoint", p.getLatitude(), p.getLongitude());
    }

    @Override
    protected void onAttach(final AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        callJS("$connector.setPointSelector");
        if (getValue() != null) {
            sendPointToClient(getValue());
        }
    }

    @ClientCallable
    private void updatePosition(final double lat, final double lon) {
        LeafletPoint point = new LeafletPoint(lat, lon);
        valueMapper.setModelValue(point, true);
    }

}