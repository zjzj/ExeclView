$(document).ready(function () {
    var userGroup;
    var userName0;
    var userPhone0;
    var userNumber0;
    var userClass0;
    var upName;
    var upNum;
    var upClass;
    var upPhone;
    var userName1;
    var userNumber1;
    var userClass1;


    function validate_form() {
        //拿到需要校验的值 正则表达式
        userGroup = $("#userGroup").val();
        userName0 = $("#userName0").val();
        userPhone0 = $("#userPhone0").val();
        userNumber0 = $("#userNumber0").val();
        userClass0 = $("#userClass0").val();





        var regxingming = /^[\u4e00-\u9fa5]{2,4}/;
        if (!regxingming.test(userName0)) {
            // alert("负责人姓名格式出错：请输入2-4位汉字");
            show_validate_msg("#userName0", "error", "上传人姓名格式出错：请输入2-4位汉字")
            return false;
        }
        else {

        }

        // var reguserPhone0 = /^[1][3,4,5,7,8][0-9]{9}$/;
        // if (!reguserPhone0.test(userPhone0)) {
        //     // alert("请输入有效的手机号");
        //     show_validate_msg("#userPhone0", "error", "请输入有效的手机号");
        //     return false;
        // } else {
        //     show_validate_msg("#userPhone0", "success", "")
        //
        // }





        return true;

    }

    //显示错误信息
    function show_validate_msg(ele, status, msg) {

        // 清除当前元素的校验状态
        $(ele).parent().removeClass("has-success has-error");
        $(ele).next("span").text("");

        if ("success" === status) {

            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);

        } else if ("error" === status) {

            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);

        }
    }

    $("#userPhone0").keyup(function () {
        var userPhone0 = this.value;
        var reguserPhone0 = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!reguserPhone0.test(userPhone0)) {
            // alert("请输入有效的手机号");
            show_validate_msg("#userPhone0", "error", "请输入有效的手机号");
            return false;
        } else {
            show_validate_msg("#userPhone0", "success", "")

        }
    })







    $("#sub").click(function (eventObject) {
        //alert("报名已结束")
        //1.校验
        if (!validate_form()) {
            return false;
        }
        //验证是否重复
        if ($(this).attr("ajax-va") === "error") {
            return false;
        }

        //发送ajax请求
        //提交数据
        var users = [];
        var user1 = {};


        user1.upName = upName ;
        user1.upNum = upNum;
        user1.upClass = upClass;
        user1.upPhone = upPhone;

        //user1.userGroup = userGroup;
       // user1.userIdentity="参赛队长";
        users[0] = user1;

        console.log(user1)

        // if (userName1 != '' && userNumber1 != '' && userClass1 != '') {
        //     var user2 = {};
        //     user2.userName = userName1;
        //     user2.userNumber = userNumber1;
        //     user2.userClass = userClass1;
        //     user2.userGroup = user1.userGroup;
        //     users[1] = user2;
        //     console.log(user2);
        // }
        //
        // if (userName2 != '' && userNumber2 != '' && userNumber2 != '') {
        //     var user3 = {};
        //     user3.userName = userName2;
        //     user3.userNumber = userNumber2;
        //     user3.userClass = userClass2;
        //     user3.userGroup = user1.userGroup;
        //     users[2] = user3;
        //     console.log(user3);
        // }
        var data = {upName: upName,upClass: upClass,upNum: upNum,upPhone: upPhone}
        $.ajax({
            url: "http://10.2.132.171:8081/ExeclView/file/upload",
            type: "POST",
            data: data,
            contentType: 'application/json; charset=UTF-8',
            success: function (result) {
                // alert(result.msg);
                // layer.alert('保存成功', {icon: 1});
                if (result.code === 1) {
                    alert('保存成功');



                    layer.alert('保存成功', function(index){
                        //do something

                        layer.close(index);
                        $("#mainform")[0].reset();
                        window.location.reload();
                    });

                    // $("#mainform")[0].reset();
                    // window.location.reload();
                // } else {
                //     if (undefined != data.extend.userGroup) {
                //         show_validate_msg("#userGroup", "error", data.extend.userGroup);
                //     }
                //     if (undefined != data.extend.userPhone) {
                //         show_validate_msg("#userPhone0", "error", data.extend.userPhone);
                //     }
                }
            }
        })

    })
});
