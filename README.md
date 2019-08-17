# LeafletJS for Flow

Vaadin 10+ Java integration for [LeafletJS](https://leafletjs.com). 

Builds will be available from https://vaadin.com/directory 

## Origin
- First project on [GitHub - leaflet-for-flow](https://github.com/mstahv/leaflet-for-flow) by [mstahv](https://github.com/mstahv)

- Some changes based on [this Fork](https://github.com/go4st/leaflet-for-flow) by [go4st](https://github.com/go4st)

## Add on this project

- Just show map Component
- Keep PointSelector Component
- Move map from Backend
- Set Zoom level from Backend
- Limit Zoom
- Add several tile layers
- Change tile layer from BackEnd
- Add/Remove GeoJSON  
- Works on [Vaadin 14](https://vaadin.com/releases/vaadin-14) with compatibilityMode


## Future work 

- Show PopUps
- Manage gemoetry syles 
- MultiPoint Selector
- ValueChangeListener
- Your Idea

## Limitations

The current version is still experimental and lacks most features of the v-leaflet add-on for older Vaadin versions.
The current version is more of a proof of concept and only contains one component to pick a JTS Point.
The LeafletPointSelector implements HasValue<Point> so it can be bound to JTS Point property with Binder.

With this implementation strategy the component will never work with the latest Vaadin Designer nor with html templates,
but it seems like a rather simple approach to get something functional done for plain Java developers.

## Development instructions

Starting the test/demo server:
```
mvn jetty:run -Dvaadin.compatibilityMode=true
```

This deploys demo at http://localhost:9998

