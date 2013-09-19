<ol class="breadcrumb">
	<#list links as link>
	<li <#if link.type??> class="${link.type}" </#if>>
		<#if link.type??>
			${link.name}
		<#else>
		<a href="<@spring.url '${link.url}'/>">
			${link.name}
		</a>
		</#if>
	</li>
	</#list>
</ol>