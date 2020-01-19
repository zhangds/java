<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>${projectName!""}首页</title>
	<meta name=“renderer” content=“webkit|ie-comp“>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- <meta http-equiv="Access-Control-Allow-Origin" content="*"> -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="shortcut icon" type="images/x-icon" href="${mvcPath!''}/images/favicon.ico">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!''}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!''}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!''}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${mvcPath!''}/easyui/1.3.2/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="${mvcPath!''}/easyui/1.3.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${mvcPath!''}/webjarslocator/font-awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="${mvcPath!''}/easyui/1.3.2/demo/demo.css">
<style type="text/css">
body.login_body{
	font-family:helvetica,tahoma,verdana,sans-serif;
    padding:20px;
    font-size:15px;
    margin:0;
    padding: 0;
    background-color: #379F8C;
}
.content {
            width: 400px;
            margin: 0 auto; /*水平居中*/
            position: relative;
            top: 50%; /*偏移*/
            margin-top: -150px; 
        }
</style>
</head>
<body class="login_body" data-options="fit:true">
	 <div id="w" class="easyui-window" title="<i class='fa fa-firefox fa-lg'></i>&nbsp;&nbsp;登录" data-options="modal:true,closable:false,resizable:false,collapsible:false,minimizable:false,maximizable:false,shadow:true"  style="width:400px;height:170px;padding:10px;">
		<form id="loginForm">
			<div>
				<label for="name" style="width: 20%;">用户名:&nbsp;&nbsp;</label> <input
					class="easyui-validatebox" type="text" name="userId" id="userId"
					data-options="required:true" style="width: 80%;" />
			</div>
			<div style="margin-top: 10px;">
				<label for="pwd" style="width: 20%;">密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input class="easyui-passwordbox easyui-validatebox" type="Password"
					name="pwd" id="pwd" prompt="Password" style="width: 80%;"
					data-options="required:true">
			</div>
		</form>
		<div style="text-align:right;padding:10px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  id="submitForm" name="submitForm" >
	    	 <i class="fa fa-check fa-lg" aria-hidden="true"></i>确认</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton"  id="clearForm" name="clearForm" >
	    	<i class="fa fa-times fa-lg" aria-hidden="true"></i>取消</a>
	    </div>
	</div>
</div>
</body>
<script type="text/javascript">
    var loginPage = {
    		"path" : '${mvcPath!""}'
    	};
</script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/login.js"></script>
</html>