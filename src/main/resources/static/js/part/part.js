angular.module('part', []).controller('part', function($scope, $http) {
	$http.get('/api/part/').success(function(data) {
		$scope.parts = data;
	});
});
