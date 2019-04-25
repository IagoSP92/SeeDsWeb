

$(document).ready(function () {
	
	$("#seguirButton").click(function(){
		alert($(this).attr("data-idContenido"));
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id_contenido':$(this).attr("data-idContenido"),
				'siguiendo': $(this).attr("data-siguiendo"),
				'action':"seguir"},				
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",
				dataType:"json",
				success:
				function(){
					
					location.reload(true);
				}				

		});

	})
	
	
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
