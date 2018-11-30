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
			$("#regionSelect").val($("#regionSeleccionada").val())
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});


			$('#regionSelect').change(function() {
//				alert("ir al detalle del grafico 1 y region "+$("#regionSelect").val());
//				ir al detalle de la region seleccionada con el reporte 1
				change_detail($("#regionSelect").val(),1);
//				getReportes();
			});
	function change_detail(idRegion, idReporte){
		window.location.href = 'detail?idCuadro='+idReporte+'&idRegion='+idRegion;
		
//		var url = 'detail?idCuadro='+idReporte+'&idRegion='+idRegion
//		$.get(url,function(data, status){
//			console.log('${data}');
//		});

//		$.ajax({
//			type : "GET",
//			url : "detail",
//			data : {
//				'idCuadro' : idReporte
//				,'idRegion': idRegion
//			},
//			success: function(result){
//				alert("EXITO!");
//				console.log(result);
//			},
//			error : function(e) {
//				console.log("ERROR: ", e);
//			}
//		});
	}
});

function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}
