app.controller("product-ctrl", function ($scope, $http) {

    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];

    $scope.initialize = function () {
        //load products
        $http.get("/rest/products/list").then(resp => {

            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate);
            })
        });

        //load category
        $http.get("/rest/category/list").then(resp => {
            $scope.cates = resp.data;

        });
    }

    $scope.initialize();

    $scope.reset = function () {
        $scope.form = {
            createDate: new Date(),
            image: "cloud-upload.jpg",
            category:{
                id: '1000',
            },
            available: false,

        }
    }
    $scope.reset();

    $scope.edit = function (item) {
        item.createDate = new Date(item.createDate);
        $scope.form = angular.copy(item);
        $("#home-tab").tab('show');

    }

    $scope.delete = function (item) {
        var item = $scope.items.find(or => or.id === item.id);
        if (item.available === false) {
            item.available = true;
        } else if (item.available === true) {
            item.available = false;
        }
        $http.put(`/rest/products/del/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items[index] = item;
            $scope.reset();
            if (item.available === true) {
                swal("congratulations!", "Phục hồi sản phẩm thành công!", "success");

            } else {
                swal("congratulations!", "Xóa sản phẩm thành công!", "success");


            }

        }).catch(err => {
            alert("Xóa sản phẩm thất bại!");
            console.log("Eror", err);
        })

    }

    $scope.create = function () {
        var name = document.getElementById('name').value
        var price = document.getElementById('price').value
        var category = document.getElementById('category').value
        var date = document.getElementById('createDate').value
        var yes = document.getElementById('available').value
        var no = document.getElementById('available1').value


        if (no === "" || yes === "" || date === "" || category === "" || price === "" || name === "") {
            swal("congratulations!", "Dữ liệu không được để trống!", "success");
        } else {
            var item = angular.copy($scope.form);
            $http.post("/rest/products", item).then(resp => {
                resp.data.createDate = new Date(resp.data.createDate);
                $scope.items.push(resp.data);
                $scope.reset();
                swal("congratulations!", "Thêm sản phẩm thành công!", "success");

            }).catch(err => {
                swal("congratulations!", "Thêm sản phẩm không thành công!", "success");
                console.log("Eror", err);
            })
        }

    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items[index] = item;
            swal("congratulations!", "Cập nhật sản phẩm thành công!", "success");
        }).catch(err => {
            swal("congratulations!", "Cập nhật sản phẩm thất bại!", "success");
            console.log("Eror", err);
        })
    }

    $scope.imageChanged = function (files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/image', data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            swal("congratulations!", "Lỗi upload hình ảnh!", "success");
            console.log("Error", error);
        })
    }

    $scope.pager = {
        page: 0,
        size: 6,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size)
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.last();
            }
        },
        next() {
            this.page++;
            if (this.page >= this.count) {
                this.first();
            }

        },
        last() {
            this.page = this.count - 1;
        }

    }

})