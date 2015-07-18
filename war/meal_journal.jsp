<html lang="en" ng-app="StarterApp">

<head>
    <link rel="stylesheet" href="node_modules/angular-material/angular-material.min.css">
    <link rel="stylesheet" href="//fonts.googleapis.com/css?family=RobotoDraft:300,400,500,700,400italic">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <meta name="viewport" content="initial-scale=1" />
</head>

<body layout="row" ng-controller="defaultController" flex>
    <md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="$mdMedia('gt-sm')" class="md-primary md-hue-2">
        <div class="sidenav-header">
            <img src="svg/lunch4.svg" alt="logo" />
            <h1>Daily Meal Journal</h1>
        </div>
        <md-list>
            <md-list-item ng-click="gotoHome()">
                <md-icon md-svg-icon="action:home"></md-icon>
                <p>Home</p>
            </md-list-item>
            <md-list-item ng-click="navigateTo('/journal')">
                <md-icon md-svg-icon="navigation:apps"></md-icon>
                <p>My Journal</p>
            </md-list-item>
            <md-list-item ng-click="navigateTo('/meals')">
                <md-icon md-svg-src="svg/cutlery6.svg"></md-icon>
                <p>Meals</p>
            </md-list-item>
        </md-list>
    </md-sidenav>
    <md-content ng-view flex></md-content>

    <script src="node_modules/angular/angular.min.js"></script>
    <script src="node_modules/angular-route/angular-route.min.js"></script>
    <script src="node_modules/angular-animate/angular-animate.min.js"></script>
    <script src="node_modules/angular-aria/angular-aria.min.js"></script>
    <script src="node_modules/angular-messages/angular-messages.min.js"></script>
    <script src="node_modules/angular-material/angular-material.min.js"></script>
    <script src="app/user-app.js"></script>
</body>

</html>