<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>用户管理</title>
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
<link rel="icon" href="${mvcPath}/images/favicon.ico">
<!--[if lt IE 9]>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/html5shiv/html5shiv.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/respond/respond.min.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/webjarslocator/pie/PIE.js"></script>
	<![endif]-->
	<link href="${mvcPath!}/webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${mvcPath!}/layui/css/layui.css" media="all" />

</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" value="" placeholder="请输入关键字"
					class="layui-input search_input">
			</div>
			<a class="layui-btn search_btn">查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal addUser">添加用户</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDelUser">删除用户</a>
		</div>
	</blockquote>
	<div class="layui-form" id="user_manager_list">
	</div>
	<div id="userManagerList_page" style="text-align:right;"></div>
	<script id="userManagerList" type="text/html">
			<table class="layui-table userManagerList">
		    <colgroup>
				<col width="70">
				<col width="14%">
				<col width="16%">
				<col width="16%">
				<col width="16%">
				<col width="6%">
				<col width="14%">
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th>编号</th>
					<th>用户ID</th>
					<th>用户名</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>性别</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="userManagerList_content" id="userManagerList_content">
		    </tbody>
			</table>
	</script>
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/managerUser.js"></script>
	<script type="text/javascript">
	</script>

</body>
</html>