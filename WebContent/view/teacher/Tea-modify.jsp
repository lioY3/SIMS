<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Student Information</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css" media="all">
</head>
<body>
	<script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
	<script src="${pageContext.request.contextPath}/static/jquery/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/layui/tool.js"></script>


	<div
		style="position: absolute; top: 0; width: 100%; height: 100%; z-index: -1">
		<img src="${pageContext.request.contextPath}/static/image/bg2.JPG" height="100%" width="100%" />
	</div>

	<br>
	<!------------------------------------------------------------搜索栏--------------------------------------------------------->
	<div class="demoTable" style="position: relative; left: 15%">
		姓名：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_name"
				autocomplete="off">
		</div>
		<button
			class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal"
			data-type="reload" id="do_searchname">
			<i class="layui-icon">&#xe615;</i>搜索
		</button>
		&nbsp;&nbsp;学号：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_no"
				autocomplete="off">
		</div>
		<button
			class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal"
			data-type="reload" id="do_searchno">
			<i class="layui-icon">&#xe615;</i>搜索
		</button>
		&nbsp;&nbsp;课程：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_cname" autocomplete="off">
		</div>
		<button
			class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal"
			data-type="reload" id="do_searchcname">
			<i class="layui-icon">&#xe615;</i>搜索
		</button>
	</div>
	<!------------------------------------------------------------------搜索栏------------------------------------------------------------>
	
	<hr class="layui-bg-blue">
	
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	<script type="text/html" id="tea_headerBar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe608;</i> 新&emsp;增
        </button>
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="refresh"  id ="ref" style=" margin-left: 15px">
            <i class="layui-icon">&#xe669;</i> 刷&emsp;新
        </button>
    </div>
	</script>
	<script type="text/html" id="tea_lineBar">
    	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
	</script>
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	
	
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	<div style="text-align: center;">
		<div class="layui-inline">
			<table id="tea-info" lay-filter="tea"></table>
		</div>
	</div>
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	
	<!-------------------------------------------------------------新增表单--------------------------------------------------------->
	<div class="layui-row" id="open_div" style="display: none;">
		<form id="add_form" class="layui-form" action="post" style="margin-top: 20px; align: center;">
			<input type="hidden" name="request_no" id="request_no">

			<div class="layui-form-item">
				<label class="layui-form-label">教师号:</label>
				<div class="layui-inline">
					<input type="text" name="tno" id="form_tno" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名：</label>
				<div class="layui-inline">
					<input type="text" name="tname" id="form_tname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
    			<label class="layui-form-label">性别:</label>
    			<div class="layui-input-block">
     				<input type="radio" name="tsex" id="form_tsex" value="男" title="男"><i class="layui-icon" style="font-size: 17px; color: #1E9FFF;">&#xe662;</i>
     				&emsp;
     				<input type="radio" name="tsex" id="form_tsex" value="女"title="女"><i class="layui-icon"style="font-size: 17px; color: red;">&#xe661;</i>
     			</div>
  			</div>
  			<div class="layui-form-item">
				<label class="layui-form-label">教授课程：</label>
				<div class="layui-inline">
					<input type="text" name="tname" id="form_tname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item" style="margin-left: 40%">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit="" lay-filter="tea_submit">提&ensp;交</button>
					<button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">重&ensp;置</button>
				</div>
			</div>
		</form>
	</div>
	<!-------------------------------------------------------------新增、编辑表单--------------------------------------------------------->
	<script>
		layui.use(['table', 'layer', 'form', 'laypage'], function() {
			var table = layui.table;
			var form = layui.form;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#tea-info',
				id : 'tableOne',
				height : 450,
				width : 1200,
				url : "${pageContext.request.contextPath}/student/test.json" //数据接口
				,
				page : true //开启分页
				,
				method: 'post'//提交方式
				,
				limit : 8//默认展示的每页记录数
				,
				limits : [ 5, 8, 10 ]//可选展示的每页记录数
				,
				size : 'sm'//小尺寸表格
				,
				toolbar: '#tea_headerBar'
				,
				title : '学生信息表'
				,
				cols : [ [ //表头
				{type: 'checkbox', fixed: 'left'}
			     ,{field: 'tno', title: '教师号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'tname', title: '姓名',align:'center'}
				 ,{field: 'tsex', title: '性别',align:'center'}
				 ,{field: 'tcourse', title: '教授课程',align:'center'}
				 ,{
		               fixed: 'right',
		               title: '操作',
		               toolbar: '#tea_lineBar',
		               width: 130,
		               align: 'center'
		             }
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
								tname : send_name.val()
							}
						}
					}, 'data');
				}
			};

			$('#do_searchname').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
			
			/*	--	按教师号重载	--	*/
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
									tno : send_no.val()
								}
							}
						}, 'data');
					}
				};

				$('#do_searchno').on('click', function() {
					var type = $(this).data('type');
					active[type] ? active[type].call(this) : '';
				});
				
				
			/*	--	按教授课程重载	--	*/
				var $ = layui.$, active = {
						reload : function() {
							var send_cname = $('#send_cname');

							//执行重载
							table.reload('tableOne', {
								page : {
									curr : 1//重新从第 1 页开始
								},
								where : {
									key : {
										cname : send_cname.val()
									}
								}
							}, 'data');
						}
					};

					$('#do_searchcname').on('click', function() {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
					});
					
 			/*	--	监听头部工具栏	--	*/
					 table.on('toolbar(tea)', function (obj) {
					    switch (obj.event) {
					        case 'refresh':
					        // 执行一个表格重载即实现刷新功能
					             table.reload('tableOne', {
					                 where: '',
					                 contentType: 'application/x-www-form-urlencoded',
					                 page: {
					                     curr: 1 //重新从第 1 页开始
					                 },
					                 url: "test.json",
					                 method: 'get'
					             });
					             break;
					             // 根据增加行为给form隐藏项赋值
					        case 'add':
					            var data = {};
					            data.request_type = 'post';
					            data.tno=null;
					            data.tname=null;
					            data.tsex=null;
					            data.cname=null;
					            // 调用打开弹层的工具方法
					            open_form("#open_div", data, '增加教师', '380px', '380px');
					            break;
					    }
					});
 			/* -- 监听操作栏  --*/
 						table.on('tool(tea)', function (obj) {
						    var data = obj.data;
						    var layEvent = obj.event;
						    var id = data.tno;
						    switch (layEvent) { 
						        case 'del':
				                    layer.confirm('真的删除该行么', function (index) {
				                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
				                        //向服务端发送删除指令
				                        $.ajax({
				                            type: "get",  //数据提交方式(post/get)
				                            url: "/goods/deleteGood?id=" + tno,  //提交到的url
				                            contentType: "application/json; charset=utf-8",
				                            dataType: "json",//返回的数据类型格式
				                            success: function (result) {
				                                layer.msg(result.msg, {icon: 1, time: 1000});
				                            }, error: function (e) {
				                                console.log(e, 'error');
				                                layer.msg("异常，请再次重试！", {icon: 1, time: 1000});
				                            }
				                        });
				                        layer.close(index);
				                    });
				                    break;
						    }
						});
 					
			/*	-- 监听表单提交-- */
						form.on('submit(tea_submit)', function (data) {
						    var uri = data.field.action;
						    var type = data.field.request_type;
						    $.ajax({
						         type: type,
						         url: '/goods/' + uri,
						         contentType: "application/json; charset=utf-8",
						         data: JSON.stringify(data.field),
						         dataType: "json",
						         success: function (result) {
						            if (result.code == "0") {
						                table.reload('tableOne', {
						                    contentType: 'application/x-www-form-urlencoded',
						                    page: {
						                        curr: 1 //重新从第 1 页开始
						                    },
						                    url: '/goods/goodsList',
						                    method: 'get'
						                });
						                layer.msg('修改成功', {icon: 1, time: 1000});
						            } else {  //失败
						                layer.alert(result.msg, {icon: 2}, function () {
						                    layer.close(index);
						                });
						            }
						        }
						    });
						    layer.close(index);//关闭弹出层
						    return false;
						});
		});
	</script>
</body>
</html>