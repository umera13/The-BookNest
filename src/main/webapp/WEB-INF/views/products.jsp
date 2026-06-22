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

<jsp:include page="/WEB-INF/views/partials/header.jsp" />
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/home.css">

<div class="home-page">

    <!-- SECTION HEADER -->

    <section class="section">

        <div class="section-header">

            <div>

                <h2 class="section-title">
                    Explore <span>Books</span>
                </h2>

                <p class="section-subtitle">
                    Discover books across categories
                </p>

            </div>

        </div>


        <!-- CATEGORY FILTER -->

        <div class="category-grid" style="margin-bottom: 40px;">

            <a href="${pageContext.request.contextPath}/products"
               class="category-card">

                <div class="category-icon">
                    📚
                </div>

                <h3 class="category-name">
                    All
                </h3>

            </a>

            <%
                if(categories != null){

                    for(Category category : categories){
            %>

            <a href="${pageContext.request.contextPath}/products?categoryId=<%= category.getCategoryId() %>"
               class="category-card">

                <div class="category-icon">
                    📖
                </div>

                <h3 class="category-name">
                    <%= category.getCategoryName() %>
                </h3>

            </a>

            <%
                    }
                }
            %>

        </div>


        <!-- PRODUCT GRID -->

        <div class="books-grid">

            <%
                if(products != null && !products.isEmpty()){

                    for(Product product : products){
            %>

            <div class="book-card">

                <img src="${pageContext.request.contextPath}/<%= product.getImageUrl() %>"
                     class="book-image"
                     alt="Book">

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

                            Add To Cart

                        </a>

                    </div>

                </div>

            </div>

            <%
                    }
                }
                else{
            %>

            <div class="empty-state">

                <h3>No Products Found</h3>

                <p>
                    Try searching with another keyword
                </p>

            </div>

            <%
                }
            %>

        </div>

    </section>

</div>


<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>