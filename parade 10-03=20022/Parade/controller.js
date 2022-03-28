console.log("asdsad");

var app = angular.module('parade', []);
app.directive('fileModel', ['$parse', function ($parse) { 
    return { 
        restrict: 'A', 
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel); 
            var modelSetter = model.assign;
            element.bind('change', function(){ 
                scope.$apply(function(){
                  modelSetter(scope, element[0].files[0]);
                }); 
            }); 
        } 
    }; 
}]);
app.controller('paradeCtrl', function ($scope, $http) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
    $scope.baseurl = "http://localhost:8084/api/";
    $scope.logindata = {};
    $scope.regdata = {};
    $scope.loginUserData = {};

    $scope.showcontent = 1;
    $scope.loggedin = false;

    if (localStorage.getItem("parade_user") != null && localStorage.getItem("parade_user") != "null") {
        $scope.loggedin = true;
        $scope.loginUserData = JSON.parse(localStorage.getItem("parade_user"));


    }

    if (localStorage.getItem("parade_screen") > 0) {
        $scope.showcontent = localStorage.getItem("parade_screen");
    }

    $scope.changeContent = function (content) {

        // $scope.showcontent = content;	
        localStorage.setItem("parade_screen", content);
        switch (content) {
            case 1:
                $scope.getAllArticles();
                $scope.showcontent = content;                
                break;
            case 2:
                $scope.getMyArticles();
                $scope.showcontent = content;
                
                break;
            case 3:
                $scope.getMyRecievedRequests();
                $scope.showcontent = content;
                
                break;
            case 4:
                $scope.getMySentRequests();
                $scope.showcontent = content;
                
                break;
            case 5:

                $scope.showcontent = content;
                
                break;
            case 6:
                $scope.showcontent = content;
                
                break;
            default:
                $scope.showcontent = content;
            
        }


    }
    $scope.sentRequests = {};
    $scope.recievedRequests = {};

    $scope.getMyRecievedRequests = function () {

        $http.get($scope.baseurl + "paerreq/puser/" + $scope.loginUserData.userid, {})
            .then(function (response) {
                $scope.recievedRequests = response.data.data;
            }, function (response) {
                showSnackbar(response.data.error, "red");
            });

    }

    $scope.onFileSelect = function ($files) {
        $scope.uploadProgress = 0;
        $scope.selectedFile = $files;
        console.log("ada",$scope.selectedFile);
    };

    $scope.getMySentRequests = function () {


        $http.get($scope.baseurl + "paerreq/puser/request/" + $scope.loginUserData.userid, {})
            .then(function (response) {
                $scope.sentRequests = response.data.data;

            }, function (response) {
                showSnackbar(response.data.error, "red");
            });

    }

    $scope.myArticles = {};
    $scope.allArticles = {};
    $scope.getMyArticles = function () {

        $http.get($scope.baseurl + "article/puser/" + $scope.loginUserData.userid, {})
            .then(function (response) {
                $scope.myArticles = response.data.data;

            }, function (response) {
                showSnackbar(response.data.error, "red");
            });

    }

    $scope.getAllArticles = function () {


        $http.get($scope.baseurl + "article", {})
            .then(function (response) {
                $scope.allArticles = response.data.data;

            }, function (response) {
                showSnackbar(response.data.error, "red");
            });
        $scope.getAllTags();

    }

    $scope.approveReq = function (index, preqId) {
        $http.get($scope.baseurl + "paerreq/" + preqId + "/ACCEPTED", {})
            .then(function (response) {
                $scope.recievedRequests[index] = response.data.data;
                showSnackbar("Accepted Successfully", "green");
            }, function (response) {
                showSnackbar(response.data.error, "red");
            });


    }



    $scope.sendRequest = function (partid, comment) {
        var requestObj = {
            article: { partId: partid },
            requestedUser: { userid: $scope.loginUserData.userid },
            requestStatus: 'REQUESTED',
            comment: comment
        };
        $http.post($scope.baseurl + "paerreq", requestObj, {})
            .then(function (response) {
                showSnackbar("Request Sent Successfully", "blue")
                // document.getElementById('id01').style.display='none';
                // localStorage.setItem("parade_user",JSON.stringify(response.data.data));
                // $scope.loginUserData = response.data.data;
                // $scope.logindata = {};
            }, function (response) {
                //   document.getElementById('id01').style.display='none';
                showSnackbar(response.data.error, "red");


            });



    }


    $scope.rejectReq = function (index, preqId) {
        $http.get($scope.baseurl + "paerreq/" + preqId + "/REJECTED", {})
            .then(function (response) {
                $scope.recievedRequests[index] = response.data.data;
                showSnackbar("Rejected Successfully", "red");
            }, function (response) {
                showSnackbar(response.data.error, "red");
            });

    }
    $scope.getAllArticlesByTag = function (tag) {


        $http.get($scope.baseurl + "article/tag/" + tag, {})
            .then(function (response) {
                $scope.allArticles = {};
                $scope.allArticles = response.data.data;

            }, function (response) {
                showSnackbar(response.data.error, "red");
            });
        $scope.getAllTags();

    }
    $scope.alltags = {};
    $scope.getAllTags = function () {


        $http.get($scope.baseurl + "article/tags", {})
            .then(function (response) {
                $scope.alltags = response.data.data;
            }, function (response) {
                showSnackbar(response.data.error, "red");
            });

    }

    $scope.submitArticle = function(){
        console.log($scope.articleFile);

        var fd = new FormData();
        fd.append('file', $scope.articleFile);
        fd.append('date',{});
        $http.post($scope.baseurl+"article/", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined} 
        }) 
        .success(function(){ 
        }) 
        .error(function(){ 
        }); 
    }

    if ($scope.loggedin) {
        $scope.getMyRecievedRequests();
        $scope.getMySentRequests();
        $scope.getMyArticles();
    }
    $scope.getAllArticles();

    $scope.login = function () {
        console.log("Data:" + $scope.logindata);

        $http.post($scope.baseurl + "login", $scope.logindata, {
            "charset": "UTF-8",
            "accept": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": "true",
        })
            .then(function (response) {
                $scope.content = response.data;
                console.log("Response:", response);
                $scope.loggedin = true;
                showSnackbar("Successfully loggedin", "blue")
                document.getElementById('id01').style.display = 'none';
                localStorage.setItem("parade_user", JSON.stringify(response.data.data));
                $scope.loginUserData = response.data.data;
                $scope.logindata = {};
            }, function (response) {
                document.getElementById('id01').style.display = 'none';
                showSnackbar(response.data.error, "red");


            });
    }

    $scope.register = function () {
        $http.post($scope.baseurl + "register", $scope.regdata, {})
            .then(function (response) {

                document.getElementById('id02').style.display = 'none';
                showSnackbar("Registerd Successfully, Kindly Login", "green");
                $scope.regdata = {};
            }, function (response) {
                document.getElementById('id02').style.display = 'none';
                showSnackbar(response.data.error, "red");
            });
    }

    $scope.logout = function () {
        $scope.loggedin = false;
        localStorage.setItem("parade_user", null);
        localStorage.setItem("parade_screen", null);
        $scope.showcontent=1;
        showSnackbar("Successfully loggedout", "blue")
    }

});

