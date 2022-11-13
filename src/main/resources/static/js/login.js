window.onload = function () {
  var vm = new Vue({
    el: ".login-box",
    data: {
      // 绑定表单元素
      user: {
        telephone: "",
        userpwd: "",
      },
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
      login: function () {
        if (
          this.user.telephone.trim() == null ||
          this.user.telephone.trim() == "" ||
          this.user.userpwd.trim() == null ||
          this.user.userpwd.trim() == ""
        ) {
          this.warn = 00000;
        } else {
          axios
            .post("http://localhost:8888/check", this.user)
            .then((response) => {
              
              this.info = response.data;
              this.warn = this.info.code;

              if (response.data.code == 10001) {
                //登陆成功
                localStorage.setItem("id", "" + this.info.data.id);
                location.href = "../pages/info.html";
              }

              // console.log(this.info);
            });
        }
        setTimeout(() => {
          this.warn = "";
        }, 700);
      },
    },
    /* data() {
                  return {
                      info: {
                          code: null,
                          data: null,
                          msg: null
                      }
                  }
              },
              mounted() {
                  axios.get("http://localhost:8888/users")
                      .then((response) => {
                          console.log(response);
                          this.info = response.data
                      })
              } */
  });
};
