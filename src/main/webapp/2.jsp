<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title></title>
    <style>
        body,#mapContainer{
            margin:0;
            height:100%;
            width:100%;
            text-align: center;
            font-size:12px;
        }
        .panel{
            position: absolute;
            top:15px;
            right: 15px;
        }
        .qrcodetxt{
            background-color: #0D9BF2;
            padding: 6px;
            color: white;
        }
        .center{
            position: absolute;
            width: 100%;
            bottom: 24px;
        }
        .btmtip {
            cursor: pointer;
            border-radius: 5px;
            background-color: #0D9BF2;
            padding: 6px;
            width: 160px;
            color: white;
            margin: 0 auto;
        }
    </style>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0?v=1.0" />
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=c037fa52ed7f0fbc3aaa96d7dcd7b313&plugin=AMap.ToolBar"></script>
    	<script src="${pageContext.request.contextPath}/AMS/assets/js/jquery.min.js"></script>
    <script>


    
        function init() {


        	$.ajax({
			     //getUrl()+
		        url:'http://117.161.2.2/WeiXinService/User_Login.ashx',
		                    type: 'post',
		                    async: false,
		                    dataType: 'json',//text、json、jsonp
		        data:{"TELNO":'139',"PWD":'123'},
		                beforeSend: function (jqXHR, s) {
		                },
		                dataFilter: function (data, dataType) {
		            		return data;
		                },
		                success: function (data, statusText, jqXHR) {
		                	var type = data.TYPE;
		                	alert(type);
		                	
		                },
		                error: function (jqXHR, textStatus, errorThrown) {
		                },
		                complete: function (jqXHR, textStatus) {
		                }});

            
            var button = document.getElementById('bt');
            map = new AMap.Map("mapContainer");
            AMap.plugin(["AMap.Driving"], function() {
                var drivingOption = {
                    policy:AMap.DrivingPolicy.LEAST_TIME,
                    map:map
                };
                var driving = new AMap.Driving(drivingOption); //构造驾车导航类
                //根据起终点坐标规划驾车路线
                driving.search([{keyword:'北京站',city:'010'},{keyword:'北京大学',city:'010'}],function(status,result){
                    button.onclick = function(){
                        driving.searchOnAMAP({
                            origin:result.origin,
                            destination:result.destination
                        });
                    } 
                });
                
            });
            map.addControl(new AMap.ToolBar());
            if(AMap.UA.mobile){
                document.getElementById('bitmap').style.display='none';
                bt.style.fontSize = '16px';
            }else{
                bt.style.marginRight = '10px';
            }

            
        }
    </script>
</head>
<body onload="init()">
    <div id="mapContainer" ></div>
    <div class='center'>
        <div id='bt' class="btmtip">点击去高德地图</div>
    </div>
    <div class="panel" id='bitmap' style='top:15px'>
        <img src="http://a.amap.com/lbs/static/img/js-drivingonapp.png" style='width:120px;height:120px'>
        <div class='qrcodetxt' style='text-align: center'>手机扫码打开demo</div>
    </div>
</body>
</html>