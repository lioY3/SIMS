<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Information</title>
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
</head>
<body>
<div style="position:absolute; top:0; width:100%; height:100%; z-index:-1">   
	<img src="../image/bg2.JPG" height="100%" width="100%"/>   
</div>
	
	<br>
	<br>
	<br>
	<div class="demoTable" style="position:relative;left:18%">
		姓名：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_name"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchname">搜索</button>
		学号：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_no"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchno">搜索</button>
		班级：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_class"
				autocomplete="off">
		</div>
		<button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" data-type="reload" id="do_searchclass">搜索</button>
	</div>
	

	<div style="text-align: center;">
        <div class="layui-inline">
			<table id="stu-info" lay-filter="test"></table>
		</div>
	</div>
	<script src="../layui/layui.js"></script>
	
	<script>
		layui.use('table', function() {
			var table = layui.table;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#stu-info',
				id : 'tableOne',
				height : 450,
				width : 900,
				url : "testdata.json" //数据接口
				,
				page : true //开启分页
				,
				limit : 8//默认展示的每页记录数
				,
				limits : [ 5, 8, 10 ]//可选展示的每页记录数
				,
				size : 'sm'//小尺寸表格
				,
				cols : [ [ //表头
/* 				{
					field : 'id',
					width : 80,
					title : 'ID',
					sort : true
				}, {
					field : 'username',
					width : 80,
					title : '用户名'
				}, {
					field : 'sex',
					width : 80,
					title : '性别',
					sort : true
				}, {
					field : 'city',
					width : 80,
					title : '城市'
				}, {
					field : 'sign',
					title : '签名',
					width : 80
				}, {
					fixed : 'right',
					title : '操作',
					toolbar : '#goods_lineBar',
					width : 80,
					align : 'center'
				} */
			     {field: 'Sno', title: '学号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'Sname', title: '姓名',align:'center'}
				 ,{field: 'Ssex', title: '性别',align:'center'}
				 ,{field: 'Snation', title: '民族',align:'center'}
				 ,{field: 'Sbirthday', title: '出生日期', sort: true,align:'center'} 
				 ,{field: 'Dname', title: '学院',align:'center'}
				 ,{field: 'Clname', title: '班级', sort: true,align:'center'} 
				] ]
			});
		
			/*	--	按姓名重载	--	*/
			var $ = layui.$, active = {
				reload : function() {
					var send_name = $('#send_name');

					//执行重载
					table.reload('tableOne', {
						page : {
							curr : 1//重新从第 1 页开始
						},
						where : {
							key : {
								Sname : send_name.val()
							}
						}
					}, 'data');
				}
			};

			$('#do_searchname').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			
			/*	--	按学号重载	--	*/
			var $ = layui.$, active = {
					reload : function() {
						var send_no = $('#send_no');

						//执行重载
						table.reload('tableOne', {
							page : {
								curr : 1//重新从第 1 页开始
							},
							where : {
								key : {
									Sno : send_no.val()
								}
							}
						}, 'data');
					}
				};

				$('#do_searchno').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
				
				
			/*	--	按班级重载	--	*/
				var $ = layui.$, active = {
						reload : function() {
							var send_class = $('#send_class');

							//执行重载
							table.reload('tableOne', {
								page : {
									curr : 1//重新从第 1 页开始
								},
								where : {
									key : {
										Clname : send_class.val()
									}
								}
							}, 'data');
						}
					};

					$('#do_searchclass').on('click', function() {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
					});
		});
	</script>
</body>
</html>