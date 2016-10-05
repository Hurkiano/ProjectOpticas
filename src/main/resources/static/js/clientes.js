function sendAction(val) {
	console.log("in sendAction function val: " + val);
	if (val == 1) {
		$('#modalClientes').modal('show');
	}
}
var app = angular.module('myApp', []);
var idCliente_g = null;
app.controller('FormSubmitController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.heightModalCliente = "300px";
			$scope.submit = function() {
				$scope.customers = null;
				$scope.contacts = null;
				$scope.noData = null;
				$scope.loaderDiv = true;
				console.log($scope.loaderDiv);
				var formData = {
					"fechaNacimiento" : $scope.inputFechaNacimiento,
					"aPaterno" : $scope.inputApaterno,
					"nombre" : $scope.inputNombreCliente
				};
				var response = $http.post('buscarClientes', formData);
				response.success(function(data, status, headers, config) {
					console.log(data[0]);
					$scope.heightModalCliente = "350px";
					$scope.loaderDiv = null;
					var len = response.length;
					var i = 0
					for (i = 0; i < len; i++) {
						data[i] = JSON.parse(data[i]);
					}
					if (data[0].mensaje) {
						$scope.customers = null;
						$scope.contacts = null;
						$scope.noData = "Sin resultados";
					} else {
						// $scope.test = data[0].contacto[0].tipo;
						$scope.heightModalCliente = "350px";
						$scope.noData = null;
						$scope.contacts = null;
						$scope.customers = data;
					}
				});
				response.error(function(data, status, headers, config) {
					$scope.loaderDiv = null;
					alert("Exception details: " + JSON.stringify({
						data : data
					}));
				});
			};
			$scope.verCliente = function(index) {
				$scope.heightModalCliente = "600px";
				console.log("index: " + index);
				var size = $scope.customers.length;
				var customer_array = $scope.customers;
				console.log("size of customer: " + size);
				for (var i = 0; i < size; i++) {
					if (i == index) {
						$scope.customer_selected = customer_array[i];
						$scope.contacts = customer_array[i].contacto;
						break;
					}
				}
			};
			$scope.modificarCliente = function(index) {
				console.log("modificar cliente index: " + index);
				document.location.href = "modificaCliente?cli=" + index;
			};
		} ]);
app.controller('anteojosInicialesController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.anteojo_selected = null;
			$scope.setAnteojosJson = function(anteojosString) {
				$scope.anteojosJson = anteojosString;
				console.log($scope.anteojosJson);
			};
			$scope.verDetalles = function(idCliente) {
				console.log("idCliente: " + idCliente);
				var size = JSON.parse($scope.anteojosJson).length;
				var anteojos_array = JSON.parse($scope.anteojosJson);
				console.log("size of anteojos: " + size);
				for (var i = 0; i < size; i++) {
					if (idCliente == anteojos_array[i].id_anteojo) {
						$scope.anteojo_selected = anteojos_array[i].recetas[0];
						break;
					}
				}
			};
		} ]);

app.controller('antecedentesController', [ '$scope', '$http',
		function($scope, $http) {

		} ]);

app.controller('agregarAnteojoSubmitController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.setIdAnteojosClient = function(id) {
				$scope.idCliente = id;
			}
			$scope.submit = function() {
				$scope.inputUsoAnteojos = 1;
				var idCliente_local;
				if (idCliente_g == null) {
					idCliente_local = $scope.idCliente;
				} else {
					idCliente_local = idCliente_g;
				}
				var formData = {
					"id_cliente" : idCliente_local,
					"uso" : $scope.inputUsoAnteojos,
					"origen" : $scope.inputOrigenAnteojos,
					"antiguedad" : $scope.inputAntiguedadAnteojos,
					"esfera_derecho" : $scope.esfera_derecho,
					"esfera_izquierdo" : $scope.esfera_izquierdo,
					"cilindro_derecho" : $scope.cilindro_derecho,
					"cilindro_izquierdo" : $scope.cilindro_izquierdo,
					"eje_derecho" : $scope.eje_derecho,
					"eje_izquierdo" : $scope.eje_izquierdo,
					"adicion_derecho" : $scope.adicion_derecho,
					"adicion_izquierdo" : $scope.adicion_izquierdo,
					"av_derecho" : $scope.av_derecho,
					"av_izquierdo" : $scope.av_izquierdo,
					"observaciones" : $scope.observaciones_ai
				};
				var response = $http.post('agregarAnteojos', formData);
				response.success(function(data, status, headers, config) {
					console.log(data.salida[0].mensaje);
					alert(data.salida[0].mensaje);
					$scope.inputUsoAnteojos = "";
					$scope.inputAntiguedadAnteojos = "";
					$scope.inputOrigenAnteojos = "";
					$scope.esfera_derecho = "";
					$scope.esfera_izquierdo = "";
					$scope.cilindro_derecho = "";
					$scope.cilindro_izquierdo = "";
					$scope.eje_derecho = "";
					$scope.eje_izquierdo = "";
					$scope.adicion_derecho = "";
					$scope.adicion_izquierdo = "";
					$scope.av_derecho = "";
					$scope.av_izquierdo = "";
				});
				response.error(function(data, status, headers, config) {
					console.log(data.salida[0].mensaje);
				});
			};

		} ]);
app.controller('agregarClienteController', [
		'$scope',
		'$http',
		function($scope, $http) {
			$scope.cliente_existe = null;
			$scope.setIdModCli = function(jsonString) {
				var objectClient = JSON.parse(jsonString)
				$scope.idCliente = objectClient.id;
				$scope.inputNombreCliente = objectClient.nombre;
				$scope.inputApaterno = objectClient.aPaterno;
				$scope.inputAMaterno = objectClient.aMaterno;
				$scope.inputFNacimiento = objectClient.fechaNacimiento;
				$scope.inputSexo = objectClient.sexo;
				$scope.inputCalleNum = objectClient.calle;
				$scope.inputCP = objectClient.cp;
				$scope.inputColonia = objectClient.colonia;
				$scope.inputMnpo = objectClient.mnpo;
				$scope.inputEdo = objectClient.estado;
				$scope.inputObs = objectClient.observaciones;
				$scope.contacts_mod = objectClient.contacto;
				$scope.arrayCol = [objectClient.colonia];
				console.log( "id cliente: " + objectClient.id);
			};
			$scope.submit = function() {
				var formData = {
					"nombre" : $scope.inputNombreCliente,
					"apellido_paterno" : $scope.inputApaterno,
					"apellido_materno" : $scope.inputAMaterno,
					"fecha_nacimiento" : $scope.inputFNacimiento,
					"sexo" : $scope.inputSexo,
					"calle" : $scope.inputCalleNum,
					"cp" : $scope.inputCP,
					"colonia" : $scope.inputColonia,
					"mnpo" : $scope.inputMnpo,
					"estado" : $scope.inputEdo,
					"obs" : $scope.inputObs,
					"contactoCorreo" : $scope.contactoCorreo,
					"contactoCellular" : $scope.contactoCellular,
					"contactoTelefono" : $scope.contactoTelefono,
					"contactoCorreoCompania" : $scope.contactoCorreoCompania
				};
				var response = $http.post('agregarCliente', formData);
				response.success(function(data, status, headers, config) {
					console.log(data.salida[0].mensaje);
					alert(data.salida[0].mensaje);
					idCliente_g = data.salida[0].id_cliente;
					$scope.idCliente = data.salida[0].id_cliente;
					$scope.cliente_existe = true;
				});
				response.error(function(data, status, headers, config) {
					alert("Exception details: " + JSON.stringify({
						data : data
					}));
				});
			};

			$scope.obtenerCP = function() {
				console.log("obtenerCP function cp: " + $scope.inputCP);
				$http.get(
						"https://api-codigos-postales.herokuapp.com/v2/codigo_postal/"
								+ $scope.inputCP).then(
						function(response) {
							var data = response.data;
							for (var i = 0; i < data.colonias.length; i++) {
								data.colonias[i] = data.colonias[i].replace(
										/á/gi, "a");
								data.colonias[i] = data.colonias[i].replace(
										/é/gi, "e");
								data.colonias[i] = data.colonias[i].replace(
										/í/gi, "i");
								data.colonias[i] = data.colonias[i].replace(
										/ó/gi, "o");
								data.colonias[i] = data.colonias[i].replace(
										/ú/gi, "u");
								data.colonias[i] = data.colonias[i].replace(
										/Á/gi, "A");
								data.colonias[i] = data.colonias[i].replace(
										/É/gi, "E");
								data.colonias[i] = data.colonias[i].replace(
										/Í/gi, "I");
								data.colonias[i] = data.colonias[i].replace(
										/Ó/gi, "O");
								data.colonias[i] = data.colonias[i].replace(
										/Ú/gi, "U");
								data.colonias[i] = data.colonias[i]
										.toUpperCase();
							}
							$scope.arrayCol = data.colonias;
							$scope.inputMnpo = data.municipio.toUpperCase();
							$scope.inputEdo = data.estado.toUpperCase();
							console.log(data.colonias);
						});
			};
			$scope.goAnteojos = function(index) {
				console.log("cntr anteojos iniciales index: " + index);
				document.location.href = "/anteojosIniciales?cli=" + index;
			};
			$scope.obtenerRecetas = function() {
				$scope.receta_selected = null;
				console.log("obtenerRecetas function");
				$http.get("/obtenerRecetas?cli=" + $scope.idCliente).then(
						function(response) {
							// $scope.greeting = response.data;
							var data = response.data;
							console.log(response.data[0]);
							$scope.recetas = data;
						});

			};
			$scope.verReceta = function(index) {
				console.log("verReceta function");
				var size = $scope.recetas.length;
				console.log("size of receta array" + size);
				for (var i = 0; i < size; i++) {
					if (i == index) {
						$scope.receta_selected = $scope.recetas[i];
						break;
					}
				}
			}
		} ]);
app.controller('agregarRxSubmitController', [ '$scope', '$http',
		function($scope, $http) {
			$scope.inputUsoRx = 1;
			$scope.setIdRXClient = function(id) {
				$scope.idCliente = id;
				console.log($scope.inputObs);
			};
			$scope.submit = function() {
				var idCliente_local;
				if (idCliente_g == null) {
					idCliente_local = $scope.idCliente;
				} else {
					idCliente_local = idCliente_g;
				}
				if ($scope.inputUsoRx == 1 || $scope.inputUsoRx == 0) {
					var formData = {
						"id_cliente" : idCliente_local,
						"uso" : $scope.inputUsoRx,
						"esfera_derecho" : $scope.esferaDerecho_1,
						"esfera_izquierdo" : $scope.esferaIzquierdo_1,
						"cilindro_derecho" : $scope.cilindroDerecho_1,
						"cilindro_izquierdo" : $scope.cilindroIzquierdo_1,
						"eje_derecho" : $scope.ejeDerecho_1,
						"eje_izquierdo" : $scope.ejeIzquierdo_1,
						"d_i_bonicular" : $scope.b_i_binocular_1,
						"observaciones" : $scope.obs_1
					};
				} else if ($scope.inputUsoRx == 2) {
					var formData = {
						"id_cliente" : idCliente_local,
						"uso" : $scope.inputUsoRx,
						"esfera_derecho" : $scope.esferaDerecho_2,
						"esfera_izquierdo" : $scope.esferaIzquierdo_2,
						"cilindro_derecho" : $scope.cilindroDerecho_2,
						"cilindro_izquierdo" : $scope.cilindroIzquierdo_2,
						"eje_derecho" : $scope.ejeDerecho_2,
						"eje_izquierdo" : $scope.ejeIzquierdo_2,
						"m_d_derecho" : $scope.mdDerecho_2,
						"m_d_izquierdo" : $scope.mdIzquierdo_2,
						"alt_seg" : $scope.alt_seg_2,
						"observaciones" : $scope.obs_2
					};
				} else if ($scope.inputUsoRx == 3) {
					var formData = {
						"id_cliente" : idCliente_local,
						"uso" : $scope.inputUsoRx,
						"esfera_derecho" : $scope.esferaDerecho_3,
						"esfera_izquierdo" : $scope.esferaIzquierdo_3,
						"cilindro_derecho" : $scope.cilindroDerecho_3,
						"cilindro_izquierdo" : $scope.cilindroIzquierdo_3,
						"eje_derecho" : $scope.ejeDerecho_3,
						"eje_izquierdo" : $scope.ejeIzquierdo_3,
						"d_i_bonicular" : $scope.b_i_binocular_3,
						"alt_seg" : $scope.alt_seg_3,
						"observaciones" : $scope.obs_3
					};
				}

				var response = $http.post('agregarRx', formData);
				response.success(function(data, status, headers, config) {
					console.log(data.salida[0].mensaje);
					alert(data.salida[0].mensaje);
					if (data.salida[0].estatus === 1) {
						if ($scope.inputUsoRx == 1) {
							$scope.idCliente = "";
							$scope.inputUsoRx = "";
							$scope.esferaDerecho_1 = "";
							$scope.esferaIzquierdo_1 = "";
							$scope.cilindroDerecho_1 = "";
							$scope.cilindroIzquierdo_1 = "";
							$scope.ejeDerecho_1 = "";
							$scope.ejeIzquierdo_1 = "";
							$scope.b_i_binocular_1 = "";
							$scope.obs_1
						} else if ($scope.inputUsoRx == 2) {
							$scope.idCliente = "";
							$scope.inputUsoRx = "";
							$scope.esferaDerecho_2 = "";
							$scope.esferaIzquierdo_2 = "";
							$scope.cilindroDerecho_2 = "";
							$scope.cilindroIzquierdo_2 = "";
							$scope.ejeDerecho_2 = "";
							$scope.ejeIzquierdo_2 = "";
							$scope.alt_seg_2 = "";
							$scope.obs_2 = "";
						} else if ($scope.inputUsoRx == 3) {
							$scope.idCliente = "";
							$scope.inputUsoRx = "";
							$scope.esferaDerecho_3 = "";
							$scope.esferaIzquierdo_3 = "";
							$scope.cilindroDerecho_3 = "";
							$scope.cilindroIzquierdo_3 = "";
							$scope.ejeDerecho_3 = "";
							$scope.ejeIzquierdo_3 = "";
							$scope.b_i_binocular_3 = "";
							$scope.alt_seg_3 = "";
							$scope.obs_3 = "";
						}
					}
					$('#modalAgrRx').modal('hide');
				});
				response.error(function(data, status, headers, config) {
					alert("Exception details: " + JSON.stringify({
						data : data
					}));
				});
			};
		} ]);