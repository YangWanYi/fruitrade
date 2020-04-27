<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fruitrade.domain.RetailDo;"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link href="https://cdn.bootcss.com/bootstrap-table/1.16.0/bootstrap-table.css" rel="stylesheet">
		<title>散户管理</title>
		<style type="text/css">
			*{
				padding: 0 0;
				margin: 0 0;
			}
			body{
				width: 100%;
				height: 100%;
				overflow-x: hidden; 
				padding: 0px 10px;
			}
			#winIframe,#fruitIframe{
				height: 100%;
				width: 100%;
				border: none;
			}
			.modal{
				margin: 5% auto;
			}
			#toolbar{
				float: left;
			}
			.searchItem{
				float: right;
				padding-right: 20px;
				height: 35px;
   				line-height: 35px;
			    margin-top: 5px;
			}
			.searchItem span,input{
				display: inline-block;
				float: left;
				padding: 5px 10px;
			}
			#searchRetail{
			    margin-top: 4px;
   	 			margin-left: 10px;
			}
		</style>
	</head>
	<body>
	
		<div id="toolbar">
			<div id="addRetail" class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增散户</div>
			<div id="editRetail" class="btn btn-success" data-toggle="modal" data-target="#myModal">编辑散户</div>
			<div id="deleteRetail" class="btn btn-danger">删除散户</div>
			<div id="addFruit" class="btn btn-primary" data-toggle="modal" data-target="#fruitModal">增加散户供应水果</div>
			<div id="editFruit" class="btn btn-success" data-toggle="modal" data-target="#fruitModal">编辑散户供应水果</div>
			<div id="deleteFruit" class="btn btn-danger">删除散户供应水果</div>
			
		</div>
		<div class="searchItem">
			<span>散户名称</span>
		    <input type="text" class="form-control" style="width: 160px;margin-top:5px;"  id="companyName" value="" placeholder="请输入散户名称">
			<span>负责人</span>
		    <input type="text" class="form-control" style="width: 160px;margin-top:5px;"  id="principal" value="" placeholder="请输入负责人">
			<div id="searchRetail" class="btn btn-info">立即搜索</div>
			<div id="clearSearch" class="btn btn-secondary">清空</div>
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title" id="myModalLabel"></h4>
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            </div>
		            <div class="modal-body">
						<iframe id="winIframe" width="100%" height="100%"></iframe>
					</div>
		            <div class="modal-footer">
		                <div class="btn btn-default" data-dismiss="modal">关闭</div>
		                <div class="btn saveNow btn-primary">确定</div>
		            </div>
		        </div>
		    </div>
		</div>
		
		
		<!-- 模态框（Modal）维护散户供应水果 -->
		<div class="modal fade" id="fruitModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <h4 class="modal-title" id="myModalLabel"></h4>
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            </div>
		            <div class="modal-body">
						<iframe id="fruitIframe" width="100%" height="100%"></iframe>
					</div>
		            <div class="modal-footer">
		                <div class="btn btn-default" data-dismiss="modal">关闭</div>
		                <div class="btn saveFruit btn-primary">确定</div>
		            </div>
		        </div>
		    </div>
		</div>
        
		<table
		  id="table"
		  data-toolbar="#toolbar"
		  data-pagination="true"
		  data-id-field="id"
		  data-page-list="[20, 25, 50, 100, all]"
		  data-show-footer="false"
		  data-side-pagination="server">
		</table>
		
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/bootstrap-table.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
		var $table = $('#table');
		$(function() {
			initTable('/listRetail.action');
		});
		
		$('#searchRetail').click(function(){ // 立即搜索
			var companyName = $("#companyName").val();
			var principal = $("#principal").val(); 
			initTable('/listRetail.action?companyName='+companyName+'&principal='+principal);
		});
		$('#clearSearch').click(function(){
			$("#companyName").val('');
			$("#principal").val('');
			initTable('/listRetail.action');
		});
		$('#addRetail').click(function(){
			$("#winIframe").attr("src","addRetail.jsp");
			$('#myModal').on('shown.bs.modal', function () {
				$(this).find('.modal-content').css('height','600px');// 修改modal的高度
				$(this).find('.modal-content').css('width','500px');// 修改modal的标题
				$(this).find('.modal-title').text('新增散户');// 修改modal的标题
			});
		});
		$('#deleteRetail').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			var ids = "";
			$(row).each(function(m,n){
				ids += n.id+',';
			});
			if(confirm('确定删除吗？')){
				$.ajax({
					type: 'post',
					dataType: 'json',
					url: '/deleteRetail.action',
					data: {'ids': ids},
					async: false,
					success: function(s){
						initTable('/listRetail.action'); // 重新加载数据
					},
					error: function(e){
						alert("删除失败！");
					}
				});
		    }
		});
		$('#editRetail').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			if(row.length > 1){
				alert("只能选择一条数据！");
				return false;
			}
			$("#winIframe").attr("src","/toUpdateRetailPage.action?id="+row[0].id);
			$('#myModal').on('shown.bs.modal', function () {
				$(this).find('.modal-content').css('height','600px');// 修改modal的高度
				$(this).find('.modal-content').css('width','500px');// 修改modal的标题
				$(this).find('.modal-title').text('编辑散户');// 修改modal的标题
			});
		});
		
		$('#addFruit').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			if(row.length > 1){
				alert("只能选择一条数据！");
				return false;
			}
			$("#fruitIframe").attr("src","/toAddSupplyVsFruitPage.action?supplyType=2&supplyId="+row[0].id);
			$('#fruitModal').on('shown.bs.modal', function () {
				$(this).find('.modal-content').css('height','600px');// 修改modal的高度
				$(this).find('.modal-content').css('width','500px');// 修改modal的标题
				$(this).find('.modal-title').text('增加散户供应水果');// 修改modal的标题
			});
		});
		$('#editFruit').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			if(row.length > 1){
				alert("只能选择一条数据！");
				return false;
			}
			if(row[0].vid == null){
				alert("该散户无可编辑水果！");
				return false;
			}
			$("#fruitIframe").attr("src","/toUpdateSupplyVsFruitPage.action?id="+row[0].vid);
			$('#fruitModal').on('shown.bs.modal', function () {
				$(this).find('.modal-content').css('height','600px');// 修改modal的高度
				$(this).find('.modal-content').css('width','500px');// 修改modal的标题
				$(this).find('.modal-title').text('编辑散户供应水果');// 修改modal的标题
			});
		});
		$('#deleteFruit').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			var ids = "";
			for(var i=0;i<row.length;i++){
				if(row[i].vid==null){
					alert("所选散户无供应水果可删除！");
					ids = '';
					break;
				}
				ids += row[i].vid+',';
				
			}
			if(ids==''){
				return false;
			}
			if(confirm('确定删除吗？')){
				$.ajax({
					type: 'post',
					dataType: 'json',
					url: '/deleteSupplyVsFruit.action',
					data: {'ids': ids},
					async: false,
					success: function(s){
						initTable('/listRetail.action'); // 重新加载数据
					},
					error: function(e){
						alert("删除失败！");
					}
				});
		    }
		});
		$(".saveNow").click(function(){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/saveOrUpdateRetail.action',
				data: $("#winIframe").contents().find("#myForm").serialize(),
				async: false,
				success: function(s){
					$('#myModal').modal('hide');
				},
				error: function(e){
					alert("保存失败！");
				}
			});
		});
		
		$(".saveFruit").click(function(){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/saveOrUpdateSupplyVsFruit.action',
				data: $("#fruitIframe").contents().find("#myForm").serialize(),
				async: false,
				success: function(s){
					$('#fruitModal').modal('hide');
				},
				error: function(e){
					alert("保存失败！");
				}
			});
		});
		
		$("#myModal,#fruitModal").on("hidden.bs.modal", function() {
		    $(this).removeData("bs.modal");
		    initTable('/listRetail.action'); // 重新加载数据
		});

		function initTable(url) {
			$table.bootstrapTable('destroy').bootstrapTable({
				height:$(window).height(),
				url: url,
				columns: [[
					{
			          checkbox: true,
			          align: 'center',
					}, {
			          field: 'id',
			          align: 'center',
			          valign: 'middle',
			          visible: false,
			        }, {
			          title: '散户编号',
			          field: 'retailCode',
			          align: 'center'
			        },{
			          title: '姓名',
			          field: 'retailName',
			          align: 'center',
			        },{
			          title: '联系电话',
			          field: 'contactNum',
			          align: 'center',
			        },{
			          title: '应季月份',
			          field: 'month',
			          align: 'center',
			        },{
			          title: '详细地址',
			          field: 'address',
			          align: 'center'
			        },{
			          title: '水果分类',
			          field: 'classifyName',
			          align: 'center'
			        },{
			          title: '水果名称',
			          field: 'fruitName',
			          align: 'center'
			        },{
			          title: '产地',
			          field: 'place',
			          align: 'center'
			        },{
			          title: '批发价',
			          field: 'price',
			          align: 'center'
			        },{
			          title: '批发单位',
			          field: 'unit',
			          align: 'center'
			        }
		        ]],
		        onLoadSuccess: function(data){
		        	console.log(data.list);
		        	return data.list;
		        }
		    });
		  }
	</script>
</html>
