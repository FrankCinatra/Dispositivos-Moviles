var imagenes = [];
var titulos = [];

var app = {
    initialize: function(){
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
    },
    receivedEvent: function(id){
        document.getElementById('hacerFoto').onclick = tomarFoto;
        let temporal = localStorage.getItem("imagenes");
        if (temporal!=null && temporal!=undefined){
            let temporal2 = localStorage.getItem("titulos");
            imagenes = temporal.split("***");
            titulos = temporal2.split("***");
            for(let i; i<imagenes.length;i++){
                document.getElementById('fotos').innerHTML += "<div class='foto'><img src='"+imagenes[k]+"'><div class='titulo'>"+titulos[k]+"</div><div class='iconoBorrar'><img src='img/borrar.png' onclick='borrar(this)'></div></div>";
            }
        }
    }
};

function tomarFoto(){
    navigator.camera.getPicture(onSuccess, onFail, { quality: 50, destinationType: Camera.DestinationType.FILE_URI, saveToPhotoAlbum: true });
}

function acabarFoto(contenido){
    let botonpulsado = contenido.buttonIndex;
    let textoescrito = contenido.input1;
    if (botonpulsado==2){
        textoescrito = "(Sin titulo)";
    }
    imagenes.push(im);
    titulos.push(textoescrito);
    guardado();
    //                                                         Nodo1                               Nodo2                                     Nodo3                                           Toma el nombre de clase del Nodo3
    document.getElementById('fotos').innerHTML += "<div class='foto'><img src='"+im+"'><div class='titulo'>"+textoescrito+"</div><div class='iconoBorrar'><img src='img/borrar.png' onclick='borrar(this)'></div></div>";
}

function borrar(clickImg){
    let abuelo = clickImg.parentNode.parentNode;
    let bisabuelo = abuelo.parentNode;
    let index = Array.prototype.indexOf.call(bisabuelo.children, abuelo);
    imagenes.splice(index, 1);
    titulos.splice(index,1);

    abuelo.style.display="none";
    //se puede usar el array de titulos o imagenes, dado que son del mismo tamaño, su funcion es la misma
    if(titulos.length<1){
        localStorage.removeItem("imagenes");
        localStorage.removeItem("titulos");
    }else{
        guardado();
    }
}

function onSuccess(imagen) {
    im = imagen;
    navigator.notification.prompt("Escribe el titulo de tu foto:", acabarFoto, "Titulo", ["Ok", "Sin titulo"], "");
}

function onFail(message) {
    alert('Failed because: ' + message);
}

function guardado(){
    let temp = imagenes.join("***");
    let temp2 = titulos.join("***");
    localStorage.setItem("imagenes",temp);
    localStorage.setItem("titulos",temp2);
}

app.initialize();