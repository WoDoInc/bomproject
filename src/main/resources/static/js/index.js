angular.module('main', ['ngRoute'])
.config(function($routeProvider) {
		$routeProvider.when('/home', {
			templateUrl: 'js/part/part.html',
			controller: 'mainController'
		})
		.when('/part/:id', {
			templateUrl: 'js/part/partdetails.html',
			controller: 'mainController'
		})
		.when('/bom', {
			templateUrl: 'js/bom/bom.html',
			controller: 'mainController'
		})
		.when('/bom/:id', {
			templateUrl: 'js/bom/bomdetails.html',
			controller: 'mainController'
		})
		.when('/newbom', {
			templateUrl: 'js/bom/newbom.html',
			controller: 'newBOMController'
		})
		.otherwise({
			redirectTo: '/home'
		});
})
.factory('partService', function($http) {
	var factory = {};
	factory.getPartsList = function() {
		return $http.get(URLS.getPartsList);
	};
	factory.getPart = function(id) {
		return $http.get(URLS.getPartsList + '/' + id);
	};

	return factory;
})
.factory('bomService', function($http) {
	var factory = {};
	factory.getBOMList = function() {
		return $http.get(URLS.getBOMList);
	};
	factory.getBOM = function(id) {
		return $http.get(URLS.getBOMList + '/' + id);
	};

	return factory;
})
.factory('newBOMService', function($http) {
	var factory = {};
	factory.description = 'a default description';
	factory.parts = [];
	
	return factory;
})
.controller('mainController', function($scope, $routeParams, partService, bomService) {
	partService.getPartsList().success(function(data) {
		$scope.parts = data;
	});
	bomService.getBOMList().success(function(data) {
		$scope.boms = data;
	});

	if($routeParams.id != null) {
		partService.getPart($routeParams.id).success(function(data) {
			$scope.partdetails = data;
		});
	}

	if($routeParams.id != null) {
		bomService.getBOM($routeParams.id).success(function(data) {
			$scope.bomdetails = data;
		});
	}
})
.controller('newBOMController', function($scope, $http, $routeParams, partService, newBOMService) {
	$scope.bom = newBOMService;
	$scope.bom.description = "A default description.";
	
	$scope.addItem = function(count, part) {
		var part = {
			count: count,
			part: part
		};
		$scope.bom.parts.push(part);
	}
	
	partService.getPartsList().success(function(data) {
		$scope.parts = data;
	});
	
	if($routeParams.id != null) {
		partService.getPart($routeParams.id).success(function(data) {
			$scope.partdetails = data;
		});
	}
	
	$scope.submit = function() {
		$http.post(URLS.getBOMList, $scope.bom);
	}
});