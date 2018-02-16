angular.module("mySpotify").controller("artistaController", function (myService, $scope) {
    $scope.app = "My Spotify";
    $scope.artista = myService.getArtista();
    $scope.artista.musicas = getArtistasMusicas($scope.artista);

    $scope.salvar = function (artista) {
      var music = null;
      if(artista.lastMusic!=null){
        music = artista.lastMusic.name;
        delete artista.lastMusic;
      }
      myService.atualizarArtista(artista,music)
        .then(function successCallback(response) {
          $scope.artista = response.data;
          myService.setArtista(response.data);
        }, function errorCallback(response) {
          alert("Erro ao atualizar artista")
        });
      delete $scope.artista;
    };

    $scope.favoritar = function (artista) {
      $scope.artista.isFavorite = true;
      myService.favoritar($scope.artista)
        .then(function successCallback(response) {
          $scope.artista = response.data;
          myService.setArtista(response.data);
        }, function errorCallback(response) {
          alert("Erro ao atualizar artista")
        });
    }

    $scope.desfavoritar = function (artista) {
      $scope.artista.isFavorite = false;
      myService.favoritar($scope.artista)
        .then(function successCallback(response) {
          $scope.artista = response.data;
          myService.setArtista(response.data);
        }, function errorCallback(response) {
          alert("Erro ao atualizar artista")
        });
    }

    function getArtistasMusicas(artista) {
      myService.getArtistaMusicas(artista)
        .then(function successCallback(response) {
          $scope.artista.musicas = response.data;
          console.log($scope.artista.musicas)
        }, function errorCallback(response) {
          alert("erro")
        });
    };
    $scope.isFavorito = function(){
      return $scope.artista.isFavorite;
    }

});