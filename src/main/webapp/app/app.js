var app = angular.module('app', []);

app.controller('ConwayCtrl', function($scope) {
    var numRows = 6;
    var numCols = 8;

    // If new
    var emptyRow = new Array(numCols).fill(false);
    $scope.rows = new Array(numRows).fill(emptyRow);

    $scope.setCell = function(isAlive, colIndex, rowIndex) {
        var row = angular.copy($scope.rows[rowIndex]);
        row[colIndex] = isAlive;
        $scope.rows[rowIndex] = row;
    }

    $scope.nextRound = function() {
        console.log('data', $scope.rows);
    }

});