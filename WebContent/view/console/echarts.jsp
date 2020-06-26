<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE>
<html>
<head>
<title>ECharts</title>
<script src="${pageContext.request.contextPath}/static/echarts/esl.js"></script>
<script src="${pageContext.request.contextPath}/static/echarts/jquery-3.3.1.js"></script>
</head>

<body>
	<div id="main" style="height:400px"></div>
    <script type="text/javascript">
        // 路径配置
        require.config({
            paths:{ 
                'echarts' : '${pageContext.request.contextPath}/static/echarts/echarts',
                'echarts/chart/bar' : '${pageContext.request.contextPath}/static/echarts/echarts'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            drewEcharts
        );
         function drewEcharts(ec) {
                
                console.log("1");
                // 基于准备好的dom，初始化echarts图表
                myChart = ec.init(document.getElementById('main')); 
                console.log("2");
                var option = {
                    tooltip: {
                        show: true
                    },
                    toolbox: {
            	        show : true,
            	        feature : { 
            	        	/* line是折线图，bar是柱形图*/
            	            magicType: {show: true, type: ['line', 'bar']},
            	        }
            	    },
                    legend: {
                        data:['人数']
                    },
                    xAxis : [
                        {
                            type : 'category',
                            
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            "name":"人数",
                            "type":"bar",
                            
                        }
                    ]
                };
                //加载数据
                loadDATA(option);
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            
        }
        function loadDATA(option){
            $.ajax({
               type : "post",
               async : false, //同步执行
               url : "${pageContext.request.contextPath}/hhh.do",
               data : {},
               dataType : "json", //返回数据形式为json
               success : function(result) {
                          if (result) {
                                 //初始化option.xAxis[0]中的data
                                  option.xAxis[0].data=[];
                                  for(var i=0;i<result.length;i++){
                                    option.xAxis[0].data.push(result[i].name);
                                  }
                                  //初始化option.series[0]中的data
                                  option.series[0].data=[];
                                  for(var i=0;i<result.length;i++){
                                    option.series[0].data.push(result[i].num);
                                  }
                           }
                        }
            });       
       }
    </script>
</body>
</html>
