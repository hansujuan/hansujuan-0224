var app = new Vue({
    el: '#app',
    data: {
        email: '',
        resetCode: '',
        newPwd: '',
        reNewPwd: ''
    },
    methods:{
        handleResetPwdClick(){
            console.log('update pwd');

            if(this.newPwd !== this.reNewPwd){
                alert('密码不一致');
                return;
            }

            
            this.resetAdministratorPwd();
        },
        resetAdministratorPwd(){
            axios.post('/administrator/resetPwd', {
                email: this.email,
                resetCode: this.resetCode,
                newPwd: this.newPwd
            })
                .then(function (response) {
                    console.log(response);
                    alert('重置密码成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('密码重置失败。。。');
                });
        }
    }
})