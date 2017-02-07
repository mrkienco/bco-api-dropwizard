<#-- @ftlvariable name="" type="com.bgatebco.api.views.TopUserView" -->
<#import "Layout.ftl" as layout>
<@layout.layout>
<h2>${leaderboardName?html}</h2>
<h1>Tuần từ ${timeStartCurrentWeek?html} đến ${timeEndCurrentWeek?html}</h1>
<div>
    <table border="1" cellspacing="2" cellpadding="2">
		<thead>
			<tr>
				<td><b>STT</b></td>
				<td><b>Tên đăng nhập</b></td>
				<td><b>Tên hiển thị</b></td>
				<td><b>Điểm</b></td>	
			</tr>
		</thead>
		<tbody>
			<#list topUsersCurrentWeek as user>
				<tr>			
					<td>${user.index}</td>
					<td>${user.username}</td>
					<td>${user.displayName}</td>
					<td>${user.score}</td>				
				</tr>
			</#list>
		</tbody>
	</table>
</div>
<h1>Tuần từ ${timeStartLastWeek?html} đến ${timeEndLastWeek?html}</h1>
<div>
    <table border="1" cellspacing="2" cellpadding="2">
		<thead>
			<tr>
				<td><b>STT</b></td>
				<td><b>Tên đăng nhập</b></td>
				<td><b>Tên hiển thị</b></td>
				<td><b>Điểm</b></td>	
			</tr>
		</thead>
		<tbody>
			<#list topUsersLastWeek as user>
				<tr>				
					<td>${user.index}</td>
					<td>${user.username}</td>
					<td>${user.displayName}</td>
					<td>${user.score}</td>				
				</tr>
			</#list>
		</tbody>
	</table>
</div>
</@layout.layout>