$(document).ready(function () {
  $("#sub1").click(function (eventObject) {
    var formData = new FormData();
    formData.append("files", $("#filename")[0].files[0]);
    $.ajax({
      url: "/ExeclView/file2/upload2",
      type:'post',
      data:formData,
      contentType: false,
      processData: false,
      success: function (data) {
        if (data.code === 1) {
          alert(data.msg);
        } else alert(data.msg);
      }
    })
  })
});


