angular.module("mySpotify").factory("loginService", function ($http, $location,userService,myService) {

    var _fazerLogin = function (usuario) {
        $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/account/login',  /*You URL to post*/
                data: { email: usuario.email, password: usuario.password } /*You data object/class to post*/
            }).then(function successCallback(response) {
                userService.storeUser(usuario.email);
                myService.setValues(response);
                $location.path('/menu')
            }, function errorCallback(response) {

                delete usuario.password;
                console.log(response)
                alert(response.data.message)
            });
    };

    var _registrar = function (usuario){
        $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/account/register',  /*You URL to post*/
                data: usuario /*You data object/class to post*/
            }).then(function successCallback(response) {
                userService.storeUser(response);
                $location.path('/index.html')
            }, function errorCallback(response) {

                delete usuario.password;
                var _message = "";

                if (!response) {
                    _message = "Usuário ou senha incorretos"
                } else {
                    _message = response.mensagem
                }

            });      
    }

    var _fazerLogout = function () {
        return $http.post(_path + "/logout");
    };

    return {
        login: _fazerLogin,
        logout: _fazerLogout,
        registrar: _registrar
    };
});