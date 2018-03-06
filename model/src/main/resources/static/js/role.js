/**
 * path
 */
layui.config({
	base : path+"/js/modules/"
}).use(['form','element','layer','jquery','tree','utilsHelper'],function(){
  var layer = layui.layer
  ,$ = layui.jquery; 
  var utilsHelper = layui.utilsHelper;
  //alert(utilsHelper.coverValue("1",4,"0"));
  //alert(utilsHelper.getJsCurrentDateTime());
  function getTreeData(obj){
	  for (x in obj){
		  console.log(x+":"+obj[x]);
		  if (x == "roleId"){
			  
		  }
		  name: '常用文件夹'
	            ,id: 'x'
	            ,alias: 'changyong'
	            	roleId:bus
	            	parentRoleId:null
	            	roleName:业务管理组
	            	createUserId:null
	            	createTime:null
	            	roleIndex:2
	            	children:[object Object],[object Object]
	  }
  };
  $.ajax({
      //提交数据的类型 POST GET
      type:"post",
      //提交的网址
      url:path+"/role/getRoles/",
      success:function(data){
      	if (data.resultData.length > 0){
      		for (var _index=0;_index<data.resultData.length;_index++){
      			getTreeData(data.resultData[_index]);
      		}
      	}
      },
      complete: function(XMLHttpRequest, textStatus){
    	  //layer.msg(textStatus);
      },
      error: function(){
      	layer.msg("发生错误请联系管理员!");
      }         
   });
 
 
  
});