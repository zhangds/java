<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="${mvcPath}/resources/images/favicon.ico" />
<link rel="Shortcut Icon" href="${mvcPath}/resources/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowResources/css/flowPublic.css"/>
<title>流程管理页面</title>
</head>
<body>
	<div class="row">
		<div class="spanTitle colPix" style="text-align:right;margin-right:10px;">流程ID
		</div>
		<div class="spanEle"><input type="text" id= "wkId" ></input>
		</div>
		<div class="spanTitle" style="text-align:right;margin-right:10px;">流程名称
		</div>
		<div class="spanEle"><input type="text" id= "wkName"></input>
		</div>
		<div class="spanTitle" style="text-align:right;margin-right:10px;">流程描述
		</div>
		<div class="spanEle"><input type="text" id= "wkRemark"></input>
		</div>
	</div>
	<div class="row">
		<div class="searh">
			<div class="buttonCls copy lastBtnCls" staffno="${staffno!''}">复制</div>
			<div class="buttonCls update" staffno="${staffno!''}">修改</div>
			<div class="buttonCls add" staffno="${staffno!''}">新增</div>
			<div class="buttonCls qry" staffno="${staffno!''}">查询</div>
			<!--
			<div class="buttonCls limit" staffno="${staffno!''}">流程时限</div>
			<div class="buttonCls fir" staffno="${staffno!''}">首派</div>
			-->
		</div>
	</div>
	<div class="row content">
		<table>
			<thead>
				<tr>
					<th class="tcenter" style="width: 4%">
						<div class="chkall" _name="bill" onclick="chkall(this)"></div>
					</th>
					<th class="tcenter" style="width: 8%">流程ID</th>
					<th class="tcenter" style="width: 20%">流程名称</th>
					<th class="tcenter" style="width: 20%">流程描述</th>
					<th class="tcenter" style="width: 8%">创建者ID</th>
					<th class="tcenter" style="width: 8%">修改者ID</th>
					<th class="tcenter" style="width: 4%">状态</th>
					<th class="tcenter" style="width: 12%">更新时间</th>
					<th class="tcenter" style="width: 6%">当前版本</th>
					<th class="tcenter" style="width: 10%">默认表单</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div id="pageTools" class="pageTool">
		</div>
	</div>
</body>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/laypage/laypage.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowDesign/js/managerList.js"></script>
</html>