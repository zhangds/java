<!DOCTYPE HTML>
<html>
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
<title>流程信息创建</title>
</head>
<body>
   <div class="row">
   		<div class="spanOneTitle colPix">流程ID:
		</div>
		<div class="spanOneEle"><input id="wkId" name="wkId" 
		type="text" value="${detailInfo.wkId!''}" 
		<#if detailInfo.wkId ?? && detailInfo.wkId !="" >disabled="disabled"
		</#if> ></input>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">流程名称:
		</div>
		<div class="spanOneEle"><input id="wkName" name="wkName" type="text" value="${detailInfo.wkName!''}"></input>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">创建者:
		</div>
		<div class="spanOneEle"><span>${detailInfo.createrId!''}</span></input>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">当前修改人:
		</div>
		<div class="spanOneEle"><span>${detailInfo.updaterId!''}</span>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">创建时间:
		</div>
		<div class="spanOneEle"><span>${detailInfo.createDt!''}</span>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">当前版本号:
		</div>
		<div class="spanOneEle"><span>${detailInfo.currVersion!''}</span>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">当前状态:
		</div>
		<div class="spanOneEle">
			<select id="wkStates" name="wkStates">
				<#if stats ??>
					<#list stats ?keys as _key>
						<#if _key == detailInfo.wkState >
							<option value="${_key!''}" selected="selected">${stats["${_key!''}"]!''}</option>
						<#else>
							<option value="${_key!''}">${stats["${_key!''}"]!''}</option>
						</#if>
					</#list>
				</#if>
			</select>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">绑定表单:
		</div>
		<div class="spanOneEle"><input id="wkForm" name="wkForm" type="text" value="${detailInfo.defForm!''}"></input>
		</div>
   </div>
   <div class="row">
   		<div class="spanOneTitle colPix">描述:
		</div>
		<div class="spanOneEle"><input type="text" id="wkRemark" name="wkRemark" value="${detailInfo.wkRemark!''}"></input>
		</div>
   </div>
   <div class="row">
   		<div class="searh">
			<div class="buttonCls cancel lastBtnCls" userId="${staffno!''}">取消</div>
			<div class="buttonCls ok" userId="${staffno!''}" version="${detailInfo.currVersion!''}" option="<#if detailInfo?? && detailInfo.wkId>update<#else>add</#if>">确认</div>
		</div>
   </div>
</body>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/laypage/laypage.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="../resources/kfFlowDesign/js/detailWKInfo.js"></script>
</html>