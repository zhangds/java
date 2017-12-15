layui.config({
	base : "../../js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;

    //添加验证规则
    form.verify({
        oldPwd : function(value, item){
        	var _oldPwd=null;
        	$.ajax({
		                type:"post",
		                url:"../encrypt",
		                data:{encryptString:value},
		                async: false,
		                success: function (result) {
		                	_oldPwd = result;  
		                }
        			});
            if(oldPwd != _oldPwd){
                return "旧密码输入错误，请重新输入！";
            }
        },
        newPwd : function(value, item){
            if(value.length <= 3){
                return "密码长度不能小于3位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    //修改密码
    form.on("submit(onChangePwd)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
//        setTimeout(function(){
//            layer.close(index);
//            layer.msg("密码修改成功！");
//            $(".pwd").val('');
//        },2000);
    	//console.log(JSON.stringify(data.field))
    	$.ajax({
            type:"post",
            url:"../user/actChangePwd",
            data:data.field,
            success: function (data) {
            	layer.close(index);
            	layer.confirm(data.msg, {
            		  	btn: ['确定'] //按钮
            		}, function(){
            			window.parent.location.href="./login";
            		});
            }
		});
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

});