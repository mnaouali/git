angular.module('mainApp.config', [ 'ui.router' ]).config(
		[ '$stateProvider', '$urlRouterProvider',
				function($stateProvider, $urlRouterProvider) {
					$stateProvider.state("home", {
						url : "/home",
						views : {
							"mainbody" : {
								templateUrl : "resources/tmpl/home.html",
							}
						},
						data : {
							pageTitle : "Home",
						}
					}).state("info_lots", {
						url : "/info",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/Available_places.html"
							},
						},
						data : {
							pageTitle : "Info",
						}
					}).state("locate_vehicle", {
						url : "/location",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/Vehicle_Position.html"
							},
						},
						data : {
							pageTitle : "Vehicle location",
						}
					}).state("enter_car", {
						url : "/enter_new_car",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/enter_car.html"
							},
						},
						data : {
							pageTitle : "Enter Car",
						}
					}).state("enter_motorbike", {
						url : "/enter_new_motorbike",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/enter_motorbike.html"
							},
						},
						data : {
							pageTitle : "Enter Motorbike",
						}
					}).state("garage_param", {
						url : "/garage_param",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/garage_param.html"
							},
						},
						data : {
							pageTitle : "Configuration",
						}
					}).state("delete_car_place", {
						url : "/delete_car_place",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/delete_car_place.html"
							},
						},
						data : {
							pageTitle : "Delete empty places",
						}
					}).state("delete_motorbike_place", {
						url : "/delete_motorbike_place",
						views : {

							"mainbody" : {
								templateUrl : "resources/tmpl/delete_motorbike_place.html"
							},
						},
						data : {
							pageTitle : "Delete empty places",
						}
					});
				} ]);