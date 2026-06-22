<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="com.booknest.model.CartItem" %>
<%@ page import="com.booknest.model.User" %>

<%
    List<CartItem> cartItems =
            (List<CartItem>) request.getAttribute("cartItems");

    BigDecimal totalAmount =
            (BigDecimal) request.getAttribute("totalAmount");

    User user =
            (User) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>

<head>

    <title>Checkout | BookNest</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/checkout.css">

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<section class="checkout-page">

    <div class="checkout-container">

        <!-- LEFT SIDE -->

        <div class="checkout-form-section">

            <h1>Checkout</h1>

            <form action="${pageContext.request.contextPath}/order"
                  method="post"
                  class="checkout-form">

                <!-- FULL NAME -->

                <div class="form-group">

                    <label>Full Name</label>

                    <input type="text"
                           name="deliveryName"
                           value="<%= user.getFullName() %>"
                           required>

                </div>

                <!-- PHONE -->

                <div class="form-group">

                    <label>Phone Number</label>

                    <input type="text"
                           name="deliveryPhone"
                           value="<%= user.getPhone() %>"
                           required>

                </div>

                <!-- ADDRESS 1 -->

                <div class="form-group">

                    <label>Address Line 1</label>

                    <input type="text"
                           name="addressLine1"
                           value="<%= user.getAddressLine1() %>"
                           required>

                </div>

                <!-- ADDRESS 2 -->

                <div class="form-group">

                    <label>Address Line 2</label>

                    <input type="text"
                           name="addressLine2"
                           value="<%= user.getAddressLine2() %>">

                </div>

                <!-- CITY + STATE -->

                <div class="form-row">

                    <div class="form-group">

                        <label>City</label>

                        <input type="text"
                               name="city"
                               value="<%= user.getCity() %>"
                               required>

                    </div>

                    <div class="form-group">

                        <label>State</label>

                        <input type="text"
                               name="state"
                               value="<%= user.getState() %>"
                               required>

                    </div>

                </div>

                <!-- POSTAL + COUNTRY -->

                <div class="form-row">

                    <div class="form-group">

                        <label>Postal Code</label>

                        <input type="text"
                               name="postalCode"
                               value="<%= user.getPostalCode() %>"
                               required>

                    </div>

                    <div class="form-group">

                        <label>Country</label>

                        <input type="text"
                               name="country"
                               value="<%= user.getCountry() %>"
                               required>

                    </div>

                </div>

                <!-- PAYMENT METHOD -->

                <div class="form-group">

                    <label>Payment Method</label>

                    <select name="paymentMethod"
                            id="paymentMethod"
                            onchange="togglePaymentFields()"
                            required>

                        <option value="" disabled selected>
                            Select Payment Method
                        </option>

                        <option value="Cash On Delivery">
                            Cash On Delivery
                        </option>

                        <option value="UPI">
                            UPI
                        </option>

                        <option value="Credit Card">
                            Credit Card
                        </option>

                    </select>

                </div>

                <!-- UPI SECTION -->

                <div id="upiFields" style="display:none;">

                    <div class="form-group">

                        <label>UPI ID</label>

                        <input type="text"
                               name="upiId"
                               placeholder="example@upi">

                    </div>

                    <div class="form-group">

                        <label>UPI PIN</label>

                        <input type="password"
                               name="upiPin"
                               placeholder="Enter UPI PIN">

                    </div>

                </div>

                <!-- CARD SECTION -->

                <div id="cardFields" style="display:none;">

                    <div class="form-group">

                        <label>Card Holder Name</label>

                        <input type="text"
                               name="cardHolderName">

                    </div>

                    <div class="form-group">

                        <label>Card Number</label>

                        <input type="text"
                               name="cardNumber"
                               maxlength="16">

                    </div>

                    <div class="form-row">

                        <div class="form-group">

                            <label>Expiry Date</label>

                            <input type="text"
                                   name="expiryDate"
                                   placeholder="MM/YY">

                        </div>

                        <div class="form-group">

                            <label>CVV</label>

                            <input type="password"
                                   name="cvv"
                                   maxlength="3">

                        </div>

                    </div>

                </div>

                <!-- PLACE ORDER BUTTON -->

                <button type="submit"
                        class="place-order-btn">

                    Place Order

                </button>

            </form>

        </div>

        <!-- RIGHT SIDE -->

        <div class="order-summary-section">

            <h2>Order Summary</h2>

            <div class="summary-items">

                <%
                    if(cartItems != null){

                        for(CartItem item : cartItems){
                %>

                <div class="summary-item">

                    <span>
                        Product ID:
                        <%= item.getProductId() %>
                    </span>

                    <span>
                        Qty:
                        <%= item.getQuantity() %>
                    </span>

                </div>

                <%
                        }
                    }
                %>

            </div>

            <div class="summary-total">

                <span>Total</span>

                <span>
                    ₹ <%= totalAmount %>
                </span>

            </div>

        </div>

    </div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

<script>

function togglePaymentFields(){

    let paymentMethod =
            document.getElementById("paymentMethod").value;

    let upiFields =
            document.getElementById("upiFields");

    let cardFields =
            document.getElementById("cardFields");

    upiFields.style.display = "none";

    cardFields.style.display = "none";

    if(paymentMethod === "UPI"){

        upiFields.style.display = "block";
    }

    else if(paymentMethod === "Credit Card"){

        cardFields.style.display = "block";
    }
}

</script>

</body>
</html>