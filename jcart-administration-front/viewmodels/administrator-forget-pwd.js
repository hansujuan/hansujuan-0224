var app = new Vue({
    el: '#app',
    data: {
        email: ''
    },
    methods:{
        handleFindBackPwdClick(){
            console.log('get pwd');
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
                    alert('重置码已发送到邮箱');
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    }
})