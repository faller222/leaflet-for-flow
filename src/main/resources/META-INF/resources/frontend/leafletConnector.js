window.Vaadin.Flow.leafletConnector = {
    initLazy: function (c) {
        // Check whether the connector was already initialized
        if (c.$connector) {
            return;
        }

        c.$connector = {

            setPoint: function (lat, lon) {
                if (!this.marker) {
                    //Init Singleton Marker
                    this.marker = L.marker([lat, lon], {draggable: true}).addTo(this.mymap);
                    this.marker.on('dragend', function (event) {
                        var marker = event.target;
                        var position = marker.getLatLng();
                        c.$connector.center();
                        c.$server.updatePosition(position.lat, position.lng);
                    });
                } else {
                    this.marker.setLatLng(L.latLng(lat, lon));
                }
                this.center();
                c.$server.updatePosition(lat, lon);
            },

            minZoom: function (zoom) {
                this.mymap.options.minZoom = zoom;
            },

            maxZoom: function (zoom) {
                this.mymap.options.maxZoom = zoom;
            },

            setZoom: function (zoom) {
                this.mymap.setZoom(zoom);
            },

            goto: function (lat, lon) {
                this.mymap.panTo([lat, lon]);
            },

            addGeoJSON: function (name, geoJSON) {
                var layer = L.geoJSON().addTo(this.mymap);
                layer.addData(JSON.parse(geoJSON));

                if (!this.myGeoJSON[name]) {
                    this.myGeoJSON[name] = new L.LayerGroup();
                    this.myGeoJSON[name].addTo(this.mymap);
                }

                this.myGeoJSON[name].addLayer(layer);
            },

            removeGeoJSON: function (name) {
                if (this.myGeoJSON[name]) {
                    this.mymap.removeLayer(this.myGeoJSON[name]);
                    this.myGeoJSON[name] = null;
                }
            },

            center: function () {
                this.mymap.panTo(this.marker.getLatLng());
            },

            setEditorContent: function (html) {
                this.editor.setContent(html);
            },

            setTile: function (url, attib) {
                this.mymap.removeLayer(this.tile);

                this.tile = L.tileLayer(url, {
                    attribution: attib,
                });
                this.tile.addTo(mymap);
            }
        };

        var currentValue = "";

        const pushChanges = function () {
            c.$server.updateValue(currentValue)
        };

        var mymap = c.$connector.mymap = L.map(c.id, {
            center: [0, 0],
            zoom: 15,
            worldCopyJump: true,
            zoomControl: false,
            minZoom: 10,
            maxZoom: 18
        });

        c.$connector.myGeoJSON = [];

        L.DomUtil.addClass(mymap._container, 'crosshair-cursor-enabled');

        var tile = c.$connector.tile = L.tileLayer('https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, &copy; <a href="https://carto.com/attribution">CARTO</a>',
        });
        tile.addTo(mymap);

        c.style.cursor = "crosshair";

        function onMapClick(e) {
            c.$connector.setPoint(e.latlng.lat, e.latlng.lng);
        }

        mymap.on('click', onMapClick);

    }
};
