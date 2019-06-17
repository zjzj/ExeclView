$(document).ready(function () {
    $.ajax({
      url:'http://10.2.132.171:8435/listRecords/101',
      type:'GET',
      datatype:'json',
      contentType: 'application/json; charset=UTF-8',
      error: function(XMLHttpRequest, textStatus, errorThrown) {
        alert(XMLHttpRequest.status);
        alert(XMLHttpRequest.readyState);
        alert(textStatus);
        //alert("请求出错！");
      },
      success:function(result){
        for (var i = 0; i < result.length; i++) {
          console.log(result[i]);
        }


        createShowingTable(result);

      }
    })

  }
)
function createShowingTable( result) {

  for (var i = 0; i < result.rows.length; i++) {
    console.log(result[i]);
    var upName=$("<td></td>").append(result.rows[i].upName).css({ "text-align": "center","vertical-align":"middle"});
    var upNum=$("<td></td>").append(result.rows[i].upNum).css({ "text-align": "center","vertical-align":"middle" });


    var classNum=$("<td></td>").append(result.rows[i].classNum).css({ "text-align": "center","vertical-align":"middle" });
    var upTime=$("<td></td>").append(result.rows[i].upTime).css({ "text-align": "center","vertical-align":"middle" });
    var userList=result.userList;
    var users=$("<td></td>").css({ "text-align": "center","vertical-align":"middle" });
    $.each(userList,function (index,x){console.log(x);
      users = users.append(x.upName + " : " + x.upClass + "<br>");
    })

    $("<tr></tr>").append(upName).append(upNum).append(classNum).append(upTime)
      .appendTo("#emps_table tbody");



  }

}
