window.onload = function () {
  var vm = new Vue({
    el: ".login-box",
    data: {
      title: [
        "两次密码不一致",
        "ID或密码为空",
        "密码长度应该位4-16位",
        "用户ID错误",
      ],
      // 绑定表单元素
      user: {
        id: "",
        userpwd: "",
      },
      confirmpwd: "",
      // 接收响应值
      info: {
        code: null,
        data: {},
        msg: null,
      },
      // 判断情况
      warn: "",
    },
    methods: {
      repassword: function () {
        console.log("hello");
        if (
          //判断是否为空
          this.user.id.trim() == null ||
          this.user.id.trim() == "" ||
          this.user.userpwd.trim() == null ||
          this.user.userpwd.trim() == "" ||
          this.confirmpwd.trim() == null ||
          this.confirmpwd.trim() == ""
        ) {
          this.warn = "00020";
          console.log(this.warn);
        }

        //两次密码不一致
        else if (this.user.userpwd.trim() != this.confirmpwd.trim()) {
          this.warn = "00010";
        } //判断密码长度
        else if (
          this.user.userpwd.trim().length < 6 ||
          this.user.userpwd.trim().length > 16
        ) {
          this.warn = "00040";
        }
        // 给后端发送消息
        else {
          console.log(this.user);
          axios
            .put("http://localhost:8888/users", this.user)
            .then((response) => {
              this.info = response.data;
              this.warn = this.info.code;
              console.log();
              if (response.data.code == 10031) {
                //注册成功
                alert("更改成功");
                setTimeout(() => {
                  location.href = "../pages/login.html";
                }, 700);
              }
              // 更改失败
              else if (response.data.code == 10030) {
                alert("ID错误");
              } else {
                alert("系统错误.请稍后再试");
              }
            });
        }
        setTimeout(() => {
          this.warn = "";
        }, 1000);
      },
    },
  });
};
