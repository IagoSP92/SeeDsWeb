

$(document).ready(function () {
	
	$(".seguirButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'siguiendo': $(this).attr("data-siguiendo"),
				'action':"seguir"},
				
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				
				success: function( valor ){
					
					if(valor.siguiendo==false){
						$(".seguirButton").attr("data-siguiendo", "false");
						
						$(".seguirButton").html(valor.mensaje);
						
						}
					
					if(valor.siguiendo==true){
						$(".seguirButton").attr("data-siguiendo", "true");
						
						$(".seguirButton").html(valor.mensaje);
						
					}
				}	
		});
	})
	
	$(".denunciarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'denunciado': $(this).attr("data-denunciado"),
				'action':"denunciar"},				
				success:
				function(){
					// CARGAR USUARIO CON JSON
					location.reload(true);
					location.reload(true);
				}
		});
	})
	
	$(".guardarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'guardado': $(this).attr("data-guardado"),
				'action':"guardar"},				
				success:
				function(valor){

					if(valor.guardado==false){
						$(".guardarButton").attr("data-guardado", "false");
						
						$(".guardarButton").html(valor.mensaje);
						
						}
					
					if(valor.guardado==true){
						$(".guardarButton").attr("data-guardado", "true");
						
						$(".guardarButton").html(valor.mensaje);
						
					}
					// CARGAR USUARIO CON JSON
//					location.reload(true);
//					location.reload(true);
				}
		});
	})
	
	$(".comentarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'comentado': $(this).attr("data-comentado"),
				'action':"comentar"},				
				success:
				function(){

					location.reload(true);
				}
		});
	})
	
	$(".valorarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'valoracion': $(this).attr("data-valoracion"),
				'action':"valorar"},				
				success:
				function(){
					// CARGAR USUARIO CON JSON
					location.reload(true);
					location.reload(true);
				}
		});
	})
	
	$("#botonEditarPerfil").click(function(){

		$("#botonEditarPerilSalvar").removeAttr('hidden');
		$("#botonEditarPerilCancelar").removeAttr('hidden');
		$(".edicionSpan").removeAttr('hidden');

/*
		$("#inputNombre").on('change', function() {
			$(this).attr('disabled','disabled');
		});
		$("#inputDescripcion").on('change', function() {
			$(this).attr('disabled','disabled');
		});
		$("#inputAvatar").on('change', function() {
			$(this).attr('disabled','disabled');
		});
		$("#inputReal").on('change', function() {
			$(this).attr('disabled','disabled');
		});
		$("#inputApellidos").on('change', function() {
			$(this).attr('disabled','disabled');
		});
		$("#inputFecha").on('change', function() {
			$(this).attr('disabled','disabled');
		});
*/

		}
	);
	
	
	checkCompatible();
	divCheck();

	$("#checkTodos").change(function(){

	})
});

function checkCompatible (){
	if(  $("#checkTodos").prop('checked')==true  ){
		$("#checkVideos").prop('checked', true);
		$("#checkListas").prop('checked', true);
		$("#checkUsuarios").prop('checked', true);
	}
}
/*
function divCheck (){
	$("#checkTodosDiv").click(toggleCheck("#checkTodos"));

}*/

function toggleCheck(check){
	if( $(check).prop('checked')==true ){
		$(check).prop('checked', false)
	} else{
		$(check).prop('checked', true);
	}
}






// PABLO  -   PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  - 
//	 	PABLO  -   PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  - 
// PABLO  -   PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  - 
// 		PABLO  -   PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  -  PABLO  - 

/*
//Menu desplegable
$(document).ready(function () { 

	$("#paises").change(function(){
		var selectedCountry = $(this).children("option:selected").val();
		$.ajax({
			type: "GET",
			url: "/PulBetWeb/usuario",
			data: { 'id':selectedCountry,
				'action':"preRegistro"},
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",
				dataType:"json",
				success: function (provinciasArray) {
					
					if(!$("#provincias").html().isEmpty){
						$("#provincias").html("");
					}
					
					for(var i = 0; i < provinciasArray.length; i++){
						$("#provincias").html($("#provincias").html()+"<option value="+provinciasArray[i].id+">"+provinciasArray[i].nome+"</option>")

					}

				}
		});

	})
	var x = ($("#acumulada").val())*($("#importe").val());
	$("#ganancias").val(x);

});


function desplegarMenu() {
	document.getElementById("meumenudes").classList.toggle("show");
}

window.onclick = function (event) {
	if (!event.target.matches('.dropbtn')) {

		var menudess = document.getElementsByClassName("menudes-contido");
		var i;
		for (i = 0; i < menudess.length; i++) {
			var openmenudes = menudess[i];
			if (openmenudes.classList.contains('show')) {
				openmenudes.classList.remove('show');
			}
		}
	}
}
*/
