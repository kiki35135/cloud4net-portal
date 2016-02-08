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


var data =  {
		name: 'PGW-SVC-NGPoP',
		poolIpClient: '172.20.70.192',
		netmask: "255.255.255.240",
		vpnIpAddress: "172.20.70.192",
		useOrangeDns: 'True',
		primaryDNS:'172.20.4.148',
		secondaryDNS:'172.20.104.1',
		apnName:'vepc6co',
		options:{
			highResiliency: 'False',
			autoElasticity: 'True',
			advancedParam: 'False'
		}
};
/* Controllers */

var cloud4netportalControllers = angular
.module('cloud4netportalControllers', ['highcharts-ng'])
.constant('emptyChart', {
	options: {credits: {enabled: false}},
	title: {text: ''},
	loading: true
});

cloud4netportalControllers.controller('IndexCtrl', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
	$scope.currentDefaultPicture = '/img/default-picture.jpg';
}]);

cloud4netportalControllers.controller('ModifyController', ['$scope',  '$http', '$interval','$modal', function($scope, $http, $modal,$interval) {
	$scope.parameters =data;
	
	$scope.status = 'Not started';
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;
	$scope.showModal =false;
	
	$scope.progress = 0;
    $scope.labels = [
      "vEPC uneployment "
    ];
	
	$scope.manualElastivity=false;

	$scope.toggleManualElastivity = function() { 
		if ($scope.manualElastivity.checked) {

			$scope.parameters.useOrangeDns= 'True';
		}
		else
		{

			$scope.parameters.useOrangeDns= 'False';
		}
	};


	
	$scope.toggleUseOrangeDns = function() { 
		if ($scope.OrangeDns.checked) {

			$scope.parameters.useOrangeDns= 'True';
		}
		else
		{

			$scope.parameters.useOrangeDns= 'False';
		}
	};

	$scope.toggleAutoElasticity = function() { 
		if ($scope.autoElasticity.checked) {
			$scope.parameters.options.highResiliency='True';
		}
		else
		{
			$scope.parameters.options.highResiliency= 'False';
		}
	};
	$scope.getPercentage = function() {
		return (parseInt($scope.progress) / parseInt(	$scope.total)) * 100;
		
	};
	$scope.boostSubmit = function() {
		$scope.submitting = true;
		$scope.loading = true;
		$scope.showModal=true;
		$scope.progress=5;
		$interval(function() {
			$scope.progress += 10; //random(0, 10);
			$scope.status = $scope.labels[0];
		    if ($scope.progress > 50) {
		        $scope.status = $scope.labels[1];
		      }
		    else if ($scope.progress > 80) {
		    	$scope.status = $scope.labels[2];
		    }
		}, 1000, 20);
		$http({
			method: 'POST',
			url: '/api/ncsConfig/modify',
			data: $scope.parameters
		}).success(function(data) {
			$scope.submitting = false;
			$scope.status = "Finished";
			$scope.loading = false;
			$scope.showModal=false;
			$modalInstance.close(data);
		}).error(function(data, status) {
			$scope.submitting = false;
			$scope.status = "Error";
			$scope.showModal =false;
			$scope.loading = false;
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};
	$scope.modifySubmit = function() {
		$scope.submitting = true;
		$scope.status = 'Processing .';
		
		$scope.showModal =true;
		if ($scope.useOrangeDns)
		{
			$scope.parameters.useOrangeDns='True';
		}
		else
		{
			$scope.parameters.useOrangeDns='False';
		}
		if ($scope.highResiliency)
		{
			$scope.parameters.highResiliency='True';
		}
		else
		{
			$scope.parameters.highResiliency='False';
		}
		if ($scope.autoElasticity)
		{
			$scope.parameters.autoElasticity='True';
		}
		else
		{
			$scope.parameters.autoElasticity='False';
		}
		if ($scope.advancedParam)
		{
			$scope.parameters.advancedParam='True';
		}
		else
		{
			$scope.parameters.advancedParam='False';
		}
		$http({
			method: 'POST',
			url: '/api/ncsConfig/boost',
			data: $scope.parameters
		}).success(function(data) {
			$scope.submitting = false;
			$scope.status = 'Finished !';
			$scope.loading = false;
			$scope.showModal =false;
			
			$modalInstance.close(data);
		}).error(function(data, status) {
			$scope.status = 'Error';
			$scope.submitting = false;
			$scope.loading = false;
			$scope.showModal =false;
			
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};


}]);



cloud4netportalControllers.controller('NCSConfigController', ['$scope', '$http', '$interval','$modal', function($scope, $http, $interval, $modal) {

	
	$scope.status = "Processing ...";
    $scope.progress = 0;
    $scope.labels = [
      "vEPC Deployment ",
      "vEPC and PE Configuration",
      "Parameters customization"
    ];
    
    var i = -1;
    function update() {
    	console.log("test update");
      $scope.progress += random(0, 10);
      if ($scope.progress > random(70, 90)) {
        $scope.progress = random(5, 50);
        i = (i + 1) % $scope.labels.length;
        $scope.status = $scope.labels[i];
      }
    //  $timeout(update, 6000);
    };
    
    function random(a, b) {
      return a + Math.floor(Math.random() * (b - a));
    };
    
	$scope.parameters =data;
	
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;
	
	$scope.showModal =false;
	
	$scope.value =0;
	$scope.total = 100;
	
	
	$scope.getPercentage = function() {
		return (parseInt($scope.progress) / parseInt(	$scope.total)) * 100;
	};

	$scope.toggleUseOrangeDns = function() { 

		if ($scope.OrangeDns.checked) {

			$scope.parameters.useOrangeDns= 'True';
		}
		else
		{

			$scope.parameters.useOrangeDns= 'False';
		}


	};
	
	$scope.toggleAutoElasticity = function() { 
		if ($scope.autoElasticity.checked) {
			$scope.parameters.options.highResiliency='True';
		}
		else
		{
			$scope.parameters.options.highResiliency= 'False';
		}
	};

	$scope.submit = function() {
		$scope.submitting = true;
		$scope.status = $scope.labels[0];
		
		$scope.loading = true;

		$interval(function() {
			$scope.progress += 10; //random(0, 10);
			$scope.status = $scope.labels[0];
		    if ($scope.progress > 50) {
		        $scope.status = $scope.labels[1];
		      }
		    else if ($scope.progress > 80) {
		    	$scope.status = $scope.labels[2];
		    }
		}, 10000, 20);

		if ($scope.useOrangeDns)
		{

			$scope.parameters.useOrangeDns='True';
		}
		else
		{

			$scope.parameters.useOrangeDns='False';
		}
		if ($scope.highResiliency)
		{

			$scope.parameters.highResiliency='True';
		}
		else
		{

			$scope.parameters.highResiliency='False';
		}
		if ($scope.autoElasticity)
		{
			$scope.parameters.autoElasticity='True';
		}
		else
		{
			$scope.parameters.autoElasticity='False';
		}
		if ($scope.advancedParam)
		{
			$scope.parameters.advancedParam='True';
		}
		else
		{
			$scope.parameters.advancedParam='False';
		}
		
		$http({
			method: 'POST',
			url: '/api/ncsConfig',
			data: $scope.parameters
		}).success(function(data) {
			$scope.submitting = false;
			//$scope.status = 'Finished !';
			$scope.loading = false;
			//myApp.hidePleaseWait();
			$interval.cancel(stop);
			$scope.showModal =false;
			
			//$modalInstance.close(data);
		}).error(function(data, status) {
			//$scope.status = 'Error';
			$scope.submitting = false;
			$scope.loading = false;
			$interval.cancel(stop);
			$scope.showModal =false;
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};


}]);


cloud4netportalControllers.controller('DeleteController', ['$scope', '$http', '$interval','$modal', function($scope, $http, $modal,$interval) {
	$scope.parameters =data;
	$scope.status = 'Not started';
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;
    $scope.progress = 0;
    $scope.labels = [
      "vEPC uneployment "
    ];
	$scope.showModal=false;
	
	$scope.getPercentage = function() {
		return (parseInt($scope.progress) / parseInt(	$scope.total)) * 100;
		
	};

	$scope.toggleUseOrangeDns = function() { 

		if ($scope.OrangeDns.checked) {

			$scope.parameters.useOrangeDns= 'True';
		}
		else
		{

			$scope.parameters.useOrangeDns= 'False';
		}


	};

	$scope.toggleAutoElasticity = function() { 
		if ($scope.autoElasticity.checked) {
			$scope.parameters.options.highResiliency='True';
		}
		else
		{
			$scope.parameters.options.highResiliency= 'False';
		}
	};

	$scope.deleteSubmit = function() {
		$scope.submitting = true;
		
		$scope.loading= true;
		$scope.showModal=true;
		$scope.status = $scope.labels[0];
		$interval(function() {
			$scope.progress += 10; //random(0, 10);
			$scope.status = $scope.labels[0];
		    if ($scope.progress > 50) {
		        $scope.status = $scope.labels[1];
		      }
		    else if ($scope.progress > 80) {
		    	$scope.status = $scope.labels[2];
		    }
		}, 10000, 20);
		if ($scope.useOrangeDns)
		{

			$scope.parameters.useOrangeDns='True';
		}
		else
		{

			$scope.parameters.useOrangeDns='False';
		}
		if ($scope.highResiliency)
		{

			$scope.parameters.highResiliency='True';
		}
		else
		{

			$scope.parameters.highResiliency='False';
		}
		if ($scope.autoElasticity)
		{
			$scope.parameters.autoElasticity='True';
		}
		else
		{
			$scope.parameters.autoElasticity='False';
		}
		if ($scope.advancedParam)
		{
			$scope.parameters.advancedParam='True';
		}
		else
		{
			$scope.parameters.advancedParam='False';
		}
		$http({
			method: 'POST',
			url: '/api/ncsConfig/delete',
			data: $scope.parameters
		}).success(function(data) {
			
			$scope.status = 'Finished !';
			
			$scope.showModal =false;

		}).error(function(data, status) {
			$scope.status = 'Error';

			$scope.showModal =false;
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};


}]);



cloud4netportalControllers.controller('BoostController', ['$scope', '$modal', '$interval','$http', function($scope, $modal, $http,$interval) {
	$scope.parameters =data;
	$scope.total=100;
	$scope.progress=0;
	
	$scope.boost = {
			parentalFw: 'True',
			webFilter: 'False',
			antivirus: "True"
	};
	$scope.getPercentage = function() {
		return (parseInt($scope.progress) / parseInt(	$scope.total)) * 100;
		
	};
	$scope.showModal=false;

	$scope.boostSubmit = function() {
		
		$scope.showModal=true;
		$scope.progress=5;
		$scope.status = $scope.labels[0];
		$interval(function() {
			$scope.progress += 10; //random(0, 10);
			$scope.status = $scope.labels[0];
		    
		}, 10000, 20);
		$http({
			method: 'POST',
			url: '/api/ncsConfig/modify',
			data: $scope.parameters
		}).success(function(data) {
			$scope.submitting = false;

			$scope.loading = false;
			$scope.showModal=false;
			
		}).error(function(data, status) {
			$scope.showModal =false;

			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};

}]);





cloud4netportalControllers.controller('OfferController', ['$scope', '$q', '$http', '$filter', '$interval', function($scope, $q, $http, $filter, $interval) {

}]);

cloud4netportalControllers.controller('AboutCtrl', ['$scope', '$q', '$http', '$filter', '$interval', function($scope, $q, $http, $filter, $interval) {

}]);