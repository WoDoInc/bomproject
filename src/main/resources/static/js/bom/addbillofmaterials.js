angular.module('addbillofmaterials', []).controller('addbillofmaterials', function($scope, $http) {
	$http.get('/api/part/').success(function(data) {
		$scope.items = data;
	});
	
	$scope.description = "A default description.";
	$scope.parts = [];
	
	$scope.addItem = function (total, item) {
		$scope.parts.push({
			count: total,
			part: item
		});
		$scope.$render();
	};
});
