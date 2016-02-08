/* 
 * Copyright 2016 Orange Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

'use strict';

/* App Module */

Array.prototype.randomize = function() {
    var i = this.length, j, temp;
    if(i===0)
	return this;
    while (--i) {
	j = Math.floor(Math.random() * (i - 1));
	temp = this[i];
	this[i] = this[j];
	this[j] = temp;
    }
    return this;
};

var myApp;
myApp = myApp || (function () {
    //var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"><div class="progress progress-striped active"><div class="bar" style="width: 100%;"></div></div></div></div>');
	var pleaseWaitDiv = $('<div class="modal hide" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false"><div class="modal-header"><h1>Processing...</h1></div><div class="modal-body"></div></div>');
    return {
        showPleaseWait: function() {
            pleaseWaitDiv.modal('show');
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();

var cloud4netportal = angular
	.module('cloud4netportal', [
	    'ngRoute', 
	    'angularFileUpload', 
	    'ui.bootstrap',
	    'angular-loading-bar',
	    'cloud4netportalControllers'
	])
	.directive('ngActiveTab', ['$location', function($location) {
	    return {
		link: function postLink(scope, element, attrs) {
		    scope.$on("$routeChangeSuccess", function(event, current, previous) {
			// this var grabs the tab-level off the attribute, or defaults to 1
			var pathLevel = attrs.activeTab || 1,
				// this var finds what the path is at the level specified
				pathToCheck = $location.path().split('/')[pathLevel],
				// this var finds grabs the same level of the href attribute
				tabLink = attrs.href.split('/')[pathLevel];

			if (pathToCheck === tabLink) {
			    element.parent().addClass("active");
			} else {
			    element.parent().removeClass("active");
			}
		    });
		}
	    };
	}])
	.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
		cfpLoadingBarProvider.includeSpinner = true;
		 
	}])
	.config(['$routeProvider', '$locationProvider', 
		function($routeProvider, $locationProvider) {
		    $locationProvider.html5Mode(true);

		    $routeProvider.
			    when('/', {
				templateUrl: '/partials/_index.html',
				controller: 'IndexCtrl'
			    }).
			    when('/subscribe', {
				templateUrl: '/partials/_subscribe.html',
				controller: 'NCSConfigController'
			    }).
			    when('/modify', {
				templateUrl: '/partials/_modify.html',
				controller: 'ModifyController'
			    }).
			    when('/add', {
				templateUrl: '/partials/_add.html',
				controller: 'BoostController'
			    }).
			    when('/delete', {
				templateUrl: '/partials/_delete.html',
				controller: 'DeleteCtrl'
			    }).
			    when('/offer', {
					templateUrl: '/partials/_offer.html',
					controller: 'OfferCtrl'
				    }).
			    when('/about', {
				templateUrl: '/partials/_about.html',
				controller: 'AboutCtrl'
			    }).
			    otherwise({
				redirectTo: '/'
			    });
		}
	])
	.filter('bytes', function() {
	    return function(bytes, precision) {
		if (isNaN(parseFloat(bytes)) || !isFinite(bytes)) return '-';
		if (bytes === 0) return '0 bytes';
		if (typeof precision === 'undefined') precision = 1;
		var units = ['bytes', 'KiB', 'MiB', 'GiB', 'TiB', 'PiB'],
			number = Math.floor(Math.log(bytes) / Math.log(1024));
		return (bytes / Math.pow(1024, Math.floor(number))).toFixed(precision) +  '' + units[number];
	    };
	})
	.filter('kibibytes', function() {
	    return function(bytes, precision) {
		if (isNaN(parseFloat(bytes)) || !isFinite(bytes)) return '-';
		if (bytes === 0) return '0 bytes';
		if (typeof precision === 'undefined') precision = 1;
		var units = ['KiB', 'MiB', 'GiB', 'TiB', 'PiB'],
			number = Math.floor(Math.log(bytes) / Math.log(1024));
		return (bytes / Math.pow(1024, Math.floor(number))).toFixed(precision) +  '' + units[number];
	    };
	})
	.run(['$rootScope', function($rootScope) {
	    $rootScope.currentYear = new Date().getFullYear();
	}]);

