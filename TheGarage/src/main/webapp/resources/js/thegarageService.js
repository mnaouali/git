function thegarageService($http) {
	var garageServices = {};
	
	/**************************************Retrieve the number of empty places*********************************************************/	
	garageServices.httpGetAvailableplaces = function() {
		return $http.get("/TheGarage/rest/place/numberofemptyplaces", {
		});
	};
	
	/**************************************Retrieve the list of empty places*********************************************************/	
	garageServices.httpGetListEmptyPlacesByType = function(placeType) {
		return $http.get("/TheGarage/rest/place/listofemptyplacesbytype", {
			params : {
				'placeType' : placeType
			}
		});
	};
	
	/**************************************Delete an empty place*********************************************************/	
	garageServices.httpDeleteEmptyPlacesByType = function(levelNumber, placeNumber, placeType) {
		return $http.delete("/TheGarage/rest/place/deleteplace", {
			params : {
				'levelNumber' : levelNumber,
				'placeNumber' : placeNumber,
				'placeType' : placeType
			}
		});
	};
	/**************************************Retrieve the vehicle's position*********************************************************/	
	garageServices.httpGetVehicleplace = function(licenceplate) {
		return $http.get("/TheGarage/rest/vehicle/vehicleplace", {
			params : {
				'licenceplate' : licenceplate
			}
		});
	};
	
	/**************************************Exit an existing vehicle*********************************************************/	
	garageServices.httpGetExitVehicle = function(licenceplate) {
		return $http.delete("/TheGarage/rest/vehicle/exitvehicle", {
			params : {
				'licenceplate' : licenceplate
			}
		});
	};
	
	/**************************************Enter a new vehicle*********************************************************/	
	garageServices.httpEnterVehicle = function(levelNumber, placeNumber, licenceplate, placeType) {
		return $http.post("/TheGarage/rest/vehicle/entervehicle",{}, {
			params : {
				'licenceplate' : licenceplate,
				'placeType' : placeType, 
				'placeNumber' : placeNumber, 
				'levelNumber' : levelNumber
			},
		});
	};
	
	/**************************************Update or create new level*********************************************************/	
	garageServices.httpUpdateOrCreateLevel = function(levelNumber, nbreCarSpace, nbreMotorbikeSpace) {
		return $http.put("/TheGarage/rest/level/add",{}, {
			params : {
				'levelNumber' : levelNumber,
				'nbreCarSpace' : nbreCarSpace, 
				'nbreMotorbikeSpace' : nbreMotorbikeSpace
			},
		});
	};
	
	/**************************************List all levels*********************************************************/	
	garageServices.httpGetLevelsList = function() {
		return $http.get("/TheGarage/rest/level/list",{}, {
		});
	};
	
	/**************************************Delete a level*********************************************************/	
	garageServices.httpDeleteLevel= function(levelNumber) {
		return $http.delete("/TheGarage/rest/level/delete", {
			params : {
				'levelNumber' : levelNumber
			}
		});
	};
	return garageServices;
}