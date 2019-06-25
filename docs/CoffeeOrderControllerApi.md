# CoffeeOrderControllerApi

All URIs are relative to *https://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOrderUsingPOST**](CoffeeOrderControllerApi.md#createOrderUsingPOST) | **POST** /order/ | createOrder
[**getOrderUsingGET**](CoffeeOrderControllerApi.md#getOrderUsingGET) | **GET** /order/{id} | getOrder


<a name="createOrderUsingPOST"></a>
# **createOrderUsingPOST**
> CoffeeOrder createOrderUsingPOST(orderRequest)

createOrder

### Example
```java
// Import classes:
//import com.example.waiterservice.swagger_client.invoker.ApiException;
//import com.example.waiterservice.swagger_client.api.CoffeeOrderControllerApi;


CoffeeOrderControllerApi apiInstance = new CoffeeOrderControllerApi();
OrderRequest orderRequest = new OrderRequest(); // OrderRequest | orderRequest
try {
    CoffeeOrder result = apiInstance.createOrderUsingPOST(orderRequest);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoffeeOrderControllerApi#createOrderUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orderRequest** | [**OrderRequest**](OrderRequest.md)| orderRequest |

### Return type

[**CoffeeOrder**](CoffeeOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="getOrderUsingGET"></a>
# **getOrderUsingGET**
> CoffeeOrder getOrderUsingGET(id)

getOrder

### Example
```java
// Import classes:
//import com.example.waiterservice.swagger_client.invoker.ApiException;
//import com.example.waiterservice.swagger_client.api.CoffeeOrderControllerApi;


CoffeeOrderControllerApi apiInstance = new CoffeeOrderControllerApi();
Long id = 789L; // Long | id
try {
    CoffeeOrder result = apiInstance.getOrderUsingGET(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CoffeeOrderControllerApi#getOrderUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| id |

### Return type

[**CoffeeOrder**](CoffeeOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

