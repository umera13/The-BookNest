<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.booknest.model.Product" %>
<%@ page import="com.booknest.model.ProductDetails" %>

<%
    Product product =
            (Product) request.getAttribute("product");

    ProductDetails details =
            (ProductDetails) request.getAttribute("productDetails");
%>

<jsp:include page="/WEB-INF/views/partials/header.jsp" />
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/product-details.css">

<div class="product-details-page">

    <div class="product-details-container">

        <!-- PRODUCT IMAGE -->

        <div class="product-image-section">

            <img src="${pageContext.request.contextPath}/<%= product.getImageUrl() %>"
                 alt="Book Image"
                 class="details-image">

        </div>


        <!-- PRODUCT INFO -->

        <div class="product-info-section">

            <div class="product-category-badge">
                Premium Collection
            </div>

            <h1 class="details-title">

                <%= product.getTitle() %>

            </h1>

            <p class="details-author">

                By <%= product.getAuthor() %>

            </p>

            <div class="details-rating">

                ⭐ <%= product.getRating() %> Rating

            </div>

            <div class="details-price">

                ₹ <%= product.getPrice() %>

            </div>

            <p class="details-description">

                <%= product.getDescription() %>

            </p>


            <!-- PRODUCT META -->

            <div class="details-meta">

                <div class="meta-box">

                    <h4>Stock</h4>

                    <p><%= product.getStock() %></p>

                </div>

                <div class="meta-box">

                    <h4>Publisher</h4>

                    <p>
                        <%= details != null ? details.getPublisher() : "N/A" %>
                    </p>

                </div>

                <div class="meta-box">

                    <h4>Language</h4>

                    <p>
                        <%= details != null ? details.getLanguage() : "N/A" %>
                    </p>

                </div>

                <div class="meta-box">

                    <h4>Pages</h4>

                    <p>
                        <%= details != null ? details.getPages() : "N/A" %>
                    </p>

                </div>

                <div class="meta-box">

                    <h4>ISBN</h4>

                    <p>
                        <%= details != null ? details.getIsbn() : "N/A" %>
                    </p>

                </div>

                <div class="meta-box">

                    <h4>Year</h4>

                    <p>
                        <%= details != null ? details.getPublicationYear() : "N/A" %>
                    </p>

                </div>

            </div>


            <!-- ACTION BUTTONS -->

            <div class="details-actions">

                <a href="${pageContext.request.contextPath}/cart?action=add&id=<%= product.getProductId() %>"
                   class="details-btn cart-btn">

                    Add To Cart

                </a>

                <a href="${pageContext.request.contextPath}/products"
                   class="details-btn back-btn">

                    Back To Products

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>