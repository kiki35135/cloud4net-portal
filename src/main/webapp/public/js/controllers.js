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

cloud4netportalControllers.controller('ModifyController', ['$scope',  '$http', '$modal', function($scope, $http, $modal) {
	$scope.parameters =data;
	
	$scope.status = 'Not started';
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;
	$scope.showModal =false;
	
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

	$scope.boostSubmit = function() {
		$scope.submitting = true;
		$scope.loading = true;
		$scope.showModal=true;
		
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

cloud4netportalControllers.controller('NCSConfigController', ['$scope', '$http', '$modal', function($scope, $http, $modal) {
	/*$scope.parameters =  {
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
	};*/
	$scope.parameters =data;
	$scope.status = 'Not started';
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;
	
	$scope.showModal =false;

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
		$scope.status = 'Processing .....';
		$scope.loading = true;
		//myApp.showPleaseWait();
		$scope.showModal =true;
		//cfpLoadingBar.inc();
		//$scope.showModal = !$scope.showModal;
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
			$scope.status = 'Finished !';
			$scope.loading = false;
			//myApp.hidePleaseWait();
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


cloud4netportalControllers.controller('DeleteController', ['$scope', '$http', '$modal', function($scope, $http, $modal) {
	$scope.parameters =data;
	$scope.status = 'Not started';
	$scope.loading = false;
	$scope.useOrangeDns=false;
	$scope.highResiliency=false;
	$scope.autoElasticity=false;
	$scope.advancedParam=false;

	$scope.showModal=false;

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
		$scope.status = 'Processing .....';
		$scope.loading= true;
		$scope.showModal=true;
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



cloud4netportalControllers.controller('BoostController', ['$scope', '$modal', '$http', function($scope, $modal, $http) {
	$scope.parameters =data;
	
	$scope.boost = {
			parentalFw: 'True',
			webFilter: 'False',
			antivirus: "True"
	};

	$scope.showModal=false;

	$scope.boostSubmit = function() {
		$scope.submitting = true;
		$scope.loading = true;
		$scope.showModal=true;
		
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

}]);





cloud4netportalControllers.controller('DeleteController', ['$scope', '$modal', '$http', function($scope, $modal, $http) {
	$scope.options = {
			highResiliency: 'False',
			autoElasticity: 'True',
			advancedParam: 'False'
	};
	

	$scope.parameters =data;
	$scope.showModal =true;
	$scope.deleteSubmit = function() {
		$scope.showModal =true;
		$scope.submitting = true;
		$http({
			method: 'POST',
			url: '/api/ncsConfig/delete',
			data: $scope.parameters
		}).success(function(data) {
			$scope.submitting = false;
			$modalInstance.close(data);
			$scope.showModal =false;
		}).error(function(data, status) {
			$scope.submitting = false;
			$scope.showModal =false;
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};

}]);


cloud4netportalControllers.controller('AboutCtrl', ['$scope', '$q', '$http', '$filter', '$interval', function($scope, $q, $http, $filter, $interval) {

}]);