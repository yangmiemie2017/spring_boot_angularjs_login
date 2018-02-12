﻿(function () {
    'use strict';
    define(['app'], function (app) {
        //angular
        //    .module('pmdFieldCrops')
        app.controller('logoutCtrl', logoutCtrl);

        logoutCtrl.$inject = ['$http', '$state', '$window'];

        function logoutCtrl($http, $state, $window) {
            var vm = this;

            vm.logout = function () {
        		$http({
        			method : 'GET',
        			url : '/logoff',
//        			timeout : 3000
        		}).then(
        				function(response) {       					
        					if ('200' == response.status) {
        						$window.sessionStorage.removeItem('TOKEN');
        					} else {

        					}
        				},
        				function(response) {
        				});
            };

            vm.logout();
        }
    })
})();