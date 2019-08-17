package com.vaadin.leaflet;

/**
 * DISCLAIMER: It is always necessary to review the [license] section
 * of each layer to see the type of license, the terms of use or the
 * copyright text that we must include in the map.
 */
public enum TileEnum {

    CARTO("https://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png", "&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors, &copy; <a href=\"https://carto.com/attribution\">CARTO</a>"),
    CARTO_LIGHT("https://cartodb-basemaps-a.global.ssl.fastly.net/light_all/{z}/{x}/{y}.png", "&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors, &copy; <a href=\"https://carto.com/attribution\">CARTO</a> | Light"),
    CARTO_DARK("http://a.basemaps.cartocdn.com/dark_all/{z}/{x}/{y}.png\n", "&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors, &copy; <a href=\"https://carto.com/attribution\">CARTO</a> | Dark"),

    OSM("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", "Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a>"),
    OSM_MAPNICK("http://tile.openstreetmap.org/{z}/{x}/{y}.png", "Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a>"),
    OSM_CYCLE("http://tile.thunderforest.com/cycle/{z}/{x}/{y}.png", "Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> | Cycle"),
    OSM_BLACK_WHITE("http://tiles.wmflabs.org/bw-mapnik/{z}/{x}/{y}.png", "Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> | B&W"),
    OSM_3D("http://tiles.osm2world.org/osm/pngtiles/n/{z}/{x}/{y}.png", "Map data © <a href=\"https://openstreetmap.org\">OpenStreetMap</a> | 3D"),

    GOOGLE("https://mt1.google.com/vt/lyrs=r&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a>"),
    GOOGLE_SAT("http://www.google.cn/maps/vt?lyrs=s@189&gl=cn&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a> | Satellite"),
    GOOGLE_HYBRID("https://mt1.google.com/vt/lyrs=y&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a> | Hybrid"),
    GOOGLE_TERRAIN("https://mt1.google.com/vt/lyrs=t&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a> | Terrain"),
    GOOGLE_TRAFFIC("https://mt1.google.com/vt?lyrs=h@159000000,traffic|seconds_into_week:-1&style=3&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a> | Traffic"),
    GOOGLE_ROADS("https://mt1.google.com/vt/lyrs=h&x={x}&y={y}&z={z}", "Map data © <a href=\"https://google.com/maps\">GoogleMaps</a> | Roads"),

    //FIXME: see https://stackoverflow.com/questions/17154781/use-bing-quadkey-tiles-instead-of-x-y-z-tiles-in-leafletjs-map
    //BING("http://ecn.dynamic.t0.tiles.virtualearth.net/comp/CompositionHandler/{q}?mkt=en-us&it=G,VE,BX,L,LA&shading=hill", "Map data © <a href=\"https://bing.com/maps\">Bing</a>"),
    //BING_SAT("http://ecn.t3.tiles.virtualearth.net/tiles/a{q}.jpeg?g=0&dir=dir_n", "Map data © <a href=\"https://bing.com/maps\">Bing</a> | Satellite"),

    ESRI_SAT("https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Satellite"),
    ESRI_NATGEO("http://services.arcgisonline.com/ArcGIS/rest/services/NatGeo_World_Map/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | NatGEO"),
    ESRI_PHYSICAL("https://server.arcgisonline.com/ArcGIS/rest/services/World_Physical_Map/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Physical"),
    ESRI_STREETS("https://server.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Streets"),
    ESRI_TERRAIN("https://server.arcgisonline.com/ArcGIS/rest/services/World_Terrain_Base/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Terrain"),
    ESRI_TOPO("https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Topo"),
    ESRI_TRANSPORTATION("https://server.arcgisonline.com/ArcGIS/rest/services/Reference/World_Transportation/MapServer/tile/{z}/{y}/{x}", "Map data © <a href=\"https://www.arcgis.com/home/webmap/viewer.html\">arcGIS</a> | Transportation"),

    STAMEN_TERRAIN("http://a.tile.stamen.com/terrain/{z}/{x}/{y}.png", "Map data © <a href=\"https://stamen.com/maps/\">Stamen</a> | Terrain"),
    STAMEN_TONER("http://tile.stamen.com/toner/{z}/{x}/{y}.png", "Map data © <a href=\"https://stamen.com/maps/\">Stamen</a> | Toner"),
    STAMEN_WATERCOLOR("http://tile.stamen.com/watercolor/{z}/{x}/{y}.jpg", "Map data © <a href=\"https://stamen.com/maps/\">Stamen</a> | Watercolor"),

    ;

    final String url;
    final String attribution;

    TileEnum(final String url, final String attribution) {
        this.attribution = attribution;
        this.url = url;
    }

    String getUrl() {
        return url;
    }

    String getAttribution() {
        return attribution;
    }
}
