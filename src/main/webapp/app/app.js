var app = angular.module('app', []);

app.controller('ConwayCtrl', function($scope, $http) {
    var numRows = 6;
    var numCols = 8;

    // initialize new grid
    var emptyRow = new Array(numCols).fill(false);
    $scope.rows = new Array(numRows).fill(emptyRow);

    // click handler function to change cell state
    $scope.setCell = function(isAlive, colIndex, rowIndex) {
        var row = angular.copy($scope.rows[rowIndex]);
        row[colIndex] = isAlive;
        $scope.rows[rowIndex] = row;
    }

    // send post to servlet and progress grid to next round
    $scope.nextRound = function() {
        $http({
            method : 'POST',
            url : 'conway',
            data : {
                numRows: numRows,
                numCols: numCols,
                rows: $scope.rows
            },
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function(data) {
            $scope.rows = data.rows;
        }).error(function(data) {
            console.log('error',data);
        });
    }

});