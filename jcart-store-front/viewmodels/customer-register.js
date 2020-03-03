var app = new Vue({
    el: '#app',
    data: {
        username:'',
        realName:'',
        mobile:'',
        email:'',
        password:'',
        repassword:'',
        newsSubscribed: false
    },
    methods:{
        handleRegisterClick(){
            if (this.password != this.repassword) {
                alert('密码不一致，请重新输入');
                return;
            }
            this.registerCustomer();
        },
        registerCustomer(){
            axios.post('/customer/register', {
                username: this.username,
                realName: this.realName,
                mobile: this.mobile,
                email: this.email,
                password: this.password,
                newsSubscribed: this.newsSubscribed
              })
              .then(function (response) {
                console.log(response);
                alert("注册成功");
              })
              .catch(function (error) {
                console.log(error);
                alert("注册失败。。。");
              });
        }
    }
})