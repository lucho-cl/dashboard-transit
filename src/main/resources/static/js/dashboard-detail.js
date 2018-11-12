$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "api/dashboard/regiones",
		success: function(result){
			var regionSelect = '';
			$.each(result, function(i, region){
				regionSelect += '<option value="' + region.id + '">'+ region.nombre + '</option>';
				
	        });
			$('#regionSelect').html(regionSelect);
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});


			$('#regionSelect').change(function() {
				alert("ir al detalle del grafico 1 y region "+$("#regionSelect").val());
//				ir al detalle de la region seleccionada con el reporte 1
//				getReportes();
			});
	
});
