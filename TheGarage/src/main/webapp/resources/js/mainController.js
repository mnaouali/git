angular.module("mainApp",
		['ui.router', 'mainApp.config', 'mainApp.thegarage'])
		.config(
				[ '$stateProvider', '$urlRouterProvider',
						function($stateProvider, $urlRouterProvider) {
							$urlRouterProvider.otherwise('/home');

							$urlRouterProvider.when("", "/home");
						} ]).run(function run() {
		})
		.controller(
				'mainController',
				function AppController($scope, $location, $state) {
					$scope.$on('$stateChangeSuccess', function(event, toState,
							toParams, fromState, fromParams) {
						if (angular.isDefined(toState.data.pageTitle)) {
							$scope.pageTitle = toState.data.pageTitle
									+ '| The parking';
						}
					});
				});