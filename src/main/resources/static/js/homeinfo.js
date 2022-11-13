window.onload = function () {

  if(sessionStorage.getItem("HOUSEID")==null || sessionStorage.getItem("HOUSEID") == ""){
    location.href = "../index.html"
  }


  var myModal = new Vue({
    el: "#gsc-modal",
    data: {
      uploadHouse: {
        housename: "",
        areas: "",
        address: "",
        description: "",
        min: "",
        max: "",
        url: "",
      },
    },
    methods: {
      uploadPic: function (e) {
        //
        let file = e.target.files[0];
        let form = new FormData();
        form.append("file", file);
        axios
          .post("http://localhost:8888/uploads/pic", form, {
            header: { "Content-Type": "multipart/form-data" },
          })
          .then((res) => {
            console.log(res.data);
            // this.url = res.data.data
            this.uploadHouse.url =
              "http://localhost:8888/image/" + res.data.data;
            console.log(this.url);
          });
      },
      saveOne: function () {
        axios
          .post("http://localhost:8888/houses", this.uploadHouse)
          .then((resp) => {
            console.log(resp.data);
            if (resp.data.code == 10011) {
              alert("保存成功！");
              this.uploadHouse.housename = "";
              this.uploadHouse.areas = "";
              this.uploadHouse.address = "";
              this.uploadHouse.description = "";
              this.uploadHouse.min = "";
              this.uploadHouse.max = "";
              this.uploadHouse.url = "";
              this.$refs.pathClear.value = "";
              location.reload()

            } else {
              alert("保存失败！");
            }
          });
      },
      //   canSave: function () {
      //     console.log(2222);
      //     if (
      //       this.uploadHouse.housename != "" &&
      //       this.uploadHouse.housename != null &&
      //       this.uploadHouse.areas != "" &&
      //       this.uploadHouse.areas != null &&
      //       this.uploadHouse.address != "" &&
      //       this.uploadHouse.address != null &&
      //       this.uploadHouse.min != "" &&
      //       this.uploadHouse.min != null &&
      //       this.uploadHouse.max != "" &&
      //       this.uploadHouse.max != null
      //     ) {
      //         console.log(111);
      //       this.saveDisabled = false;
      //     }
      //   }
    },
  });

  var houseInfo = new Vue({
    el: "#houseInfo",
    data: {
      id: "",
      info: {
        data: {},
        code: "",
        msg: "",
      },

      // 样式
      inputDis: true,
      istransparent: "transparent",
      isborder: 0,
      isshow: "none",
    },
    methods: {
      // 上传图片
      uploadPic: function (e) {
        let file = e.target.files[0];
        let form = new FormData();
        form.append("file", file);
        axios
          .post("http://localhost:8888/uploads/pic", form, {
            header: { "Content-Type": "multipart/form-data" },
          })
          .then((res) => {
            console.log(res.data);
            // this.url = res.data.data
            this.info.data.url = "http://localhost:8888/image/" + res.data.data;
            console.log(this.url);
          });
      },
      // 查询信息
      getById: function () {
        axios
          .get(
            "http://localhost:8888/houses/" + sessionStorage.getItem("HOUSEID")
          )
          .then((resp) => {
            this.info = resp.data;
            console.log(this.info);
          });
      },
      //修改按钮，使可以编辑
      updateInfo: function () {
        this.inputDis = false;
        this.istransparent = "";
        this.isborder = "";
        this.isshow = "block";
      },
      // 发送更改信息
      send: function () {
        this.inputDis = true;
        (this.istransparent = "transparent"), (this.isborder = 0);
        this.isshow = "none";
        axios
          .put("http://localhost:8888/houses/", this.info.data)
          .then((resp) => {
            if (resp.data.code == 10031) {
              alert("修改成功！");
              this.getById();
            } else {
              alert("修改失败！");
            }
          });
      },
      //删除数据
      deleted: function () {
        console.log(1111);
        axios
          .delete(
            "http://localhost:8888/houses/" + sessionStorage.getItem("HOUSEID")
          )
          .then((resp) => {
            if (resp.data.code == 10021) {
              alert("删除成功！")
              location.href = "../index.html";

            } else {
              alert("删除失败！")
              location.reload();

            }
          });
      },
    },

    //钩子函数
    mounted: function () {
      this.id = sessionStorage.getItem("HOUSEID");
      console.log(this.id);
      axios.get("http://localhost:8888/houses/" + this.id).then((resp) => {
        this.info = resp.data;
        console.log(this.info);
      });
    },
  });
};
