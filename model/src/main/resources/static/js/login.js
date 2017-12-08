layui.config({
	base : "js/"
}).use(['jquery','form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
//	$(window).resize(function(){
//		if($(".video-player").width() > $(window).width()){
//			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
//		}else{
//			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
//		}
//	}).resize();
	
	//登录按钮事件
	form.on("submit(login)",function(data){
		//window.location.href = "../../index.html";
		//alert(data.field.password + ":"+data.field.username);
		//alert(JSON.stringify(data.field));
		$.ajax({
            //提交数据的类型 POST GET
            type:"post",
            //提交的网址
            url:"../user/login/"+data.field.username+"/"+data.field.password,
            //提交的数据
            //data:{Name:"sanmao",Password:"sanmaoword"},
            //返回数据的格式
            //datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            //beforeSend:function(){$("#video_mask").html("logining");},
            //成功返回之后调用的函数             
            success:function(data){
            	//$("#msg").html(decodeURI(data));
            	//{"msg":"登录成功!","redirect":"index","success":true}
            	layer.msg(data.msg);
            	if (data.success){
            		location.href=data.redirect;
            	}
            },
            //调用执行后调用的函数
            complete: function(XMLHttpRequest, textStatus){
               //alert(XMLHttpRequest.responseText);
               //alert(textStatus);
                //HideLoading();
            },
            //调用出错执行的函数
            error: function(){
                //请求出错处理
            	alert(1);
            }         
         });
		return false;
	});
	
})
