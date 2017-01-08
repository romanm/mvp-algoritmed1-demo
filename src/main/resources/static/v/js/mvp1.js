angular.module('medicApp', [])
.controller('MedicCtrl', function($scope, $http) {
	console.log('---medicApp-----MedicCtrl--------');
	$scope.patient = {'patient_pib':'','patient_address':''};

	/*
	 * Запис пацієнта в БД
	 * */
	$scope.saveNewPatient = function (patient){
		console.log(patient);
		$http.post('/v/saveNewPatient', patient).then(
			function(response) {
				console.log(response);
			}
			, function(response) {
				console.log(response);
			}
		);
	}

	$scope.historyTreatmentAnalysis = {};
	$scope.historyTreatmentAnalysis.date = new Date();
	console.log($scope.historyTreatmentAnalysis.date);
});

