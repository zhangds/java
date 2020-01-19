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
    font-size:13px;
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
	 <div id="w" class="easyui-window" title="Basic Window" data-options="modal:true,closed:false"  style="width:500px;height:200px;padding:10px;">
		The window content.
	</div>
</div>
</body>
<script type="text/javascript">
    var mainPage = {
    		"path" : '${mvcPath!""}'
    	};
</script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/template.js"></script>
</html>