

$(document).ready(function () {
		$.ajax({			
			type: "GET",
			url: "/SeeDsWeb/relaciones",
			data: { 
				'id':$(".mainWindow").attr("data-idContenido"),
				'tipo':$(".mainWindow").attr("data-tipo"),
				'action':"sumar_visita"},				
				contentType:"application/x-www-form-urlencoded; charset=ISO-8859-1",				
				dataType:"json",
				
				success: function( valor ){					
						$("#detalleVisitas").html(visitas);					
				}	
		});
});

