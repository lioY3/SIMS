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
		&nbsp;&nbsp;班级：
		<div class="layui-inline">
			<input class="layui-input" name="keyword" id="send_class" autocomplete="off">
		</div>
		<button
			class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal"
			data-type="reload" id="do_searchclass">
			<i class="layui-icon">&#xe615;</i>搜索
		</button>
	</div>
	<!------------------------------------------------------------------搜索栏------------------------------------------------------------>
	
	
	<hr class="layui-bg-blue">
	
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	<script type="text/html" id="Stu_headerBar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="add">
            <i class="layui-icon">&#xe608;</i> 新&emsp;增
        </button>
        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-event="refresh"  id ="ref" style=" margin-left: 15px">
            <i class="layui-icon">&#xe669;</i> 刷&emsp;新
        </button>
    </div>
	</script>
	<script type="text/html" id="Stu_lineBar">
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="edit"><i class="layui-icon ">&#xe642;</i> </a>
    	<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i></a>
        </button>
	</script>
	<!---------------------------------------------------新增、刷新、编辑、删除按钮-------------------------------------------------------->
	
	
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	<div style="text-align: center;">
		<div class="layui-inline">
			<table id="stu-info" lay-filter="Stu"></table>
		</div>
	</div>
	<!-------------------------------------------------------------数据表格--------------------------------------------------------------->
	
	<!-------------------------------------------------------------新增、编辑表单--------------------------------------------------------->
	<div class="layui-row" id="open_div" style="display: none;">
		<form id="add_form" class="layui-form" action="" style="margin-top: 20px; align: center;">
			<!--隐藏字段action，用来区分增加和编辑行为-->
			<input type="hidden" name="action" id="action">
			<!--隐藏字段request_type，用于提供请求方式:get,post,put-->
			<input type="hidden" name="request_type" id="request_type">
			<input type="hidden" name="request_Sno" id="request_Sno">

			<div class="layui-form-item">
				<label class="layui-form-label">学号:</label>
				<div class="layui-inline">
					<input type="text" name="Sno" id="form_Sno" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名：</label>
				<div class="layui-inline">
					<input type="text" name="Sname" id="form_Sname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
    			<label class="layui-form-label">单选框</label>
    			<div class="layui-input-block">
     				<input type="radio" name="Ssex" id="form_Ssex" value="男" title="男"><i class="layui-icon" style="font-size: 17px; color: #1E9FFF;">&#xe662;</i>
     				&emsp;
     				<input type="radio" name="Ssex" id="form_Ssex" value="女"title="女"><i class="layui-icon"style="font-size: 17px; color: red;">&#xe661;</i>
     			</div>
  			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">生日：</label>
					<div class="layui-input-inline">
				        <input type="text"  name="Sbirthday" id="form_birthday" class="layui-input" placeholder="yyyy-MM-dd">
				    </div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">身份证:</label>
				<div class="layui-inline">
					<input type="text" name="Sid" id="form_Sid" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学院:</label>
				<div class="layui-inline">
					<input type="text" name="Dname" id="form_Dname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">班级:</label>
				<div class="layui-inline">
					<input type="text" name="Clname" id="form_Clname" class="layui-input" autocomplete="off">
				</div>
			</div>
			<div class="layui-form-item" style="margin-left: 40%">
				<div class="layui-btn-group">
					<button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit="" lay-filter="Stu_submit">提&ensp;交</button>
					<button type="reset" class="layui-btn layui-btn-primary layui-btn-sm">重&ensp;置</button>
				</div>
			</div>
		</form>
	</div>
	<script>
			layui.use('laydate', function(){
			  var laydate = layui.laydate;
			  //执行一个laydate实例
			  laydate.render({
			    elem: '#form_birthday' 
			  });
			});
	</script>
	<!-------------------------------------------------------------新增、编辑表单--------------------------------------------------------->
	<script>
		layui.use(['table', 'layer', 'form', 'laypage'], function() {
			var table = layui.table;
			var form = layui.form;
			
		/*		-- 表格实例	 --		*/		
			table.render({
				elem : '#stu-info',
				id : 'tableOne',
				height : 450,
				width : 1200,
				url : "test.json" //数据接口
				,
				page : true //开启分页
				,
				limit : 8//默认展示的每页记录数
				,
				limits : [ 5, 8, 10 ]//可选展示的每页记录数
				,
				size : 'sm'//小尺寸表格
				,
				toolbar: '#Stu_headerBar'
				,
				title : '学生信息表'
				,
				cols : [ [ //表头
				{type: 'checkbox', fixed: 'left'}
			     ,{field: 'Sno', title: '学号', sort: true, fixed: 'left',align:'center'}
				 ,{field: 'Sname', title: '姓名',align:'center'}
				 ,{field: 'Ssex', title: '性别',align:'center'}
				 ,{field: 'Snation', title: '民族',align:'center'}
				 ,{field: 'Sbirthday', title: '出生日期', sort: true,align:'center'} 
				 ,{field: 'Sid', title: '身份证',align:'center'}
				 ,{field: 'Dname', title: '学院',align:'center'}
				 ,{field: 'Clname', title: '班级', sort: true,align:'center'} 
				 ,{
		               fixed: 'right',
		               title: '操作',
		               toolbar: '#Stu_lineBar',
		               width: 160,
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
					
 			/*	--	监听头部工具栏	--	*/
					 table.on('toolbar(Stu)', function (obj) {
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
					            data.action = 'addStu';
					            data.request_type = 'post';
					            data.Sno=null;
					            data.Sname=null;
					            data.Ssex=null;
					            data.Sbirthday=null;
					            data.Sid=null;
					            data.Dname=null;
					            data.Clname=null;
					            // 调用打开弹层的工具方法
					            open_form("#open_div", data, '增加学生', '380px', '550px');
					            break;
					    }
					});
 			/* -- 监听操作栏  --*/
 						table.on('tool(Stu)', function (obj) {
						    var data = obj.data;
						    var layEvent = obj.event;
						    var id = data.Sno;
						    switch (layEvent) {
						        case 'edit':
						            // 根据编辑行为为form隐藏项赋值
						            data.action = 'updateStu';
						            data.request_type = 'post';
						            data.request_Sno = id;
						            open_form("#open_div", data, '编辑学生', '380px', '550px');
						            break; 
						        case 'del':
				                    layer.confirm('真的删除该行么', function (index) {
				                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
				                        //向服务端发送删除指令
				                        $.ajax({
				                            type: "get",  //数据提交方式(post/get)
				                            url: "/goods/deleteGood?id=" + Sno,  //提交到的url
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
						form.on('submit(Stu_submit)', function (data) {
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