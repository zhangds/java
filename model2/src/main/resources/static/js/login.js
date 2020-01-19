/**
 * 
 */
$(function(){
	//url:loginPage.path+'/checkUser',
	$(".login_body #submitForm").off("click").on("click",function(){
		if ($('#loginForm').form('validate')){
			$.ajax({
	            type: "POST",
	            url: loginPage.path+'/checkUser',
	            data: {"userId":$('.login_body #userId').val(),"pwd":$('.login_body #pwd').val()},
	            async: true,
	            cache:false,
	            success: function (data) {
	            	alert(data);
	            }, beforeSend : function (XMLHttpRequest) {
	           	}, complete : function(XMLHttpRequest,textStatus){
	            }
			});
		}
	});
	$(".login_body #clearForm").off("click").on("click",function(){
		$('.login_body #loginForm').form('clear');
	});
	
});