<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">


<script src="../js/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/bootstrap-table.css">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrapValidator.css">
<link rel="stylesheet" href="../css/bootstrap-select.css">

<script src="../js/bootstrap.min.js"></script>

<script src="../js/bootstrap-select.js"></script>
<script src="../js/bootstrap-table.js"></script>
<script src="../js/bootstrapValidator.js"></script>
<script src="../js/bootstrapValidator.min.js"></script>

<script src="../js/bootstrap-table-zh-CN.js"></script>
<script src="../js/bootstrapselect/defaults-zh_CN.js"></script>


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
            <label for="modal-sex" class="control-label">性别:</label>
           
          	<select class="selectpicker show-tick form-control" id="modal-sex" name="modalsex">
            	<option value=0>男</option>
            	<option value=1>女</option>
            </select>
          </div>
          
          <div class="form-group">
            <label for="modal-state" class="control-label">状态:</label>
            
            <select class="selectpicker show-tick form-control" id="modal-state" name="modalstate">
            	<option value=0 style="color:green">正常</option>
            	<option value=1 style="color:red">注销</option>
            </select>
            
          </div>
          
          <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="editUser111()">保存</button>
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
            <label for="modal-name1" class="control-label">姓名:</label>
            <input type="text" class="form-control" id="modal-name1" name="modalname">
          </div>
          <div class="form-group">
            <label for="modal-code1" class="control-label">代号:</label>
            <input type="text" class="form-control" id="modal-code1" name="modalcode">
          </div>
          
           <div class="form-group">
            <label for="modal-sex1" class="control-label">性别:</label>
           
          	<select class="selectpicker show-tick form-control" id="modal-sex1" name="modalsex"  title="请选择.....">
            	<option value=0>男</option>
            	<option value=1>女</option>
            </select>
          </div>
          
          <div class="form-group">
            <label for="modal-state1" class="control-label">状态:</label>
            
            <select class="selectpicker show-tick form-control" id="modal-state1" name="modalstate" title="请选择.....">
            	<option value=0 style="color:green">正常</option>
            	<option value=1 style="color:red">注销</option>
            </select>
            
          </div>
          
          
          
          
            <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="insertUser()">保存</button>
		     </div>
        </form>
      </div>
    
      
      
    </div>
  </div>
</div>
	
</body>
</html>