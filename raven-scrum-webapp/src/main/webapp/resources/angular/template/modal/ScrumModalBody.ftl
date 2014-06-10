<div class="box-small-wrapper">
<form class="form-horizontal box-small-content">
	<fieldset>
	<div class="alert alert-danger" ng-if="failed">
<p>Selecting user, task, and taks type is obligatory, check your data and try again</p>
</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Select task type</label>
						<input type="hidden" ui-select2="select2data.select2types" ng-model="select2data.select2type" data-placeholder="Pick a task type" style="width: 100%">	
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Assign to:</label>
						<input type="hidden" ui-select2="select2data.select2users" ng-model="select2data.select2user" data-placeholder="Pick a user" style="width: 100%">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Select parent task:</label>
						<input type="hidden" ui-select2="select2data.select2tasks" ng-model="select2data.select2task" data-placeholder="Pick a parent task" style="width: 100%">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-1">
						<label for="login">Task description</label>
						<textarea class="form-control" rows="3" maxlength="255" ng-model="select2data.taskdescription">
		
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