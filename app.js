'use strict';
var app = angular.module('StarterApp', ['ngMaterial', 'ngRoute', 'ngMessages']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/journal', {
            'templateUrl': 'list.html',
            'controller': 'journalController'
        })
        .when('/journal/:journalId', {
            'templateUrl': 'journal_detail.html',
            'controller': 'journalDetailController'
        })
        .when('/meals', {
            'templateUrl': 'add.html',
            'controller': 'mealsController'
        })
        .when('/meals/:mealId', {
            'templateUrl': 'meal_detail.html',
            'controller': 'mealDetailController'
        })
        .otherwise({
            'redirectTo': '/journal'
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
        .defaultIconSet('svg/core-icons.svg');
}]);

app.filter('underscoreToHuman', function () {
    return function (input) {
        input += '';
        input = input.replace('_', ' ');
        return input.replace(/\w\S*/g, function (txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        });
    }
});

app.controller('defaultController', ['$scope', '$mdSidenav', function ($scope, $mdSidenav, $mdDialog) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };
    $scope.navigateTo = function (where) {
        window.location = window.location.href.split('#')[0] + '#' + where;
    };
}]);

app.controller('journalController', ['$scope', '$mdDialog', function ($scope, $mdDialog, $event) {
    // reserve date 1438617600000
    var meals = [
        {
            'id': 1,
            'name': 'Porkchop with Monggos',
            'calories': 23,
            'unit': 'serving(s)',
            'quantity': 2,
            'dateCreated': 1435075200000
        },
        {
            'id': 2,
            'name': 'Bihon with kape',
            'calories': 23,
            'unit': 'serving(s)',
            'quantity': 4,
            'dateCreated': 1437926400000
        },
        {
            'id': 3,
            'name': 'Beefsteak with Siomai',
            'calories': 23,
            'unit': 'serving(s)',
            'quantity': 1,
            'dateCreated': 1432396800000
        },
        {
            'id': 4,
            'name': 'Nilat.ang baki with lettuce',
            'calories': 23,
            'unit': 'serving(s)',
            'quantity': 3,
            'dateCreated': 1438012800000
        }
    ];

    var now = new Date();
    var timestampToday = new Date(now.getFullYear(), now.getMonth(), now.getDate()).getTime();
    var timestampYesterday = timestampToday - 86400000;
    var temp = new Date(now.setDate(now.getDate() - now.getDay()));
    var timestampThisWeek = new Date(temp.getFullYear(), temp.getMonth(), temp.getDate());

    var objects = {};
    var totals = {};
    var grandTotal = 0;

    for (var i = 0; i < meals.length; i++) {
        if (meals[i].dateCreated >= timestampToday) {
            if (!objects.hasOwnProperty('today')) {
                objects.today = [];
                totals.today = 0;
            }

            objects.today.push(meals[i]);
            totals.today += meals[i].calories;
        } else if (meals[i].dateCreated >= timestampYesterday) {
            if (!objects.hasOwnProperty('yesterday')) {
                objects.yesterday = [];
                totals.yesterday = 0;
            }

            objects.yesterday.push(meals[i]);
            totals.yesterday += meals[i].calories;
        } else if (meals[i].dateCreated >= timestampThisWeek) {
            if (!objects.hasOwnProperty('this_week')) {
                objects.this_week = [];
                totals.this_week = 0;
            }

            objects.this_week.push(meals[i]);
            totals.this_week += meals[i].calories;
        } else {
            if (!objects.hasOwnProperty('others')) {
                objects.others = [];
                totals.others = 0;
            }

            objects.others.push(meals[i]);
            totals.others += meals[i].calories;
        }
        grandTotal += meals[i].calories;
    }

    if (grandTotal > 2000) {
        // dialog or toast ! whatever
        // if dialog
        $mdDialog.show(
            $mdDialog.alert()
            .parent(angular.element(document.body))
            .title('Calorie count exceeds 2000!')
            .content('You seriously need to stop eating. You\'re too fat already.')
            .ariaLabel('Alert Dialog Demo')
            .ok('Okay!')
            .targetEvent($event)
        );
        // if toast -> be sure to inject $mdToast
        /*$mdToast.show(
            $mdToast.simple()
            .content('You seriously need to stop eating. You\'re too fat already.')
            .hideDelay(1000)
        );*/
    }

    $scope.meals = objects;
    $scope.totals = totals;

    $scope.openJournal = function (journal) {
        window.location = window.location.href.split('#')[0] + '#/journal/' + journal.id;
    };

    $scope.searchBoxHidden = true;
    $scope.searchJournal = function () {
        $scope.searchBoxHidden = !$scope.searchBoxHidden;
        /* if (!$scope.searchBoxHidden) {
            document.getElementById('search-box').focus();
        } */
    };
}]);

app.controller('mealsController', ['$scope', function ($scope) {
    $scope.meals = [
        {
            'id': 1,
            'name': 'Porkchop with Monggos',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 2
        },
        {
            'id': 1,
            'name': 'Bihon with kape',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 4
        },
        {
            'id': 1,
            'name': 'Beefsteak with Siomai',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 1
        },
        {
            'id': 1,
            'name': 'Nilat.ang baki with lettuce',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 3
        },
        {
            'id': 1,
            'name': 'Porkchop with Monggos',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 2
        },
        {
            'id': 1,
            'name': 'Bihon with kape',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 4
        },
        {
            'id': 1,
            'name': 'Beefsteak with Siomai',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 1
        },
        {
            'id': 1,
            'name': 'Nilat.ang baki with lettuce',
            'calories': '23',
            'unit': 'serving(s)',
            'quantity': 3
        }
    ];

    $scope.openMeal = function (meal) {
        window.location = window.location.href.split('#')[0] + '#/meals/' + meal.id;
    };

    $scope.searchBoxHidden = true;
    $scope.searchMeals = function () {
        $scope.searchBoxHidden = !$scope.searchBoxHidden;
        /* if (!$scope.searchBoxHidden) {
            document.getElementById('search-box').focus();
        } */
    };
}]);

app.controller('mealDetailController', ['$scope', function ($scope) {
    var meal = {
        'id': 1,
        'name': 'Porkchop with Monggos',
        'calories': '23 cal',
        'unit': 'serving(s)',
        'quantity': 2
    };

    $scope.currentMeal = meal;

    $scope.ui = {
        'toolbarLabel': meal.name
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/meals';
    };
}]);

app.controller('journalDetailController', ['$scope', '$mdDialog', '$mdToast', function ($scope, $mdDialog, $mdToast) {
    var journal = {
        'id': 1,
        'name': 'Nilat.ang baki with lettuce',
        'calories': '23 cal',
        'unit': 'serving(s)',
        'quantity': 3
    };

    $scope.currentJournal = journal;

    $scope.ui = {
        'toolbarLabel': journal.name
    };

    $scope.backToJournal = function () {
        window.location = window.location.href.split('#')[0] + '#/journal';
    };

    $scope.deleteJournal = function (ev) {
        // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.confirm()
            .parent(angular.element(document.body))
            .title('Are you sure you want to delete this journal?')
            .content('This action cannot be undone because we dont give a shit.')
            .ariaLabel('Lucky day')
            .ok('Delete')
            .cancel('Cancel')
            .targetEvent(ev);
        $mdDialog.show(confirm).then(function () {
            // delete logic
        }, function () {
            // do nothing
        });
    };

    $scope.updateJournal = function () {
        // update logic here
        $mdToast.show(
            $mdToast.simple()
            .content('Simple Toast!')
            .hideDelay(1000)
        );
        window.location = window.location.href.split('#')[0] + '#/journal';
    };
}]);