for (var i = 0; i < 10; i++) {
    var testEndPoint = {};
    testEndPoint.name = "测试支付详情页情况11" + i;
    testEndPoint.desc = "测试支付详情页压力情况11" + i;
    testEndPoint.url = "https://www.youzan.com/"
    db.test_endpoints.insert(testEndPoint);
}