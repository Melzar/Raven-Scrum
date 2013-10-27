    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="closeModal()">&times;</button>
          <h3 class="modal-title">{{data.modaltitle}}</h3>
        </div>
        <div class="modal-body">
            <ng-include src="data.modalbody"></ng-include>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal"  ng-click="closeModal()">Close</button>
          <button type="button" class="btn btn-primary" ng-click="save()">Save changes</button>
        </div>
      </div>
    </div>