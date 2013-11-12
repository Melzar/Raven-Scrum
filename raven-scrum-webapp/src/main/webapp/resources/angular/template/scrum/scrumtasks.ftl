	<div class="box-small-wrapper box-no-radius box-border-left-right" ng-repeat="task in scrumtasks">
			<div class="row loadin">
				<div class="col-lg-12">
					<div class="task-wrapper">
					<div class="task-header">
						<a class="task-title">{{task.title}}</a>
						<small> : {{task.description}}</small>
						<span class="pull-right">		
									T: {{task.progress.TODO.length}}
									D: {{task.progress.DOING.length}}
									U: {{task.progress.UAT.length}}
									DN: {{task.progress.DONE.length}}
						</span>
					</div>
					</div>
				</div>
			</div>
			<div class="subtask-table" id="{{task.id}}">
					<div class="bordered-right subtask-column column-first" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.TODO" subtask="{{task.id}}" ng-dropped data-state="TODO">
						<div class="subtask" ng-model="task.progress.TODO" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.TODO" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}' }" data-idsub="{{subtask.id}}"  data-drag="true" ng-init="subtask.state = 'TODO'" ng-scrum-task></div>
					</div>
					<div class="bordered-right subtask-column column-middle" data-drop="true" ng-model="task.progress.DOING" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" subtask="{{task.id}}" ng-dropped data-state="DOING">
						<div class="subtask" ng-model="task.progress.DOING" ng-repeat="subtask in task.progress.DOING" data-drag="true"  data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" data-idsub="{{subtask.id}}" ng-init="subtask.state = 'DOING'" ng-scrum-task></div>
					</div>
					<div class="bordered-right subtask-column column-middle" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.UAT" subtask="{{task.id}}" ng-dropped data-state="UAT">
						<div class="subtask" ng-model="task.progress.UAT" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.UAT" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-idsub="{{subtask.id}}" data-drag="true" ng-init="subtask.state = 'UAT'" ng-scrum-task></div>
					</div>
					<div class="subtask-column column-last" data-drop="true" data-jqyoui-options="{accept:'.subtask:not([subtask={{task.id}}])'}" jqyoui-droppable="{multiple:true, onDrop: 'changestate'}" ng-model="task.progress.DONE" subtask="{{task.id}}" ng-dropped data-state="DONE">
						<div class="subtask subtask-done" ng-model="task.progress.DONE" jqyoui-draggable="{index: {{$index}}, animate:false, onStop: 'getsubtask'}" ng-repeat="subtask in task.progress.DONE" data-jqyoui-options="{revert: 'invalid', containment: '#'+'{{task.id}}'}" data-idsub="{{subtask.id}}" data-drag="true"  ng-init="subtask.state = 'DONE'" ng-scrum-task></div>					
					</div>		
			</div>
	</div>