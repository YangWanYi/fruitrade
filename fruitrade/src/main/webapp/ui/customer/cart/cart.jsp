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
		<title>订单中心</title>
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
		</style>
	</head>
	<body>
	
		<div id="toolbar">
			<div id="deleteOrder" class="btn btn-danger">删除</div>
			<div id="payNow" class="btn btn-primary">支付</div>
			
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
		var userId = "${sessionScope.user.id}";
		$(function() {
			initTable('/listOrder.action?userId='+userId);
		});
		
		$('#deleteOrder').click(function(){
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
					url: '/deleteOrder.action',
					data: {'ids': ids},
					async: false,
					success: function(s){
						initTable('/listOrder.action'); // 重新加载数据
					},
					error: function(e){
						alert("删除失败！");
					}
				});
		    }
		});
		
		$("#payNow").click(function(){
			var row = $table.bootstrapTable('getSelections');
			if(row.length == 0){
				alert("请选择数据！");
				return false;
			}
			var ids = "";
			$(row).each(function(m,n){
				ids += n.id+',';
			});
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/pay.action',
				data: {'ids': ids},
				async: false,
				success: function(s){
					alert("支付成功！");
					initTable('/listOrder.action?userId='+userId);
				},
				error: function(e){
					alert("支付失败！");
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
					}, {
			          field: 'id',
			          align: 'center',
			          valign: 'middle',
			          visible: false,
			        }, {
			          title: '水果名称',
			          field: 'fruitId',
			          align: 'center',
			          formatter: function (value, row, index) {
			                if (value) {
			                	$.ajax({
			    					type: 'post',
			    					dataType: 'json',
			    					url: '/getFruitById.action',
			    					data: {'id': value},
			    					async: false,
			    					success: function(s){
			    						value = s.fruitName;
			    					},
			    					error: function(e){
			    						value = "";
			    					}
			    				});
			                } else if (value == '1') {
			                	value = "";
			                }
			          	return value;
		              }
			        },{
			          title: ' 购买数量',
			          field: 'purchaseNum',
			          align: 'center',
			          formatter: function (value, row, index) {
		                if (value ) {
    						$.ajax({
		    					type: 'post',
		    					dataType: 'json',
		    					url: '/getFruitById.action',
		    					data: {'id': row.fruitId},
		    					async: false,
		    					success: function(s){
		    						value = value+' '+ s.purchasingUnit;
		    					},
		    					error: function(e){
		    						value = "";
		    					}
		    				});
		                } else if (value == '1') {
		                	value = "";
		                }
			          	return value;
		              }
			        }, {
			          title: '单价',
			          field: 'unitPrice',
			          align: 'center',
			        },{
			          title: '总额',
			          field: 'totalPrice',
			          align: 'center'
			        },{
			          title: '订单状态',
			          field: 'orderState',
			          align: 'center',
			          formatter: function (value, row, index) {
			                if (value == '0') {
	    						value = "未支付";
			                } else if (value == '1') {
			                	value = "已支付";
			                }
			          	return value;
		              }
			        },{
			          title: '下单时间',
			          field: 'createTime',
			          align: 'center',
			          formatter: function (value, row, index) {
		                if (value ) {
    						value = value.replace('T', ' ');
		                } else if (value == '1') {
		                	value = "";
		                }
			          	return value;
		              }
			        },{
			          title: '付款时间',
			          field: 'payTime',
			          align: 'center',
			          formatter: function (value, row, index) {
		                if (value ) {
    						value = value.replace('T', ' ');
		                } else if (value == '1') {
		                	value = "";
		                }
			          	return value;
		              }
			        },{
			          title: '预计送达时间',
			          field: 'planDeliveryTime',
			          align: 'center',
			          formatter: function (value, row, index) {
		                if (value ) {
    						value = value.replace('T', ' ');
		                } else if (value == '1') {
		                	value = "";
		                }
			          	return value;
		              }
			        },{
			          title: '邮费',
			          field: 'postage',
			          align: 'center'
			        }
		        ]]
		    });
		  }
	</script>
</html>
