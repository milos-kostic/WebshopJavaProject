
<!--  taglib za security: -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- prefix security -->

 <!-- Brand Logo -->
    <a href="http://localhost:8080/MyShopProject/admin/" class="brand-link">
      <img src="${pageContext.request.contextPath}/admin/dist/img/AdminLTELogo.png" alt="Cubes School Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">Cubes School</span>
    </a>
    
    
 <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
         
         
		         <sec:authorize access="hasRole('admin')">
		          <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Categories
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="category-list" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Categories list</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="category-form" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Add Category</p>
		                </a>
		              </li>
		            </ul>
		          </li>
		       
		          
          
	          <li class="nav-item has-treeview">
	            <a href="#" class="nav-link">
	              <i class="nav-icon far fa-plus-square"></i>
	              <p>
	                Stickers
	                <i class="right fas fa-angle-left"></i>
	              </p>
	            </a>
	            <ul class="nav nav-treeview">
	              <li class="nav-item">
	                <a href="sticker-list" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Stickers list</p>
	                </a>
	              </li>
	              <li class="nav-item">
	                <a href="sticker-form" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Add Sticker</p>
	                </a>
	              </li>
	            </ul>
	          </li>          
	          
	          
	          <li class="nav-item has-treeview">
	            <a href="#" class="nav-link">
	              <i class="nav-icon far fa-plus-square"></i>
	              <p>
	                Users
	                <i class="right fas fa-angle-left"></i>
	              </p>
	            </a>
	            <ul class="nav nav-treeview">
	              <li class="nav-item">
	                <a href="user-list" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Users list</p>
	                </a>
	              </li>
	              <li class="nav-item">
	                <a href="user-form" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Add User</p>
	                </a>
	              </li>
	            </ul>
	          </li>          
	          
	         </sec:authorize>
	             
	             
	             
             
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Product
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="product-list#" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Product list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="product-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Product</p>
                </a>
              </li>
            </ul>
          </li>
          
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Other
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="message-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Message list</p>
                </a>
              </li>
            
            </ul>    
            
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="slider-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Slider list</p>
                </a>
              </li>             
            </ul>   
                    
                    
             
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="static-page-form?page=about" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Edit Anout us page</p>
                </a>
              </li>             
            </ul>              
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="static-page-form?page=location" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Edit Location page</p>
                </a>
              </li>             
            </ul>   
            
            
          </li>
           
          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->

 