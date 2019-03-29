$(document).ready(function() {

    $('[data-toggle="tooltip"]').tooltip({'placement': 'top'});
	
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
//				ir al detalle de la region seleccionada con el reporte 1
				change_detail($("#regionSelect").val(),1);
			});
	function change_detail(idRegion, idReporte){
		window.location.href = 'detail?idCuadro='+idReporte+'&idRegion='+idRegion;
		
	}
	
	$('#btn-left').click(function(e) {  
//		alert("#btn-left");
//        alert($("#regionSeleccionada").val());
//        alert($("#reporteSeleccionado").val());
//        alert($("#prevReporte").val());
		if($("#prevReporte").val()=="home"){
			window.location.href = '/';
		}else{
//			alert($("#prevReporte").val());
			change_detail($("#regionSelect").val(),$("#prevReporte").val());
		}
    });
	$('#btn-right').click(function(e) {  
		if($("#nextReporte").val()=="home"){
			window.location.href = '/';
		}else{
			change_detail($("#regionSelect").val(),$("#nextReporte").val());
		}
	});
});

$(function() {
	if($("#prevReporte").val()=="home"){
		$( "#btn-left p" ).text( "VOLVER AL HOME" );
	}
	if($("#nextReporte").val()=="home"){
		$( "#btn-right p" ).text( "VOLVER AL HOME" );
	}
  });

function topFunction() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}
