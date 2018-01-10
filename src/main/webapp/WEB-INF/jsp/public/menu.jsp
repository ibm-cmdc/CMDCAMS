<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
	function clisubmenu(oid){
		
		$('.tpl-left-nav-menu a').each(function(index,ele){
		
            if($(ele).hasClass("nav-link active")){
            	$(ele).removeClass("nav-link active");
            	$(ele).addClass("nav-link tpl-left-nav-link-list");
            }else if($(ele).hasClass("active")){
            	$(ele).removeClass("active");
            };
     	});

		 $("#"+oid).addClass("active");
		 $("#"+oid).parent().parent().prev("a").addClass("nav-link active");
		// $("#"+oid).parent().parent().prev("a").lastChild.attr("class", "am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate");
		 $("#"+oid).parent().parent().css('display','block')
		//var a = document.getElementById(oid);
		//a.setAttribute("class","active");
	};

	 function getmenu(right){
		 var rights;
		 $.ajax({
 		   url:'${pageContext.request.contextPath}/getmenu',
             type: 'post',
             async: false,
             dataType: 'text',//text、json、jsonp
 			 data:{rights:right},
         beforeSend: function (jqXHR, s) {
         },
         dataFilter: function (data, dataType) {
     		return data;
         },
         success: function (data, statusText, jqXHR) {
        	 rights =  data;
         },
         error: function (jqXHR, textStatus, errorThrown) {
         	
         },
         complete: function (jqXHR, textStatus) {
         }});

         return rights;
	};

	$(function(){ 

		 var v_right = '<%=session.getAttribute("v_right")%>' ;
		 var v_menuid = '<%=session.getAttribute("v_menuid")%>' ;
		
		 var ms = getmenu(v_right);
		 var menus = eval(ms);
		 var menu = document.getElementById('menu');
		 var tyurl = '${pageContext.request.contextPath}';

		 for(var num=0;num<menus.length;num++){
			 var li = document.createElement("li");
			 li.setAttribute("class","tpl-left-nav-item");
			 var a = document.createElement("a");
			 a.setAttribute("class","nav-link tpl-left-nav-link-list");  //nav-link active
			 a.setAttribute("id",menus[num].MENU_ID);
			 a.setAttribute("onclick","clisubmenu("+menus[num].MENU_ID+")");
			 a.setAttribute("href",tyurl+menus[num].MENU_URL+"?menuid="+menus[num].MENU_ID);
			 var i = document.createElement("i");
			 i.setAttribute("class",menus[num].MENU_ICON);
			 var span = document.createElement("span");
			 span.innerHTML= menus[num].MENU_NAME;
			 
			 a.appendChild(i);
			 a.appendChild(span);
			 
			 
			 var submenu = menus[num].subMenu;
			 var isdefualt = false;
			 
			 if(submenu.length>0){
				 var sul = document.createElement("ul");
				 sul.setAttribute("class","tpl-left-nav-sub-menu");
				// sul.setAttribute("style","display: block");
				 var sli = document.createElement("li");
				 
				 for(var snum=0;snum<submenu.length;snum++){

					 if(v_menuid==submenu[snum].MENU_ID){
						 isdefualt=true;
					 };
					 
					 var sa = document.createElement("a");
					 sa.setAttribute("class","");  // active
					 sa.setAttribute("id",submenu[snum].MENU_ID);
					 sa.setAttribute("onclick","clisubmenu("+submenu[snum].MENU_ID+")");
					 if('#'==submenu[snum].MENU_URL){
						 sa.setAttribute("href","javascript:;");
					 }else{
						 sa.setAttribute("href",tyurl+submenu[snum].MENU_URL+"?menuid="+submenu[snum].MENU_ID);
					 };
					 
					 var si = document.createElement("i");
					 si.setAttribute("class","am-icon-angle-right");
					 var sspan = document.createElement("span");
					 sspan.innerHTML= submenu[snum].MENU_NAME;
					 sa.appendChild(si);
					 sa.appendChild(sspan);
					 sli.appendChild(sa);
				 };
				 
				 var ei = document.createElement("i");
				 if(isdefualt){
					 ei.setAttribute("class","am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate");
			     }else{
			    	 ei.setAttribute("class","am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right");
				 };
				 
				 a.appendChild(ei);
				 sul.appendChild(sli);
				//有子菜单不设置URL跳转
				 a.setAttribute("href","javascript:;");
				 li.appendChild(a);
				 li.appendChild(sul);
				 
			 }else{
				 li.appendChild(a);
		 	 };
			 
			 menu.appendChild(li);
		 };



	    // ==========================
	    // 侧边导航下拉列表
	    // ==========================

	    $('.tpl-left-nav-link-list').on('click', function() {
	        $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
	            .end()
	            .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
	    });


	     
		 if(v_menuid.length>0){
			 clisubmenu(v_menuid);
		 };
		 
	});



</script>

	<!-- 左侧菜单 -->

        <div class="tpl-left-nav tpl-left-nav-hover">
            <div class="tpl-left-nav-title">
                	实物资产系统
            </div>
            <div class="tpl-left-nav-list">
                <ul id="menu" class="tpl-left-nav-menu">
                    
                </ul>
            </div>
        </div>

	<!-- 左侧菜单 -->
