angular.module('insuranceApp', [])
.controller('InsuranceCtrl', function($scope, $http) {
	console.log('---insuranceApp-----InsuranceCtrl--------');
	$http.get('/r/insurance/patients').then(
			function(response) {
				console.log(response);
				$scope.insurancePatients = response.data.insurancePatients;
			}
			, function(response) {
				console.log(response);
			}
	);
});

angular.module('medicApp', [])
.controller('MedicCtrl', function($scope, $http) {
	console.log('---medicApp-----MedicCtrl--------');
	$scope.patient = {'patient_pib':'','patient_address':''};

	$http.get('/r/medical/patients').then(
			function(response) {
				console.log(response);
				$scope.medicPatients = response.data.medicPatients;
			}
			, function(response) {
				console.log(response);
			}
	);
	/*
	 * Запис пацієнта в БД
	 * */
	$scope.saveNewPatient = function (patient){
		console.log(patient);
		$http.post('/v/saveNewPatient', patient).then(
				function(response) {
					console.log(response);
					var patientById =  response.data.patientById;
					console.log(patientById);
					$scope.medicPatients.unshift(patientById);
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

