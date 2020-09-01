<fieldset style="border: 1px solid #00B4E1;">
    <legend>回单信息</legend>
	<div class="row">
		<div class="spanTitle" style="width: 18%;">
			回单模板选择
		</div>
		<div class="spanEle" style="width: 25%;">
			<select name="backFormId" id="backFormId" style="width: 95%;border-radius: 3px;height: 28px;line-height: 28px;padding-top: 0px;padding-bottom: 0px;padding-left: 5px;padding-right: 5px;border: 1px solid #b0bec7;">
				<option value="">请选择...</option>
			</select>
		</div>
		<div class="spanTitle" style="width: 55%;text-align: left;color: #F40;">
			重新选择回单模板，保存后将清空原设置模板的所有内容!
		</div>
	</div>
	<div class="row" style="margin-top: 5px;height: auto;">
		<table id="backFormTable" onselectstart="return false;" style="-moz-user-select:none;">
			<thead>
				<tr>
					<th>模板元素名</th>
					<th>可见</th>
					<th>可见规则</th>
					<th>必填</th>
					<th>必填规则</th>
				</tr>
			</thead>
			<tbody>
			</tbody>	
		</table>
	</div>
</fieldset>
<fieldset style="border: 1px solid #00B4E1;">
    <legend>附加信息</legend>
	<div class="row" style="margin-top: 5px;height: auto;">
		<table id="addZRDXTable"onselectstart="return false;" style="-moz-user-select:none;">
			<thead>
				<tr>
					<th>附加元素名</th>
					<th>可见</th>
					<th>可见规则</th>
					<th>必填</th>
					<th>必填规则</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</fieldset>
<div class="row">
	<div class="spanTitle" style="text-align:center;width: 15%;padding-right: 0px;float: right;">
		<div class="searh">
			<div class="buttonCls saveBackForms">保存</div>
		</div>
	</div>
</div>
