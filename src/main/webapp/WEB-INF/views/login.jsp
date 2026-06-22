<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp" />
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/auth.css">

<div class="auth-page">

    <div class="auth-container">

        <!-- LEFT SIDE -->

        <div class="auth-left">

            <div class="auth-badge">
                Welcome Back
            </div>

            <h1>
                Login To BookNest
            </h1>

            <p>

                Continue exploring your favourite books,
                manage your cart and track your orders.

            </p>

            
        </div>


        <!-- RIGHT SIDE -->

        <div class="auth-right">

            <h2 class="form-title">
                Login
            </h2>


            <!-- SUCCESS MESSAGE -->

            <%
                String successMessage =
                        (String) request.getAttribute("successMessage");

                if(successMessage != null){
            %>

            <div class="success-message">
                <%= successMessage %>
            </div>

            <%
                }
            %>


            <!-- ERROR MESSAGE -->

            <%
                String errorMessage =
                        (String) request.getAttribute("errorMessage");

                if(errorMessage != null){
            %>

            <div class="error-message">
                <%= errorMessage %>
            </div>

            <%
                }
            %>


            <!-- LOGIN FORM -->

            <form action="${pageContext.request.contextPath}/login"
                  method="post"
                  class="auth-form">

                <div class="form-group">

                    <label>Email</label>

                    <input type="email"
                           name="email"
                           required>

                </div>

                <div class="form-group">

                    <label>Password</label>

                    <input type="password"
                           name="password"
                           required>

                </div>

                <button type="submit"
                        class="auth-btn">

                    Login

                </button>

            </form>


            <!-- REGISTER LINK -->

            <div class="auth-footer">

                Don't have an account?

                <a href="${pageContext.request.contextPath}/register">

                    Register

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>