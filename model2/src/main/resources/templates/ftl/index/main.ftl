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
	<link rel="shortcut icon" type="images/x-icon" href="${mvcPath}/images/favicon.ico">
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="${mvcPath!""}/easyui/1.3.2/themes/metro/easyui.css">
	<link rel="stylesheet" type="text/css" href="${mvcPath!""}/easyui/1.3.2/themes/icon.css">
*{
	font-size:12px;
}
body {
    font-family:helvetica,tahoma,verdana,sans-serif;
    padding:20px;
    font-size:13px;
    margin:0;
}
h2 {
    font-size:18px;
    font-weight:bold;
    margin:0;
    margin-bottom:15px;
}
.demo-info{
	background:#FFFEE6;
	color:#8F5700;
	padding:12px;
}
.demo-tip{
	width:16px;
	height:16px;
	margin-right:8px;
	float:left;
}
</head>
<body class="main_body easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;line-height:60px;background:#FFF;padding:0px">
	</div>
	<div id="menuTool" title="菜单" data-options="region:'west',split:false,title:'&nbsp;&nbsp;',border:true" style="width:210px;padding:0px;">
				<!--<div id="sysPanel" class="easyui-panel" title="系统管理" style="width:208px;height:auto;padding:0px;"
				data-options="iconCls:'icon-blank',collapsible:true">
				<ul id='sysMenu' class="easyui-tree" ></ul>
			 <ul class="easyui-tree" data-options="animate:true">
				<li><span>My Documents</span>
					<ul>
						<li data-options="state:'closed',attributes:{url:'123'}" ><span>Photos</span>
							<ul>
								<li data-options="iconCls:'icon-sum'"><span>Friend</span></li>
								<li><span>Wife</span></li>
								<li><span>Company</span></li>
							</ul></li>
						<li data-options="state:'closed'"><span>Program Files</span>
							<ul>
								<li>Intel</li>
								<li>Java</li>
								<li>Microsoft Office</li>
								<li>Games</li>
							</ul></li>
						<li>index.html</li>
						<li>about.html</li>
						<li>welcome.html</li>
					</ul></li>
			</ul> -->
		</div>
	</div>
	<!-- <div data-options="region:'east',split:true,title:'East'" style="width:100px;padding:10px;">east region</div> -->
	<div data-options="region:'south',border:false" style="height:50px;line-height:50px;background:#FFF;padding:0px;text-align: right;">
		copyright @2020 zhangds&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<div data-options="region:'center',title:''">
	
	</div>
</body>
<script type="text/javascript">
    var mainPage = {
    		"path" : '${mvcPath!""}',
    		"menus":${menus![]}
    	};
</script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/easyui/1.3.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/main.js"></script>
</html>