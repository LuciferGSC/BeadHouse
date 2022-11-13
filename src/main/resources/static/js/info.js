window.onload = function(){
    var vm = new Vue({
        el: ".gsc-info",
        data: {
            disabled: true,
            user: {
                data: {
                    id: '',
                    telephone: '',
                    userpwd: '',
                    emaile: '',
                    sex: '',
                    username: '',
                    deleted: ''
                },
                code: '',
                msg: ""
            }
        },
        methods: {
            get: function () {
                this.user.id = localStorage.getItem("id");
                axios.get("http://localhost:8888/users/" + this.user.id, this.user).then((resp) => {
                    this.user = resp.data
                    // console.log(this.user);
                })
            },
            change: function () {
                this.disabled = false
                console.log(this.disabled);
            },
            update: function () {
                axios.put("http://localhost:8888/users", this.user.data).then((resp) => {
                    this.user = resp.data;
                    /*console.log('-------');
                    console.log(resp.data); */
                    this.get()
                    alert("修改成功")
                }).catch(err=>{
                    alert('修改失败')
                    this.get();
                })

                this.disabled = true;
            }
        },
        mounted: function () {

            if (localStorage.getItem("id") == null || localStorage.getItem("id") == '') {
                console.log(this.user);
                location.href = './login.html'
            } else {
                this.user.id = localStorage.getItem("id");
                axios.get("http://localhost:8888/users/" + this.user.id, this.user).then((resp) => {
                    this.user = resp.data
                    // console.log(this.user);
                })
            }
        }
    })
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
}