for (var i = 0; i < 10; i++) {
    var scenario = {};
    scenario.name = "测试支付详情页情况00" + i;
    scenario.desc = "测试支付详情页压力情况00" + i;
    db.scenarios.insert(scenario);
}