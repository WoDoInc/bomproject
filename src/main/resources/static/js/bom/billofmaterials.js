angular.module('billofmaterials', []).controller('billofmaterials', function($scope, $http) {
	$http.get('/api/billofmaterials/').success(function(data) {
		$scope.boms = data;
	});
});
