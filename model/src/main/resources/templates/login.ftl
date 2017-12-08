<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页</title>
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
	<link rel="Shortcut Icon" type="images/x-icon" href="${mvcPath}/images/favicon.ico" />
	<link rel="Bookmark" type="images/x-icon" href="${mvcPath}/images/favicon.ico">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link href="${mvcPath!""}/webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${mvcPath!""}/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${mvcPath!""}/css/login.css" media="all" />
</head>
<body>
	<!-- <video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
	    <source src="login.mp4" type="video/mp4">
	</video> -->
	<div class="video_mask"></div>
	<div class="login">
	    <h1>${projectName!""}-管理登录</h1>
	    <form class="layui-form">
	    	<div class="layui-form-item">
				<input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
		    </div>
		    <div class="layui-form-item">
				<input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
		    </div>
		    <!-- <div class="layui-form-item form_code">
				<input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
				<div class="code"><img src="../../images/code.jpg" width="116" height="36"></div>
		    </div> -->
			<button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
		</form>
	</div>
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/login.js"></script>
</body>
</html>