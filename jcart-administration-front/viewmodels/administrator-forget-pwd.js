var app = new Vue({
    el: '#app',
    data: {
        email: ''
    },
    methods:{
        handleFindBackPwdClick(){
            console.log('get pwd');
            this.loading=true;
            this.getPwdResetCode();
        },
        getPwdResetCode(){
            axios.get('/administrator/getPwdResetCode', {
                params: {
                    email: this.email
                }
            })
                .then(function (response) {
                    console.log(response);
                    this.loading=false;
                    alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})