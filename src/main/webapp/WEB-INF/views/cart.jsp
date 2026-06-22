<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="com.booknest.model.CartItem" %>
<%@ page import="com.booknest.model.Product" %>

<%
    List<CartItem> cartItems =
            (List<CartItem>) request.getAttribute("cartItems");

    List<Product> cartProducts =
            (List<Product>) request.getAttribute("cartProducts");

    java.math.BigDecimal totalAmount =
            (java.math.BigDecimal) request.getAttribute("totalAmount");
%>

<jsp:include page="/WEB-INF/views/partials/header.jsp" />
<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/cart.css">

<div class="cart-page">

    <div class="cart-container">

        <!-- PAGE HEADER -->

        <div class="cart-header">

            <h1>
                Your <span>Cart</span>
            </h1>

            <p>
                Review your selected books before checkout.
            </p>

        </div>


        <%
            if(cartItems != null && !cartItems.isEmpty()){
        %>

        <!-- CART ITEMS -->

        <div class="cart-items">

            <%
                for(int i = 0; i < cartItems.size(); i++){

                    CartItem item = cartItems.get(i);

                    Product product = cartProducts.get(i);
            %>

            <div class="cart-card">

                <!-- IMAGE -->

                <div class="cart-image-section">

                    <img src="${pageContext.request.contextPath}/<%= product.getImageUrl() %>"
                         alt="Book">

                </div>


                <!-- DETAILS -->

                <div class="cart-details">

                    <h2>
                        <%= product.getTitle() %>
                    </h2>

                    <p class="cart-author">

                        By <%= product.getAuthor() %>

                    </p>

                    <div class="cart-price">

                        ₹ <%= item.getPriceAtAddition() %>

                    </div>

                </div>


                <!-- QUANTITY -->

                <div class="cart-quantity">

                    <a href="${pageContext.request.contextPath}/cart?action=decrease&id=<%= product.getProductId() %>"
                       class="qty-btn">

                        -

                    </a>

                    <span>

                        <%= item.getQuantity() %>

                    </span>

                    <a href="${pageContext.request.contextPath}/cart?action=increase&id=<%= product.getProductId() %>"
                       class="qty-btn">

                        +

                    </a>

                </div>


                <!-- REMOVE -->

                <div class="cart-remove">

                    <a href="${pageContext.request.contextPath}/cart?action=remove&id=<%= product.getProductId() %>"
                       class="remove-btn">

                        Remove

                    </a>

                </div>

            </div>

            <%
                }
            %>

        </div>


        <!-- SUMMARY -->

        <div class="cart-summary">

            <h2>
                Cart Summary
            </h2>

            <div class="summary-row">

                <span>Total Amount</span>

                <span>

                    ₹ <%= totalAmount %>

                </span>

            </div>

            <a href="${pageContext.request.contextPath}/checkout"
               class="checkout-btn">

                Proceed To Checkout

            </a>

        </div>

        <%
            }
            else{
        %>

        <!-- EMPTY CART -->

        <div class="empty-cart">

            <h2>
                Your Cart is Empty
            </h2>

            <p>
                Add books to continue shopping.
            </p>

            <a href="${pageContext.request.contextPath}/products"
               class="shop-btn">

                Explore Books

            </a>

        </div>

        <%
            }
        %>

    </div>

</div>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>