var app = new Vue({
    el: '#app',
    data: {
        orderId: '',
        orderTime: '',
        customerName: '',
        mobile: '',
        email: '',
        productId: '',
        productCode: '',
        productName: '',
        quantity: '',
        reason: '',
        comment: '',
        opened: '',
    },
    computed:{
        orderTimestamp(){
            return this.orderTime.getTime();
        }
    },
    mounted(){
        console.log('orderId and productId');

        var url = new URL(location.href);
        this.orderId = url.searchParams.get("orderId");
        if (!this.orderId) {
            alert('orderId is not null');
            return;
        }

        this.productId = url.searchParams.get("productId");
        if (!this.productId) {
            alert('productId is not null');
            return;
        }
        this.getOrderById();
    },
    methods:{
        handleApplyClick(){
            console.log('shenqing view');
            this.applyReturn();
        },
        getOrderById(){
            axios.get('/order/getById', {
                params: {
                    orderId: this.orderId
                }
              })
              .then(function (response) {
                console.log(response);
                var order = response.data;
                    app.orderTime = new Date(order.createTimestamp);
                    var orderProducts = order.orderProducts;
                    var returnProduct = orderProducts.find(p => p.productId == app.productId);
                    app.productCode = returnProduct.productCode;
                    app.productName = returnProduct.productName;
                    app.quantity = returnProduct.quantity;
              })
              .catch(function (error) {
                console.log(error);
              });
        },
        applyReturn(){
            axios.post('/return/apply', {
                orderId: this.orderId,
                orderTimestamp: this.orderTimestamp,
                customerName: this.customerName,
                mobile: this.mobile,
                email: this.email,
                productCode: this.productCode,
                productName: this.productName,
                quantity: this.quantity,
                reason: this.reason,
                opened: this.opened,
                comment: this.comment
              })
              .then(function (response) {
                console.log(response);
                alert('申请成功，等待商家处理');
              })
              .catch(function (error) {
                console.log(error);
                alert('申请失败，请联系商家');
              });
        }
    }
})