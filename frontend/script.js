angular.module('DesafioApp', [])
   .controller('DesafioController', function($scope, $http) {
    

    $scope.init = function(){
        $http.get("http://localhost:8080/controle-lancamento/").then(function(data){
            $scope.data = data;
        })
    }

});