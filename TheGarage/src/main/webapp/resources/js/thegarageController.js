function thegarageController(thegarageService, $q, $scope, $state) {
	
	
	$scope.carPlaceToDelete = {
			selected : ''
	};
	
	$scope.carLevelNumberToDelete = {
			selected : ''
	};
	
	$scope.motorbikePlaceToDelete = {
			selected : ''
	};
	
	$scope.motorbikeLevelNumberToDelete = {
			selected : ''
	};
	
	$scope.levelNumberToDelete = {
			selected : ''
	};
	
	$scope.carLevelNumber = {
			selected : ''
	};
	
	$scope.carPlace = {
			selected : ''
	};
	$scope.motorbikeLevelNumber = {
			selected : ''
	};
	
	$scope.motorbikePlace = {
			selected : ''
	};
	
	/**************************************Load the number of empty places*********************************************************/
	$scope.getAvailablePlaces = function (){
		thegarageService.httpGetAvailableplaces().then(
				function(response) {
					$scope.CarSpaces = response.data.CarSpaces;
					$scope.MotorbikeSpaces = response.data.MotorbikeSpaces;
				}, function(errResponse) {
					console.error('Error while getting the number of empty places');
					return $q.reject(errResponse);
				})
	}
	$scope.getAvailablePlaces();
	
	/**************************************Load the list of all levels*********************************************************/
	$scope.getlevelsList = function (){
		thegarageService.httpGetLevelsList().then(
				function(response) {
					$scope.levelsDetails = response.data
				}, function(errResponse) {
					console.error('Error while getting the list of levels');
					return $q.reject(errResponse);
				})
	}
	$scope.getlevelsList();
	
	/**************************************List empty vehicle places*********************************************************/
	$scope.getListEmptyVehiclePlaces = function(vehicleSpace) {
		thegarageService
				.httpGetListEmptyPlacesByType(vehicleSpace)
				.then(
						function(response) {
							$scope.levels = response.data;
							$scope.placeType = response.data[0];
						},
						function(errResponse) {
							console
									.error('Error while getting the list of empty places');
							return $q.reject(errResponse);
						})
	}
	
	/**************************************delete empty vehicle places*********************************************************/
	$scope.deleteEmptyVehiclePlaces = function(levelNumber, placeNumber, placeType) {
		thegarageService
			.httpDeleteEmptyPlacesByType(levelNumber, placeNumber, placeType)
			.then(
						function(response) {
							$state.go("home");
						},
						function(errResponse) {
							console.error('Error while deleting the vehicle place');
							return $q.reject(errResponse);
						})
	}
	
	/**************************************Retrieve the place of the vehicle ***************************************************/
	$scope.getVehiclePlace = function (licenceplate){
		thegarageService.httpGetVehicleplace(licenceplate).then(
				function(response) {
					$scope.levelNumber = response.data.levelNumber;
					$scope.placeNumber = response.data.placeNumber;
					$state.go("locate_vehicle");
				}, function(errResponse) {
					console.error('Error while getting the place of the vehicle');
					return $q.reject(errResponse);
				})
	}
	/**************************************Exit an existing vehicle *********************************************************/
	$scope.exitVehicle = function (licenceplate){
		thegarageService.httpGetExitVehicle(licenceplate).then(
				function(response) {
					$state.go("home");
				}, function(errResponse) {
					console.error('Error while exiting the vehicle');
					return $q.reject(errResponse);
				})
	}
	/**************************************Enter a new vehicle *********************************************************/
	$scope.enterNewVehicle = function (licenceplate, placeType, placeNumber, levelNumber){
		thegarageService.httpEnterVehicle(licenceplate, placeType, placeNumber, levelNumber).then(
				function(response) {
					$state.go("home");
				}, function(errResponse) {
					console.error('Error while entring a new car');
					return $q.reject(errResponse);
				})
	}
	/**************************************Update or create new level *********************************************************/
	$scope.updateOrCreateLevel = function (levelNumber, nbreCarSpace, nbreMotorbikeSpace){
		thegarageService.httpUpdateOrCreateLevel(levelNumber, nbreCarSpace, nbreMotorbikeSpace).then(
				function(response) {
					$state.go("home");
				}, function(errResponse) {
					console.error('Error while Updating or creating a new level');
					return $q.reject(errResponse);
				})
	}
	/**************************************Delete an existing level *********************************************************/
	$scope.deleteLevel = function (levelNumber){
		console.log ("levelNumber",levelNumber)
		thegarageService.httpDeleteLevel(levelNumber).then(
				function(response) {
					$state.go("home");
				}, function(errResponse) {
					console.error('Error while deleting the requested level');
					return $q.reject(errResponse);
				})
	}

}