<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>角色管理</title>
<meta name=“renderer” content=“webkit|ie-comp“>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="icon" href="${mvcPath}/images/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link href="${mvcPath!}/webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${mvcPath!}/layui/css/layui.css" media="all" />
</head>
<body class="childrenBody">
	<fieldset class="layui-elem-field layui-field-title"
		style="margin-top: 20px;">
		<legend>基本树</legend>
	</fieldset>

	<div
		style="display: inline-block; width: 250px; height: auto; padding: 10px; border: 1px solid #ddd; overflow: auto;">
		<ul id="rolesList"></ul>
	</div>
	<!-- <div ><input type="text" id="treeselecttest" placeholder="模块" autocomplete="off" class="layui-input"></div> -->
</body>
	<script type="text/javascript">
		var path = "${mvcPath?js_string}";
	</script>
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/role.js"></script>
	
</html>