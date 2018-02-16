angular.module('mySpotify').service('myService', function ($http, $location, userService) {
    var _albuns = [];
    var _musicas = [];
    var _playlists = [];
    var _playlist;
    var _album;
    var _artista;
    var _favoritos = [];

    var _adicionarArtista = function (artista) {

        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/artist/add',
                headers: {username: userService.getToken()},
                data: artista})
    }
    var _adicionarMusica = function (musica) {
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/album/add',
                headers: { username: userService.getToken() },
                data: musica
        })
    }
    var _adicionarAlbum = function (album,nomeAlbum) {

        _albuns.push(angular.copy({ nomeAlbum: nomeAlbum, album }));
    }
    var _adicionarPlaylist = function (playlist) {    
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/playlists/addPlaylist',
                headers: { username: userService.getToken() },
                data: playlist
            }
        )
    }

    var _removerArtista = function (artistas) {
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/artist/remove',
                headers: { username: userService.getToken() },
                data: artistas
            })
        
    }

    var _atualizarArtista = function (artista,name) {
        $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/artist/updateArtist',
                headers: { username: userService.getToken(), name:name },
                data: artista
            })      
    }

    var _favoritar = function (artista) {
        $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/artist/favorite',
                headers: { username: userService.getToken()},
                data: artista
            })
    }

    var _addMusicPlaylist = function (musica,namePlaylist) {
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/playlists/addMusic',
                headers: { username: userService.getToken(), name:namePlaylist },
                data: musica
            })     
    }

    var _getA = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/artist/getAll',
                headers: { username: userService.getToken() }
            })
        
    }
    var _getAlbuns = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/album/getAll',
                headers: { username: userService.getToken() }
        })

    }
    var _getMusicas = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/album/getAllMusics',
                headers: { username: userService.getToken() }
            })
    }
    var _getPlaylists = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/playlists/getAll',
                headers: { username: userService.getToken() }
            })
    }    
    var _removerPlaylist = function (playlists) {

        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/playlists/remove',
                headers: { username: userService.getToken() },
                data: playlists
            })
    }

    var _existsMusic = function (musicas, musica) {
        console.log(musicas.album);
        for (var i = 0; i < musicas.album.length; i++) {
            if (musicas.album[i].nome == musica.nome) {
                return true;
            }
        }
        return false;
    }
    var _existsPlaylist = function (nome) {
        for (var i = 0; i < _playlists.length; i++) {
            if (_playlists[i].nomePlaylist == nome) {
                return true;
            }
        }
        return false;
    }
    
    var _getFavoritos = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/artist/getAllFavorite',
                headers: { username: userService.getToken() }
            })
    }

    var _getArtistaMusicas = function (artista) {
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/artist/getAllArtistMusics',
                headers: { username: userService.getToken() },
                data: artista
            })     
    }

    var _setValues = function (response) {
        
        _artistas = response.data.artistSet;
    }

    var _getArtistas = function () {
        return [];
    }
    var _getPlaylist = function () {
        return _playlist;
    }
    var _getAlbumMusicas = function () {
        return $http(
            {
                method: 'POST',
                url: 'http://localhost:8080/album/getAlbum',
                headers: { username: userService.getToken() },
                data: _album
            })   
    }
    var _getLastMusics = function () {
        return $http(
            {
                method: 'GET',
                url: 'http://localhost:8080/album/getLastMusics',
                headers: { username: userService.getToken() }
            })
    }
    var _getAlbum = function () {
        return _album;
    }
    var _getArtista = function () {
        return _artista;
    }
    var _setArtista = function (artista) {
        _artista = artista;
    }
    var _setPlaylist = function (playlist) {
        _playlist = playlist;
    }
    var _setAlbum = function (album) {
        _album = album;
    }

    return {
        adicionarArtista: _adicionarArtista,
        adicionarMusica: _adicionarMusica,
        adicionarPlaylist: _adicionarPlaylist,
        addMusicPlaylist: _addMusicPlaylist,
        atualizarArtista: _atualizarArtista,
        favoritar: _favoritar,
        removerArtista: _removerArtista,
        removerPlaylist: _removerPlaylist,
        getArtistas: _getArtistas,
        getMusicas: _getMusicas,
        getPlaylists: _getPlaylists,
        getPlaylist: _getPlaylist,
        getAlbum: _getAlbum,
        setAlbum: _setAlbum,
        setPlaylist: _setPlaylist,
        getArtistaMusicas: _getArtistaMusicas,
        setArtista: _setArtista,
        existsMusic: _existsMusic,
        setValues: _setValues,
        getA: _getA,
        getAlbuns: _getAlbuns,
        getFavoritos: _getFavoritos,
        getLastMusics: _getLastMusics,
        getAlbumMusicas: _getAlbumMusicas,
        getArtista: _getArtista
    };

});


/*

Da mesma forma o usuário pode excluir artistas da lista de favoritos, exibindo uma mensagem de confirmação caso o usuário aceite ou não a exclusão.
O usuário poderá criar uma playlist com determinado nome e adicionar músicas a determinada playlist.
Da mesma forma o usuário poderá excluir determinada playlist ou excluir determinada música de tal playlist, exibindo uma mensagem de confirmação caso o usuário aceite ou não a exclusão.
*/