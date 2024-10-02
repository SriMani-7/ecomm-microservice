<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Orders</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .order-header {
            background-color: #007bff;
            color: white;
        }

        .accordion-button:not(.collapsed) {
            color: #fff;
            background-color: #007bff;
        }

        .accordion-button {
            color: #007bff;
        }

        .btn-cancel {
            background-color: #dc3545;
            color: white;
        }

        .btn-invoice {
            background-color: #28a745;
            color: white;
        }

        .order-status {
            font-size: 0.9rem;
        }

        .order-summary-title {
            font-weight: 600;
        }

        .table thead th {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <!-- Include Header -->
    <jsp:include page="../header.jsp" />

    <div class="container mt-4">
        <h2 class="mb-4">Your Orders</h2>
        <div class="accordion accordion-flush" id="orderAccordion">
            <c:forEach var="order" items="${orders}">
                <div class="accordion-item">
                    <h2 class="accordion-header" id="heading${order.orderId}">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${order.orderId}" aria-expanded="true" aria-controls="collapse${order.orderId}">
                            Order #${order.orderId} - Placed on: ${order.orderDate} <span class="badge bg-secondary float-end">${order.orderStatus}</span>
                        </button>
                    </h2>
                    <div id="collapse${order.orderId}" class="accordion-collapse collapse" aria-labelledby="heading${order.orderId}" data-bs-parent="#orderAccordion">
                        <div class="accordion-body">
                            <div class="row">
                                <!-- Left side: Order Details -->
                                <div class="col-lg-8">
                                    <h6 class="order-summary-title">Order Summary</h6>
                                    <table class="table table-bordered mb-3">
                                        <thead>
                                            <tr>
                                                <th>Product Name</th>
                                                <th>Description</th>
                                                <th>Quantity</th>
                                                <th>Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="item" items="${order.orderItems}">
                                                <tr>
                                                    <td>${item.productName}</td>
                                                    <td>${item.discription}</td>
                                                    <td>${item.quantity}</td>
                                                    <td>$${item.price * item.quantity}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- Right side: Delivery Address, Payment Method, Total & Actions -->
                                <div class="col-lg-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <h6>Delivery Address</h6>
                                            <p>${order.address}</p>
                                            <h6>Payment Method</h6>
                                            <p>${order.paymentType}</p>
                                            <h6>Total Amount</h6>
                                            <h4 class="text-primary mb-4">$${order.totalAmount}</h4>
                                            <form action="cancelOrder" method="POST" style="display: inline;">
                                                <input type="hidden" name="orderId" value="${order.orderId}">
                                                <c:if test="${order.orderStatus == 'PLACED'}">
                                                    <button class="btn btn-cancel w-100 mb-2">Cancel Order</button>
                                                </c:if>
                                            </form>
                                            <button class="btn btn-invoice w-100" onclick="window.open('printInvoice?orderId=${order.orderId}', '_blank')">Print Invoice</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-2 text-muted">
                                <p>Estimated Delivery: ${order.deliveryDate}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
