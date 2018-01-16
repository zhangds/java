/**
 * 
 */
layui.config({
	base : "js/"
}).use(['element','form','layer','jquery'],function(){
	var form = layui.form();
	var element = layui.element();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
	var index = layer.load(2, {
	  shade: [0.6,'#fff'] //0.1透明度的白色背景
	});
	$ = layui.jquery;
	form.verify({
		loginId : [/^[A-Za-z0-9_.]+$/,"只能输入英文、数字、.和_!"],
		loginIdOnlyOne : function (value){
			var _check_LoginId_count = 100;
			$.ajax({
				url : path + '/user/getUserIdCount/'+value,
				type : 'POST',
				async : false,
				timeout : 2000,
				dataType : 'json',
				success : function(data, textStatus, jqXHR) {
					_check_LoginId_count = data;
				}
			});
			if (editUser == null && _check_LoginId_count > 0){
				return "用户ID重复,请填写新ID!";
			}
			if (editUser != null && _check_LoginId_count != 1){
				return "用户ID有问题,不能修改!";
			}
		}
	});
	form.on('submit(userInfoSave)', function(data) {
		var option = (editUser != null?'edit':'add');
		data.field.option = option;
		//console.log(data.field);
		$.ajax({
			url : path + '/user/userAdd',
			type : 'POST',
			async : true,
			data : data.field,
			timeout : 2000,
			dataType : 'json',
			success : function(data, textStatus, jqXHR) {
				if (data.flag == true) {
					var index = layer.getFrameIndex(window.name);
					if (index != null){
						layer.close(index);
					}
					layer.msg(data.msg, {icon : 1});
				} else {
					layer.msg(data.msg, {icon : 5});
				}
			}
		});
		return false;
	});
	userInfo = {
		init : function (){
			userInfo.initElement();
			form.render();
		},
		initElement : function (){
			if (editUser != null){
				$(".userInfo form #loginId").val(editUser.loginId);
				$(".userInfo form #loginId").attr("disabled",true);
				$(".userInfo form #loginName").val(editUser.loginName);
				$(".userInfo form #mobile").val(editUser.mobile);
				$(".userInfo form #email").val(editUser.email);
				$(".userInfo form #loginStatus[value='"+editUser.loginStatus+"']").attr("checked",true);
				$(".userInfo form #sex[value='"+editUser.sex+"']").attr("checked",true);
			}
			$(".userInfo form #createUserId").val(currentUserId);
		}
	};
	userInfo.init();
	layer.close(index);
});