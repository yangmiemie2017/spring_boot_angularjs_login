﻿(function () {
    'use strict';

    var requireConfig = {
        'baseUrl': '/Scripts/spa/'

    };

    require.config(requireConfig);

    define('app', function () {

        return angular
             .module('strive', ['ngRequire', 'ui.router', 'ngResource', 'ngAnimate', 'ngplus', 'ui.bootstrap', 'ngFileUpload',
                         'ui.router.state', 'ncy-angular-breadcrumb', 'ui.grid', 'ui.grid.selection', 'ngMessages', 'blocks.logger',
                         'angular.filter', 'ui.grid.edit', 'ui.grid.pagination', 'angular-drag', 'ui.tree'
             ])
             //.constant('authInterceptor', authInterceptor)
//            .config(function ($httpProvider) {
//                $httpProvider.interceptors.push(authInterceptor);
//            })
             .config(function ($breadcrumbProvider) {
                 $breadcrumbProvider.setOptions({
                     prefixStateName: 'home',
                     template: 'bootstrap3'
                 });
             })
             .constant('toastr', toastr)
             .config(function toastrConfig(toastr) {
                 toastr.options.closeButton = true;
                 //toastr.options.preventDuplicates = true;
                 toastr.options.timeOut = 0;
                 toastr.options.extendedTimeOut = 0;
                 toastr.options.positionClass = 'toast-bottom-left';
                 toastr.options.tapToDismiss = false;
             })
             .config(config)
             .run(kickstart)

        function LeaveModalCtrl($uibModalInstance, message) {
            var vm = this;

            vm.message = message;
            vm.yes = function () {
                $uibModalInstance.close();
            }

            vm.no = function () {
                $uibModalInstance.dismiss('cancel');
            }
        }



        config.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider', '$requireProvider'];
        function config($stateProvider, $urlRouterProvider, $locationProvider, $requireProvider) {

            var requireJS = $requireProvider.requireJS;
            var requireCSS = $requireProvider.requireCSS;

            //$locationProvider.html5Mode({
            //    enabled: true,
            //    requireBase: false
            //});

            $urlRouterProvider.rule(function ($injector, $location) {
                //what this function returns will be set as the $location.url
                var path = $location.path(), normalized = path.toLowerCase();
                if (path != normalized) {
                    //instead of returning a new url string, I'll just change the $location.path directly so I don't have to worry about constructing a new url string and so a new state change is not triggered
                    $location.replace().path(normalized);
                }
                // because we've returned nothing, no state change occurs
            });


            //
            // For any unmatched url, redirect to /
            $urlRouterProvider
                .otherwise("/");

            // Now set up the states
            $stateProvider
                .state('login', {
                    url: "/",
                    templateUrl: "login.html",
                    resolve: {
                        deps: requireJS([
                          'login'
                        ])
                    },
                    ncyBreadcrumb: {
                        label: 'Login'
                    }
                })
                .state('regist', {
                    url: "/regist",
                    templateUrl: "Scripts/spa/regist/regist.html",
                    resolve: {
                        deps: requireJS([
                          'regist/regist.controller'
                        ])
                    },
                    ncyBreadcrumb: {
                        label: 'Regist'
                    }
                })                   
                .state('home', {
                    url: "/home",
                    templateUrl: "Scripts/spa/home/home.html",
                    resolve: {
                        deps: requireJS([
                          'home/home.controller'
                        ])
                    },
                    ncyBreadcrumb: {
                        label: 'Home'
                    }
                })

            .state('noAccess', {
                url: "/no-access",
                templateUrl: "Scripts/spa/securityError/access-deny.html",
                resolve: {
                    deps: requireJS([
                      'blocks/accessDeny/accessDenyInterceptor'
                    ])
                },
                ncyBreadcrumb: {
                    skip: true
                }
            })
            .state('logout', {
                url: "/logout",
                templateUrl: "Scripts/spa/logout/logout.html",
                controller: "logoutCtrl as loCtrl",
                resolve: {
                    deps: requireJS([
                      'logout/logout.controller'
                    ])
                },
                ncyBreadcrumb: {
                    skip: true
                }
            })
            .state('help', {
                url: "/help",
                templateUrl: "Scripts/spa/help/help.html",
                resolve: {
                    deps: requireJS([
                        'help/help.controller'
                    ])
                },
                ncyBreadcrumb: {
                    skip: true
                }
            })
            ;
        }

        kickstart.$inject = ['$rootScope', '$log', 'logger', '$state', '$window'];
        function kickstart($rootScope, $log, logger, $state, $window) {
            $rootScope.$on('$stateChangeStart',
               function (event, toState, toParams, fromState, fromParams, options) {
                   $log.info('$stateChangeStart: ' + toState.name);

                   var token = $window.sessionStorage.getItem("TOKEN");
                   if (['login', 'regist'].indexOf(toState.name)<0 && (token == null || token == '')) {
                       event.preventDefault();
                       $state.go('login');
                       return;
                   }

                   if (toState.name != fromState.name) {
                       logger.remove();
                   }

               });

            $rootScope.$on('$stateChangeError',
     function (event, toState, toParams, fromState, fromParams, error) {
         $log.info('$stateChangeError: ' + toState.name);

     });
        }

        authInterceptor.$inject = ['$rootScope', '$q', '$window', '$state'];
        function authInterceptor($rootScope, $q, $window, $state) {
            return {
                request: function (config) {
                    config.headers = config.headers || {};
                    var token = $window.sessionStorage.getItem("TOKEN");
                    if (token != null && token != '') {
                        config.defaults.headers.common['Authorization']  = 'Token ' + $window.sessionStorage.getItem('TOKEN');
                    }
                    return config;
                },
                response: function (response) {
                    if (response.status === 401) {
                        $state.go('login');
                    }
                    return response || $q.when(response);
                }
            };
        }

    })
})();