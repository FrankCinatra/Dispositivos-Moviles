document.addEventListener('deviceready', onDeviceReady, false);
function onDeviceReady() {
    inicio();
}

// Creo el array
listacompra=[];

function inicio(){
    // Creo los eventos
    $("button").click(validar);
    $("#producto").keypress(teclado);
    leerguardado();
}
function leerguardado(){
    // Leo el localstorage (datos locales)
    leido=localStorage.lista;
    if (leido!=undefined && leido.length>0 && leido!=null){
        // Si hay datos guardados
        listacompra=leido.split("**");
        rellenarlista();
    }
}
function rellenarlista(){
    $("#lista").empty();
    for (k=0; k<listacompra.length;k++){
        $("#lista").append("<div class='linea'>"+listacompra[k]+"<img onclick='borrar(this)'src='../img/eliminar.gif'></div>");
    }
}
function guardardatos(){
    // Guardo datos locales, pero antes los convierto a texto
    conversion=listacompra.join("**");
    localStorage.lista=conversion;
}
function teclado(e){
    // Detecto la pulsación del enter (código ASCII: 13)
    if (e.keyCode==13){
        validar();
    }
}
function validar(){
    cosa=$("#producto").val();
    if (listacompra.indexOf(cosa.toLowerCase())<0 && cosa.length>0){
        listacompra.push(cosa);
        $("#lista").append("<div class='linea'>"+cosa+"<img onclick='borrar(this)'src='../img/eliminar.gif'></div>");
        guardardatos();
    }
    $("#producto").val("").focus();
}
function borrar(e){
    // Borro el dato del array (con splice) y del html (con remove)
    buscar=$(e).parent().index();
    listacompra.splice(buscar,1);
    $(e).parent().remove();
    guardardatos();
}