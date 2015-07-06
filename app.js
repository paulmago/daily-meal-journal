'use strict';
var app = angular.module('StarterApp', ['ngMaterial', 'ngRoute', 'ngMessages']);

app.config(['$routeProvider', function ($routeProvider) {
    // user routes
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
        });

    // admin routes
    $routeProvider
        .when('/admin/meals', {
            'templateUrl': 'admin/index.html',
            'controller': 'adminMealsController'
        })
        .when('/admin/meals/:mealId', {
            'templateUrl': 'admin/meal_detail.html',
            'controller': 'adminMealDetailController'
        });

    // the only invalid route -> because no one's perfect :)
    $routeProvider
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

// admin controllers
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

    $scope.openMeal = function (meal) {
        window.location = window.location.href.split('#')[0] + '#/admin/meals/' + meal.id;
    };
}]);

app.controller('adminMealDetailController', ['$scope', '$routeParams', function ($scope, $routeParams) {
    var meal = {
        'id': 1,
        'name': 'Porkchop with Monggos',
        'calories': '23 cal',
        'unit': 'serving(s)',
        'quantity': 2
    };

    $scope.ui = {
        'toolbarLabel': meal.name
    };

    $scope.backToMeals = function () {
        window.location = window.location.href.split('#')[0] + '#/admin/meals';
    };

    $scope.updateMeal = function () {
        window.location = window.location.href.split('#')[0] + '#/admin/meals';
    };
}]);


/*********************
 ** USER controllers
 **********************/

// base file controller
app.controller('defaultController', ['$scope', '$mdSidenav', function ($scope, $mdSidenav, $mdDialog) {
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };
    $scope.navigateTo = function (where) {
        window.location = window.location.href.split('#')[0] + '#' + where;
    };
}]);

// meals list controller
app.controller('mealsController', ['$scope', '$http', function ($scope, $http) {
    $http
        .get('test/meals.json')
        .success(function (response) {
            $scope.meals = response.meals;
        });

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

// meal detail (also used to add )
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

    // this functions adds the current meal to the user's journal
    $scope.addJournal = function () {
        // check the current total calories + this meal's calories
        // if it's greater than 2000 show a dialog
        /*if (grandTotal > 2000) {
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
        }*/
        // check if this meal is the eleventh meal
        // if it is, show a dialog showing 'nilapas na 10 imo meal okie ?'
        // TODO : put it here

        // redirect the user back to its journal
        window.location = window.location.href.split('#')[0] + '#/journal';
    };
}]);

// user's journal controller
app.controller('journalController', ['$scope', '$http', function ($scope, $http) {
    // getting journals
    $http
        .get('test/journals.json')
        .success(function (response) {
            var meals = response.journals;

            // get timestamps for related dates to compare against journal data
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
                    totals.today += meals[i].calories * meals[i].quantity;
                } else if (meals[i].dateCreated >= timestampYesterday) {
                    if (!objects.hasOwnProperty('yesterday')) {
                        objects.yesterday = [];
                        totals.yesterday = 0;
                    }

                    objects.yesterday.push(meals[i]);
                    totals.yesterday += meals[i].calories * meals[i].quantity;
                } else if (meals[i].dateCreated >= timestampThisWeek) {
                    if (!objects.hasOwnProperty('this_week')) {
                        objects.this_week = [];
                        totals.this_week = 0;
                    }

                    objects.this_week.push(meals[i]);
                    totals.this_week += meals[i].calories * meals[i].quantity;
                } else {
                    if (!objects.hasOwnProperty('others')) {
                        objects.others = [];
                        totals.others = 0;
                    }

                    objects.others.push(meals[i]);
                    totals.others += meals[i].calories * meals[i].quantity;
                }
                grandTotal += meals[i].calories;
            }

            $scope.rawMeals = meals;
            $scope.groupedMeals = objects;
            $scope.totals = totals;
        });

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

// user's journal detail controller
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
            .content('Journal Updated!')
            .hideDelay(1000)
        );
        window.location = window.location.href.split('#')[0] + '#/journal';
    };
}]);