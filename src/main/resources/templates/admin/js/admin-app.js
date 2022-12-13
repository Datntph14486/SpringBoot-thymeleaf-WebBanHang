app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
        .when("/product",{
            templateUrl: "/template/admin/product/index.html",
            controller: "product-ctrl"
        })
       .when("/authorize",{
            templateUrl: "/template/admin/authority/index.html",
           controller:"authority-ctrl"
       })
      .when("/unauthorize",{
          templateUrl:"/template/admin/authority/unauthorized.html",
          controller:"authority-ctrl"
      })
        .otherwise({
            template:"<h1 class='text-center'> FPT Polytechnic Administration </h1>"
        })
})
