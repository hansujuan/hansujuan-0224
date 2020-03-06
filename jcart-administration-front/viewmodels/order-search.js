var app = new Vue({
    el: '#app',
    data: {
        pageInfo:'',
        pageNum:1
    },
    mounted(){
        console.log('order search');
        this.orderSearchList();
    },
    methods:{
        handlePageChange(val){
            console.log('pageNum change');
            this.pageNum=val;
            this.orderSearchList();
        },
        orderSearchList(){
            axios.get('/order/search', {
                params: {
                    pageNum: this.pageNum
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