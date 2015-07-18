'use strict';
var app = angular.module('StarterApp', ['ngMaterial', 'ngRoute', 'ngMessages']);

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
}]);

// MEALS LISTING
app.controller('adminMealsController', ['$scope', '$http', function ($scope, $http) {
    $http
        .get('test/meals.json')
        .success(function (response) {
            $scope.meals = response.meals;
        });

    $scope.searchBoxHidden = true;
    $scope.searchMeals = function () {
        $scope.searchBoxHidden = !$scope.searchBoxHidden;
    };

    // action performed when a meal on the list is clicked
    $scope.openMeal = function (meal) {
        window.location = window.location.href.split('#')[0] + '#/meals/' + meal.id;
    };

    // FAB action
    $scope.addNewMeal = function () {
        window.location = window.location.href.split('#')[0] + '#/meals/add';
    };
}]);

// MEAL DETAILS
app.controller('adminEditMealController', ['$scope', '$routeParams', '$mdDialog', '$mdToast', function ($scope, $routeParams, $mdDialog, $mdToast) {
    var mealId = $routeParams.param1;

    // assuming this is the meal that we got
    var meal = {
        "name": "Hard Boiled Egg",
        "id": 2,
        "defaultQuantity": 1,
        "unit": "whole large",
        "calories": 78,
        "iconUrl": "meals-icons/hard-boiled-eggs.jpg",
        "imageUrl": "meals-images/hard-boiled-eggs.jpg"
    };

    $scope.mealName = meal.name;
    $scope.quantity = meal.defaultQuantity;
    $scope.unit = meal.unit;
    $scope.calories = meal.calories;

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
            .content('This action cannot be undone because we dont give a shit.')
            .ariaLabel('Lucky day')
            .ok('Delete')
            .cancel('Cancel')
            .targetEvent(ev);
        $mdDialog.show(confirm).then(function () {
            $mdToast.show(
                $mdToast.simple()
                .content('Meal Deleted!')
                .hideDelay(1000)
                .position($scope.getToastPosition())
            );
            window.location = window.location.href.split('#')[0] + '#/meals';
        }, function () {
            // do nothing
        });
    };

    $scope.updateMeal = function () {
        if ($scope.editMealForm.$valid) {
            $mdToast.show(
                $mdToast.simple()
                .content('Meal Updated!')
                .hideDelay(1000)
                .position($scope.getToastPosition())
            );
            window.location = window.location.href.split('#')[0] + '#/meals';
        } else {
            $mdToast.show(
                $mdToast.simple()
                .content('You have to fix the errors before updating the meal!')
                .hideDelay(1000)
                .position($scope.getToastPosition())
            );
        }
    };
}]);

app.controller('adminAddMealController', ['$scope', '$mdToast', function ($scope, $mdToast) {
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
    $scope.addMeal = function () {
        if ($scope.addMealForm.$valid) {
            $mdToast.show(
                $mdToast.simple()
                .content('Meal Added!')
                .hideDelay(1000)
                .position($scope.getToastPosition())
            );
            window.location = window.location.href.split('#')[0] + '#/meals';
        } else {
            $mdToast.show(
                $mdToast.simple()
                .content('You have to fix the errors before adding the meal!')
                .hideDelay(1000)
                .position($scope.getToastPosition())
            );
        }
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/meals';
    };
}]);