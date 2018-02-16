angular.module("mySpotify").controller("artistasController", function (myService,$scope){
      $scope.app = "My Spotify";
      $scope.artistas = getArtistas();
      $scope.lastMusics = getLastMusics();
      $scope.artistasFavoritos = getFavoritos();

      $scope.adicionarArtista = function(artista){
        myService.adicionarArtista(artista)
          .then(function successCallback(response) {
            $scope.artistas.push(angular.copy(response.data));
          }, function errorCallback(response) {
            alert("erro")
          });
        delete $scope.artista;
      };
      
      $scope.removerArtista = function (artistas) {
        var artistaRemovido = artistas.filter(function (artista) {
          if (artista.selecionado) {
            return artista;
          }; 
        });        
        myService.removerArtista(artistaRemovido)
          .then(function successCallback(response) {
            console.log(response.data)
            $scope.artistas = response.data
          }, function errorCallback(response) {
            alert("erro")
          });
      };
      $scope.isArtistaSelecionado = function (artistas) {
        if(artistas !=null)
          return artistas.some(function (artista) {
            return artista.selecionado;
          });
      };
      $scope.getInfoArtista = function (artista){
        myService.setArtista(artista);
      };
      $scope.classe = "selecionado";
      $scope.adicionarAosFavoritos = function (artista) {
        myService.adicionarAosFavoritos(artista);
        alert("Adicionado aos favoritos");
        delete $scope.artista;
      };
      function getArtistas() {
        myService.getA()
          .then(function successCallback(response) {
            $scope.artistas = response.data;

          }, function errorCallback(response) {
            alert("erro")
          });
      };
      function getFavoritos() {
        myService.getFavoritos()
          .then(function successCallback(response) {
            $scope.artistasFavoritos = response.data;

          }, function errorCallback(response) {
            alert("erro")
          });
      };
      function getLastMusics() {
        myService.getLastMusics()
          .then(function successCallback(response) {
            $scope.lastMusics = response.data;

          }, function errorCallback(response) {
            alert("erro")
          });
      };
      function getArtist(nome) {
        for (var i = 0; i < $scope.artistas.length; i++) {
          if (artistas[i].name == nome) {
            return artistas[i].name;
          }
        }
        return null;
      };
});