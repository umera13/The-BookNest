<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.booknest.model.Product" %>
<%@ page import="com.booknest.model.Category" %>

<%
    List<Product> products =
            (List<Product>) request.getAttribute("products");

    List<Category> categories =
            (List<Category>) request.getAttribute("categories");
%>

<!-- Header -->
<jsp:include page="partials/header.jsp"/>

<!-- Navbar -->
<jsp:include page="partials/navbar.jsp"/>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/home.css">

<div class="home-page">

    <!-- =========================
         HERO SECTION
    ========================== -->

    <section class="hero-section">

        <div class="hero-content">

            <div class="hero-badge">
                Discover Your Next Favorite Book
            </div>

            <h1 class="hero-title">
                Welcome to <span>BookNest</span>
            </h1>

            <p class="hero-subtitle">

                Explore thousands of books across
                fiction, programming, AI, self-help,
                comics and much more.

            </p>

            <div class="hero-actions">

                <a href="${pageContext.request.contextPath}/products"
                   class="hero-btn primary">

                    Explore Books

                </a>

                <a href="#featured-books"
                   class="hero-btn secondary">

                    Trending Now

                </a>

            </div>

        </div>

        <div class="hero-image">

            <img src="${pageContext.request.contextPath}/assets/images/hero/booknest-hero.png"
                 alt="BookNest Hero"
                 class="hero-image">

        </div>

    </section>


    <!-- =========================
         CATEGORIES
    ========================== -->

    <section class="section">

        <div class="section-header">

            <div>

                <h2 class="section-title">
                    Browse by <span>Category</span>
                </h2>

                <p class="section-subtitle">
                    Explore books from different genres
                </p>

            </div>

        </div>

        <div class="category-grid">

            <%
                if(categories != null){

                    for(Category category : categories){
            %>

            <a href="${pageContext.request.contextPath}/products?categoryId=<%= category.getCategoryId() %>"
   class="category-card">

                <div class="category-icon">
                    📚
                </div>

                <h3 class="category-name">
                    <%= category.getCategoryName() %>
                </h3>

                <p class="category-count">
                    Explore Collection
                </p>

            </a>

            <%
                    }
                }
            %>

        </div>

    </section>


    <!-- =========================
         FEATURED BOOKS
    ========================== -->

    <section class="section" id="featured-books">

        <div class="section-header">

            <div>

                <h2 class="section-title">
                    Featured <span>Books</span>
                </h2>

                <p class="section-subtitle">
                    Handpicked books for readers
                </p>

            </div>

        </div>

        <div class="books-grid">

            <%
                if(products != null){

                    for(Product product : products){
            %>

            <div class="book-card">

                <img src="${pageContext.request.contextPath}/<%= product.getImageUrl() %>"
                     alt="Book Image"
                     class="book-image">

                <div class="book-content">

                    <h3 class="book-title">
                        <%= product.getTitle() %>
                    </h3>

                    <p class="book-author">
                        By <%= product.getAuthor() %>
                    </p>

                    <div class="book-price">

                        ₹ <%= product.getPrice() %>

                    </div>

                    <div class="book-meta">

                        <span class="rating">
                            ⭐ <%= product.getRating() %>
                        </span>

                        <span>
                            Stock: <%= product.getStock() %>
                        </span>

                    </div>

                    <div class="card-actions">

                        <a href="${pageContext.request.contextPath}/product?id=<%= product.getProductId() %>"
                           class="card-btn secondary">

                            Details

                        </a>

                        <a href="${pageContext.request.contextPath}/cart?action=add&id=<%= product.getProductId() %>"
                           class="card-btn primary">

                            Add to Cart

                        </a>

                    </div>

                </div>

            </div>

            <%
                    }
                }
            %>

        </div>

    </section>


    <!-- =========================
         FEATURE BANNER
    ========================== -->

    <section class="section">

        <div class="feature-banner">

            <div>

                <h3>
                    Start Building Your Reading Habit Today
                </h3>

                <p>

                    BookNest provides a seamless online
                    reading and shopping experience with
                    modern UI, curated books, and secure ordering.

                </p>

            </div>

            <a href="${pageContext.request.contextPath}/products"
               class="hero-btn primary">

                Explore Now

            </a>

        </div>

    </section>

</div>

<!-- Footer -->
<jsp:include page="partials/footer.jsp"/>

</body>
</html>