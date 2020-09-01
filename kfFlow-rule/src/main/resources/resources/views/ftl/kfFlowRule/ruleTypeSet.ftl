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
	<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowRule/css/ruleTypeSet.css"/>
	<title>规则类型选择</title>
</head>

<body>
    <div id="ruleTypeSet" class="wrap">
    	<div class="row">
    		<!--<input type="text" id="ruleType" name="ruleType" style="width: 100%;">
			<select id="ruleType" name="ruleType">
				<option value="iconParam" selected="selected">参数类</option>
				<option value="iconCondition">条件类</option>
				<option value="iconAction">行为类</option>
			</select>-->
			<input name="ruleType" id="ruleType" type="radio" value="iconParam" checked="checked" />参数类&nbsp;&nbsp;
			<input name="ruleType" id="ruleType" type="radio" value="iconCondition" />条件类&nbsp;&nbsp;
			<input name="ruleType" id="ruleType" type="radio" value="iconAction" />行为类
    	</div>
    	<div class="row">
    		<div class="searh">
				<div class="buttonCls checkOk">确定</div>
			</div>
    	</div>
	</div>
</body>

<script type="text/javascript">
    var ruleTypeSet = {
        staffno: "${staffno!''}",
        flowId: "${flowId!''}",
        nodeId: "${nodeId!''}",
        ruleId: "${ruleId!''}",
        rulePid: "${rulePid!''}",
        lineId : "${lineId!' '}",
        opType:"${opType!''}"
    };
</script>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/laypage/laypage.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/ruleTypeSet.js"></script>
</html>
    