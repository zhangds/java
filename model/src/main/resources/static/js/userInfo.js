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
		console.log(data.field);
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
//	form.verify({
//		tableName : [/^[A-Za-z0-9_.]+$/,"只能输入英文、数字、.和_!"],
//		tableNamed : [/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]){1,50}$/,"只能输入英文、汉子和数字,且长度小于50!"],
//		tableRemarks : [/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9_-]|[\r\n]|[.]|[ ]){1,500}$/,"只能输入英文、汉子、数字、_、-和.,且长度小于500!"],
//		versions : [/^[0-9.]+$/,"只能输入数字和.!"],
//		fields : [/^[0-9]+$/,"只能输入数字!"],
//		records : [/^[0-9]+$/,"只能输入数字!"]
//		});
//	
//	form.on('submit(metaTableInfoSave)', function(data) {
//		if (data.field.states){
//			data.field.states = 'Y';
//		}else{
//			data.field.states = 'N';
//		}
//		$.ajax({
//			url : path + '/meta/Table/save/tableInfo',
//			type : 'POST',
//			async : true,
//			data : data.field,
//			timeout : 2000,
//			dataType : 'json',
//			success : function(data, textStatus, jqXHR) {
//				if (data.flag == 'true') {
//					var index = layer.getFrameIndex(window.name);
//					if (index != null){
//						layer.close(index);
//					}
//					layer.msg(data.msg, {icon : 1});
//				} else {
//					layer.msg(data.msg, {icon : 5});
//				}
//			}
//		});
//		return false;
//	});
//	
//	metaTableInfo = {
//			init : function (){
//				//metaTableInfo.getSelectOpt();
//				metaTableInfo.initElement();
//				form.render();
//			},initElement : function (){
//				$(".metaTableInfo #versions").val(1);
//				$(".metaTableInfo #fields").val(0);
//				$(".metaTableInfo #records").val(0);
//				$(".metaTableInfo #tableRemarks").val("");
//				
//				if (oneData){
//					metaTableInfo.showOldValue(oneData);
//				}
//				
//			}
//			,showOldValue : function (datas){
//				$(".metaTableInfo form #tableName").val(datas.tableName);
//				$(".metaTableInfo form #tableName").attr("disabled",true);
//				$(".metaTableInfo #tableDbSourceName").val(datas.tableDbSourceName);
//				$(".metaTableInfo #tableDbSourceName").attr("disabled",true);
//				$(".metaTableInfo form #tableNamed").val(datas.tableNamed);
//				$(".metaTableInfo form #tableType[value='"+datas.tableType+"']").attr("checked",true);
//				$(".metaTableInfo form #tableRemarks").val(datas.remarks);
//				if (datas.states == 'Y'){
//					$(".metaTableInfo form #states").attr("checked",true);
//				}
//				$(".metaTableInfo form #versions").val(datas.versions);
//				$(".metaTableInfo form #fields").val(datas.fields);
//				$(".metaTableInfo form #records").val(datas.records);
//				
//			}
//		};
//	
//	metaTableInfo.init();
	
	layer.close(index);
	
});