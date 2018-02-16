angular.module("mySpotify").factory("userService", function ($cookies) {

    var _storeUser = function (token) {
        $cookies.putObject("token", token);
    };

    var _removeUser = function (token) {
        $cookies.remove(token);
    };

    var _getUser = function () {
        return $cookies.getObject("token");
    };

    return {
        removeUser: _removeUser,
        getToken: _getUser,
        storeUser: _storeUser
    };
});