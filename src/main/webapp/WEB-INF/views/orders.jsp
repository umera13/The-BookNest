<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>

<%@ page import="com.booknest.model.Order" %>

<%
    List<Order> orders =
            (List<Order>) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html>

<head>

    <title>My Orders | BookNest</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/orders.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<section class="orders-page">

    <div class="orders-container">

        <h1>My Orders</h1>

        <%
            if(orders == null || orders.isEmpty()){
        %>

        <div class="empty-orders">

            <h2>No Orders Found</h2>

            <p>
                You have not placed any orders yet.
            </p>

            <a href="${pageContext.request.contextPath}/products"
               class="shop-btn">

                Start Shopping

            </a>

        </div>

        <%
            }
            else{

                for(Order order : orders){
        %>

        <div class="order-card">

            <div class="order-top">

                <div>

                    <h3>
                        Order #<%= order.getOrderId() %>
                    </h3>

                    <p>
                        <strong>Date:</strong>
                        <%= order.getOrderDate() %>
                    </p>

                </div>

                <div class="status">

                    <%= order.getOrderStatus() %>

                </div>

            </div>

            <div class="order-details">

    <p>

        <strong>Total Amount:</strong>

        ₹ <%= order.getTotalAmount() %>

    </p>

    <p>

        <strong>Payment:</strong>

        <%= order.getPaymentMethod() %>

    </p>

    <p>

        <strong>Delivery To:</strong>

        <%= order.getDeliveryName() %>

    </p>

    <p>

        <strong>Phone:</strong>

        <%= order.getDeliveryPhone() %>

    </p>

    <p>

        <strong>Address:</strong>

        <%= order.getDeliveryAddressLine1() %>,
        <%= order.getDeliveryAddressLine2() %>,
        <%= order.getDeliveryCity() %>,
        <%= order.getDeliveryState() %>,
        <%= order.getDeliveryPostalCode() %>,
        <%= order.getDeliveryCountry() %>

    </p>

</div>

<%
    if(order.getOrderStatus().equals("Placed")){
%>

<form action="${pageContext.request.contextPath}/cancel-order"
      method="post">

    <input type="hidden"
           name="orderId"
           value="<%= order.getOrderId() %>">

    <button type="submit"
            class="cancel-btn">

        Cancel Order

    </button>

</form>

<%
    }
%>

        </div>

        <%
                }
            }
        %>

    </div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>