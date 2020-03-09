var app = new Vue({
    el: '#app',
    data: {
        returnId: '',
        orderId: '',
        orderTimestamp: '',
        customerId: '',
        customerName: '',
        mobile: '',
        email: '',
        status: '',
        action: '',
        productCode: '',
        productName: '',
        quantity: '',
        reason: '',
        opened: '',
        comment: '',
        createTimestamp: '',
        updateTimestamp: '',
        actions: [
            { value: 0, label: '退货' },
            { value: 1, label: '换货' },
            { value: 2, label: '修理' }
        ],
        selectedAction: ''
    },
    mounted() {
        console.log('mounted view');
        var url = new URL(location.href);
        this.returnId = url.searchParams.get('returnId');
        if (!this.returnId) {
            alert('returnId is not null');
            return;
        }
        this.getReturnById();
    },
    methods: {
        handleUpdateAction() {
            console.log('update Return');
            this.updateReturn();
        },
        updateReturn() {
            axios.post('/return/updateAction', {
                action:this.selectedAction,
                returnId:this.returnId
              })
              .then(function (response) {
                console.log(response);
                alert('更新成功');
                app.getReturnById();
              })
              .catch(function (error) {
                console.log(error);
                alert('更新失败。。。');
              });
        },
        getReturnById() {
            axios.get('/return/getById', {
                params: {
                    returnId: this.returnId
                }
            })
                .then(function (response) {
                    console.log(response);
                    var aReturn = response.data;
                    app.orderId = aReturn.orderId;
                    app.orderTimestamp = aReturn.orderTimestamp;
                    app.customerId = aReturn.customerId;
                    app.customerName = aReturn.customerName;
                    app.mobile = aReturn.mobile;
                    app.email = aReturn.email;
                    app.status = aReturn.status;
                    app.action = aReturn.action;
                    app.selectedAction = aReturn.action;
                    app.productCode = aReturn.productCode;
                    app.productName = aReturn.productName;
                    app.quantity = aReturn.quantity;
                    app.reason = aReturn.reason;
                    app.opened = aReturn.opened;
                    app.comment = aReturn.comment;
                    app.createTimestamp = aReturn.createTimestamp;
                    app.updateTimestamp = aReturn.updateTimestamp;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})