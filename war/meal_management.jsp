<html lang="en" ng-app="StarterApp">

<head>
    <link rel="stylesheet" href="css/angular-material.min.css">
    <link rel="stylesheet" href="//fonts.googleapis.com/css?family=RobotoDraft:300,400,500,700,400italic">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <meta name="viewport" content="initial-scale=1" />
</head>

<body layout="row" ng-controller="defaultController" flex>
    <md-sidenav layout="column" class="md-sidenav-left md-whiteframe-z2" md-component-id="left" md-is-locked-open="$mdMedia('gt-sm')" class="md-primary md-hue-2">
        <div class="sidenav-header">
            <img src="svg/lunch4.svg" alt="logo" />
            <h1>Daily Meal Journal</h1>
            <div class="subheader">Admin Version</div>
        </div>
        <md-list>
        	<md-list-item ng-click="gotoHome()">
                <md-icon md-svg-icon="action:home"></md-icon>
                <p>Home</p>
            </md-list-item>
            <md-list-item ng-click="navigateTo('/meals')">
                <md-icon md-svg-src="svg/cutlery6.svg"></md-icon>
                <p>Meals</p>
            </md-list-item>
        </md-list>
    </md-sidenav>
    <md-content layout="column" ng-view flex></md-content>

    <!-- Angular Material Dependencies -->
    <script src="js/angular.min.js"></script>
    <script src="js/angular-route.min.js"></script>
    <script src="js/angular-animate.min.js"></script>
    <script src="js/angular-aria.min.js"></script>
    <script src="js/angular-messages.min.js"></script>
    <script src="js/angular-material.min.js"></script>
    <script src="js/admin-app.js"></script>
</body>

</html>