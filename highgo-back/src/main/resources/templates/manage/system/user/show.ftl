<#import "../../tpl/htmlBase.ftl" as html>
<@html.htmlBase>
<form action="http://localhost:8080/manage/account" theme="simple" id="form">

<div id="tabs">
	<ul>
		<li><a href="#tabs-1">基本信息</a></li>
	</ul>
	<div id="tabs-1">
		<div class="alert alert-info" style="margin-bottom: 2px;text-align: left;">
			<strong>后台管理人员信息：</strong>
		</div>
		<table class="table table-bordered">
					<tr style="display: none;">
						<td>id</td>
						<td><input type="hidden" value="${manager.id!""}" name="manager.id" label="id" id="id"/></td>
					</tr>
					<tr>
						<td style="text-align: right;width: 200px;">昵称</td>
						<td style="text-align: left;">${manager.nickname!""}</td>
					</tr>
					<tr>
						<td style="text-align: right;">登录名</td>
						<td style="text-align: left;">${manager.username!""}</td>
					</tr>
					<tr>
						<td style="text-align: right;">Email地址</td>
						<td style="text-align: left;">${manager.email!""}</td>
					</tr>
					<tr>
						<td style="text-align: right;">状态</td>
						<td style="text-align: left;">
							<#if manager.status=="y">启用
							<#else>禁用</#if>
						</td>
					</tr>
					
				</table>
	</div>
</div>
</form>

<script type="text/javascript">
$(function() {
	$( "#tabs" ).tabs({
		//event: "mouseover"
	});
	
});

</script>
</@html.htmlBase>