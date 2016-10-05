$(document).on("click", ".detallesTD", function(e) {
	$("#dialog_clientes_selected").show();
	$("#dialog_clientes_selected").dialog({
		height : 500,
		width : 550,
		autoOpen : false,
		show : {},
		hide : {}
	}).dialog("open");

});

$(document).on("click", "#dialog_agregar_contacto_link", function(e) {
	$("#dialog_agrega_contacto").show();
	$("#dialog_agrega_contacto").dialog({
		height : 500,
		width : 550,
		autoOpen : false,
		show : {},
		hide : {}
	}).dialog("open");

});

$(document).on("click", "#dialog_agregar_anteojos_link", function(e) {
	$("#dialog_agrega_anteojos").show();
	$("#dialog_agrega_anteojos").dialog({
		height : 450,
		width : 850,
		autoOpen : false,
		show : {},
		hide : {}
	}).dialog("open");

});

$(document).ready(
		function() {
			$("#dialog_clientes").dialog({
				height : 700,
				width : 900,
				autoOpen : false,
				show : {},
				hide : {}
			});
			$("select[name=inputTipoTransaccion]").change(
					function() {
						console.log("value select"
								+ $('select[name=inputTipoTransaccion]').val())
						if ($('select[name=inputTipoTransaccion]').val() == 1) {
							$("#dialog_clientes").dialog("open");
						}
					});
			$("#tabs_seccion_cliente").tabs({
				height : 3000
			});
			
			

		});

