app = angular.module("admin-app", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
        .when("/product",{
            templateUrl: "/admin/product/index.html",
             controller: "product-ctrl"
        })
       .when("/authorize",{
            templateUrl: "/admin/authority/index.html",
            controller:"authority-ctrl"
       })
      .when("/unauthorize",{
          templateUrl:"/admin/authority/unauthorized.html",
        //   controller:"authority-ctrl"
      })
        .when("/order",{
            templateUrl: "/admin/order/index.html",
            controller : "order-ctrl"
        })
        .otherwise({
            template:"<h1 class='text-center'> FPT Polytechnic Administration </h1>"
        })
})
