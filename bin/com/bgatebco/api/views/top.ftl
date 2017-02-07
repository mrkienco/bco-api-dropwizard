<#-- @ftlvariable name="" type="com.bgatebco.api.views.TopUserView" -->
<#import "Layout.ftl" as layout>
<@layout.layout>
<h1>Users</h1>
<div>
    <ul>
        <#list topUsersCurrentWeek as user>
            <li>${user.username}</li>
        </#list>
    </ul>
</div>
</@layout.layout>