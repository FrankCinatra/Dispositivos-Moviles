var app = {
    initialize: function(){
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },
    receivedEvent: function(id){
        navigator.camera.getPicture(onSuccess, onFail, { quality: 50,
            destinationType: Camera.DestinationType.FILE_URI });
    }
};

app.initialize();

function onSuccess(imageURI) {
    var image = document.getElementById('imagen');
    image.src = imageURI;
}

function onFail(message) {
    alert('Failed because: ' + message);
}