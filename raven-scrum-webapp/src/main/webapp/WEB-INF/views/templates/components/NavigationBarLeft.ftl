<div class="box">
<div class="sidebar-list">
	<#list navlinks as nav>
		<a href="<@spring.url '${nav.url}'/>" class="sidebar-item <#if nav.type??> active </#if>">
			<i class="${nav.icon}"></i>
			<span>${nav.name}</span>
			
		</a>
	</#list>
</div>
</div>