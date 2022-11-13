window.onload = function () {
  var vm = new Vue({
    el: "#myinfo",
    data: {
      houses: {
        housename: "",
        areas: "",
        min: "",
        max: "",
      },
      pages: {
        code: "",
        data: {},
        msg: "",
      },
      pageCount: 1,
      preDisabled: false,
      nextDisabled: false,

      //
    },
    methods: {
      save: function (count, houses) {
        axios
          .post("http://localhost:8888/houses/" + count, houses)
          .then((resp) => {
            this.pages = resp.data;
            // console.log("------");
            // console.log(this.pages);
          });
      },
      prePage: function () {
        if (this.pageCount == 1) {
          this.preDisabled = true;
        } else {
          this.nextDisabled = false;
          this.pageCount--;
          this.save(this.pageCount, this.houses);
        }
      },
      nextPage: function () {
        console.log(this.pages.data.pages);
        if (this.pageCount == this.pages.data.pages) {
          this.nextDisabled = true;
        } else {
          this.preDisabled = false;
          this.pageCount++;
          this.save(this.pageCount, this.houses);
        }
      },
      firstPage: function () {
        this.nextDisabled = false;
        this.pageCount = 1;
        this.save(1, this.houses);
      },
      endPage: function () {
        this.preDisabled = false;
        this.pageCount = this.pages.data.pages;
        this.save(this.pages.data.pages, this.houses);
      },
      
      transmitSession:function(index){
        // console.log(index);
        // console.log(this.pages.data.records[index].id);
        sessionStorage.setItem('HOUSEID', this.pages.data.records[index].id)
      }
    },
    mounted: function () {
      axios
        .post("http://localhost:8888/houses/" + this.pageCount, this.houses)
        .then((resp) => {
          this.pages = resp.data;
          // console.log("------");
          // console.log(this.pages);
        });
    },
  });

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
      }
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
            this.uploadHouse.url = "http://localhost:8888/image/" + res.data.data;
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
};
