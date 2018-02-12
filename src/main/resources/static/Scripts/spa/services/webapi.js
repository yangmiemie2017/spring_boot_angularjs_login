﻿(function () {
    'use strict';

    angular
        .module('strive')
        .factory('webapi', webapi);

    webapi.$inject = ['$http', '$httpParamSerializer','$window'];

    function webapi($http, $httpParamSerializer, $window) {

        return {

            post: function (className, actionName, parameters) {
        		var token = $window.sessionStorage.getItem('TOKEN');
        		if(token != null) {
        			$http.defaults.headers.common['Authorization'] = 'Token ' + token;
        		}
        		$http.defaults.headers.common['Content-Type'] = 'application/json';
        		
                return $http({
                    method: 'POST',
                    url: '/api',
                    data: JSON.stringify({ clazz: className, action: actionName, parameters: parameters })
                });

            },

            getMenu: function () {
                return $http({
                    method: 'get',
                    url: 'api/Menu'
                });
            },
        }


    }


})();