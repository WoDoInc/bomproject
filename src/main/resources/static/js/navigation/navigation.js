angular.module('navigation', ['ngRoute']).controller(
		'navigation',

		function($scope, $route) {
			$scope.tab = function(route) {
				return $route.current && route === $route.current.controller;
			};
		});
