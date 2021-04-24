<%@page import="BEAN.Role"%>
<%@page import="BEAN.MyUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="apple-touch-icon" sizes="76x76"
	href="<%=request.getContextPath()%>/Template/Admin/img/apple-icon.png">
<link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/Template/Admin/img/favicon.png">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<title>Edit Product</title>
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,600,700,800"
	rel="stylesheet" />
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<!-- Nucleo Icons -->
<link
	href="<%=request.getContextPath()%>/Template/Admin/css/nucleo-icons.css"
	rel="stylesheet" />
<!-- CSS Files -->
<link
	href="<%=request.getContextPath()%>/Template/Admin/css/black-dashboard.css?v=1.0.0"
	rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="<%=request.getContextPath()%>/Template/Admin/demo/demo.css"
	rel="stylesheet" />
</head>
<body>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("USERMODEL")==null)
    response.sendRedirect("/Laptop/Login");
if(session.getAttribute("USERMODEL")!=null) {
	MyUser myUser =(MyUser) session.getAttribute("USERMODEL");
	Role role = myUser.getRole();
	if(role.getRoleName().equals("ROLE_ADMIN")==false){
		response.sendRedirect("/Laptop/Login");
	}
}
%>


	<div class="wrapper">

		<!-- Begin menu -->
		<jsp:include page="Menu.jsp" />
		<!-- End menu -->

		<div class="main-panel" data="blue">


			<!-- Begin Header -->
			<jsp:include page="Header.jsp" />
			<!--  End Header -->


			<div class="content">
				<div class="row">
					<div class="col-md-8">
            <div class="card">
            
              <div class="card-header">
                <h5 class="title">Edit Product</h5>
              </div>
              
              <div class="card-body">
              
              <c:forEach items="${list}" var="lis">
              
                <form method = "GET" action="<%=request.getContextPath()%>/admin/updateproductbyid">
                
                  <%-- <div class="row">
                  
                   <div class="col-md-5 pr-md-1">
                      <div class="form-group">
                        <label>Id Of Product</label>
                        <input type="text" class="form-control" disabled="" name="id" value="${lis.id}">
                      </div>
                    </div>
                    
                  </div> --%>
                  <div class="row">
                  
                
                      <div class="col-md-12">
                      <div class="form-group">
                        <label>Id Product</label>
                        <input type="text" readonly="true"  class="form-control" name="id" placeholder="Name" value="${lis.id}">
                      </div>
                    </div>
                    
                    <div class="col-md-6 pr-md-1">
                      <div class="form-group">
                        <label>Name Product</label>
                        <input type="text" class="form-control" name="name" placeholder="Name" value="${lis.name}">
                      </div>
                    </div>
                    
                    <div class="col-md-6 pl-md-1">
                      <div class="form-group">
                        <label>Price</label>
                        <input type="text" class="form-control" name="price" placeholder="Price" value="${lis.price}">
                      </div>
                    </div>
                    
                    <div class="col-md-6 pr-md-1">
                      <div class="form-group">
                        <label>Warranty</label>
                        <input type="text" class="form-control"  name = "warranty" placeholder="Warranty" value="${lis.warranty}">
                      </div>
                    </div>
                    
                    
                    <div class="col-md-6 pl-md-1">
                      <div class="form-group">
                        <label>Promotion Price</label>
                        <input type="text" class="form-control" name="promotionPrice" placeholder="Promotion Price" value="${lis.promotionPrice}">
                      </div>
                    </div>
                    
                    
                    <div class="col-md-6 pr-md-1">
                      <div class="form-group">
                        <label>Date Created</label>
                        <input type="text"	disabled="" class="form-control" placeholder="Date Created" value="${lis.dateCreated}">
                      </div>
                    </div>
                    
                    
                    <div class="col-md-6 pl-md-1">
                      <div class="form-group">
                        <label>User Created</label>
                        <input type="text"	disabled="" class="form-control" placeholder="User Created" value="${lis.userCreated}">
                      </div>
                    </div>
                    
                    <div class="col-md-6 pr-md-1">
                      <div class="form-group">
                        <label>Date Modified</label>
                        <input type="text" 	disabled=""class="form-control" placeholder="Date Modified" value="${lis.dateModified}">
                      </div>
                    </div>
                    
                    
                    <div class="col-md-6 pl-md-1">
                      <div class="form-group">
                        <label>User Modified</label>
                        <input type="text"	disabled="" class="form-control" placeholder="User Modified" value="${lis.userModified}">
                      </div>
                    </div>
                    
                  </div>

                  <div class="card-footer">
                  
                <button type="submit" class="btn btn-fill btn-primary">Save</button>
                
                <a class="btn btn-fill btn-success" href="/Laptop/admin/products?page=1"> Cancel </a>
                <c:if test="${lis.status == true}">
                	<a class="btn btn-fill btn-info" href="/Laptop/admin/unlockproduct?status=true&id=${lis.id}"> Lock </a>
                </c:if>
                <c:if test="${lis.status == false}">
                	 <a class="btn btn-fill btn-light" href="/Laptop/admin/unlockproduct?status=false&id=${lis.id}"> Unlock </a>
                </c:if>
                <a class="btn btn-fill btn-danger" href="/Laptop/admin/deleteproduct?id=${lis.id}"> Delete </a>
              </div>
                </form>
                </c:forEach>
              </div>
              
            </div>
            </div>
					<div class="col-md-4">
            <div class="card card-user">
              <div class="card-body">
                <p class="card-text">
                  <div class="author">
                    <div class="block block-one"></div>
                    <div class="block block-two"></div>
                    <div class="block block-three"></div>
                    <div class="block block-four"></div>
                   	<c:forEach items="${list}" var="lis">
	                   	<form action="<%=request.getContextPath()%>/admin/UploadAvatarProduct"  enctype="multipart/form-data" method = "POST">
	                   	 <input  type="file" name="file" class="file" accept="image/*" /> <img id="preview" src="<%=request.getContextPath()%>/image/${lis.image}" />
						 <input type="hidden" name="idOfProductt"  value="${lis.id}" />                	
	                   	<button type="submit" class="btn btn-fill btn-primary">Save</button>
	                   	</form>
                    </c:forEach>
                  </div>
              </div>
            </div>
          </div>
				</div>
			</div>
			<!--Begin footer -->
			<jsp:include page="Footer.jsp" />
			<!-- End Footer -->
		</div>
	</div>


	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/core/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/core/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/core/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/plugins/perfect-scrollbar.jquery.min.js"></script>
	<!--  Google Maps Plugin    -->
	<!-- Place this tag in your head or just before your close body tag. -->
	<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
	<!-- Chart JS -->
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/plugins/chartjs.min.js"></script>
	<!--  Notifications Plugin    -->
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/plugins/bootstrap-notify.js"></script>
	<!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
	<script
		src="<%=request.getContextPath()%>/Template/Admin/js/black-dashboard.min.js?v=1.0.0"></script>
	<!-- Black Dashboard DEMO methods, don't include it in your project! -->
	<script src="<%=request.getContextPath()%>/Template/Admin/demo/demo.js"></script>
	<script>
	
	$(document).on("click", ".browse", function() {
		  var file = $(this).parents().find(".file");
		  file.trigger("click");
		});
		$('input[type="file"]').change(function(e) {
		  var fileName = e.target.files[0].name;
		  $("#file").val(fileName);

		  var reader = new FileReader();
		  reader.onload = function(e) {
		    // get loaded data and render thumbnail.
		    document.getElementById("preview").src = e.target.result;
		  };
		  // read the image file as a data URL.
		  reader.readAsDataURL(this.files[0]);
		});
	
    $(document).ready(function() {
      $().ready(function() {
        $sidebar = $('.sidebar');
        $navbar = $('.navbar');
        $main_panel = $('.main-panel');

        $full_page = $('.full-page');

        $sidebar_responsive = $('body > .navbar-collapse');
        sidebar_mini_active = true;
        white_color = false;

        window_width = $(window).width();

        fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



        $('.fixed-plugin a').click(function(event) {
          if ($(this).hasClass('switch-trigger')) {
            if (event.stopPropagation) {
              event.stopPropagation();
            } else if (window.event) {
              window.event.cancelBubble = true;
            }
          }
        });

        $('.fixed-plugin .background-color span').click(function() {
          $(this).siblings().removeClass('active');
          $(this).addClass('active');

          var new_color = $(this).data('color');

          if ($sidebar.length != 0) {
            $sidebar.attr('data', new_color);
          }

          if ($main_panel.length != 0) {
            $main_panel.attr('data', new_color);
          }

          if ($full_page.length != 0) {
            $full_page.attr('filter-color', new_color);
          }

          if ($sidebar_responsive.length != 0) {
            $sidebar_responsive.attr('data', new_color);
          }
        });

        $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (sidebar_mini_active == true) {
            $('body').removeClass('sidebar-mini');
            sidebar_mini_active = false;
            blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
          } else {
            $('body').addClass('sidebar-mini');
            sidebar_mini_active = true;
            blackDashboard.showSidebarMessage('Sidebar mini activated...');
          }

          // we simulate the window Resize so the charts will get updated in realtime.
          var simulateWindowResize = setInterval(function() {
            window.dispatchEvent(new Event('resize'));
          }, 180);

          // we stop the simulation of Window Resize after the animations are completed
          setTimeout(function() {
            clearInterval(simulateWindowResize);
          }, 1000);
        });

        $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
          var $btn = $(this);

          if (white_color == true) {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').removeClass('white-content');
            }, 900);
            white_color = false;
          } else {

            $('body').addClass('change-background');
            setTimeout(function() {
              $('body').removeClass('change-background');
              $('body').addClass('white-content');
            }, 900);

            white_color = true;
          }

        });

        $('.light-badge').click(function() {
          $('body').addClass('white-content');
        });

        $('.dark-badge').click(function() {
          $('body').removeClass('white-content');
        });
      });
    });
  </script>
	<script src="https://cdn.trackjs.com/agent/v3/latest/t.js"></script>
	<script>
    window.TrackJS &&
      TrackJS.install({
        token: "ee6fab19c5a04ac1a32a645abde4613a",
        application: "black-dashboard-free"
      });
  </script>
</body>
</html>