angular.module('main', [ 'ngRoute', 'billofmaterials', 'part', 'navigation' ])
	.config(
		function($routeProvider, $httpProvider, $locationProvider) {

			$locationProvider.html5Mode(true);

			$routeProvider.when('/', {
				templateUrl : 'js/bom/billofmaterials.html',
				controller : 'billofmaterials'})
			.when('/billofmaterials', {
				templateUrl : 'js/bom/billofmaterials.html',
				controller : 'billofmaterials'})
			.when('/part', {
				templateUrl : 'js/part/part.html',
				controller : 'part'})
			.otherwise('/');

			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	})
