<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fruitrade.domain.OrderDo;"%>
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
		<title>订单管理</title>
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
			#searchOrder{
			    margin-top: 4px;
   	 			margin-left: 10px;
			}
		</style>
	</head>
	<body>
	
		<div id="toolbar">
			<div id="editOrder" class="btn btn-success" data-toggle="modal" data-target="#myModal">编辑订单</div>
		</div>
		<div class="searchItem">
			<span>订单号</span>
		    <input type="text" class="form-control" style="width: 160px;margin-top:5px;"  id="id" value="" placeholder="请输入订单号">
			<div id="searchOrder" class="btn btn-info">立即搜索</div>
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
			initTable('/listOrder.action');
		});
		
		$('#searchOrder').click(function(){ // 立即搜索
			var id = $("#id").val();
			initTable('/listOrder.action?id='+id);
		});
		$('#clearSearch').click(function(){
			$("#id").val('');
			initTable('/listOrder.action');
		});
		$('#editOrder').click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			if(row.length > 1){
				alert("只能选择一条数据！");
				return false;
			}
			if(row[0].orderState=='1'){
				alert('已支付订单不可编辑!');
				return false;
			}
			$("#winIframe").attr("src","/toUpdateOrderPage.action?id="+row[0].id);
			$('#myModal').on('shown.bs.modal', function () {
				$(this).find('.modal-content').css('height','600px');// 修改modal的高度
				$(this).find('.modal-content').css('width','500px');// 修改modal的标题
				$(this).find('.modal-title').text('编辑订单');// 修改modal的标题
			});
		});
		
		$(".saveNow").click(function(){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/saveOrUpdateOrder.action',
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
		
		$("#myModal").on("hidden.bs.modal", function() {
		    $(this).removeData("bs.modal");
		    initTable('/listOrder.action'); // 重新加载数据
		});

		function initTable(url) {
			$table.bootstrapTable('destroy').bootstrapTable({
				height:$(window).height(),
				url: url,
				columns: [[
					{
			          checkbox: true,
			          align: 'center',
					},{
			          title: '客户姓名',
			          field: 'userName',
			          align: 'center'
			        },{
			          title: '订单号',
			          field: 'id',
			          align: 'center',
			        },{
			          title: '订单状态',
			          field: 'orderState',
			          align: 'center',
			          formatter: function (value, row, index) {
			        	  if("1"==value){
			        		  value = "已支付";
			        	  }else if("0"==value){
			        		  value = "未支付"
			        	  }
	                    return value;
		              }
			        },{
			          title: '总额（元）',
			          field: 'totalPrice',
			          align: 'center',
			        },{
			          title: '邮费（元）',
			          field: 'postage',
			          align: 'center'
			        },{
			          title: '下单时间',
			          field: 'createTime',
			          align: 'center',
			          formatter: function (value, row, index) {
			        	  if(value){
			        		  return value.replace('T', ' ');
			        	  }
		              }
			        },{
			          title: '预计送达时间',
			          field: 'planDeliveryTime',
			          align: 'center',
			          valign: 'middle',
			          width: 300,
			          formatter: function (value, row, index) {
			        	  if(value){
			        		  return value.replace('T', ' ');
			        	  }
		              }
			        },{
			          title: '付款时间',
			          field: 'payTime',
			          align: 'center',
			          valign: 'middle',
			          width: 300,
			          formatter: function (value, row, index) {
			        	  if(value){
			        		  return value.replace('T', ' ');
			        	  }
		              }
			        }
		        ]],
		        onLoadSuccess: function(data){
		        	return data.list;
		        }
		    });
		  }
	</script>
</html>
