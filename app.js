'use strict';
var app = angular.module('StarterApp', ['ngMaterial', 'ngRoute', 'ngMessages']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            'templateUrl': 'list.html',
            'controller': 'listController'
        })
        .when('/meals/add', {
            'templateUrl': 'add.html',
            'controller': 'addController'
        })
        .when('/meals/:mealId', {
            'templateUrl': 'detail.html',
            'controller': 'detailController'
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
        .defaultIconSet('svg/core-icons.svg')
        .iconSet('device', 'svg/device-icons.svg')
        .iconSet('navigation', 'svg/navigation-icons.svg')
        .iconSet('content', 'svg/content-icons.svg');
}]);

app.controller('defaultController', ['$scope', '$mdSidenav', function ($scope, $mdSidenav) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };
    $scope.fabAction = '#/add';
}]);

app.controller('listController', ['$scope', function ($scope) {
    $scope.todayMeals = [
        {
            'name': 'Porkchop with Monggos',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 2
        },
        {
            'name': 'Bihon with kape',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 4
        }
    ];
    $scope.yesterdayMeals = [
        {
            'name': 'Beefsteak with Siomai',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 1
        },
        {
            'name': 'Nilat.ang baki with lettuce',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 3
        }
    ];
}]);

app.controller('addController', ['$scope', function ($scope) {
    $scope.breakfasts = [
        {
            'id': 1,
            'name': 'Porkchop with Monggos',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 2
        },
        {
            'id': 1,
            'name': 'Bihon with kape',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 4
        }
    ];
    $scope.lunchs = [
        {
            'id': 1,
            'name': 'Beefsteak with Siomai',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 1
        },
        {
            'id': 1,
            'name': 'Nilat.ang baki with lettuce',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 3
        }
    ];
    $scope.dinners = [
        {
            'id': 1,
            'name': 'Porkchop with Monggos',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 2
        },
        {
            'id': 1,
            'name': 'Bihon with kape',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 4
        }
    ];
    $scope.appetizers = [
        {
            'id': 1,
            'name': 'Beefsteak with Siomai',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 1
        },
        {
            'id': 1,
            'name': 'Nilat.ang baki with lettuce',
            'calories': '23 cal',
            'unit': 'serving(s)',
            'quantity': 3
        }
    ];

    $scope.openMeal = function (meal) {
        window.location = window.location.href.split('#')[0] + '#/meals/' + meal.id;
    };
}]);

app.controller('detailController', ['$scope', function ($scope) {
    var meal = {
        'id': 1,
        'name': 'Porkchop with Monggos',
        'calories': '23 cal',
        'unit': 'serving(s)',
        'quantity': 2
    };

    $scope.currentMeal = meal;

    $scope.toolbar = {
        'label': meal.name
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/meals/add';
    };
}]);