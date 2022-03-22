
document.addEventListener('deviceready', onDeviceReady, false);

function onDeviceReady() {
    inicio();
}
var credito = Math.floor(Math.random()*4)+9;
var imagenes = ["Ichigo.png","Ulquiorra.png","Yamamoto.png","Yoruichi.png","Sado.png","Yachiru.png","Orihime.png"];
var premios = [6,5,5,4,3,3,1];
var numerosActuales=[];
var au;
var activos = false;

function inicio(){
	document.getElementById("tirar").onclick=lanzarInicio;
	document.getElementById("equis").onclick=cerrar;
	au=document.getElementById("sonido");
	for(let k=0; k<document.getElementsByClassName("boton").length; k++){
		document.getElementsByClassName("boton")[k].onclick=lanzarUno;
	}
	actualizar();
}
function lanzarInicio(){
	if(credito>0) {
		sonido("tirar.mp3");
		activos = true;
	    numerosActuales=[];
		for(let k=0; k<document.getElementsByClassName("boton").length; k++){
			numerosActuales.push(escogerNumero(""));
			mostrarImagen(k,numerosActuales[k]);
		}
		comparar();
	}
}
function lanzarUno() {
	if(credito>0 && activos == true){
		sonido("reintento.mp3");
		let hijos = this.parentNode.parentNode.children;
		//Saber en que seccion me encuentro
		for(let k=0;k<hijos.length;k++){
			if(this.parentNode == hijos[k]){
				numerosActuales[k]=escogerNumero(numerosActuales[k]);
				mostrarImagen(k,numerosActuales[k]);
				comparar();
				break;
			}
		}
	}
}
function escogerNumero(actual){
	do{
		var azar = Math.floor(Math.random()*imagenes.length);
	}while(azar==actual);
	return azar;
}
function mostrarImagen(num,im){
	document.getElementsByClassName("imagen")[num].getElementsByTagName("img")[0].src="img/"+ imagenes[im];
}
function comparar(){
	if(numerosActuales[0]==numerosActuales[1] && numerosActuales[1]==numerosActuales[2]) {
		activos = false;
		let premio = premios[numerosActuales[0]];
		let mensaje = `has ganado ${premio} monedas <div>`;
			for(let i=0; i<premio ; i++){
			 	mensaje +=`<img src="img/moneda.png">`;
			}
			mensaje += `</div>`;
		mostrarMsj(mensaje);
		sonido("victoria.mp3");
		credito += premios[numerosActuales[0]];
	}
	credito--;
	actualizar();
}
function actualizar(){
	document.getElementById("dinero").innerHTML = credito;
	document.getElementById("monedas").innerHTML = "";
	for(var i=1; i<=credito; i++) {
		document.getElementById("monedas").innerHTML += `<img src="img/moneda.png">`;
	}
	if(credito<1){
		mostrarMsj("<b>GAME OVER</b><div class='subtitulo'>No te queda dinero</div>");
		sonido("game-over.mp3");
	}
}
function mostrarMsj(msj){
	document.getElementById("velo").style.display="flex";
	document.getElementById("mensaje").innerHTML = msj;
}
function cerrar(){
	document.getElementById("velo").style.display="none";
	au.pause();
}
function sonido(audio){
	au.src="audios/"+audio;
	au.play();
}