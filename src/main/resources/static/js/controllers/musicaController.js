angular.module("mySpotify").controller("musicaController", function (myService,$scope){
    $scope.app = "My Spotify";
    $scope.albuns = getAlbuns();
    $scope.musicas = getMusicas();
    $scope.playlists = getPlaylists();
    $scope.playlist = myService.getPlaylist();
    $scope.album = getAlbumMusicas();

    //PROBLEMAS AO RETORNAR OS ALBUNS
    //PROBLEMAS AO INSERIR OUTRA MUSICA NO MESMO ALBUM

    $scope.adicionarMusica = function(musica,album){
        musica.album = getAlbum(album.name)
        if(musica.album == null){
            musica.album = album;
        }
        console.log(musica.album.artist);
        
        myService.adicionarMusica(musica)
            .then(function successCallback(response) {
                var album = [];
                var existsAlbum = false;
                /*for (var i = 0; i < $scope.albuns.length; i++) {
                    if ($scope.albuns[i].name === musica.album) {

                        if (!_existsMusic($scope.albuns[i], musica)) {
                            $scope.albuns[i].album.push(angular.copy(musica));
                            existsAlbum = true;
                        } else {
                            alert("Música já Existe");
                            return;
                        }
                    }
                }
                if (existsAlbum == false) {
                    album.push(angular.copy(musica));
                    _adicionarAlbum(album, musica.album);
                }*/
               $scope.musicas.push(angular.copy(response.data));
               getAlbuns();
            }, function errorCallback(response) {
                alert("erro")
            });
            
        delete $scope.musica;
    };
    var _adicionarAlbum = function (album, nomeAlbum) {

        $scope.albuns.push(angular.copy({ name: nomeAlbum, album }));
    }
    $scope.removerMusica = function (musicas) {
        var musicaRemovida = musicas.filter(function (musica) {
          if (musica.selecionada) {
            return musica;
          };
        });
    };
    $scope.removerPlaylist = function (playlists) {
        var playlistRemovida = playlists.filter(function (playlist) {
            if (playlist.selecionada) {
                return playlist;
            };
        });
        delete playlistRemovida.selecionada;
        myService.removerPlaylist(playlistRemovida)
            .then(function successCallback(response) {
                $scope.playlists = response.data
            }, function errorCallback(response) {
                alert("erro")
            });
    };
    $scope.isMusicaSelecionada = function (musicas) {
        if(musicas!=null){
            return musicas.some(function (musica) {
            return musica.selecionada;
            });
        }  
    };
    $scope.adicionarMusicaAPlaylist = function (musica) {
        myService.addMusicPlaylist(musica,$scope.playlist.name)
            .then(function successCallback(response) {
                $scope.playlist = response.data;
            }, function errorCallback(response) {
                alert("erro")
            });
    }
    $scope.getInfoPlaylist = function (playlist) {
        myService.setPlaylist(playlist);
    };
    $scope.getInfoAlbum = function (album) {
        myService.setAlbum(album);
    };
    $scope.salvarPlaylist = function (nome) {
        musicas = getMusicasMarcadas();
        if(musicas === null || nome === null){
            alert("Playlist não pode ser criada sem nenhuma música ou nome")
        }else{
            playlist = { name: nome, musicSet: musicas };
            myService.adicionarPlaylist(playlist)
                .then(function successCallback(response) {
                    $scope.playlists.push(angular.copy(playlist));
                }, function errorCallback(response) {
                    alert("erro")
            });
        }
        delete $scope.playlist;
    }
    /*$scope.isPlaylistSelecionada = function (playlists) {
        if(playlists === null){
            return null;
        }
        return playlists.some(function (playlist) {
            return playlist.selecionada;
        });
    };*/
    $scope.classe = "selecionada";
    function getAlbuns() {
        myService.getAlbuns()
            .then(function successCallback(response) {
                $scope.albuns = response.data;

            }, function errorCallback(response) {
                alert("erro")
        });
    };
    function getAlbum(nome) {
        for (var i = 0; i < $scope.albuns.length; i++) {
            if ($scope.albuns[i].name == nome) {
                return $scope.albuns[i];
            }
        }
        return null;
    };
    function getMusicasMarcadas() {
        var musicas = [];
        for (var i = 0; i < $scope.musicas.length; i++) {
            if ($scope.musicas[i].selecionada === true) {
                delete $scope.musicas[i].selecionada;
                musicas.push(angular.copy($scope.musicas[i]));
            }
        }  
        return musicas;  
    }
    function getMusicas() {
        myService.getMusicas()
            .then(function successCallback(response) {
                $scope.musicas = response.data;
            }, function errorCallback(response) {
                alert("erro")
        });
    };
    function getPlaylists() {
        myService.getPlaylists()
            .then(function successCallback(response) {
                $scope.playlists = response.data;
            }, function errorCallback(response) {
                alert("erro")
            });
    };
    function getArtist(nome) {
        return artistasController.getArtist(nome);
    };
    function getAlbumMusicas() {
        if(myService.getAlbum()!=null){
            myService.getAlbumMusicas()
                .then(function successCallback(response) {
                    var album = myService.getAlbum();
                    album.musicSet = response.data;
                    $scope.album = album;
                }, function errorCallback(response) {
                    alert("erro")
            });
        }
    }
});