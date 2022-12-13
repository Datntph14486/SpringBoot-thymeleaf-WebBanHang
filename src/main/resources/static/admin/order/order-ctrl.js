app.controller("order-ctrl",function ($scope,$http) {
    $scope.orderPending = [];
    $scope.orderConfirmed = [];
    $scope.orderDelivering = [];
    $scope.orderSuccessful = [];
    $scope.orders =[];
    $scope.orderDetails={};

    $scope.initialize = function() {

        $http.get('/rest/orders/pending').then(resp => {
        $scope.orderPending = resp.data;
    })
        $http.get('/rest/orders/confirmed').then(resp => {
            $scope.orderConfirmed = resp.data;
        })
        $http.get('/rest/orders/delivering').then(resp => {
            $scope.orderDelivering = resp.data;
        })
        $http.get('/rest/orders/successful').then(resp => {
            $scope.orderSuccessful = resp.data;
        })
        $http.get('/rest/orders/').then(resp => {
                $scope.orders = resp.data;
            })
    }
    $scope.show_order_details = function(id) {
        $http.get(`/rest/orders/orderdetail/${id}`).then(resp =>{
            $scope.orderDetails = resp.data;
        })
    }
    $scope.orderStatus_changed = function (id){
        var item = $scope.orders.find(or => or.id === id);

        if(item){
          if (item.status === 'PENDING'){
              item.status= 'CONFIRMED';
              let index = $scope.orderPending.findIndex(a => a.id == item.id);
              $scope.orderPending.splice(index, 1);
              $scope.orderConfirmed.push(item);
          }else if (item.status === 'CONFIRMED'){
              item.status= 'DELIVERING';
              let index2 = $scope.orderConfirmed.findIndex(a => a.id == item.id);
              $scope.orderConfirmed.splice(index2, 1);
              $scope.orderDelivering.push(item);
          }else if(item.status === 'DELIVERING'){
              item.status= 'SUCCESSFUL';
              let index3= $scope.orderDelivering.findIndex(a => a.id == item.id);
              $scope.orderDelivering.splice(index3, 1);
              $scope.orderSuccessful.push(item);

          }
            $scope.updateOrder(item);

        }

    }
    $scope.updateOrder = function(order){
        $http.put(`/rest/orders/update_status/${order.id}`,order).then(resp => {
            swal("congratulations!", "Cập nhật trạng thái thành công!", "success");
        }).catch(err => {
            swal("congratulations!", "Cập nhật trạng thái thất bại!", "success");
        })

    }


    $scope.initialize();
    }
)