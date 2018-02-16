angular.module("mySpotify").controller("loginController", function (userService,loginService,$http,$scope,$location) {

    $scope.app = "My Spotify";

    $scope.login = function (usuario) {   
        loginService.login(usuario);
    };  
    $scope.registrar = function (usuario) {
        loginService.registrar(usuario);
    };
});