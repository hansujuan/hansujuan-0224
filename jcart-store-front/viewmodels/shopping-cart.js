var app = new Vue({
    el: '#app',
    data: {
        myShoppingCart: []

    },
    computed:{
        totalPrice(){
            var subTotalPrices=this.myShoppingCart.map(p=>{
                return p.unitPrice * p.discount * p.quantity;
            });
            var totalPrice = subTotalPrices.reduce((a, b) => a + b, 0);
            var totalPriceStr = totalPrice.toFixed(2);
            totalPrice = parseFloat(totalPriceStr);
            return totalPrice;
        }
    },
    mounted(){
        
        console.log('mounted shuchu');
        var myShoppingCartJson = localStorage['myShoppingCartJson'];
        // console.log(myShoppingCartJson);
        this.myShoppingCart = myShoppingCartJson ? JSON.parse(myShoppingCartJson) : [];
    },
    methods:{
        handleClearCart(){
            console.log('clear cart click');
            this.myShoppingCart = [];
            localStorage.removeItem('myShoppingCartJson');
        },
        handleDelete(index, row){
            console.log('delete');
            this.myShoppingCart.splice(index, 1);
            localStorage['myShoppingCartJson'] = JSON.stringify(this.myShoppingCart);
            this.$message.success('删除购物车成功');
        },
        handleUpdate(index,row){
            console.log('update quantity');
            localStorage['myShoppingCartJson'] = JSON.stringify(this.myShoppingCart);
            this.$message.success('修改购物车成功');
        }
    }
})