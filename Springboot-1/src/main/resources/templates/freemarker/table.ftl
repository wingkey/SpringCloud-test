<!DOCTYPE html>
<html lang="zh-CN">
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">


<script src="../js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/bootstrap-table.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrapValidator.css">


<script src="../js/bootstrap.min.js"></script>

<script src="../js/bootstrap-table.js"></script>
<script src="../js/bootstrapValidator.js"></script>
<script src="../js/bootstrapValidator.min.js"></script>

<script src="../js/bootstrap-table-zh-CN.js"></script>

<script src="../js/defin/confirm.js"></script>
<script src="../js/defin/table.js"></script>

<title>测试表格插件</title>


</head>
<body>
	<table id="grid">
	
	</table>
	
	
	
	
<div class="modal fade" id="editvue" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="width: 600px">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	修改:
      </div>
      <div class="modal-body">
        <form id="editform">
          <div class="form-group">
            <label for="modal-id" class="control-label">id:</label>
            <input type="text" class="form-control" id="modal-id" name="modalid" readOnly="true">
          </div>
          <div class="form-group">
            <label for="modal-name" class="control-label">姓名:</label>
            <input type="text" class="form-control" id="modal-name" name="modalname">
          </div>
          <div class="form-group">
            <label for="modal-code" class="control-label">代号:</label>
            <input type="text" class="form-control" id="modal-code" name="modalcode">
          </div>
          <div class="form-group">
            <label for="modal-age" class="control-label">年龄:</label>
            <input type="text" class="form-control" id="modal-age" name="modalage">
          </div>
          
            <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="submit" class="btn btn-primary" onclick="editUser()">保存</button>
		     </div>
        </form>
      </div>
    
      
      
    </div>
  </div>
</div>


<div class="modal fade" id="insertUserModel" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="width: 600px">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        	新增:
      </div>
      <div class="modal-body">
        <form id="insertform">
          <div class="form-group">
            <label for="modal-name" class="control-label">姓名:</label>
            <input type="text" class="form-control" id="modal-name1" name="modalname">
          </div>
          <div class="form-group">
            <label for="modal-code" class="control-label">代号:</label>
            <input type="text" class="form-control" id="modal-code1" name="modalcode">
          </div>
          <div class="form-group">
            <label for="modal-age" class="control-label">年龄:</label>
            <input type="text" class="form-control" id="modal-age1" name="modalage">
          </div>
          
            <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="submit" class="btn btn-primary" onclick="insertUser()">保存</button>
		     </div>
        </form>
      </div>
    
      
      
    </div>
  </div>
</div>
	
</body>
</html>