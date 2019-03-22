<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>测试freemarker配合bootstrap</title>


<link rel="stylesheet" href="../css/bootstrap.css">

<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="../js/jquery-3.3.1.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="../js/bootstrap.min.js"></script>
<script src="../js/defin/page.js"></script>

<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>


<body>



	<!-- 搜索区域  -->
	<div class="row" style="padding-bottom: 20px; margin-top: 20px;">
		<!-- 搜索框的长度为该行的3/4  -->
		<div class="col-md-9">
			<div class="input-group">
				<input id="searchString" type="text" style="height: 28px;"
					class="form-control" placeholder="请输入实体名"> <span
					class="input-group-btn">
					<button type="button" class="btn btn-info" onclick="search()"
						onkeypress="Enter()">
						<span class="glyphicon glyphicon-search" aria-hidden="true" /> 搜索
					</button>
				</span>
			</div>
		</div>
	</div>
	<!-- 表格显示 -->
	<div class="row">
		<div class="col-md-12" style="margin-top: 20px;">
			<div class="panel panel-info">
				<div class="panel-heading">人员信息</div>
				<table id="table"
					class="table table-striped table-bordered table-hover datatable">
					<thead id="tem" >
						<th id="id">id</th>
						<th id="name">姓名</th>
						<th id="code">代号</th>
						<th id="age">年龄</th>
						<th id="caozuo">操作</th>
					</thead>
					
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 页面底部显示 -->
	<!-- 每页显示几条记录 -->
	<div id="bottomTool" class="row-fluid">
		<div class="span6" style="width: 25%;; margin-right: 10px;float:left;">
			<div class="dataTables_length" id="DataTables_Table_0_length">
				<label> 每页 <select id="pageSize" onchange="research()"
					aria-controls="DataTables_Table_0" size="1"
					name="DataTables_Table_0_length">
						<option selected="selected" value="10">10</option>
						<option value="25">25</option>
						<option value="50">50</option>
						<option value="100">100</option>
				</select> 条记录
				</label>
			</div>
		</div>
		<!-- 显示第 1 至 10 项记录，共 57 项 -->
		<div class="span6" style="width: 25%;float:left;">
			<div id="DataTables_Table_0_info" class="dataTables_info">显示第 1
				至 10 项记录，共 57 项</div>
		</div>
		<!-- 第2页 -->
		<div class="span6" style="width: 30%;float:left;">
			<div class="dataTables_paginate paging_bootstrap pagination" style="display:inline-block;">
				<ul id="previousNext">
					<li onclick="previous()" class="prev disabled"><a
						id="previousPage" href="#">← 上一页</a></li>
					<div id="page" style="float: left;">
						<select id="pageNum" onchange="search()"
							style="width: 50PX; margin-right: 1px;"
							aria-controls="DataTables_Table_0" size="1"
							name="DataTables_Table_0_length">
							<option selected="selected" value="1">1</option>
						</select>
					</div>
					<li class="next" onclick="next()"><a id="nextPage" href="#">下一页
							→ </a></li>
				</ul>
			</div>
		</div>
	</div>





</body>
</html>