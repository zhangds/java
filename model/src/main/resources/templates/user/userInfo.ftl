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
	<fieldset class="layui-elem-field userInfo">
		<legend><span><#if editUser ??>修改<#else>创建</#if></span>用户信息</legend>
		<div class="layui-field-box">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">用户ID</label>
					<div class="layui-input-block">
						<input type="text" id="loginId" name="loginId" lay-verify="required|loginId|loginIdOnlyOne"
							placeholder="请输入用户ID" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<input type="text" id="loginName" name="loginName" lay-verify="required"
							placeholder="请输入用户中文名称" autocomplete="off" class="layui-input">
					</div>
				</div>
		
				<div class="layui-form-item">
					<label class="layui-form-label">用户状态</label>
					<div class="layui-input-block">
						 <input type="radio" id="loginStatus" name="loginStatus" value="ON" title="开启" checked="">
					     <input type="radio" id="loginStatus" name="loginStatus" value="CLOSE" title="锁定">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">电话</label>
					<div class="layui-input-block">
					     <input type="text" id="mobile" name="mobile" lay-verify="required|phone|number"
							placeholder="请输入电话号码" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱</label>
					<div class="layui-input-block">
					     <input type="text" id="email" name="email" lay-verify="required|email"
							placeholder="请输入电子邮箱" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-block">
						 <input type="radio" id="sex" name="sex" value="M" title="男" checked="">
					     <input type="radio" id="sex" name="sex" value="F" title="女">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">创建者</label>
					<div class="layui-input-block">
					     <input type="text" id="createUserId" name="createUserId"
							placeholder="请输入创建者ID" autocomplete="off" class="layui-input" disabled="">
					</div>
				</div>
				<!-- <div class="layui-form-item">
					<div class="layui-collapse" lay-accordion="">
						<div class="layui-colla-item">
							<h2 class="layui-colla-title">拓展属性</h2>
							<div class="layui-colla-content">
								<div class="layui-form-item">
									<label class="layui-form-label">字段数</label>
									<div class="layui-input-block">
										<input type="text" id="fields" name="fields"
											lay-verify="fields" placeholder="请输入表的字段数" autocomplete="off"
										class="layui-input">
									</div>
								</div>
								<div class="layui-form-item">
									<label class="layui-form-label">记录数</label>
									<div class="layui-input-block">
										<input type="text" id="records" name="records"
											lay-verify="records" placeholder="请输入表记录数" autocomplete="off"
										class="layui-input">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> -->

				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="userInfoSave">保存</button>
					</div>
				</div>
			</form>
		</div>
	</fieldset>
	
	<script type="text/javascript" src="${mvcPath!""}/layui/layui.js"></script>
	<script type="text/javascript" src="${mvcPath!""}/js/userInfo.js"></script>
	<script type="text/javascript">
		var path = "${mvcPath?js_string}";
		var currentUserId = <#if currentUser??>'${currentUser.loginName}'<#else>null</#if>;
		var editUser = <#if editUserJson??>${editUserJson}<#else>null</#if>;
	</script>
</body>
</html>