		<div class="bsubtask" sprint="true" ng-model="task.subtasksRaw" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: ''}" data-jqyoui-options="{revert: 'invalid', containment: '#backlog'}" data-drag="true">
			<div class="backlog-subtask container-wrapper">
				<div class="backlog-subtask container task-child">
					<a class="subtask-title {{subtask.state}} text-overflow text-limit-1">{{subtask.title}} - {{subtask.id}} : <small>{{subtask.description}}</small></a>
				</div>
   			 </div>							
		</div>
