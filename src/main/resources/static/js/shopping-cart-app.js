let app = angular.module("shopping-cart-app", []);
 app.controller("shopping-cart-ctrl", function ($scope, $http) {

  $scope.cart = {
    items: [],
    add(id) {

      var item = this.items.find((item) => item.id === id);
      if (item) {
        item.qty++;
        this.saveToLocalStorage();
      } else {
        $http.get(`/rest/products/${id}`).then((resp) => {
          resp.data.qty = 1;
          this.items.push(resp.data);
          this.saveToLocalStorage();
        })
      }
    },
      remove(id){ // xóa sản phẩm khỏi giỏ hàng
        var item = this.items.find((item) => item.id === id);
        this.items.splice(item,1);
        this.saveToLocalStorage();

      },
      clear() { // xóa toàn bộ sản phẩm khỏi gior hàng
          this.items = [];
          this.saveToLocalStorage();
      },

    // tính số lượng các mặt hàng tron giỏ
     get count(){
      return this.items.map(item => item.qty).reduce((total,qty)=> total += qty,0);
     },
    // tổng tiền mặt các mặt hàng trong giỏ
    get amount(){
      return this.items.map(item => item.qty * item.price).reduce((total,qty)=> total += qty,0);
    },
    // lưu giỏ hàng vào local storage
    saveToLocalStorage() {
      var json = JSON.stringify(angular.copy(this.items));
      localStorage.setItem("cart", json);
    },
      loadFromLocalStorege(){
        var json = localStorage.getItem("cart");
       this.items = json ? JSON.parse(json) :[];

      }
  }

  $scope.cart.loadFromLocalStorege();
//đặt hàng
     $scope.order = {
         // get account(){
         //     return {username: $auth.user.username}
         // },
         createDate: new Date(),
         address: "",
         status: "PENDING",
         account: {username: $("#username").text()},
         get orderDetails(){
             return $scope.cart.items.map(item => {
                 return {
                     product:{id: item.id},
                     price: item.price,
                     quantity: item.qty
                 }
             });
         },
         purchase(){

             var order = angular.copy(this);
             // Thực hiện đặt hàng
             $http.post("/rest/orders", order).then(resp => {
                 alert("Đặt hàng thành công!");
                 $scope.cart.clear();
                 location.href = "/order/detail/" + resp.data.id;
             }).catch(error => {
                 alert("Đặt hàng lỗi!")
                 console.log(error)
             })
         },
         update(){

         }
     }

})

