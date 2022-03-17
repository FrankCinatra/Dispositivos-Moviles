var Latitude = undefined;
var Longitude = undefined;
var app = {
    initialize: function(){
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },
    onDeviceReady: function(){
        this.receivedEvent('deviceready');
    },
    receivedEvent: function(id){
        navigator.geolocation.getCurrentPosition(onMapSuccess, onMapError, {enableHighAccuracy: true});
    }
};

var onMapSuccess = function (position) {

    Latitude = position.coords.latitude;
    Longitude = position.coords.longitude;

    getMap(Latitude, Longitude);

}

function getMap(latitude, longitude) {
    var latLong = new google.maps.LatLng(latitude, longitude);
    var mapOptions = {
        center: new google.maps.LatLng(0, 0),
        zoom: 11,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("map"), mapOptions);

    /*var marker = new google.maps.Marker({
        position: latLong
    });

    marker.setMap(map);
    map.setZoom(15);
    map.setCenter(marker.getPosition());*/
}

function onMapError(error) {
    console.log('code: ' +error.code +'\n' +'message: ' +error.message +'\n');
}

app.initialize();