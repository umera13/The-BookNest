<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Integer orderId =
            (Integer) request.getAttribute("orderId");
%>

<!DOCTYPE html>
<html>

<head>

    <title>Order Success | BookNest</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
        }

        body{
            background:#020817;
            font-family:'Poppins',sans-serif;
        }

        .success-page{

            min-height:90vh;

            display:flex;
            justify-content:center;
            align-items:center;

            padding:60px 20px;

            background:
                radial-gradient(
                    circle at top,
                    rgba(139,92,246,0.15),
                    transparent 40%
                ),
                #020817;
        }

        .success-card{

            width:100%;
            max-width:850px;

            background:
                linear-gradient(
                    145deg,
                    rgba(15,23,42,0.98),
                    rgba(2,8,23,0.98)
                );

            border:1px solid rgba(139,92,246,0.15);

            border-radius:28px;

            padding:55px;

            position:relative;

            overflow:hidden;

            box-shadow:
                0 20px 60px rgba(0,0,0,0.45);

            animation:fadeIn 0.8s ease;
        }

        @keyframes fadeIn{

            from{
                opacity:0;
                transform:translateY(30px);
            }

            to{
                opacity:1;
                transform:translateY(0);
            }
        }

        .glow{

            position:absolute;

            width:300px;
            height:300px;

            background:
                radial-gradient(
                    rgba(139,92,246,0.25),
                    transparent 70%
                );

            top:-120px;
            right:-80px;

            border-radius:50%;
        }

        .success-top{

            display:flex;
            align-items:center;
            gap:25px;

            margin-bottom:40px;
        }

        .success-icon{

            width:95px;
            height:95px;

            border-radius:50%;

            background:
                linear-gradient(
                    135deg,
                    #8b5cf6,
                    #7c3aed
                );

            display:flex;
            align-items:center;
            justify-content:center;

            font-size:45px;

            color:white;

            box-shadow:
                0 10px 30px rgba(139,92,246,0.35);

            animation:pulse 2s infinite;
        }

        @keyframes pulse{

            0%{
                transform:scale(1);
            }

            50%{
                transform:scale(1.05);
            }

            100%{
                transform:scale(1);
            }
        }

        .success-heading h1{

            font-size:42px;

            color:white;

            margin-bottom:10px;
        }

        .success-heading p{

            color:#94a3b8;

            font-size:16px;

            line-height:1.8;
        }

        .order-info{

            background:
                linear-gradient(
                    145deg,
                    rgba(30,41,59,0.8),
                    rgba(15,23,42,0.9)
                );

            border:1px solid rgba(139,92,246,0.15);

            border-radius:22px;

            padding:30px;

            margin-bottom:35px;
        }

        .order-label{

            color:#94a3b8;

            font-size:14px;

            margin-bottom:10px;

            letter-spacing:1px;
        }

        .order-id{

            color:#8b5cf6;

            font-size:34px;

            font-weight:700;

            margin-bottom:15px;
        }

        .delivery-text{

            color:#cbd5e1;

            font-size:15px;
        }

        .status-track{

            display:flex;

            align-items:center;

            justify-content:space-between;

            gap:10px;

            margin-top:35px;

            flex-wrap:wrap;
        }

        .status-step{

            flex:1;

            min-width:130px;

            text-align:center;

            position:relative;
        }

        .status-circle{

            width:65px;
            height:65px;

            border-radius:50%;

            margin:0 auto 15px;

            display:flex;
            align-items:center;
            justify-content:center;

            font-size:26px;

            background:#1e293b;

            border:2px solid #334155;

            color:#94a3b8;
        }

        .status-step.active .status-circle{

            background:
                linear-gradient(
                    135deg,
                    #8b5cf6,
                    #7c3aed
                );

            border:none;

            color:white;

            box-shadow:
                0 8px 25px rgba(139,92,246,0.35);
        }

        .status-step h4{

            color:white;

            font-size:15px;

            margin-bottom:6px;
        }

        .status-step p{

            color:#94a3b8;

            font-size:13px;
        }

        .line{

            flex:1;

            height:4px;

            background:#1e293b;

            border-radius:20px;

            margin-top:-55px;
        }

        .line.active{

            background:
                linear-gradient(
                    90deg,
                    #8b5cf6,
                    #7c3aed
                );
        }

        .action-buttons{

            display:flex;

            gap:20px;

            margin-top:45px;

            flex-wrap:wrap;
        }

        .continue-btn,
        .orders-btn{

            flex:1;

            min-width:220px;

            padding:16px 24px;

            border-radius:16px;

            text-align:center;

            text-decoration:none;

            font-weight:600;

            transition:0.3s;
        }

        .continue-btn{

            background:
                linear-gradient(
                    135deg,
                    #8b5cf6,
                    #7c3aed
                );

            color:white;

            box-shadow:
                0 12px 25px rgba(139,92,246,0.25);
        }

        .continue-btn:hover{

            transform:translateY(-3px);
        }

        .orders-btn{

            border:1px solid rgba(139,92,246,0.25);

            background:#0f172a;

            color:white;
        }

        .orders-btn:hover{

            background:#1e293b;

            transform:translateY(-3px);
        }

        .success-footer{

            margin-top:35px;

            text-align:center;

            color:#64748b;

            font-size:14px;
        }

        @media(max-width:768px){

            .success-card{

                padding:35px 25px;
            }

            .success-top{

                flex-direction:column;

                text-align:center;
            }

            .success-heading h1{

                font-size:32px;
            }

            .status-track{

                flex-direction:column;
            }

            .line{

                width:4px;
                height:40px;

                margin:0 auto;
            }

            .action-buttons{

                flex-direction:column;
            }
        }

    </style>

</head>

<body>

<jsp:include page="/WEB-INF/views/partials/navbar.jsp" />

<section class="success-page">

    <div class="success-card">

        <div class="glow"></div>

        <div class="success-top">

            <div class="success-icon">

                ✓

            </div>

            <div class="success-heading">

                <h1>

                    Order Placed Successfully

                </h1>

                <p>

                    Thank you for shopping with BookNest.
                    Your books are being prepared for shipment.

                </p>

            </div>

        </div>

        <div class="order-info">

            <div class="order-label">

                ORDER ID

            </div>

            <div class="order-id">

                #<%= orderId %>

            </div>

            <div class="delivery-text">

                Estimated delivery within 3 - 5 business days

            </div>

            <div class="status-track">

                <div class="status-step active">

                    <div class="status-circle">

                        ✓

                    </div>

                    <h4>Placed</h4>

                    <p>Order Confirmed</p>

                </div>

                <div class="line active"></div>

                <div class="status-step active">

                    <div class="status-circle">

                        📦

                    </div>

                    <h4>Packed</h4>

                    <p>Preparing Shipment</p>

                </div>

                <div class="line"></div>

                <div class="status-step">

                    <div class="status-circle">

                        🚚

                    </div>

                    <h4>Shipped</h4>

                    <p>Expected Soon</p>

                </div>

                <div class="line"></div>

                <div class="status-step">

                    <div class="status-circle">

                        🏠

                    </div>

                    <h4>Delivered</h4>

                    <p>On The Way</p>

                </div>

            </div>

        </div>

        <div class="action-buttons">

            <a href="${pageContext.request.contextPath}/products"
               class="continue-btn">

                Continue Shopping

            </a>

            <a href="${pageContext.request.contextPath}/orders"
               class="orders-btn">

                View My Orders

            </a>

        </div>

        <div class="success-footer">

            A confirmation email and invoice have been sent successfully.

        </div>

    </div>

</section>

<jsp:include page="/WEB-INF/views/partials/footer.jsp" />

</body>
</html>