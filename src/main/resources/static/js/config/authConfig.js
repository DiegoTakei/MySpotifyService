angular.module('MySpotify').run(function ($rootScope, $state, $http, userService) {

    var _user = {}

    $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
        _user = userService.getToken();

        if (_user) {
            alert("Setando headers")
            $http.defaults.headers.common['Authorization'] = _user;
        } else {
            $http.defaults.headers.common['Authorization'] = '';
        }

    });
});