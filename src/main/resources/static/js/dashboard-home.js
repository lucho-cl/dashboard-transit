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
			
			
//			$( "#customerTable tbody tr:odd" ).addClass("info");
//			$( "#customerTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});

//	al inicio, cargar reportes de RM
	getReportes();
	
	// do Filter on View
//	$("#inputFilter").on("keyup", function() {
//	    var inputValue = $(this).val().toLowerCase();
//	    $("#customerTable tr").filter(function() {
//	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
//	    });
//	});
	
//	$('#regionSelect').change(
//	    function() {
//	        $.getJSON("http://localhost:8181/appointment/agencies", {
//	            cityId : $(this).val(),
//	            ajax : 'true'
//	        }, function(data) {
//	            var html = '<option value="">--alege agentia--</option>';
//	            var len = data.length;
//	            for ( var i = 0; i < len; i++) {
//	                html += '<option value="' + data[i].nume + '">'
//	                        + data[i].nume + '</option>';
//	            }
//	            html += '</option>';
//	            $('#agency').html(html);
//	        });
//	    });

			$('#regionSelect').change(function() {
				getReportes();
			});
	
});

	function getReportes() {


	    if( !$("#regionSelect").val() ) {
//	    	ver si pasar el valor aqui o dejarlo en el controller
//	        alert("sin valor");
	    }else{
//	    	alert("ahora si pos");
	    }
	    
	    var search = {}
	    search["regionSelect"] = $("#regionSelect").val();

	    $("#regionSelect").prop("disabled", true);

	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/api/dashboard/cuadros",
	        data: JSON.stringify(search),
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
				var reportes = '';
				$.each(data.result, function(i, cuadro){
//					armar cada cuadro
					reportes += '<div class="col-sm-6">';
					reportes += '<p>' + cuadro.valor + '</p>';
					reportes += '<p>' + cuadro.texto + '</p>';
//					reportes += '<p> <a th:href="@{detail(idCuadro=' + cuadro.id + ',idRegion=' + cuadro.region + ')}">Ver ' + cuadro.nombre + '</a></p>';
					reportes += '<p> <a href="/detail?idCuadro=' + cuadro.id + '&idRegion=' + cuadro.region + '">Ver ' + cuadro.nombre + '</a></p>';
					reportes += '</div>';

//		  			<div th:each="cuadro, rowStat : ${cuadros}" class="col-sm-6">
//						<div class="panel panel-default">
//							<div class="panel-body">
//								<p th:text="${cuadro.valor}">valor</p>
//								<p th:text="${cuadro.texto}">texto</p>
//								<p> <a th:href="@{detail(idCuadro=${cuadro.id},idRegion=${regionSeleccionada})}">Ver <span th:text="${cuadro.nombre}">nombre</a></p>
//							</div>
//						</div>
//		  			</div>
		        });
				$('#reportes').html(reportes);

//	            var json = "<h4>Ajax Response</h4><pre>"
//	                + JSON.stringify(data, null, 4) + "</pre>";
//	            $('#feedback').html(json);

	            console.log("SUCCESS : ", data);
	            $("#regionSelect").prop("disabled", false);

	        },
	        error: function (e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);

	        }
	    });
}