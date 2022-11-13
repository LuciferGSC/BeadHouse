window.onload = function () {
  var vm = new Vue({
    el: ".login-box",
    data: {
      title: [
        "两次密码不一致",
        "账户或密码为空",
        "电话号码格式不正确",
        "密码长度应该位4-16位",
        "账户已经存在",
        "注册成功",
      ],
      // 绑定表单元素
      user: {
        telephone: "",
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
      regist: function () {
        //为空
        if (
          this.user.telephone.trim() == null ||
          this.user.telephone.trim() == "" ||
          this.user.userpwd.trim() == null ||
          this.user.userpwd.trim() == "" ||
          this.confirmpwd.trim() == null ||
          this.confirmpwd.trim() == ""
        ) {
          this.warn = "00020";
          console.log(this.warn);
        } // 判断电话号码是否正确
        else if (
          !/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(
            this.user.telephone.trim() + ""
          )
        ) {
          console.log(this.user.telephone);
          this.warn = "00030";
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
          axios
            .post("http://localhost:8888/users", this.user)
            .then((response) => {
              this.info = response.data;
              this.warn = this.info.code;
              console.log();
              if (response.data.code == 10011) {
                //注册成功
                alert("注册成功");
                setTimeout(() => {
                  location.href = "../pages/login.html";
                }, 700);
              }
              // 账户已存在
              else if (response.data.code == 10010) {
                alert("账户已存在");
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
