<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>成绩信息查询</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static//layui/css/layui.css" media="all">
</head>
<body>
<div style="position:absolute; top:0; width:100%; height:100%; z-index:-1">   
	<img src="${pageContext.request.contextPath}/static/image/bg2.JPG" height="100%" width="100%"/>   
</div>
	
	<br>
	<br>
	<br>
	<div class="demoTable" style="position:relative;left:16%">
		学号：
		<div class="layui-inline">
			<input class="layui-input" name="cno" id="send_no"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchno">
		<i class="layui-icon">&#xe615;</i>搜索</button>
		课程：
		<div class="layui-inline">
			<input class="layui-input" name="cname" id="send_name"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchname">
		<i class="layui-icon">&#xe615;</i>搜索</button>
	</div>
	

	<div style="text-align: center;">
        <div class="layui-inline">
			<table id="score-info" lay-filter="test"></table>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	
	<script>
		layui.use('table', function() {
			var table = layui.table;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#score-info',
				id : 'tableOne',
				height : 500,
				width : 1300,
				url : "${pageContext.request.contextPath}/StudentServlet?method=StudentList", //数据接口
				method: 'post',
				page : true, //开启分页
				
				limit : 14,//默认展示的每页记录数
				
				limits : [ 14,20,25 ],//可选展示的每页记录数
				
				size : 'sm',//小尺寸表格
				
				cols : [ [ //表头
			      {field: 'sno', title: '学号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'sname', title: '姓名',align:'center'}
				 ,{field: 'cno', title: '课程号',align:'center'}
				 ,{field: 'cname', title: '课程名',align:'center'}
				 ,{field: 'tname', title: '授课教师',align:'center'} 
				 ,{field: 'score', title: '成绩',sort: true,align:'center'} 
				] ]
			});
		
				
			/*	--	搜索重载	--	*/
				var $ = layui.$, active = {
						reload : function() {
							var send_no = $('#send_no');
							var send_name = $('#send_name');
							//执行重载
							table.reload('tableOne', {
								page : {
									curr : 1//重新从第 1 页开始
								},
								where : {
									key : {
										Sno : send_no.val(),
										Sname : send_name.val()
									}
								}
							}, 'data');
						}
					};

					$('#do_searchno').on('click', function() {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
			});
					$('#do_searchname').on('click', function() {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
			});
		});
	</script>
</body>
</html>