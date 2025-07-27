<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="">
    <meta name="description" content="Online garden shop">
    <meta name="keywords" content="garden, shop, plants, decorate,
            decoration">


    <!--ios compatibility-->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-title" content="garden Shop">
    <link rel="apple-touch-icon" href="apple-icon-144x144.png">


    <!--Android compatibility-->

    <meta name="mobile-web-app-capable" content="yes">
    <meta name="application-name" content="Garden Shop">
    <link rel="icon" type="${pageContext.request.contextPath}/front/image/png" href="android-icon-192x192.png">

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />


    <!--FONTS-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/front/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <!--THEME CSS-->
    <link href="${pageContext.request.contextPath}/front/css/theme.css" rel="stylesheet" type="text/css" />




    <title>Garden Shop</title>
</head>

<body>

	<jsp:include page="include-front-header.jsp"/>

<main>

    <!--LOCATION MAP START-->
    <section class="location-map" style="background-color: #fff">
        <div class="container">
            <div class="embed-responsive embed-responsive-16by9 b-radius">
                <iframe class="embed-responsive-item" allowfullscreen
                    src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d6729.2175020694!2d20.415230656369506!3d44.83607936847891!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ssr!2srs!4v1534173827565"
                    width="800" height="400" frameborder="0" style="border:0" allowfullscreen></iframe>
            </div>
        </div>
    </section>
    <!--LOCATION MAP ENDS-->





    <!--LOCATION MAIN START-->
    <section class="services-3--bottom-article contact-main-form location-main" style="background-color: #fff">
        <div class="container">
            <h3 class="text-uppercase">location</h3>
            <div class="row d-flex justify-content-between align-items-bottom">
                <div class="col-md-6 col-lg-6 mb-4 mb-md-0">
                    <article class="contact-details text-uppercase">
                        <div class="contact-group">
                            <p>Address:</p>
                            <a href="#">Tyche collins, 09 downtown, victoria, australia</a>
                        </div>
                        <div class="contact-group">
                            <p>Phone 1: <a href="#">(+01) 123 456 7896</a></p>
                            <p>Phone 2: <a href="#">(+09) 123 456 7890</a></p>
                        </div>
                        <div class="contact-group">
                            <p class="d-inline-block">E-mail:</p>
                            <a class="lead mb-2 mb-lg-3" href="mailto:gardenshop@gmail.com">gardenshop@gmail.com</a>
                        </div>
                        <div class="contact-group">
                            <p>RADNO VREME:</p>
                            <p class="contact-medium">Monday-Friday : 9:00 AM to 7:00 PM</p>
                            <p class="contact-medium">Sunday : Closed</p>
                        </div>
                    </article>
                </div>
                <div class="col-md-6 col-lg-6 text-center">
                    <figure class="location-zoom-img d-inline-block">
                        <img src="${pageContext.request.contextPath}/front/img/services/service-3-img.png" alt="" />
                        <span class="zoom-img">
                            <img src="${pageContext.request.contextPath}/front/img/zoom/icon.png" alt="" />
                        </span>
                    </figure>
                </div>
            </div>
        </div>

    </section>
    <!--LOCATION MAIN ENDS-->

</main>

<!--FOOTER START-->
<footer style="background-color: #404040">
    <div class="container">
        <a href="#header" id="footerButton" class="d-inline-block text-uppercase btn btn-warning">back to top<span
                class="d-inline-block fa fa-angle-up"></span></a>
        <div class="row text-center d-flex align-items-center">
            <div class="col-12 col-md-6 col-xl-4 text-md-left mb-3 mb-md-0">
                <a class="footer-brand d-inline-block">
                    <img src="${pageContext.request.contextPath}/front/img/logo.png" alt="" />
                </a>
            </div>
            <div class="col-12 col-md-6 col-xl-4 text-md-right">
                <div class="footer-info-wrapper mb-1 mb-md-0">
                    <a class="d-inline-block mr-2 mr-lg-4 mr-xl-5" href="#">
                    	<img class="mobile"
                            src="${pageContext.request.contextPath}/front/img/icons/mobile.png" alt="" />(+01) 123 456 7896</a>
                    <a class="d-inline-block" href="mailto:gardenshop@gmail.com"><img class="mail"
                            src="${pageContext.request.contextPath}/front/img/icons/mail.png" alt="" />gardenshop@gmail.com</a>
                </div>
            </div>
            <div class="col-12 col-md-12 col-xl-4 text-md-right align-self-lg-center">
                <p><img class="clock" src="${pageContext.request.contextPath}/front/img/icons/clock.png" alt="" />Monday-Friday : 9:00 AM to 7:00 PM Sunday :
                    Closed</p>
            </div>
        </div>
    </div>
</footer>
<!--FOOTER ENDS-->









<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/front/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/popper.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/owl.carousel.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/front/js/jquery.zoom.min.js" type="text/javascript"></script>


<script src="${pageContext.request.contextPath}/front/js/main.js" type="text/javascript"></script>


</body>

</html>