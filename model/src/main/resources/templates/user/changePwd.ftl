<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>修改密码</title>
	<meta name=“renderer” content=“webkit|ie-comp“>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="">
	<meta name="description" content="">
	<link rel="icon" href="${mvcPath}/images/favicon.ico">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link href="${mvcPath!""}/webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${mvcPath!""}/css/font.css" media="all" />
	<link rel="stylesheet" href="${mvcPath!""}/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${mvcPath!""}/css/main.css" media="all" />
	<link rel="stylesheet" href="${mvcPath!""}/css/user.css" media="all" />
</head>
<body class="childrenBody">
	<form class="layui-form changePwd">
		<div style="margin:0 0 15px 110px;color:#f00;">新密码必须两次输入一致才能提交</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">用户ID</label>
		    <div class="layui-input-block">
		    	<input type="text" name="loginId" value="${user.loginId!''}" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">用户名</label>
		    <div class="layui-input-block">
		    	<input type="text" name="loginName" value="${user.loginName!''}" disabled class="layui-input layui-disabled">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">旧密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请输入旧密码" lay-verify="required|oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-block">
		    	<input type="password" name="pwd" value="" placeholder="请输入新密码" lay-verify="required|newPwd" id="oldPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <label class="layui-form-label">确认密码</label>
		    <div class="layui-input-block">
		    	<input type="password" value="" placeholder="请确认密码" lay-verify="required|confirmPwd" class="layui-input pwd">
		    </div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		    	<button class="layui-btn" lay-submit="" lay-filter="onChangePwd">立即修改</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/changePwd.js"></script>
	<script type="text/javascript">
	 var oldPwd = '${user.loginPassword!""}';
	</script>
</body>
</html>