'use strict';
var app = angular.module('MealManagementApp', ['ngMaterial', 'ngRoute', 'ngMessages']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/meals', {
            'templateUrl': 'admin/meals.html',
            'controller': 'adminMealsController'
        })
        .when('/meals/add', {
            'templateUrl': 'admin/meal_add.html',
            'controller': 'adminAddMealController'
        })
        .when('/meals/:mealId', {
            'templateUrl': 'admin/meal_edit.html',
            'controller': 'adminEditMealController'
        });

    // the only invalid route -> because no one's perfect :)
    $routeProvider
        .otherwise({
            'redirectTo': '/meals'
        });
}]);

app.config(function ($mdThemingProvider) {
    $mdThemingProvider
        .theme('default')
        .primaryPalette('indigo')
        .accentPalette('orange');
});

app.config(['$mdIconProvider', function ($mdIconProvider) {
    $mdIconProvider
        .iconSet('action', 'svg/action-icons.svg')
        .iconSet('device', 'svg/device-icons.svg')
        .iconSet('navigation', 'svg/navigation-icons.svg')
        .iconSet('content', 'svg/content-icons.svg')
        .iconSet('alert', 'svg/alert-icons.svg')
        .iconSet('image', 'svg/image-icons.svg')
        .defaultIconSet('svg/core-icons.svg');
}]);

app.controller('defaultController', ['$scope', '$mdSidenav', function ($scope, $mdSidenav, $mdDialog) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };
    $scope.navigateTo = function (where) {
        window.location = window.location.href.split('#')[0] + '#' + where;
    };
    $scope.gotoHome = function () {
        window.location = window.location.origin;
    };
}]);

// MEALS LISTING
app.controller('adminMealsController', ['$scope', '$http', function ($scope, $http) {
    $http
        .get('/meals')
        .success(function (response) {
            $scope.meals = response;
        });

    $scope.searchBoxHidden = true;
    $scope.searchMeals = function () {
        $scope.searchBoxHidden = !$scope.searchBoxHidden;
    };

    // action performed when a meal on the list is clicked
    $scope.openMeal = function (meal) {
        window.location = window.location.href.split('#')[0] + '#/meals/' + meal.mealId;
    };

    // FAB action
    $scope.addNewMeal = function () {
        window.location = window.location.href.split('#')[0] + '#/meals/add';
    };
}]);

// MEAL DETAILS
app.controller('adminEditMealController', ['$scope', '$http', '$httpParamSerializerJQLike', '$routeParams', '$mdDialog', '$mdToast', function ($scope, $http, $httpParamSerializerJQLike, $routeParams, $mdDialog, $mdToast) {
    var mealId = $routeParams.mealId;

    $http({
        url : '/meals', 
        method : "GET",
        params : {id : mealId}
    }).success(function (response) {
        var meal = response;
        $scope.mealName = meal.name;
        $scope.defaultQuantity = meal.defaultQuantity;
        $scope.unit = meal.unit;
        $scope.calories = meal.calories;
    });

    $scope.ui = {
        'toolbarLabel': 'Edit Meal'
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/meals';
    };

    // helper stuffs
    $scope.toastPosition = {
        bottom: true,
        top: false,
        left: false,
        right: true
    };
    $scope.getToastPosition = function () {
        return Object.keys($scope.toastPosition)
            .filter(function (pos) {
                return $scope.toastPosition[pos];
            })
            .join(' ');
    };

    $scope.deleteMeal = function (ev) {
        var confirm = $mdDialog.confirm()
            .parent(angular.element(document.body))
            .title('Are you sure you want to delete this meal?')
            .content('This action cannot be undone.')
            .ariaLabel('Lucky day')
            .ok('Delete')
            .cancel('Cancel')
            .targetEvent(ev);

        $mdDialog.show(confirm).then(function () {
            var mealData = { 'mealId' : mealId };

            $http
                ({
                    method: 'DELETE',
                    url: '/meals',
                    params: mealData
                })
                .success(function (data, status, headers, config) {
                	if(data.errorList == []) {
	                    $mdToast.show(
	                        $mdToast.simple()
	                        .content('Meal Deleted!')
	                        .hideDelay(1000)
	                        .position($scope.getToastPosition())
	                    );
	                    window.location = window.location.href.split('#')[0] + '#/meals';
                	} else {
                		var alert = $mdDialog.alert()
                        .parent(angular.element(document.body))
                        .title('Invalid data error')
                        .content(data.errorList)
                        .ariaLabel('Deleting a meal server error')
                        .ok('Okay')
                        .targetEvent(ev);
                    
	                    $mdDialog.show(alert).then(function () {
	                        // do nothing
	                    });
                	}
                })
                .error(function (data, status, headers, config) {
                    var alert = $mdDialog.alert()
                        .parent(angular.element(document.body))
                        .title('Server connection error')
                        .content('Something went wrong when processing your request, please try again.')
                        .ariaLabel('Updating a meal server error')
                        .ok('Okay')
                        .targetEvent(ev);
                    
                    $mdDialog.show(alert).then(function () {
                        // do nothing
                    });
                });

        }, function () {
            // do nothing
        });
    };

    $scope.updateMeal = function (ev) {
        if ($scope.editMealForm.$valid) {
            var mealData =
                {
                    'calories' : $scope.calories,
                    'defaultQuantity' : $scope.defaultQuantity,
                    'unit' : $scope.unit,
                    'name' : $scope.mealName,
                    'mealId' : mealId
                };

            $http
                ({
                    method: 'PUT',
                    url: '/meals',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    data: $httpParamSerializerJQLike({'data' : JSON.stringify(mealData)})
                })
                .success(function (data, status, headers, config) {
                	if(data.errorList == []) {
	                    $mdToast.show(
	                        $mdToast.simple()
	                        .content('Meal Updated!')
	                        .hideDelay(1000)
	                        .position($scope.getToastPosition())
	                    );
	                    window.location = window.location.href.split('#')[0] + '#/meals';
	                } else {
	            		var alert = $mdDialog.alert()
	                    .parent(angular.element(document.body))
	                    .title('Invalid data error')
	                    .content(data.errorList)
	                    .ariaLabel('Updating a meal server error')
	                    .ok('Okay')
	                    .targetEvent(ev);
	                
	                    $mdDialog.show(alert).then(function () {
	                        // do nothing
	                    });
	            	}
                })
                .error(function (data, status, headers, config) {
                    var alert = $mdDialog.alert()
                        .parent(angular.element(document.body))
                        .title('Server connection error')
                        .content('Something went wrong when processing your request, please try again.')
                        .ariaLabel('Updating a meal server error')
                        .ok('Okay')
                        .targetEvent(ev);
                    
                    $mdDialog.show(alert).then(function () {
                        // do nothing
                    });
                });

        } else {            
            var alert = $mdDialog.alert()
                .parent(angular.element(document.body))
                .title('Some data inputted are invalid/missing.')
                .content('You have to fix the errors before updating the meal.')
                .ariaLabel('Updating a meal')
                .ok('Okay, I\'ll fix it.')
                .targetEvent(ev);
            
            $mdDialog.show(alert).then(function () {
                // do nothing
            });
        }
    };
}]);

app.controller('adminAddMealController', ['$scope', '$http', '$httpParamSerializerJQLike', '$mdToast', '$mdDialog', function ($scope, $http, $httpParamSerializerJQLike, $mdToast, $mdDialog) {
    $scope.ui = {
        'toolbarLabel': 'Add Meal'
    };

    // helper stuffs
    $scope.toastPosition = {
        bottom: true,
        top: false,
        left: false,
        right: true
    };
    $scope.getToastPosition = function () {
        return Object.keys($scope.toastPosition)
            .filter(function (pos) {
                return $scope.toastPosition[pos];
            })
            .join(' ');
    };

    // CHECK ACTION -- when the admin submits the new meal
    $scope.addMeal = function (ev) {
        if ($scope.addMealForm.$valid) {
            var mealData =
                {
                    'calories' : $scope.calories,
                    'defaultQuantity' : $scope.defaultQuantity,
                    'unit' : $scope.unit,
                    'name' : $scope.mealName
                };

            $http
                ({
                    method: 'POST',
                    url: '/meals',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    data: $httpParamSerializerJQLike({'data' : JSON.stringify(mealData)})
                })
                .success(function (data, status, headers, config) {
                	if(data.errorList == []) {
	                    $mdToast.show(
	                        $mdToast.simple()
	                        .content('Meal Added!')
	                        .hideDelay(1000)
	                        .position($scope.getToastPosition())
	                    );
	                    window.location = window.location.href.split('#')[0] + '#/meals';
                	} else {
                		var alert = $mdDialog.alert()
                        .parent(angular.element(document.body))
                        .title('Invalid data error')
                        .content(data.errorList)
                        .ariaLabel('Adding a meal server error')
                        .ok('Okay')
                        .targetEvent(ev);
                    
	                    $mdDialog.show(alert).then(function () {
	                        // do nothing
	                    });
                	}
                })
                .error(function (data, status, headers, config) {
                    var alert = $mdDialog.alert()
                        .parent(angular.element(document.body))
                        .title('Server connection error')
                        .content('Something went wrong when processing your request, please try again.')
                        .ariaLabel('Adding a meal server error')
                        .ok('Okay')
                        .targetEvent(ev);
                    
                    $mdDialog.show(alert).then(function () {
                        // do nothing
                    });
                });
            
        } else {
            var alert = $mdDialog.alert()
                .parent(angular.element(document.body))
                .title('Some data inputted are invalid/missing.')
                .content('You have to fix the errors before adding the meal.')
                .ariaLabel('Adding a meal')
                .ok('Okay, I\'ll fix it.')
                .targetEvent(ev);
            
            $mdDialog.show(alert).then(function () {
                // do nothing
            });
        }
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/meals';
    };
}]);