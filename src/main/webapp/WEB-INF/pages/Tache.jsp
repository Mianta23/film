<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.*,com.example.film.model.*"%>
<%List<Action> action = (List<Action>)request.getAttribute("action");%>
<%List<Personnage> pers = (List<Personnage>)request.getAttribute("personnage");%>
<%List<Tache> tache = (List<Tache>)request.getAttribute("tache");%>
<%int id = (int)request.getAttribute("id");%>
<html>
  <head>
      <link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">
      <link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

      <!-- Google Fonts -->
      <link href="https://fonts.gstatic.com" rel="preconnect">
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

      <!-- Vendor CSS Files -->
      <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.snow.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/vendor/simple-datatables/style.css" rel="stylesheet">

      <!-- Template Main CSS File -->
      <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
      <title>Liste taches</title>
  </head>

  <body>
  <header id="header" class="header fixed-top d-flex align-items-center">

      <div class="d-flex align-items-center justify-content-between">
          <a href="<%= request.getContextPath() %>/" class="logo d-flex align-items-center">
              <img src="assets/img/logo.png" alt="">
              <span class="d-none d-lg-block">E-News</span>
          </a>
          <i class="bi bi-list toggle-sidebar-btn"></i>
      </div><!-- End Logo -->

      <div class="search-bar">
          <form class="search-form d-flex align-items-center" method="get" action="<%= request.getContextPath() %>/search">
              <input type="text" name="search" placeholder="Search" title="Enter search keyword">
              <button type="submit" title="Search"><i class="bi bi-search"></i></button>
          </form>
      </div><!-- End Search Bar -->

      <nav class="header-nav ms-auto">
          <ul class="d-flex align-items-center">

              <li class="nav-item d-block d-lg-none">
                  <a class="nav-link nav-icon search-bar-toggle " href="#">
                      <i class="bi bi-search"></i>
                  </a>
              </li><!-- End Search Icon-->

              <li class="nav-item dropdown">

                  <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                      <i class="bi bi-bell"></i>
                      <span class="badge bg-primary badge-number">4</span>
                  </a><!-- End Notification Icon -->

                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
                      <li class="dropdown-header">
                          You have 4 new notifications
                          <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="notification-item">
                          <i class="bi bi-exclamation-circle text-warning"></i>
                          <div>
                              <h4>Lorem Ipsum</h4>
                              <p>Quae dolorem earum veritatis oditseno</p>
                              <p>30 min. ago</p>
                          </div>
                      </li>

                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="notification-item">
                          <i class="bi bi-x-circle text-danger"></i>
                          <div>
                              <h4>Atque rerum nesciunt</h4>
                              <p>Quae dolorem earum veritatis oditseno</p>
                              <p>1 hr. ago</p>
                          </div>
                      </li>

                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="notification-item">
                          <i class="bi bi-check-circle text-success"></i>
                          <div>
                              <h4>Sit rerum fuga</h4>
                              <p>Quae dolorem earum veritatis oditseno</p>
                              <p>2 hrs. ago</p>
                          </div>
                      </li>

                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="notification-item">
                          <i class="bi bi-info-circle text-primary"></i>
                          <div>
                              <h4>Dicta reprehenderit</h4>
                              <p>Quae dolorem earum veritatis oditseno</p>
                              <p>4 hrs. ago</p>
                          </div>
                      </li>

                      <li>
                          <hr class="dropdown-divider">
                      </li>
                      <li class="dropdown-footer">
                          <a href="#">Show all notifications</a>
                      </li>

                  </ul><!-- End Notification Dropdown Items -->

              </li><!-- End Notification Nav -->

              <li class="nav-item dropdown">

                  <a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
                      <i class="bi bi-chat-left-text"></i>
                      <span class="badge bg-success badge-number">3</span>
                  </a><!-- End Messages Icon -->

                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow messages">
                      <li class="dropdown-header">
                          You have 3 new messages
                          <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="message-item">
                          <a href="#">
                              <img src="assets/img/messages-1.jpg" alt="" class="rounded-circle">
                              <div>
                                  <h4>Maria Hudson</h4>
                                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                                  <p>4 hrs. ago</p>
                              </div>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="message-item">
                          <a href="#">
                              <img src="assets/img/messages-2.jpg" alt="" class="rounded-circle">
                              <div>
                                  <h4>Anna Nelson</h4>
                                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                                  <p>6 hrs. ago</p>
                              </div>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="message-item">
                          <a href="#">
                              <img src="assets/img/messages-3.jpg" alt="" class="rounded-circle">
                              <div>
                                  <h4>David Muldon</h4>
                                  <p>Velit asperiores et ducimus soluta repudiandae labore officia est ut...</p>
                                  <p>8 hrs. ago</p>
                              </div>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li class="dropdown-footer">
                          <a href="#">Show all messages</a>
                      </li>

                  </ul><!-- End Messages Dropdown Items -->

              </li><!-- End Messages Nav -->

              <li class="nav-item dropdown pe-3">

                  <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                      <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
                      <span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>
                  </a><!-- End Profile Iamge Icon -->

                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                      <li class="dropdown-header">
                          <h6>Kevin Anderson</h6>
                          <span>Web Designer</span>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li>
                          <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                              <i class="bi bi-person"></i>
                              <span>My Profile</span>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li>
                          <a class="dropdown-item d-flex align-items-center" href="users-profile.html">
                              <i class="bi bi-gear"></i>
                              <span>Account Settings</span>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li>
                          <a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
                              <i class="bi bi-question-circle"></i>
                              <span>Need Help?</span>
                          </a>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>

                      <li>
                          <a class="dropdown-item d-flex align-items-center" href="#">
                              <i class="bi bi-box-arrow-right"></i>
                              <span>Sign Out</span>
                          </a>
                      </li>

                  </ul><!-- End Profile Dropdown Items -->
              </li><!-- End Profile Nav -->

          </ul>
      </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

      <ul class="sidebar-nav" id="sidebar-nav">

          <li class="nav-item">
              <a class="nav-link collapsed" href="index.html">
                  <i class="bi bi-grid"></i>
                  <span>Dashboard</span>
              </a>
          </li><!-- End Dashboard Nav -->

          <li class="nav-item">
              <a class="nav-link " data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
                  <i class="bi bi-menu-button-wide"></i><span>Components</span><i class="bi bi-chevron-down ms-auto"></i>
              </a>
              <ul id="components-nav" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
                  <li>
                      <a href="<%=request.getContextPath()%>/ListeFilm">
                          <i class="bi bi-circle"></i><span>Accueil</span>
                      </a>
                  </li>
              </ul>
          </li><!-- End Components Nav -->


      </ul>

  </aside><!-- End Sidebar-->
  <main id="main" class="main">

      <div class="pagetitle">
          <h1>Liste des taches</h1>
          <nav>
              <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                  <li class="breadcrumb-item">Scene</li>
                  <li class="breadcrumb-item active">taches</li>
              </ol>
          </nav>
      </div>
      <div class="col-3">
          <h1>Ajouter une tache</h1>
      <form>
          <input type="hidden" id="idscene" name="idscene" value="<%=id%>">
          <div class="form-group">
              <label>Personnage</label>
              <select id="idpers" name="idpers" class="form-select">
                  <%for(Personnage ps : pers){%>
                        <option value="<%=ps.getId()%>"><%=ps.getNomPersonnage()%></option>
                  <%}%>
              </select>
          </div>
          <div class="form-group">
              <label>Type</label>
              <select id="idaction" name="idaction" class="form-select">
                  <%for(Action ac: action){%>
                        <option value="<%=ac.getId()%>"><%=ac.getNomType()%></option>
                  <%}%>
              </select>
          </div>
          <div class="form-group">
              <label>Dialogue</label>
              <input type="text" id="dialogue" name="dialogue" class="form-control">
          </div>
          <div class="form-group">
              <label>Duree</label>
              <input type="time" id="duree" name="duree" class="form-control">
          </div>
          <br>
          <button type="button" class="btn btn-primary"  onclick={actiohn()}>soumettre</button>
      </form>
      </div>

      <p class="h2">Liste:</p>
      <table class="table table-striped">
          <tr>
              <th>Scene</th>
              <th>Personnage</th>
              <th>Action</th>
              <th>Dialogue</th>
              <th>Duree</th>
          </tr>
          <%for(Tache t:tache){%>
          <tr>
              <td><%=t.getScene().getNomScene()%></td>
              <td><%=t.getPersonnage().getNomPersonnage()%></td>
              <td><%=t.getAction().getNomType()%></td>
              <td><%=t.getDescription()%></td>
              <td><%=t.getDuree()%></td>
          </tr>
          <%}%>
      </table>
      <script>
          function actiohn(){
              let formActuStatus = new FormData();
              console.log(document.getElementById("duree").value + ":00");
              alert(document.getElementById("dialogue").value);
              formActuStatus.append('idscene', document.getElementById("idscene").value);
              formActuStatus.append('idpers',document.getElementById("idpers").value);
              formActuStatus.append('idaction',document.getElementById("idaction").value);
              formActuStatus.append('description',document.getElementById("dialogue").value);
              formActuStatus.append('duree',document.getElementById("duree").value + ":00");
              fetch('<%=request.getContextPath()%>/new-tache', {
                  method: 'POST',
                  body: formActuStatus
              }).then(() => alert('reussi'));
          }
      </script>
  </main>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.min.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  </body>
</html>
