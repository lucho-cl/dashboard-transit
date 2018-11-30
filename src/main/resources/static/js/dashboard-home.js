$(document).ready(
		function() {

			// DO GET
			$.ajax({
				type : "GET",
				url : "api/dashboard/regiones",
				success : function(result) {
					var regionSelect = '';
					$.each(result, function(i, region) {
						regionSelect += '<option value="' + region.id + '">'
								+ region.nombre + '</option>';

					});
					$('#regionSelect').html(regionSelect);

					// $( "#customerTable tbody tr:odd" ).addClass("info");
					// $( "#customerTable tbody tr:even" ).addClass("success");
				},
				error : function(e) {
					alert("ERROR: ", e);
					console.log("ERROR: ", e);
				}
			});

			// primera carga de reportes
			getReportes();

			$('#regionSelect').change(function() {
				getReportes();
			});

		});

function getReportes() {
	// if( !$("#regionSelect").val() ) {
	// ver si pasar el valor aqui o dejarlo en el controller
	// alert("sin valor");
	// }else{
	// alert("ahora si pos");
	// }
	var search = {}
	search["regionSelect"] = $("#regionSelect").val();

	$("#regionSelect").prop("disabled", true);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/api/dashboard/cuadros",
		data : JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			var reportes = '';
			$.each(data.result, function(i, cuadro) {
				// armar cada cuadro/reporte
				reportes += '<div class="col-sm-6">';
				reportes += '<div class="caja';
//				ver si debo deshabilitar
				if(!cuadro.habilitado){
					reportes += ' no-graph';
				}
				reportes += '">';
				if(cuadro.habilitado){
					reportes += '<a href="/detail?idCuadro=' + cuadro.id
					+ '&idRegion=' + cuadro.region + '">';
				}
				reportes += '<div class="row" style="height: 60%;">';
				reportes += '<div class="col cuadro-1">';
				reportes += '<section>' + cuadro.valor + '</section>';
				reportes += '</div>';
				reportes += '<div class="col cuadro-2">';
				reportes += '<section>' + cuadro.texto + '</section>';
				reportes += '</div>';
				reportes += '</div>';
				reportes += '<div class="row" style="height: 40%;">';
				reportes += '<div class="col-2 cuadro-3">';
				reportes += '<section><span  class="'+cuadro.icono+'"></span></section>';
				reportes += '</div>';
				reportes += '<div class="col-10 cuadro-4">';
				reportes += '<section>VER ';
				reportes += cuadro.nombre;
				reportes += '<span class="fas fa-chevron-right"></span>';
				reportes += '</section>';
				reportes += '</div>';
				reportes += '</div>';
				if(cuadro.habilitado){
					reportes += '</a>';
				}
				reportes += '</div>';
				reportes += '</div>';

			});
			$('#reportes').html(reportes);

			console.log("SUCCESS : ", data);
			$("#regionSelect").prop("disabled", false);

		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);

		}
	});
}