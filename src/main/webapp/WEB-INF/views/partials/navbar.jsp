<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar">

    <!-- Logo -->
    <div class="logo-section">
        <a href="${pageContext.request.contextPath}/home" class="logo">
            BookNest
        </a>
    </div>

    <!-- Search Bar -->
    <div class="search-section">
        <form action="${pageContext.request.contextPath}/products" method="get">

           <input type="text"
       name="keyword"
       class="search-input"
       placeholder="Search books...">

            <button type="submit" class="search-btn">
                Search
            </button>

        </form>
    </div>

    <!-- Navigation Links -->
    <div class="nav-links">

        <a href="${pageContext.request.contextPath}/home">
            Home
        </a>

        <a href="${pageContext.request.contextPath}/products">
            Products
        </a>

        <a href="${pageContext.request.contextPath}/cart">
            Cart
        </a>

        <a href="${pageContext.request.contextPath}/orders">
            My Orders
        </a>

        <a href="${pageContext.request.contextPath}/login" class="login-btn">
            Login
        </a>

    </div>

</nav>