<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/partials/header.jsp" />
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/auth.css">

<div class="auth-page">

    <div class="auth-container register-container">

        <!-- LEFT SIDE -->

        <div class="auth-left">

            <div class="auth-badge">
                Join BookNest
            </div>

            <h1>
                Create Your Account
            </h1>

            <p>

                Start your reading journey with thousands
                of books across multiple categories.

            </p>

           
        </div>


        <!-- RIGHT SIDE -->

        <div class="auth-right">

            <h2 class="form-title">
                Register
            </h2>


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


            <!-- REGISTER FORM -->

            <form action="${pageContext.request.contextPath}/register"
                  method="post"
                  class="auth-form">

                <div class="form-group">

                    <label>Full Name</label>

                    <input type="text"
                           name="fullName"
                           required>

                </div>

                <div class="form-group">

                    <label>Email</label>

                    <input type="email"
                           name="email"
                           required>

                </div>

                <div class="form-group">

                    <label>Phone</label>

                    <input type="text"
                           name="phone"
                           required>

                </div>

                <div class="form-group">

                    <label>Password</label>

                    <input type="password"
                           name="password"
                           required>

                </div>

                <div class="form-group">

                    <label>Address Line 1</label>

                    <input type="text"
                           name="addressLine1"
                           required>

                </div>

                <div class="form-group">

                    <label>Address Line 2</label>

                    <input type="text"
                           name="addressLine2">

                </div>

                <div class="form-row">

                    <div class="form-group">

                        <label>City</label>

                        <input type="text"
                               name="city"
                               required>

                    </div>

                    <div class="form-group">

                        <label>State</label>

                        <input type="text"
                               name="state"
                               required>

                    </div>

                </div>

                <div class="form-row">

                    <div class="form-group">

                        <label>Postal Code</label>

                        <input type="text"
                               name="postalCode"
                               required>

                    </div>

                    <div class="form-group">

                        <label>Country</label>

                        <input type="text"
                               name="country"
                               required>

                    </div>

                </div>

                <button type="submit"
                        class="auth-btn">

                    Create Account

                </button>

            </form>


            <!-- LOGIN LINK -->

            <div class="auth-footer">

                Already have an account?

                <a href="${pageContext.request.contextPath}/login">

                    Login

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>