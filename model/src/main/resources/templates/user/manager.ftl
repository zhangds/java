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
	<blockquote class="layui-elem-quote user_manager_search">
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" value="" id="likeKey" name="likeKey" placeholder="请输入关键字"
					class="layui-input search_input">
			</div>
			<a class="layui-btn search_btn"><i class="fa fa-search" aria-hidden="true">&nbsp;&nbsp;查询</i></a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal addUser"><i class="fa fa-plus" aria-hidden="true">&nbsp;&nbsp;添加用户</i></a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn batchOpenUser"><i class="fa fa-lock" aria-hidden="true">&nbsp;&nbsp;开启用户</i></a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-warm batchCloseUser"><i class="fa fa-unlock" aria-hidden="true">&nbsp;&nbsp;锁定用户</i></a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDelUser"><i class="fa fa-minus" aria-hidden="true">&nbsp;&nbsp;删除用户</i></a>
		</div>
	</blockquote>
	<div class="layui-form" id="user_manager_list">
	</div>
	<div id="userManagerList_page" style="text-align:right;"></div>
	<script id="userManagerList" type="text/html">
			<table class="layui-table userManagerList">
		    <colgroup>
				<col width="80">
				<col width="50">
				<col width="10%">
				<col width="14%">
				<col width="12%">
				<col width="16%">
				<col width="6%">
				<col width="14%">
				<col width="6%">
				<col>
		    </colgroup>
		    <thead>
				<tr>
					<th style="text-align: center;">编号</th>
					<th><input type="checkbox" name="select" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th style="text-align: center;">用户ID</th>
					<th style="text-align: center;">用户名</th>
					<th style="text-align: center;">电话</th>
					<th style="text-align: center;">邮箱</th>
					<th style="text-align: center;">性别</th>
					<th style="text-align: center;">创建时间</th>
					<th style="text-align: center;">状态</th>
					<th style="text-align: center;">操作</th>
				</tr> 
		    </thead>
		    <tbody class="userManagerList_content" id="userManagerList_content">
				{{# layui.each(d.list, function (index, item){ }}
					<tr>
						<td style="text-align: center;">{{= (d.pageNum-1)*d.pageSize+index+1 || '' }}</td>
						<td><input type="checkbox" name="selectOne" lay-skin="primary" lay-filter="choose"></td>
						<td style="text-align:left;">{{= item.loginId || '' }}</td>
						<td style="text-align:left;">{{= item.loginName || '' }}</td>
						<td style="text-align:left;">{{= item.mobile || '' }}</td>
						<td style="text-align:left;">{{= item.email || '' }}</td>
						<td style="text-align:left;">{{= item.sex=='M'?'男':'女' || '' }}</td>
						<td style="text-align:left;">{{= item.createTime || '' }}</td>
						<td style="text-align:left;">{{= item.loginStatus=='ON'?'开启':'锁定' || '' }}
						</td>
						<td style="text-align:center;">
							<div class="layui-btn-group">
								<button class="layui-btn layui-btn-small layui-btn-sm" title="修改" option="editUserOne" oppara="{{= item.loginId || '' }}">
    								<i class="layui-icon">&#xe642;</i>
  								</button>
								<button class="layui-btn layui-btn-warm layui-btn-small layui-btn-sm" title="密码初始化" option="initPwdUserOne" oppara="{{= item.loginId || '' }}">
    								<i class="layui-icon">&#xe60f;</i>
  								</button>
							</div>
						</td>
					</tr>
				{{#  }); }}
				{{#  if(d.length === 0){ }}
  					无数据
				{{#  } }}
		    </tbody>
			</table>
	</script>
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/managerUser.js"></script>
	<script type="text/javascript">
	</script>

</body>
</html>