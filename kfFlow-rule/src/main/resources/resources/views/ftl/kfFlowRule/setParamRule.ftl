<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowRule/css/setParamRule.css"/>
	<title>参数设置</title>
</head>

<body>
    <div class="setParamRule wrap">
    	<div class="row" style="margin-top: 0px;">
    		<div class="spanTitle" style="text-align:left;width: 100%;padding-right:0px;background-color: #00B7FF;color: #FFF;text-indent: 2em;font-size: 14px;">
				参数设置
			</div>
    	</div>
	    <div class="row">
	    	<div class="spanTitle colPix" style="text-align:right;margin-right:10px;width: 15%;">
				选取参数对象
			</div>
			<div class="spanEle" style="width: 24%;">
				<select id="paramObject" name="paramObject" >
					<#list selectEle?keys as key>
						<option value="${key}">${selectEle[key]!''}</option>
					</#list>
					<!--selected="selected"-->
				</select>
			</div>
			<div class="spanTitle" style="text-align:right;margin-right:10px;width: 10%;">
				属性查找
			</div>
			<div class="spanEle" style="width: 24%;">
				<input type="text" id="paramKey" name="paramKey" value="">
			</div>
			<div class="spanTitle" style="text-align:right;margin-right:10px;width: 15%;overflow: unset;">
				<div class="searh"><div class="buttonCls qry" >查询</div></div>
			</div>
	    </div>
	    <div class="row" style="width:640px;height: 330px;">
			<div class="spanEle" style="width:630px;border-width: 0px;margin-left: 5px;overflow:auto;">
				<table style="margin: 0 auto;">
					<thead>
						<tr>
							<th style="width: 50%">属性名称</th>
							<th style="width: 50%">属性标识</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr class="interleave" idno="20100821000019"><td>20100821000019</td><td>武汉客户服务中心</td></tr>
						<tr class="" idno="20100821000233"><td>20100821000233</td><td>黄石10000号客服中心</td></tr> -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
    var setParam = {
        staffno: "${staffno!''}",
        flowId: "${flowId!''}",
        nodeId: "${nodeId!''}",
        lineId: "${lineId!''}",
        ruleId: "${ruleId!''}",
        ruleType: "${ruleType!''}",
        opType: "${opType!''}",
        paramTypes : {<#list selectEle?keys as key>'${key}':"${selectEle[key]!''}",</#list>},
        node : ${currentNode!"{}"}
    };
</script>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/laypage/laypage.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/setParamRule.js"></script>
</html>