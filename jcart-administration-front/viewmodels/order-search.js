var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1,
        orderId:'',
        customerName:'',
        totalPrice:'',
        selectedStatus:'',
        statuses:[
            { value: 0, label: '待处理' },
            { value: 1, label: '处理中' },
            { value: 2, label: '待发货' },
            { value: 3, label: '已发货' },
            { value: 4, label: '待签收' },
            { value: 5, label: '已签收' },
            { value: 6, label: '待支付' },
            { value: 7, label: '已支付' },
            { value: 8, label: '取消' },
            { value: 9, label: '拒绝' },
            { value: 10, label: '完成' },
            { value: 11, label: '待评价' },
            { value: 12, label: '已评价' }
        ],
        startTime:'',
        endTime:''
    },
    mounted(){
        console.log('order search');
        this.orderSearchList();
    },
    methods:{
        handleSearchClick(){
            this.pageNum=1;
            this.orderSearchList();
        },
        handleClearClick(){
            this.orderId = '';
            this.customerName = '';
            this.selectedStatus = '';
            this.totalPrice = '';
            this.startTime = '';
            this.endTime = '';
            this.orderSearchList();
        },
        handlePageChange(val){
            console.log('pageNum change');
            this.pageNum=val;
            this.orderSearchList();
        },
        orderSearchList(){
            axios.get('/order/search', {
                params: {
                    pageNum: this.pageNum,
                    orderId: this.orderId,
                    customerName: this.customerName,
                    status: this.selectedStatus,
                    totalPrice: this.totalPrice,
                    startTimestamp: this.startTime ? this.startTime.getTime() : '',
                    endTimestamp: this.endTime ? this.endTime.getTime() : ''
                }
            })
                .then(function (response) {
                    console.log(response);
                    app.pageInfo = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})