<div class="box-small-wrapper">
<form class="form-horizontal box-small-content">
	<fieldset>
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Select task type</label>
						<input type="hidden" ui-select2="data.select2types" ng-model="data.select2type" data-placeholder="Pick a task type" style="width: 100%">	
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Assign to:</label>
						<input type="hidden" ui-select2="data.select2users" ng-model="data.select2user" data-placeholder="Pick a user" style="width: 100%">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Select parent task:</label>
						<input type="hidden" ui-select2="data.select2tasks" ng-model="data.select2task" data-placeholder="Pick a parent task" style="width: 100%">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Task description</label>
						<textarea class="modal-textarea" ng-model="data.taskdescription">
		
						</textarea>		
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Priority</label>							
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Estimate</label>				
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Attachment</label>
								
					</div>
				</div>						
			</div>
		</div>
	</fieldset>
</form>
</div>