

$(document).ready(function () {
	
	$("#seguirButton").click(function(){
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
						$("#seguirButton").attr("data-siguiendo", "false");						
						$("#seguirButton").html(valor.mensaje);						
					}					
					if(valor.siguiendo==true){
						$("#seguirButton").attr("data-siguiendo", "true");						
						$("#seguirButton").html(valor.mensaje);						
					}
				}	
		});
	})
	
	
	$("#denunciarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'denunciado': $(this).attr("data-denunciado"),
				'action':"denunciar"},	
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				success:
				function(valor){
					if(valor.denunciado==false){
						$("#denunciarButton").attr("data-denunciado", "false");						
						$("#denunciarButton").html(valor.mensaje);						
					}					
					if(valor.denunciado==true){
						$("#denunciarButton").attr("data-denunciado", "true");						
						$("#denunciarButton").html(valor.mensaje);						
					}
				}
		});
	})
	
	$("#guardarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'guardado': $(this).attr("data-guardado"),
				'action':"guardar"},
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				success:
				function(valor){
					if(valor.guardado==false){
						$("#guardarButton").attr("data-guardado", "false");						
						$("#guardarButton").html(valor.mensaje);						
					}					
					if(valor.guardado==true){
						$("#guardarButton").attr("data-guardado", "true");						
						$("#guardarButton").html(valor.mensaje);						
					}
				}
		});
	})
	
	
	$("#detalleValoracion").click(function(){
		$("#insertarNota").toggle('hidden');
		$(".insertarNotaButton").toggle('hidden');
	})	
	$(".insertarNotaButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(".mainWindow").attr("data-idContenido"),
				'tipo':$(".mainWindow").attr("data-tipo"),
				'mi_valoracion':$("#insertarNota").val(),
				'action':"valorar"},
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				success:
				function( valor ){
					$("#detalleValoracion").html(valor.valor);
				}
		});
		$("#insertarNota").hide();
		$(".insertarNotaButton").hide();
	})
	
	
	
	$("#comentarButton").click(function(){
		$("#commentarioTextDiv").removeAttr('hidden');
	})
	$("#comentarButtonEnviar").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				'comentado':$("#comentarioText").val(),
				'action':"comentar"},				
				success:
				function(){
					location.reload(true);
				}
		});
	})
	
		$("#noComentarButton").click(function(){
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
	
	
		
	$("#valorarButton").click(function(){
		$.ajax({
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(this).attr("data-idContenido"),
				'tipo':$(this).attr("data-tipo"),
				//'valoracion': $(this).attr("data-valoracion"),
				'action':"valorar"},
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				success:
				function(valoracion, myValoration){
					
					$(".seguirButton").attr("data-siguiendo", "false");						
					$(".seguirButton").html(valor.mensaje);						
					
					// CARGAR USUARIO CON JSON
					location.reload(true);
					location.reload(true);
				}
		});
	})
	
	
	$("#botonEditarPerfil").click(function(){
		
		$(".edicionSpan").toggle('hidden');
		$("#botonEditarPerilCancelar").toggle('hidden');
		$("#botonEditarPerilSalvar").toggle('hidden');
		}
	);
	
	$("#editarContenidoButton").click(function(){
		$("#edicionLista").toggle('hidden');
		$("#botonEditarListaCancelar").toggle('hidden');
		}
	);
	$("#editarVideosListaButton").click(function(){
		$("#operacionesLista").toggle('hidden');
		}
	);
	$("#eliminarButton").click(function(){
		$("#textConfirm").toggle('hidden');
		$("#cancelarEliminarButton").toggle('hidden');
		$("#confirmButtonA").toggle('hidden');
		$(this).toggle('hidden');
		}
	);
	$("#cancelarEliminarButton").click(function(){
		$("#textConfirm").toggle('hidden');
		$("#confirmButtonA").toggle('hidden');
		$("#eliminarButton").toggle('hidden');
		$(this).toggle('hidden');
	});
	
	checkCompatible();

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


function toggleCheck(check){
	if( $(check).prop('checked')==true ){
		$(check).prop('checked', false)
	} else{
		$(check).prop('checked', true);
	}
}



