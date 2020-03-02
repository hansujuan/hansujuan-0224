var app = new Vue({
    el: '#app',
    data: {
        administratorId: '',
        username: '',
        realName: '',
        password: '',
        email: '',
        avatarUrl: '',
        selectedStatus: '',
        statuses: [
            { value: 0, label: '禁用' },
            { value: 1, label: '启用' }
        ]
    },
    mounted() {
        var url = new URL(location.href);
        this.administratorId = url.searchParams.get("administratorId");
        if (!this.administratorId) {
            alert('administratorId is not null');
            return;
        }
        this.getAdministratorById();

    },
    methods: {
        handleUpdateClick(){
            console.log('update click');
            this.updateAdministrator();
        },
        updateAdministrator(){
            axios.post('/administrator/update', {
                administratorId: this.administratorId,
                password: this.password,
                realName: this.realName,
                email: this.email,
                avatarUrl: this.avatarUrl,
                status: this.selectedStatus
              })
              .then(function (response) {
                console.log(response);
                alert("修改成功");
              })
              .catch(function (error) {
                console.log(error);
                alert("修改失败");
              });
        },
        getAdministratorById() {
            axios.get('/administrator/getById', {
                params: {
                  administratorId:this.administratorId
                }
              })
              .then(function (response) {
                console.log(response);
                var administrator = response.data;
                app.username = administrator.username;
                app.realName = administrator.realName;
                app.email = administrator.email;
                app.avatarUrl = administrator.avatarUrl;
                app.selectedStatus = administrator.status;
              })
              .catch(function (error) {
                console.log(error);
              });
        }
    }
})